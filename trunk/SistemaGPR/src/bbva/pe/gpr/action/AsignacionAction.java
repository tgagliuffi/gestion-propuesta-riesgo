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

import com.grupobbva.bc.per.tele.ldap.serializable.IILDPeUsuario;

import bbva.pe.gpr.bean.Rol;
import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.bean.Usuario;
import bbva.pe.gpr.context.Context;
import bbva.pe.gpr.form.AsignacionForm;
import bbva.pe.gpr.service.AsignacionService;
import bbva.pe.gpr.service.CatalogoService;
import bbva.pe.gpr.service.SeguridadService;
import bbva.pe.gpr.service.SolicitudService;
import bbva.pe.gpr.util.Constant;
import bbva.pe.gpr.util.UtilDate;

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
		request.getSession().setAttribute("lstRol", seguridadService.getLstRolesByCriteria(rolBean));
		return mapping.findForward("success");		
	}
	
	public List<Usuario> consultarUsuarioAjax(String codRol){	
		try {
			return catalogoService.getLstUsuariosRiesgo(codRol);
		} catch (Exception e) {
			logger.error("Exception AsignacionAction.consultarUsuarioEvaludores: " + e.getMessage());
		}
		return  new ArrayList<Usuario>();
	}
	
	public List<Solicitud> consultarSolicitudAjax(String codCentral, String nroSolicitud, 
			   String fechaIngresoIni, String fechaIngresoFin, String fechaVencimiento) throws Exception {

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

		try {
		List<Solicitud> lstSolicitud=solicitudService.getLstSolicitudes(solicitudBean);
		return lstSolicitud;
		} catch (Exception e) {
			logger.error("BusquedaSolicitudAction.consultarSolicitudAjax " +e);
		}
	return new  ArrayList<Solicitud>();
	}
	
	public ActionForward grabarAsignaciones(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AsignacionForm asignacionForm = (AsignacionForm)form;
		IILDPeUsuario bean = (IILDPeUsuario)request.getSession().getAttribute("USUARIO_SESION");
		String codUsuarioAsigno = bean.getUID();
		String indMensaje=null;
		String strMensaje=null;
		if(asignacionService.asignarSolicitudMasiva(asignacionForm.getHdnArreglo(), asignacionForm.getHdnRegistro(), codUsuarioAsigno)>0){
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

}
