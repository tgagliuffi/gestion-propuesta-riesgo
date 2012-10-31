package bbva.pe.gpr.action;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import bbva.pe.gpr.bean.Banca;
import bbva.pe.gpr.bean.MultitablaDetalle;
import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.context.Context;
import bbva.pe.gpr.form.SolicitudForm;
import bbva.pe.gpr.service.AplicativoPersonasService;
import bbva.pe.gpr.service.AplicativoRCCService;
import bbva.pe.gpr.service.AplicativoRCDService;
import bbva.pe.gpr.service.CatalogoService;
import bbva.pe.gpr.service.ControlService;
import bbva.pe.gpr.service.SolicitudService;
import bbva.pe.gpr.serviceImpl.AplicativoPersonasServiceImpl;
import bbva.pe.gpr.serviceImpl.AplicativoRCCServiceImpl;
import bbva.pe.gpr.serviceImpl.AplicativoRCDServiceImpl;
import bbva.pe.gpr.util.Constant;
import bbva.pe.gpr.util.UtilDate;

import com.grupobbva.bc.per.tele.ldap.serializable.IILDPeUsuario;



public class IngresoSolicitudAction extends DispatchAction {

	private static Logger logger = Logger.getLogger(IngresoSolicitudAction.class);
	
	SolicitudService solicitudService;
	CatalogoService catalogoService;
	AplicativoPersonasService appPersonasService;
	ControlService controlService;
	AplicativoRCDService appRCDService;
	AplicativoRCCService appRCCService;
	
	public IngresoSolicitudAction() {
		solicitudService = (SolicitudService)Context.getInstance().getBean("solicitudService");
		catalogoService = (CatalogoService)Context.getInstance().getBean("catalogoService");
		controlService = (ControlService)Context.getInstance().getBean("controlService");
		appPersonasService = new AplicativoPersonasServiceImpl();
		appRCDService = new AplicativoRCDServiceImpl();
		appRCCService = new AplicativoRCCServiceImpl();
	}
	
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = "success";		
		return mapping.findForward(target);
	}
	
	public ActionForward insertSolicitud(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String target = "success";
		SolicitudForm solicitudForm = (SolicitudForm)form;
		Solicitud solicitudBean = new Solicitud();
		String indMensaje = null;
		String strMensaje = null;
		
		solicitudBean.setCodCentral(solicitudForm.getCodCentral());
		solicitudBean.setNumeroDocumento(solicitudForm.getNumeroDocumento());
		solicitudBean.setDesSolicitante(solicitudForm.getDesSolicitante());								
		solicitudBean.setCodOficina(solicitudForm.getCodOficina());
		solicitudBean.setDesOficina(solicitudForm.getDesOficina());								
		solicitudBean.setGestorCod(solicitudForm.getGestorCod());
		solicitudBean.setGestorNom(solicitudForm.getGestorNom());								
		solicitudBean.setEmpleadorCod(solicitudForm.getEmpleadorCod());
		solicitudBean.setEmpleadorNom(solicitudForm.getEmpleadorNom());				
		solicitudBean.setGerenciaTerritorialCod(solicitudForm.getGerenciaTerritorialCod());
		solicitudBean.setGerenciaTerritorialNom(solicitudForm.getGerenciaTerritorialNom());
		solicitudBean.setDeudaDirecta(solicitudForm.getDeudaDirecta());
		solicitudBean.setDeudaIndirecta(solicitudForm.getDeudaIndirecta());
		solicitudBean.setCastigo(solicitudForm.getCastigo());
		solicitudBean.setDeudaSistemaFinanciero(solicitudForm.getDeudaSistemaFinanciero());
		solicitudBean.setScorating(solicitudForm.getScorating());
		solicitudBean.setRating(solicitudForm.getRating());
		solicitudBean.setReelevancia(solicitudForm.getRelevPublica1()!=null?solicitudForm.getRelevPublica1():Constant.VACIO + 
									 solicitudForm.getRelevPublica1()!=null?solicitudForm.getRelevPublica1():Constant.VACIO +
									 solicitudForm.getRelevPublica1()!=null?solicitudForm.getRelevPublica1():Constant.VACIO + 
									 solicitudForm.getRelevPublica1()!=null?solicitudForm.getRelevPublica1():Constant.VACIO +
									 solicitudForm.getRelevPublica1()!=null?solicitudForm.getRelevPublica1():Constant.VACIO);
		solicitudBean.setClasificacion(solicitudForm.getClasificacion());	
		solicitudBean.setOficinaAltaCod(solicitudForm.getOficinaAltaCod());
		solicitudBean.setOficinaAltaNom(solicitudForm.getOficinaAltaNom());
		solicitudBean.setEjecutivoCtaCod(solicitudForm.getEjecutivoCtaCod());				
		solicitudBean.setEjecutivoCtaNom(solicitudForm.getEjecutivoCtaNom());
		solicitudBean.setCodMultTipoPersona(solicitudForm.getCodMultTipoPersona());
		
		solicitudBean.setRiesgoActual(solicitudForm.getRiesgoActual());
		solicitudBean.setRiesgoGrupal(solicitudForm.getRiesgoGrupal());
		solicitudBean.setFechaIngreso(new Date());	
		solicitudBean.setCodBanca(solicitudForm.getCodBanca());
		solicitudBean.setCodMultMoneda(solicitudForm.getCodMultMoneda());
		
		
		Long nroSolicitud = solicitudService.registraSolicitud(solicitudBean, null);
		try {
			 if(nroSolicitud != null){
					if(controlService.validacionMontosPlazos(solicitudBean)==1){
						if(solicitudService.updateDictaminaEnOficina(solicitudBean)>1){
							indMensaje = Constant.MSJ_ALERT;
							strMensaje = "Se ha ingresado la solicitud " + nroSolicitud + ". Requiere asignación en oficina";	
						}
					}else{
						if(solicitudService.asignacionAutomatica(solicitudBean) != 0){
							indMensaje = Constant.MSJ_ALERT;
							strMensaje = "Se ha ingresado la solicitud " + nroSolicitud + ". La cúal fue enviada a riegos.";	
						}else{
							indMensaje = Constant.MSJ_ALERT;
							strMensaje = "Se ha ingresado la solicitud " + nroSolicitud + ". No se completo la asignación.";	
						}
					}
				}else{
					indMensaje = Constant.MSJ_ERROR;
					strMensaje = "Sucedio un error en el ingreso de Solicitud.";				
				}
				 
		} catch (Exception e) {
			logger.error("Exception IngresoSolicitudAction.insertSolicitud: " + e.getMessage());
			indMensaje = Constant.MSJ_ERROR;
			strMensaje = "Sucedio un error. Comuniquese con el Administrador";
			request.setAttribute("sindMensaje", indMensaje);
			request.setAttribute("strMensaje", strMensaje);
			return mapping.findForward(target);
			
		}
		request.setAttribute("indMensaje", indMensaje);
		request.setAttribute("strMensaje", strMensaje);
		return mapping.findForward(target);
	}
	
	public ActionForward showClient(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String indMensaje=null;
		String strMensaje=null;
		String target="show";
		try {
			SolicitudForm solicitudForm = (SolicitudForm)form;
			Solicitud solicitudBean = appPersonasService.invokeClient(solicitudForm.getCodCentral());
			IILDPeUsuario usuarioSesion = null;
			
			if(solicitudBean!=null){
				MultitablaDetalle multDetalleBean = catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_NATURALEZA, solicitudBean.getCodMultTipoPersona());
				
				usuarioSesion = (IILDPeUsuario)request.getSession(true).getAttribute("USUARIO_SESION");
				solicitudBean = appRCCService.invokeDeudaSisFinanciero(solicitudBean);
				solicitudBean = appRCDService.invokeDedudasRCD(solicitudBean);
				
				
				solicitudForm.setDesMultTipoPersona((multDetalleBean!=null?multDetalleBean.getStrValor():Constant.VALOR_ENCONTRADO));				
				solicitudForm.setCodMultTipoPersona(multDetalleBean.getCodMultitabla()!=null?multDetalleBean.getCodMultitabla()+Constant.GUION+multDetalleBean.getCodElemento():null);
				solicitudForm.setNumeroDocumento(solicitudBean.getNumeroDocumento());
				solicitudForm.setDesSolicitante(solicitudBean.getDesSolicitante());								
				solicitudForm.setCodOficina(solicitudBean.getCodOficina());
				solicitudForm.setDesOficina(solicitudBean.getDesOficina());								
				solicitudForm.setGestorCod(solicitudBean.getGestorCod());
				solicitudForm.setGestorNom(solicitudBean.getGestorNom());								
				solicitudForm.setEmpleadorCod(solicitudBean.getEmpleadorCod());
				solicitudForm.setEmpleadorNom(solicitudBean.getEmpleadorNom());				
				solicitudForm.setGerenciaTerritorialCod(solicitudBean.getGerenciaTerritorialCod());
				solicitudForm.setGerenciaTerritorialNom(solicitudBean.getGerenciaTerritorialNom());
				solicitudForm.setDeudaDirecta(solicitudBean.getDeudaDirecta());
				solicitudForm.setDeudaIndirecta(solicitudBean.getDeudaIndirecta());
				solicitudForm.setCastigo(solicitudBean.getCastigo());
				solicitudForm.setDeudaSistemaFinanciero(solicitudBean.getDeudaSistemaFinanciero());
				solicitudForm.setScorating(solicitudBean.getScorating());
				solicitudForm.setRating(solicitudBean.getRating());
				solicitudBean.setReelevancia(solicitudBean.getReelevancia());
				solicitudForm.setRelevPublica1(solicitudBean.getArrayReelevancia()[0]!=null?solicitudBean.getArrayReelevancia()[0]:Constant.VACIO);
				solicitudForm.setRelevPublica2(solicitudBean.getArrayReelevancia()[1]!=null?solicitudBean.getArrayReelevancia()[1]:Constant.VACIO);
				solicitudForm.setRelevPublica3(solicitudBean.getArrayReelevancia()[2]!=null?solicitudBean.getArrayReelevancia()[2]:Constant.VACIO);
				solicitudForm.setRelevPublica4(solicitudBean.getArrayReelevancia()[3]!=null?solicitudBean.getArrayReelevancia()[3]:Constant.VACIO);
				solicitudForm.setRelevPublica5(solicitudBean.getArrayReelevancia()[4]!=null?solicitudBean.getArrayReelevancia()[4]:Constant.VACIO);
				solicitudForm.setClasificacion(solicitudBean.getClasificacion());	
				solicitudForm.setOficinaAltaCod(usuarioSesion.getBancoOficina().getCodigo());
				solicitudForm.setOficinaAltaNom(usuarioSesion.getBancoOficina().getDescripcion());
				solicitudForm.setEjecutivoCtaCod(usuarioSesion.getUID());
				solicitudForm.setEjecutivoCtaNom(usuarioSesion.getApellido1() + Constant.ESPACIO + usuarioSesion.getApellido2() + Constant.ESPACIO + usuarioSesion.getNombre());
				solicitudForm.setStrFechaIngreso(UtilDate.fechaActual());		
				solicitudForm.setCodBanca(solicitudBean.getCodBanca());
									
				Banca bancaBean = new Banca();
				bancaBean.setEstado(new BigDecimal(1));
				request.getSession().setAttribute("lstBancas", catalogoService.getLstBancaByCriteria(bancaBean));
				request.getSession().setAttribute("lstMonedas", catalogoService.getLstMultitablaDetalle(Constant.TABLA_MONEDA));
			}
		} catch (Exception e) {
			logger.error("Exception IngresoSolicitudAction.showClient: " + e.getMessage());
			indMensaje = Constant.MSJ_ERROR;
			strMensaje = "Sucedio un error. Comuniquese con el Administrador";
			request.setAttribute("sindMensaje", indMensaje);
			request.setAttribute("strMensaje", strMensaje);
			return mapping.findForward(target);	
		}
		return mapping.findForward(target);
	}

	
}
