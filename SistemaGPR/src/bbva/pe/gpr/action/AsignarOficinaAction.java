package bbva.pe.gpr.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import bbva.pe.gpr.bean.Banca;
import bbva.pe.gpr.bean.Rol;
import bbva.pe.gpr.bean.Usuario;
import bbva.pe.gpr.context.Context;
import bbva.pe.gpr.form.AsignarOficinaForm;
import bbva.pe.gpr.service.CatalogoService;
import bbva.pe.gpr.service.SeguridadService;
import bbva.pe.gpr.util.Constant;
import bbva.pe.gpr.util.JSONArray;
import bbva.pe.gpr.util.JSONObject;


public class AsignarOficinaAction extends DispatchAction {

	
	private CatalogoService catalogoService; 
	private SeguridadService seguridadService;
	
	public AsignarOficinaAction() {
		catalogoService = (CatalogoService)Context.getInstance().getBean("catalogoService");
		seguridadService = (SeguridadService)Context.getInstance().getBean("seguridadService");
	}
	
	public ActionForward listarAsignarOficina(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Banca banca = new Banca();
		banca.setEstado(Constant.ESTADO_ACTIVO);
		try{
			Rol rolBean = new Rol();
			rolBean.setEstado(Constant.ESTADO_ACTIVO);
		request.setAttribute("getLstBanca", catalogoService.getLstBancaByCriteria(banca));
		request.setAttribute("getLstRoles", seguridadService.getLstRolesByCriteria(rolBean));
		}catch (Exception e) {
		 System.out.print("Error "+e.getLocalizedMessage());
		}
		return mapping.findForward("asignarOficina");
	}
	
	public void busquedaUsuario(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		JSONObject jsonObjectRoot=new JSONObject();
		JSONArray jsonArray= new JSONArray();
		String valor="";
		AsignarOficinaForm manteParamForm =(AsignarOficinaForm)form;
		Usuario user= new Usuario();
	    
		if(manteParamForm.getApeMaterno()!=null && manteParamForm.getApeMaterno()!="" ){
			user.setApellidoMaterno("%"+manteParamForm.getApeMaterno()+"%");
		}
		if(manteParamForm.getApePaterno()!=null && manteParamForm.getApePaterno()!="" ){
			user.setApellidoPaterno("%"+manteParamForm.getApePaterno()+"%");
		}
		if(manteParamForm.getNombreUsuario()!=null && manteParamForm.getNombreUsuario()!="" ){
			 user.setNombre("%"+manteParamForm.getNombreUsuario()+"%");
		}
		if(manteParamForm.getCodUsuario()!=null && manteParamForm.getCodUsuario()!="" ){
		    user.setCodUsuario("%"+manteParamForm.getCodUsuario()+"%");
		}
		if(manteParamForm.getBancaUsuario()!=null && manteParamForm.getBancaUsuario()!="" ){
		    user.setGenero("%"+manteParamForm.getBancaUsuario()+"%");
		}
		if(manteParamForm.getCargoUsuario()==null || manteParamForm.getCargoUsuario().equals("")){
			valor="-1";
		}else{
			valor=manteParamForm.getCargoUsuario();
		}
	 	BigDecimal codRol=new BigDecimal(valor);
		user.setCodRol(codRol);
	    user.setEstado(Constant.ESTADO_ACTIVO);
	    try{
			int i=0;
			List<Usuario> getLstUser=catalogoService.getLstUsuario(user);
			int totalPages = 0;
            int totalCount = getLstUser.size();
            if (totalCount > 0) {
                if (totalCount % Integer.parseInt(rows) == 0) {
                    totalPages = totalCount / Integer.parseInt(rows);
                } else {
                    totalPages = (totalCount / Integer.parseInt(rows)) + 1;
                }
            } else {
                totalPages = 0;
            }
		for(Usuario users:getLstUser){
		    JSONArray jsonArray2= new JSONArray();
		    JSONObject jsonObject=new JSONObject();
		    jsonArray2.put(users.getCodUsuario());
		    jsonArray2.put(users.getNombre()+" "+users.getApellidoMaterno());
		    jsonArray2.put(users.getGenero());
		    jsonArray2.put(users.getCodCargo());
		    jsonObject.put("id",i++);
		    jsonObject.put("cell",jsonArray2);	
			jsonArray.put(jsonObject);
	   }
	    jsonObjectRoot.put("page",page);
		jsonObjectRoot.put("total",totalPages);
		jsonObjectRoot.put("records",getLstUser.size());
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