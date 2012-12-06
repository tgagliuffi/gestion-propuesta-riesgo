package bbva.pe.gpr.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.actions.DownloadAction.StreamInfo;

import bbva.pe.gpr.bean.Asignacion;
import bbva.pe.gpr.bean.Banca;
import bbva.pe.gpr.bean.BancaSub;
import bbva.pe.gpr.bean.Rol;
import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.bean.SolicitudDetalle;
import bbva.pe.gpr.bean.SolicitudMensaje;
import bbva.pe.gpr.bean.SolicitudOperacion;
import bbva.pe.gpr.context.Context;
import bbva.pe.gpr.form.SolicitudForm;
import bbva.pe.gpr.service.CatalogoService;
import bbva.pe.gpr.service.SeguridadService;
import bbva.pe.gpr.service.SolicitudService;
import bbva.pe.gpr.util.Constant;
import bbva.pe.gpr.util.ReporteExcel;
import bbva.pe.gpr.util.UtilDate;

public class BusquedaSolicitudAction extends DispatchAction {
	private static Logger logger = Logger.getLogger(BusquedaSolicitudAction.class);
	
	SolicitudService solicitudService;
	CatalogoService catalogoService;
	SeguridadService seguridadService;
	
	public BusquedaSolicitudAction() {
		solicitudService=(SolicitudService)Context.getInstance().getBean("solicitudService");
		catalogoService=(CatalogoService)Context.getInstance().getBean("catalogoService");
	    seguridadService=(SeguridadService)Context.getInstance().getBean("seguridadService");
	}
	
	public ActionForward listarSolicitud(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			Rol rolBean = new Rol();
			rolBean.setEstado(Constant.ESTADO_ACTIVO);
			Banca bancaBean = new Banca();
			bancaBean.setEstado(Constant.ESTADO_ACTIVO);
			
			request.getSession().setAttribute("lstRol", seguridadService.getLstRolesByCriteria(rolBean));
			request.getSession().setAttribute("lstEstados", catalogoService.getLstMultitablaDetalle(Constant.TABLA_ESTADOS_SOLCITUD));
			request.getSession().setAttribute("lstBancas", catalogoService.getLstBancaByCriteria(bancaBean));
			
		}catch (Exception e) {
		 System.out.print("Error "+e.getLocalizedMessage());
		}
		return mapping.findForward("success");
	}

	public ActionForward detalleSolicitud(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
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
			
		}catch (Exception e) {
		 System.out.print("Error "+e.getLocalizedMessage());
		}
		return mapping.findForward("detalle");
	}
	
	public List<Solicitud> consultarSolicitudAjax(String codBanca, String codOficina, String desOficina, String codCentral, String nroSolicitud, 
									   String fechaIngresoIni, String fechaIngresoFin, String codEstadoMult, 
									   String codRol, String regEvaluador, String apeEvaluador, String subBanca) throws Exception {
		
		Solicitud solicitudBean = new Solicitud();
		solicitudBean.setAsignacionBean(new Asignacion());
		if(codBanca != null  && !codBanca.equalsIgnoreCase(Constant.STR_VACIO) && !codBanca.equalsIgnoreCase("-1")){
			solicitudBean.setCodBanca(new BigDecimal(codBanca));
		}if(codOficina!=null && !codOficina.equalsIgnoreCase(Constant.STR_VACIO)){
			solicitudBean.setCodOficina(codOficina);
		}if(desOficina!=null && !desOficina.equalsIgnoreCase(Constant.STR_VACIO) ){
			solicitudBean.setDesOficina(Constant.CHAR_PORCENTAJE+desOficina+Constant.CHAR_PORCENTAJE);
		}if(codCentral!=null && !codCentral.equalsIgnoreCase(Constant.STR_VACIO)){
			solicitudBean.setCodCentral(codCentral);
		}if(nroSolicitud!=null && !nroSolicitud.equalsIgnoreCase(Constant.STR_VACIO)){
			solicitudBean.setNroSolicitud(new Long(nroSolicitud));
		}if(fechaIngresoIni!=null && !fechaIngresoIni.equalsIgnoreCase(Constant.STR_VACIO)){
			solicitudBean.setFechaIngresoIni(UtilDate.stringToUtilDate(fechaIngresoIni, null));
		}if(fechaIngresoFin!=null && !fechaIngresoFin.equalsIgnoreCase(Constant.STR_VACIO)){
			solicitudBean.setFechaIngresoFin(UtilDate.stringToUtilDate(fechaIngresoFin, null));
		}if(codEstadoMult!=null && !codEstadoMult.equalsIgnoreCase(Constant.STR_VACIO) && !codEstadoMult.equalsIgnoreCase("-1")){
			solicitudBean.setEstadoSolicitud(codEstadoMult.substring(0, 4)+Constant.CHAR_GUION+codEstadoMult.substring(4, 9));
		}if(codRol != null && !codRol.equalsIgnoreCase(Constant.STR_VACIO) && !codRol.equalsIgnoreCase("-1")){
			solicitudBean.getAsignacionBean().setCodRol(new BigDecimal(codRol));
		}if(regEvaluador != null && !regEvaluador.equalsIgnoreCase(Constant.STR_VACIO)){
			solicitudBean.getAsignacionBean().setCodUsuario(regEvaluador);
		}if(apeEvaluador!=null && !apeEvaluador.equalsIgnoreCase(Constant.STR_VACIO)){
			solicitudBean.getAsignacionBean().setNombre(Constant.CHAR_PORCENTAJE+apeEvaluador+Constant.CHAR_PORCENTAJE);
		}if(subBanca!=null && !subBanca.equalsIgnoreCase(Constant.STR_VACIO) && !subBanca.equalsIgnoreCase("-1")){
			solicitudBean.setCodSubanca(subBanca);
		}

	   try {
			List<Solicitud> lstSolicitud=solicitudService.getLstSolicitudes(solicitudBean);
			return lstSolicitud;
	} catch (Exception e) {
		logger.error("BusquedaSolicitudAction.consultarSolicitudAjax " +e);
	}
	   return new  ArrayList<Solicitud>();
	}
	
	public List<SolicitudDetalle> consultarSolicitudDetalleAjax(String nroSolicitud) throws Exception 
	{
		try {
			Solicitud solicitudBean = new Solicitud();
			solicitudBean.setNroSolicitud(Long.parseLong(nroSolicitud));
			List<SolicitudDetalle> lstSolicitudDetalle = solicitudService.getListSolicitudDetalleForId(solicitudBean);
			return lstSolicitudDetalle;
		}catch (Exception e) {
			logger.error("BusquedaSolicitudAction.consultarSolicitudAjax " +e);
			return new  ArrayList<SolicitudDetalle>();
		}
	}
	public StreamInfo  reporteExcelAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SolicitudForm solicitudForm = (SolicitudForm)form;
		Solicitud solicitudBean = new Solicitud();
		if(solicitudForm.getCodBanca() != null  && !solicitudForm.getCodBanca().equalsIgnoreCase(Constant.STR_VACIO) && !solicitudForm.getCodBanca().equalsIgnoreCase("-1")){
			solicitudBean.setCodSubanca(solicitudForm.getCodBanca());
		}if(solicitudForm.getCodOficina()!=null && !solicitudForm.getCodOficina().equalsIgnoreCase(Constant.STR_VACIO)){
			solicitudBean.setCodOficina(solicitudForm.getCodOficina());
		}if(solicitudForm.getDesOficina() !=null && !solicitudForm.getDesOficina().equalsIgnoreCase(Constant.STR_VACIO) ){
			solicitudBean.setDesOficina(Constant.CHAR_PORCENTAJE+solicitudForm.getDesOficina()+Constant.CHAR_PORCENTAJE);
		}if(solicitudForm.getCodCentral()!=null && !solicitudForm.getCodCentral().equalsIgnoreCase(Constant.STR_VACIO)){
			solicitudBean.setCodCentral(Constant.CHAR_PORCENTAJE+solicitudForm.getCodCentral()+Constant.CHAR_PORCENTAJE);
		}if(solicitudForm.getNroSolicitud()!=null && !solicitudForm.getNroSolicitud().equalsIgnoreCase(Constant.STR_VACIO)){
			solicitudBean.setNroSolicitud(new Long(solicitudForm.getNroSolicitud()));
		}if(solicitudForm.getFechaIngresoIni()!=null && !solicitudForm.getFechaIngresoIni().equalsIgnoreCase(Constant.STR_VACIO)){
			solicitudBean.setFechaIngresoIni(UtilDate.stringToUtilDate(solicitudForm.getFechaIngresoIni(), null));
		}if(solicitudForm.getFechaIngresoFin()!=null && !solicitudForm.getFechaIngresoFin().equalsIgnoreCase(Constant.STR_VACIO)){
			solicitudBean.setFechaIngresoFin(UtilDate.stringToUtilDate(solicitudForm.getFechaIngresoFin(), null));
		}if(solicitudForm.getCodEstadoMult()!=null && !solicitudForm.getCodEstadoMult().equalsIgnoreCase(Constant.STR_VACIO) && !solicitudForm.getCodEstadoMult().equalsIgnoreCase("-1")){
			solicitudBean.setEstado(new BigDecimal(solicitudForm.getCodEstadoMult()));
		}if(solicitudForm.getCodRol() != null && !solicitudForm.getCodRol().equalsIgnoreCase(Constant.STR_VACIO) && !solicitudForm.getCodRol().equalsIgnoreCase("-1")){
			solicitudBean.getAsignacionBean().setCodRol(new BigDecimal(solicitudForm.getCodRol()));
		}if(solicitudForm.getRegEvaluador() != null && !solicitudForm.getRegEvaluador().equalsIgnoreCase(Constant.STR_VACIO)){
			solicitudBean.getAsignacionBean().setCodUsuario(solicitudForm.getRegEvaluador());
		}if(solicitudForm.getNomEvaluador()!=null && !solicitudForm.getNomEvaluador().equalsIgnoreCase(Constant.STR_VACIO)){
			solicitudBean.getAsignacionBean().setNombre(Constant.CHAR_PORCENTAJE+solicitudForm.getNomEvaluador()+Constant.CHAR_PORCENTAJE);
		}
		
		
		String path = null;  
		InputStream in = null;
		OutputStream out = null;
		File archivo = null;
		String ERROR_NO_EXISTE_ARCHIVO = null;
		PrintWriter pw = null;		
		
		try {
			
			List<Solicitud> lstSolicitud=solicitudService.getLstSolicitudes(solicitudBean);
			ReporteExcel reporte = new ReporteExcel();
		//	String ruta = getServlet().getServletContext().getRealPath("WebContent/archivo");
			path = reporte.generaExcel(lstSolicitud);
			
			
			
			if (path != null) {				
				try {
					if(new File(path).length()>0L){
						archivo = new File(path);				
						in = new FileInputStream(archivo);
						
						if (in != null) {
							out = new BufferedOutputStream(response.getOutputStream());
							in = new BufferedInputStream(in);
							String contentType = "application/unknow";
							response.setContentType(contentType);
							response.setHeader("Content-Disposition",
									"attachment; filename=\"" + archivo.getName() + "\"");
							int c;
							while ((c = in.read()) != -1) {
								out.write(c);
							}
							
							return null;
						}
					}
					else{
						ERROR_NO_EXISTE_ARCHIVO = "Archivo no encontrado";
						response.setContentType("text/html");
						pw = response.getWriter();
						pw.write("<html><head>"+
								"<script language=\"javascript\">" +
								"function mensaje(){" +
								"alert(\""+ERROR_NO_EXISTE_ARCHIVO+"\");" +
								"history.back();" +
								"}" +
								"</script></head>"+
								"<body onload=\"mensaje();\">" +
								"</body></html>");
					}
				} finally {
					if (in != null)
						try {
							in.close();
						} catch (Exception e) {
							logger.info(e.getMessage(), e);
						}
					if (out != null)
						try {
							out.close();
						} catch (Exception e) {
							logger.info(e.getMessage(), e);
						}
				}
		}			
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		}	
	
		
		return null;
	}
	
	public List<SolicitudOperacion> listOperationsAjax(String nroSolicitud) throws Exception 
	{
		try {
			Solicitud solicitudBean = new Solicitud();
			solicitudBean.setNroSolicitud(Long.parseLong(nroSolicitud));
			List<SolicitudOperacion> lstSolicitudDetalle = solicitudService.selectOperacionByNroSolicitud(solicitudBean);
			return lstSolicitudDetalle;
		}catch (Exception e) {
			logger.error("BusquedaSolicitudAction.consultarSolicitudAjax " +e);
			return new  ArrayList<SolicitudOperacion>();
		}
	}
	
	public List<SolicitudMensaje> listMessagesAjax(String nroSolicitud) throws Exception 
	{
		try {
			SolicitudMensaje solicitudBean = new SolicitudMensaje();
			solicitudBean.setNroSolicitud(Long.parseLong(nroSolicitud));
			List<SolicitudMensaje> lstSolicitudDetalle = solicitudService.getListMessagesAjax(solicitudBean);
			return lstSolicitudDetalle;
		}catch (Exception e) {
			logger.error("BusquedaSolicitudAction.listMessagesAjax " +e);
			return new  ArrayList<SolicitudMensaje>();
		}
	}
	
	public List<BancaSub> getLstSubBanca(String codBanca){
		BancaSub bancaSubBean = new BancaSub();
		bancaSubBean.setCodBanca(new BigDecimal(codBanca));
		List<BancaSub> lstSubBanca =  new ArrayList<BancaSub>();
		try {
			BancaSub subanca = new BancaSub();
			subanca.setCodBanca(new BigDecimal(-1));
			subanca.setDescripcion(Constant.SELECCIONE);
			lstSubBanca.add(subanca);
			for (BancaSub bancaSub : catalogoService.getLstSubBanca(bancaSubBean)) {
				lstSubBanca.add(bancaSub);
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lstSubBanca;
	}
}
