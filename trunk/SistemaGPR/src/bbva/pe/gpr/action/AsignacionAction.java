package bbva.pe.gpr.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.directwebremoting.WebContextFactory;

import bbva.pe.gpr.bean.BancaSub;
import bbva.pe.gpr.bean.Rol;
import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.bean.Usuario;
import bbva.pe.gpr.bean.UsuarioSubanca;
import bbva.pe.gpr.context.Context;
import bbva.pe.gpr.form.AsignacionForm;
import bbva.pe.gpr.service.AsignacionService;
import bbva.pe.gpr.service.CatalogoService;
import bbva.pe.gpr.service.SeguridadService;
import bbva.pe.gpr.service.SolicitudService;
import bbva.pe.gpr.util.Constant;
import bbva.pe.gpr.util.UtilDate;

import com.grupobbva.bc.per.tele.ldap.serializable.IILDPeUsuario;

public class AsignacionAction extends DispatchAction {
	private static Logger logger = Logger.getLogger(AsignacionAction.class);
	SolicitudService solicitudService;
	CatalogoService catalogoService;
	SeguridadService seguridadService;
	AsignacionService asignacionService;
	
	
	public AsignacionAction() {
		solicitudService=(SolicitudService)Context.getInstance().getBean("solicitudService");
		catalogoService=(CatalogoService)Context.getInstance().getBean("catalogoService");
	    seguridadService=(SeguridadService)Context.getInstance().getBean("seguridadService");
	    asignacionService = (AsignacionService)Context.getInstance().getBean("asignacionService");
	  
	}
	
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Rol rolBean = new Rol();
		rolBean.setEstado(Constant.ESTADO_ACTIVO);		
		request.getSession().setAttribute("lstCargos", catalogoService.getLstMultitablaDetalle("T020"));
		return mapping.findForward("success");		
	}
	
	public List<Usuario> consultarUsuarioAjax(String codCargo){	
		try {
			HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
			IILDPeUsuario usuarioSesion = (IILDPeUsuario)request.getSession().getAttribute("USUARIO_SESION");
			BancaSub subBancaBean = new BancaSub();
			Usuario usuario = new Usuario();
			
			subBancaBean.setCodUsuario(usuarioSesion.getUID());
			UsuarioSubanca record = catalogoService.getSubancaPorUsuario(subBancaBean);
			if(subBancaBean!=null){
				usuario.setCodBanca(record.getCodBanca());
			}
			usuario.setCodigoUsuarioSession(usuarioSesion.getUID());
			if(!codCargo.equals(Constant.RESET_COMBO)){
				usuario.setCodCargoGPR(Constant.TABLA_CARGO_GPR+Constant.CHAR_GUION+codCargo);
			}
			return catalogoService.getLstUsuariosRiesgo(usuario);
			
		} catch (Exception e) {
			logger.error("Exception AsignacionAction.consultarUsuarioAjax: " + e.getMessage());
		}
		return  new ArrayList<Usuario>();
	}
	
	public List<Solicitud> consultarSolicitudAjax(String codCentral, String nroSolicitud, 
			   String fechaIngresoIni, String fechaIngresoFin, String fechaVencimiento, String usuario) throws Exception {
		HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
		Solicitud solicitudBean = new Solicitud();
		if(codCentral!=null && !codCentral.equalsIgnoreCase(Constant.STR_VACIO)){
		solicitudBean.setCodCentral(Constant.CHAR_PORCENTAJE+codCentral+Constant.CHAR_PORCENTAJE);
		}if(nroSolicitud!=null && !nroSolicitud.equalsIgnoreCase(Constant.STR_VACIO)){
		solicitudBean.setNroSolicitud(new Long(nroSolicitud));
		}if(fechaIngresoIni!=null && !fechaIngresoIni.equalsIgnoreCase(Constant.STR_VACIO)){
		solicitudBean.setFechaIngresoIni(UtilDate.stringToUtilDate(fechaIngresoIni, null));
		}if(fechaIngresoFin!=null && !fechaIngresoFin.equalsIgnoreCase(Constant.STR_VACIO)){
		solicitudBean.setFechaIngresoFin(UtilDate.stringToUtilDate(fechaIngresoFin, null));
		}
		solicitudBean.setEstadoSolicitud(Constant.TABLA_ESTADOS_SOLCITUD+Constant.CHAR_GUION+Constant.ESTADO_SOLICITUD_PENDIENTE);
		IILDPeUsuario usuarioSesion = (IILDPeUsuario)request.getSession().getAttribute("USUARIO_SESION");
		BancaSub subBancaBean = new BancaSub();
		subBancaBean.setCodUsuario(usuarioSesion.getUID());
		UsuarioSubanca bean= catalogoService.getSubancaPorUsuario(subBancaBean);
		if(bean!=null){
			solicitudBean.setCodBanca(bean.getCodBanca());
		}
		
		try {
		List<Solicitud> lstSolicitud=solicitudService.getLstSolicitudes(solicitudBean);
		if(usuario.equals(Constant.STR_VACIO)){
			return lstSolicitud;
		}else{
			Usuario usuarioBean = new Usuario();
			usuarioBean.setCodigoUsuario(usuario);
			usuarioBean = catalogoService.getUsuarioMontos(usuarioBean);
			for (Solicitud solicitud : lstSolicitud) {
				if(solicitud.getGrupoPersona().equalsIgnoreCase(Constant.GRUPO_PER_NATUAL)){
					if(solicitud.getRiesgoTotal().compareTo(usuarioBean.getMtoMaxPerNatual())==-1){
						lstSolicitud.remove(solicitud);
					}
				}if(solicitud.getGrupoPersona().equalsIgnoreCase(Constant.GRUPO_CON_RATING)){
					if(solicitud.getRiesgoTotal().compareTo(usuarioBean.getMtoMaxRating())==-1){
						lstSolicitud.remove(solicitud);
					}
				}if(solicitud.getGrupoPersona().equalsIgnoreCase(Constant.GRUPO_SIN_RATING)){
					if(solicitud.getRiesgoTotal().compareTo(usuarioBean.getMtoSinRating())==-1){
						lstSolicitud.remove(solicitud);
					}
				}
			}
			return lstSolicitud;
		}
		
		} catch (Exception e) {
			logger.error("BusquedaSolicitudAction.consultarSolicitudAjax " +e);
		}
	return new  ArrayList<Solicitud>();
	}
	
	public ActionForward grabarAsignaciones(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AsignacionForm asignacionForm = (AsignacionForm)form;
		IILDPeUsuario usuarioSesion = (IILDPeUsuario)request.getSession().getAttribute("USUARIO_SESION");
		String codUsuarioAsigno = usuarioSesion.getUID();
		String indMensaje=null;
		String strMensaje=null;
		String nombre = usuarioSesion.getNombre()+ Constant.ESPACIO + usuarioSesion.getApellido1() + Constant.ESPACIO + usuarioSesion.getApellido2();
		if(asignacionService.asignarSolicitudMasiva(asignacionForm.getHdnArreglo(), asignacionForm.getHdnRegistro(), codUsuarioAsigno, nombre)>0){
			indMensaje = Constant.MSJ_OK;
			strMensaje = "Se registro exitosamente las asignaciones";
		}else{
			indMensaje = Constant.MSJ_ERROR;
			strMensaje = "Sucedio un error en el registro.";
		}
		request.setAttribute("indMensaje", indMensaje);
		request.setAttribute("strMensaje", strMensaje);
		return mapping.findForward("success");
				
	}
	
	public List<Solicitud> consultarSolicitudPorUsuarioAjax(String codUsuarioAsignacion) throws Exception {
		HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
		Solicitud solicitudBean = new Solicitud();
		solicitudBean.setEstadoSolicitud(Constant.TABLA_ESTADOS_SOLCITUD+Constant.CHAR_GUION+Constant.ESTADO_SOLICITUD_PENDIENTE);
		
		IILDPeUsuario usuarioSesion = (IILDPeUsuario)request.getSession().getAttribute("USUARIO_SESION");
		
		BancaSub subBancaBean = new BancaSub();
		subBancaBean.setCodUsuario(usuarioSesion.getUID());
		UsuarioSubanca bean= catalogoService.getSubancaPorUsuario(subBancaBean);
		
		if(bean!=null){
			solicitudBean.setCodBanca(bean.getCodBanca());
		}
		solicitudBean.setUsuarioAsignacion(codUsuarioAsignacion);
		try {
			List<Solicitud> lstSolicitud=solicitudService.getLstSolicitudes(solicitudBean);
			return lstSolicitud;
		} catch (Exception e) {
			logger.error("BusquedaSolicitudAction.consultarSolicitudAjax " +e);
		}
		return new  ArrayList<Solicitud>();
	}
	

}
