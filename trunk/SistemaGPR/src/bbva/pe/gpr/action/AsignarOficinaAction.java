package bbva.pe.gpr.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import bbva.pe.gpr.bean.BancaSub;
import bbva.pe.gpr.bean.Oficina;
import bbva.pe.gpr.bean.Rol;
import bbva.pe.gpr.bean.Territorio;
import bbva.pe.gpr.bean.Usuario;
import bbva.pe.gpr.bean.UsuarioOficina;
import bbva.pe.gpr.context.Context;
import bbva.pe.gpr.form.AsignarOficinaForm;
import bbva.pe.gpr.service.CatalogoService;
import bbva.pe.gpr.util.Constant;

public class AsignarOficinaAction extends DispatchAction {
	private static Logger logger = Logger.getLogger(AsignarOficinaAction.class);
	private CatalogoService catalogoService; 
	
	public AsignarOficinaAction() {
		catalogoService = (CatalogoService)Context.getInstance().getBean("catalogoService");
	}
	
	public ActionForward listarAsignarOficina(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BancaSub bancaSub = new BancaSub();
		Rol rolBean = new Rol();
		bancaSub.setEstado(Constant.ESTADO_ACTIVO);
		rolBean.setEstado(Constant.ESTADO_ACTIVO);
		try {
			//TODO:CAMBIAR
		request.setAttribute("lstBancaSub", catalogoService.getLstSubBanca(bancaSub));
		request.setAttribute("getLstRoles", catalogoService.getLstRolesByCriteria(rolBean));
		}catch (Exception e) {
			System.out.print("Error " + e.getLocalizedMessage());
			logger.error("AsignarOficinaAction.listarAsignarOficina", e);
		}
		return mapping.findForward("asignarOficina");
	}
	
	public ActionForward listarTerritoriOficina(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Territorio territorio = new Territorio();
		territorio.setEstado(Constant.ESTADO_ACTIVO);
		String codUsuario= request.getParameter("codUsuario1");
		Usuario user = new Usuario();
		user.setCodigoUsuario(codUsuario);
		user.setEstado("1");
		try {
		List<Usuario> getLstUser = catalogoService.getLstUsuario(user);
	    AsignarOficinaForm asignarOficinaForm= new AsignarOficinaForm();
		asignarOficinaForm.setCodAsignar(getLstUser.get(0).getCodigoUsuario());
		asignarOficinaForm.setNombreUsuario(getLstUser.get(0).getNombres());
		request.setAttribute("getUsuario",asignarOficinaForm);
		request.setAttribute("getLstTerritorio",catalogoService.getLstTerritorioByCriteria(territorio));
		}catch (Exception e) {
			System.out.print("Error " + e.getLocalizedMessage());
			logger.error("AsignarOficinaAction.listarTerritorioOficina", e);
		}
		return mapping.findForward("parametriaEvaluador");
	}
	
	public List<Usuario> consultarAjax(String codUsuario,String nombreUsuario ,String codBanca,Long codRol) {
		Usuario user = new Usuario();
		user.setEstado("1");
		try {
			 if(codUsuario!=null && codUsuario !=""){
			 user.setCodigoUsuario("%"+codUsuario+"%");
			 }
			 if(codUsuario!=null ){
			 user.setCodigoUsuario("%"+codUsuario+"%");
			 }
			 if(nombreUsuario!=null && nombreUsuario !=""){
			 user.setNombres("%"+nombreUsuario+"%");
			 }
			 if(nombreUsuario!=null){
			 user.setNombres("%"+nombreUsuario+"%");
			 }
			 if(codBanca!=null && codBanca !=""){
			 user.setCodUsuarioCreacion("%"+codBanca+"%");
			 }
			user.setIdUsuario(codRol);
		    List<Usuario> getLstUser = catalogoService.getLstUsuario(user);
			return getLstUser;
			 } catch (Exception e) {
		logger.error("AsignarOficinaAction.listarTerritorioOficina", e);		 
			return new ArrayList<Usuario>();
			 }
	}
	
	public List<Oficina> consultarOficinaAjax(BigDecimal valor) {
		try {
			Oficina oficina = new Oficina();
			oficina.setEstado(Constant.ESTADO_ACTIVO);
			oficina.setCodTerritorio(valor);
			List<Oficina> getLstOficina = catalogoService.getLstOficinaByCriteria(oficina);
			return getLstOficina;
		    } catch (Exception e) 
		    {
			System.out.print(""+e.getMessage());
		    	return new ArrayList<Oficina>();
		}
	}
	
	public List<UsuarioOficina> consultarOficinaAsignadaAjax(String user) {
		try {
			Usuario usuario= new Usuario();
			usuario.setEstado("1");
			usuario.setCodigoUsuario(user);
			List<UsuarioOficina> getLstOficinaAsignada=catalogoService.getLstOficinaAsignada(usuario);
			return getLstOficinaAsignada;
		    } catch (Exception e){
			System.out.print(""+e.getMessage());
		    return new ArrayList<UsuarioOficina>();
		}
	}
	
	public List<Territorio> consultarTerritorioAjax() {
		try {
			Territorio territorio = new Territorio();
			territorio.setEstado(Constant.ESTADO_ACTIVO);
			List<Territorio> getLstOficinaAsignada=catalogoService.getLstTerritorioByCriteria(territorio);
			return getLstOficinaAsignada;
		    } catch (Exception e){
		    logger.error("AsignarOficinaAction.listarTerritorioOficina", e);
		    return new ArrayList<Territorio>();
		}
	}
	
	public String saveAsignarOficinaAjax(String codOficina, String codigoUsuario){
		try {
			UsuarioOficina oficinaAsignada= new UsuarioOficina();
			oficinaAsignada.setCod_oficina(codOficina);
			oficinaAsignada.setCod_usuario(codigoUsuario);
			oficinaAsignada.setEstado(Constant.ESTADO_ACTIVO);
			String tipo=(catalogoService.getUsuarioTipo(codigoUsuario)!=null?catalogoService.getUsuarioTipo(codigoUsuario):"");
			if(tipo.equals(Constant.USUARIO_OFICINA)){
			return "Oficina";				
			}else{
				String asignacion=catalogoService.getOficinaAsignadaExiste(oficinaAsignada);
				if(!asignacion.equals("1")){
				 catalogoService.saveOficinaAsignada(oficinaAsignada);						
				 }
				return "No existe";
			}
		} catch (Exception e) {
			logger.error("AsignarOficinaAction.listarTerritorioOficina", e);
		   return e.getMessage();
		}
	}
	
	public String eliminarOficinaAsignadaAjax(String codOficina){
		try {
			StringTokenizer st = new StringTokenizer(codOficina, "**");
			String concatIds = "";
			while (st.hasMoreTokens()) {
				String elemento = st.nextToken();
				StringTokenizer stComas = new StringTokenizer(elemento, "-");
				while (stComas.hasMoreTokens()) {
					stComas.nextToken();
					if(stComas.hasMoreTokens()){
						String id = stComas.nextToken();
						concatIds = concatIds + ","+id+",";
					}
				}					
			}
			concatIds = concatIds.substring(0, concatIds.length()-1);
			catalogoService.deleteOficinaAsignada(concatIds);
			return "Eliminado";
		} catch (Exception e) {
			logger.error("AsignarOficinaAction.listarTerritorioOficina", e);
		   return e.getLocalizedMessage();
		}
	}	
}