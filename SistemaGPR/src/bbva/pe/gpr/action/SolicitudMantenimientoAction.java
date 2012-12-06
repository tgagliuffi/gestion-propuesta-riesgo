package bbva.pe.gpr.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.directwebremoting.WebContextFactory;

import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.bean.SolicitudDetalle;
import bbva.pe.gpr.context.Context;
import bbva.pe.gpr.service.CatalogoService;
import bbva.pe.gpr.service.SolicitudService;
import bbva.pe.gpr.util.Constant;

public class SolicitudMantenimientoAction extends DispatchAction
{
	
	private static Logger logger = Logger.getLogger(BusquedaSolicitudAction.class);
	
	SolicitudService solicitudService;
	CatalogoService catalogoService;

	public SolicitudMantenimientoAction() 
	{
		solicitudService=(SolicitudService)Context.getInstance().getBean("solicitudService");	
		catalogoService=(CatalogoService)Context.getInstance().getBean("catalogoService");
	}
	
	public Map<String, Object> actualizarPrioridadMasiva(String prioridades) throws Exception 
	{
	
		Map<String, Object> result = new HashMap<String, Object>();
		
		try 
		{			
			Solicitud objSolicitud = null;
			for(String prioridad : prioridades.split("\\|"))
			{
				objSolicitud = new Solicitud();
				objSolicitud.setNroSolicitud(Long.parseLong(prioridad.split("\\-")[0]));
				objSolicitud.setPrioridad(prioridad.split("\\-")[1]);
				solicitudService.actualizarSolicitud(objSolicitud);
			}
			
			result.put("STATUS", "EXITO");
			
		} catch (Exception e) 
		{
			logger.error("SolicitudMantenimientoAction.actualizarPrioridadMasiva " + e);
			result.put("STATUS", "ERROR");
		}
		
		return result;
	}
	
	public Map<String, Object> actualizarAnulacionMasiva(String solicitudes) throws Exception 
	{
	
		Map<String, Object> result = new HashMap<String, Object>();
		
		try 
		{			
			Solicitud objSolicitud = null;
			for(String nroSolicitud : solicitudes.split("\\|"))
			{
				objSolicitud = new Solicitud();
				objSolicitud.setNroSolicitud(Long.parseLong(nroSolicitud));
				objSolicitud.setEstado(new BigDecimal(0));
				solicitudService.actualizarSolicitud(objSolicitud);
			}
			
			result.put("STATUS", "EXITO");
			
		} catch (Exception e) 
		{
			logger.error("SolicitudMantenimientoAction.actualizarAnulacionMasiva " + e);
			result.put("STATUS", "ERROR");
		}
		
		return result;
	}
	
	public ActionForward detalleSolicitud(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			
			if(request.getParameter("asigPrioridadIndividual")!=null){
				request.setAttribute("asigPrioridadIndividual", true);
				Solicitud solicitudBean = new Solicitud();
				solicitudBean.setNroSolicitud(Long.parseLong(request.getParameter("codigoSolicitud")));
				List<Solicitud> lstSolicitudDetalle =  solicitudService.getLstSolicitudes(solicitudBean);
				if(lstSolicitudDetalle.get(0)!=null){
					 solicitudBean = lstSolicitudDetalle.get(0);
					if(solicitudBean.getReelevancia()!=null){
						solicitudBean.setRelevPublica1(solicitudBean.getReelevancia().length()>=3?(catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_RELEVANCIA_PUBLICA, solicitudBean.getReelevancia().substring(0, 3))!=null?catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_RELEVANCIA_PUBLICA, solicitudBean.getReelevancia().substring(0, 3)).getStrValor():Constant.STR_VACIO):Constant.STR_VACIO);
						solicitudBean.setRelevPublica2(solicitudBean.getReelevancia().length()>=6?(catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_RELEVANCIA_PUBLICA, solicitudBean.getReelevancia().substring(3, 6))!=null?catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_RELEVANCIA_PUBLICA, solicitudBean.getReelevancia().substring(03, 6)).getStrValor():Constant.STR_VACIO):Constant.STR_VACIO);
						solicitudBean.setRelevPublica3(solicitudBean.getReelevancia().length()>=9?(catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_RELEVANCIA_PUBLICA, solicitudBean.getReelevancia().substring(6, 9))!=null?catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_RELEVANCIA_PUBLICA, solicitudBean.getReelevancia().substring(6, 9)).getStrValor():Constant.STR_VACIO):Constant.STR_VACIO);
						solicitudBean.setRelevPublica4(solicitudBean.getReelevancia().length()>=12?(catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_RELEVANCIA_PUBLICA, solicitudBean.getReelevancia().substring(9, 12))!=null?catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_RELEVANCIA_PUBLICA, solicitudBean.getReelevancia().substring(9, 12)).getStrValor():Constant.STR_VACIO):Constant.STR_VACIO);
					
					}
					if(solicitudBean.getPrioridad().equals(Constant.PRIORIDAD_ALTA))
						solicitudBean.setPrioridad("1");
					if(solicitudBean.getPrioridad().equals(Constant.PRIORIDAD_NORMAL))
						solicitudBean.setPrioridad("2");
					if(solicitudBean.getPrioridad().equals(Constant.PRIORIDAD_BAJA))
						solicitudBean.setPrioridad("3");
					request.setAttribute("Solicitud", solicitudBean);
				}
			}
			if(request.getParameter("asigAnulacionIndividual")!=null){
				request.setAttribute("asigAnulacionIndividual", true);
				Solicitud solicitudBean = new Solicitud();
				solicitudBean.setNroSolicitud(Long.parseLong(request.getParameter("codigoSolicitud")));
				List<Solicitud> lstSolicitudDetalle =  solicitudService.getLstSolicitudes(solicitudBean);
				if(lstSolicitudDetalle.get(0)!=null){
					 solicitudBean = lstSolicitudDetalle.get(0);
					if(solicitudBean.getReelevancia()!=null){
						solicitudBean.setRelevPublica1(solicitudBean.getReelevancia().length()>=3?(catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_RELEVANCIA_PUBLICA, solicitudBean.getReelevancia().substring(0, 3))!=null?catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_RELEVANCIA_PUBLICA, solicitudBean.getReelevancia().substring(0, 3)).getStrValor():Constant.STR_VACIO):Constant.STR_VACIO);
						solicitudBean.setRelevPublica2(solicitudBean.getReelevancia().length()>=6?(catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_RELEVANCIA_PUBLICA, solicitudBean.getReelevancia().substring(3, 6))!=null?catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_RELEVANCIA_PUBLICA, solicitudBean.getReelevancia().substring(03, 6)).getStrValor():Constant.STR_VACIO):Constant.STR_VACIO);
						solicitudBean.setRelevPublica3(solicitudBean.getReelevancia().length()>=9?(catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_RELEVANCIA_PUBLICA, solicitudBean.getReelevancia().substring(6, 9))!=null?catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_RELEVANCIA_PUBLICA, solicitudBean.getReelevancia().substring(6, 9)).getStrValor():Constant.STR_VACIO):Constant.STR_VACIO);
						solicitudBean.setRelevPublica4(solicitudBean.getReelevancia().length()>=12?(catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_RELEVANCIA_PUBLICA, solicitudBean.getReelevancia().substring(9, 12))!=null?catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_RELEVANCIA_PUBLICA, solicitudBean.getReelevancia().substring(9, 12)).getStrValor():Constant.STR_VACIO):Constant.STR_VACIO);
					
					}request.setAttribute("Solicitud", solicitudBean);
				}
			}
			
			
			
		}catch (Exception e) {
		 System.out.print("Error "+e.getLocalizedMessage());
		}
		return mapping.findForward("success");
	}
	
	public List<SolicitudDetalle> consultarSolicitudDetalleAjax(String nroSolicitud) throws Exception 
	{
		try {
			Solicitud solicitudBean = new Solicitud();
			solicitudBean.setNroSolicitud(Long.parseLong(nroSolicitud));
			List<SolicitudDetalle> lstSolicitudDetalle = solicitudService.getListSolicitudDetalleForId(solicitudBean);
			return lstSolicitudDetalle;
		}catch (Exception e) {
			logger.error("BusquedaSolicitudAction.consultarSolicitudDetalleAjax " +e);
			return new  ArrayList<SolicitudDetalle>();
		}
	}
	
	public String priorizarSolicitud(String nroSolicitud, String value) throws Exception 
	{
		String  strMensaje;	
	 
		try {
			
			if(solicitudService.asignarPrioridadSolicitud(nroSolicitud, value)>0){
				strMensaje = "Se actualizo exitosamente el registro.";
			}else{
				strMensaje = "Sucedio un error en la actualización.";
			}
			
		}catch (Exception e) {
			logger.error("BusquedaSolicitudAction.priorizarSolicitud " +e);
			return null;
		}
		return strMensaje;
	}
	
	public String anularSolicitud(String nroSolicitud, String value) throws Exception 
	{  HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
		try {
			String indMensaje, strMensaje;			
				if(solicitudService.anularSolicitud(nroSolicitud)>0){
					indMensaje = Constant.MSJ_ALERT;
					strMensaje = "Se ha actualizado exitosamente el registro.";
				
				}else{
					indMensaje = Constant.MSJ_ERROR;
					strMensaje = "Se ha ingresado la solicitud " + nroSolicitud + ". Requiere asignación en oficina";
				
				}
				request.setAttribute("indMensaje", indMensaje);
				request.setAttribute("strMensaje", strMensaje);
		}catch (Exception e) {
			logger.error("BusquedaSolicitudAction.consultarSolicitudAjax " +e);
			return  null;
		}return null;
	}
	
}
