package bbva.pe.gpr.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import bbva.pe.gpr.bean.Banca;
import bbva.pe.gpr.bean.Rol;
import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.context.Context;
import bbva.pe.gpr.form.SolicitudForm;
import bbva.pe.gpr.service.CatalogoService;
import bbva.pe.gpr.service.SeguridadService;
import bbva.pe.gpr.service.SolicitudService;
import bbva.pe.gpr.util.Constant;
import bbva.pe.gpr.util.JSONArray;
import bbva.pe.gpr.util.JSONObject;
import bbva.pe.gpr.util.UtilDate;

public class BusquedaSolicitudAction extends DispatchAction {
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
	
	public void buscarSolicitud(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Rol rolBean = new Rol();
		rolBean.setEstado(Constant.ESTADO_ACTIVO);
		Banca bancaBean = new Banca();
		bancaBean.setEstado(Constant.ESTADO_ACTIVO);
		
		request.getSession().setAttribute("lstRol", seguridadService.getLstRolesByCriteria(rolBean));
		request.getSession().setAttribute("lstEstados", catalogoService.getLstMultitablaDetalle(Constant.TABLA_ESTADOS_SOLCITUD));
		request.getSession().setAttribute("lstBancas", catalogoService.getLstBancaByCriteria(bancaBean));
		
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		
		JSONObject jsonObjectRoot=new JSONObject();
		JSONArray jsonArray= new JSONArray();
		
		SolicitudForm solicitudForm =(SolicitudForm)form;
		Solicitud solicitudBean = new Solicitud();

		if(solicitudForm.getCodCentral()!=null && solicitudForm.getCodCentral()!="" ){
			solicitudBean.setCodCentral(Constant.SIMBOLO_PORCENTAJE+solicitudForm.getCodCentral()+Constant.SIMBOLO_PORCENTAJE);
		}
		if(solicitudForm.getCodOficina()!=null && solicitudForm.getCodOficina()!="" ){
			solicitudBean.setCodOficina(Constant.SIMBOLO_PORCENTAJE+solicitudForm.getCodOficina()+Constant.SIMBOLO_PORCENTAJE);
		}
		if(solicitudForm.getDesOficina()!=null && solicitudForm.getDesOficina()!="" ){
			solicitudBean.setDesOficina(Constant.SIMBOLO_PORCENTAJE+solicitudForm.getDesOficina()+Constant.SIMBOLO_PORCENTAJE);
		}
		solicitudBean.setCodBanca(solicitudForm.getCodBanca());
		solicitudBean.setNroSolicitud(new Long(solicitudForm.getNroSolicitud()));
		solicitudBean.setFechaIngresoIni(UtilDate.stringToUtilDate(solicitudForm.getFechaIngresoIni(), ""));
		solicitudBean.setFechaIngresoFin(UtilDate.stringToUtilDate(solicitudForm.getFechaIngresoFin(), ""));
		solicitudBean.setCodEstadoMult(solicitudForm.getCodEstadoMult());
		
	    try{
			int i=0;
			List<Solicitud> lstSolicitud=solicitudService.getLstSolicitudes(solicitudBean);
			int totalPages = 0;
            int totalCount = lstSolicitud.size();
            if (totalCount > 0) {
                if (totalCount % Integer.parseInt(rows) == 0) {
                    totalPages = totalCount / Integer.parseInt(rows);
                } else {
                    totalPages = (totalCount / Integer.parseInt(rows)) + 1;
                }
            } else {
                totalPages = 0;
            }
            
           
		for(Solicitud solicitudJson:lstSolicitud){
		    JSONArray jsonArray2= new JSONArray();
		    JSONObject jsonObject=new JSONObject();
		    jsonArray2.put(solicitudJson.getNroSolicitud());
		    jsonArray2.put(solicitudJson.getFechaIngreso());
		    jsonArray2.put(solicitudJson.getCodCentral());
		    jsonArray2.put(solicitudJson.getDesMultTipoPersona());
		    jsonArray2.put(solicitudJson.getMtoSolicitud());
		    jsonArray2.put(solicitudJson.getRiesgoActual());
		    jsonArray2.put(solicitudJson.getRiesgoTotal());
		    jsonArray2.put(solicitudJson.getDesBanca());
		    jsonArray2.put(solicitudJson.getCodOficina());
		    jsonArray2.put(solicitudJson.getFechaIngreso());
		    jsonArray2.put(solicitudJson.getDesOficina());
		    jsonArray2.put(solicitudJson.getHdnCodigo());
		    jsonObject.put("id",i++);
		    jsonObject.put("cell",jsonArray2);	
			jsonArray.put(jsonObject);
	   }
	    jsonObjectRoot.put("page",page);
		jsonObjectRoot.put("total",totalPages);
		jsonObjectRoot.put("records",lstSolicitud.size());
		jsonObjectRoot.put("rows", jsonArray);
	}catch (Exception e) {
		jsonObjectRoot.put("success", false);
		jsonObjectRoot.put("message", e.getCause());
	}
		out.print(jsonObjectRoot.toString());
		out.flush();
		out.close();
}
}
