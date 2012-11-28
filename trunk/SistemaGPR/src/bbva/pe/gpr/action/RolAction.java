package bbva.pe.gpr.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import bbva.pe.gpr.bean.Funcion;
import bbva.pe.gpr.bean.FuncionRol;
import bbva.pe.gpr.bean.Rol;
import bbva.pe.gpr.context.Context;
import bbva.pe.gpr.form.RolesForm;
import bbva.pe.gpr.service.CatalogoService;
import bbva.pe.gpr.util.Constant;
import bbva.pe.gpr.util.ReadProperties;

public class RolAction extends DispatchAction{
	private static Logger logger = Logger.getLogger(RolAction.class);
	private CatalogoService catalogoService; 
	public RolAction() {
		catalogoService = (CatalogoService)Context.getInstance().getBean("catalogoService");
	}
	
	public ActionForward listarRoles(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("creaRol");		
	}	
	
	public List<Rol> getLstRoles() throws Exception{
		List<Rol> getLrol=catalogoService.getLsRoles();
		if(getLrol.isEmpty()){
			return new ArrayList<Rol>();
		}
		return getLrol;
	}
	
	public ActionForward guardarCanalAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			try {
		    RolesForm rolesForm =(RolesForm)form;
		    String codRolHdn=rolesForm.getCodRolHdn();
			String codRol = rolesForm.getCodRol();
			String descripRol = rolesForm.getDescripcion();
			String referRol = rolesForm.getReferencia();
			ReadProperties readProperties = new ReadProperties();
			Map<String, String> mapResult =catalogoService.saveRol(codRolHdn, codRol, descripRol, referRol);	
			rolesForm.setCodRol("");
			rolesForm.setDescripcion("");
			rolesForm.setReferencia("");
			if(mapResult.get("msgError") != null) {
				response.setStatus(500);
				response.getWriter().write(readProperties.getProperty(mapResult.get("msgError")));
			}
			}catch (Exception e) {
				logger.error("RolAction.guardarCanalAjax", e);
			}
			return null;
		}
	
	public ActionForward listarRolesFunciones(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String codRol= request.getParameter("codRolAsignar");
		Rol rol= catalogoService.getRolSelectByPrimaryKey(new BigDecimal(codRol));
		try {
		RolesForm rolForm= new RolesForm();
	 	rolForm.setCodRol(rol.getCodRol().toString());
	 	rolForm.setDescripcion(rol.getDescripcion());
		request.setAttribute("getRolForm",rolForm);
		}catch (Exception e) {
			System.out.print("Error " + e.getLocalizedMessage());
			logger.error("RolAction.listarRolesFunciones", e);
		}
		return mapping.findForward("rolFunciones");
	}
	
	public List<Funcion> consultarFunciones() throws Exception{
	 Funcion funcion= new Funcion();
	 funcion.setEstado("1");
		List<Funcion> getLstFuncion= catalogoService.getLstFuncionByCriteria(funcion);
		if(getLstFuncion.isEmpty()){
			return new ArrayList<Funcion>();
		}
		return getLstFuncion;
	}
	
	public List<Funcion> lstRolesFunciones(String codRol) throws Exception{
			List<Funcion> getLstFuncion= catalogoService.getLstRolesFuncion(codRol);
			if(getLstFuncion.isEmpty()){
				return new ArrayList<Funcion>();
			}
			return getLstFuncion;
	}
	
	public String saveRolFuncion(BigDecimal codRol, BigDecimal codFuncion){
		FuncionRol funcionRol= new FuncionRol();
		funcionRol.setCodFuncion(codFuncion);
		funcionRol.setCodRol(codRol);
		funcionRol.setEstado(Constant.ESTADO_ACTIVO);
		if(catalogoService.rolFuncionExistente(funcionRol)=="0"){
			catalogoService.saveRolFunciones(funcionRol);
			return "grabo";			
		}else
	        return "existe";
	}
}
