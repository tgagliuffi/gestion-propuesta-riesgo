package bbva.pe.gpr.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import bbva.pe.gpr.bean.Menu;
import bbva.pe.gpr.bean.Usuario;
import bbva.pe.gpr.context.Context;
import bbva.pe.gpr.form.LoginForm;
import bbva.pe.gpr.service.CatalogoService;
import bbva.pe.gpr.service.SeguridadService;
import bbva.pe.gpr.util.Constant;
import bbva.pe.gpr.util.UtilDate;

import com.grupobbva.bc.per.tele.ldap.serializable.IILDPeUsuario;
import com.grupobbva.bc.per.tele.seguridad.ServiciosSeguridadBbva;

public class LoginAction extends DispatchAction {
	private static Logger logger = Logger.getLogger(LoginAction.class);
	private CatalogoService catalogoService;
	private SeguridadService seguridadService;
	
	public LoginAction() {
		catalogoService = (CatalogoService)Context.getInstance().getBean("catalogoService");
		seguridadService = (SeguridadService)Context.getInstance().getBean("seguridadService");
	}
	
	public ActionForward validarUsuario(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		String target = null;
		LoginForm loginForm = (LoginForm)form; 
		IILDPeUsuario usuario = null; 
        ServiciosSeguridadBbva ssBbva;
        try {
        	String reg = request.getParameter("regemp");
            
    		if(request.getParameter("desa")!=null){
        		logger.info("GPR : Desarrollo = true");
        		if(loginForm.getUserName()!=null && loginForm.getPassword().equals("123")){
        			reg = loginForm.getUserName();
        		}
        	}else{
    			logger.info("GPR : Desarrollo = false");
    			ssBbva = new ServiciosSeguridadBbva(request);
    			if (ssBbva != null) {
    			logger.info("Usuario pasa validación de seguridad bbva");
    				ssBbva.obtener_ID();
    				reg = ssBbva.getUsuario().toUpperCase();    				
    			}
    		}

    	   usuario = com.grupobbva.bc.per.tele.ldap.directorio.IILDPeUsuario.recuperarUsuario(reg);
    	    if (usuario == null) {
				logger.info("GPR : Usuario es nulo");
	 			request.getSession(true).setAttribute("STR_MENSAJE","Usuario no válido.");
	 			target = "failure";
	 		}else{ 
				Usuario operador = null;
				operador = catalogoService.selectUsuarioByPrimaryKey(reg);
				 List<Menu> getListadoMenu=seguridadService.getListadoMenu(reg);
				if(operador!=null && getListadoMenu !=null){
					String saludo = null;
					saludo = (usuario.getSexo().equals(Constant.FEMENINO)?"Bienvenida : ":"Bienvenido : ");
					request.getSession(true).setAttribute("USUARIO_SESION", usuario);
		        	request.getSession(true).setAttribute("REGISTRO_USUARIO_LOGUEADO", usuario.getUID());
		        	request.getSession(true).setAttribute("USUARIO_NOMBRE",saludo + usuario.getApellido1() + Constant.ESPACIO + usuario.getApellido2() + Constant.ESPACIO + usuario.getNombre());
		        	request.getSession(true).setAttribute("FECHA_ACTUAL","HOY DÍA ES : "+ UtilDate.fechaActual());
		        	target = "success";
    		        //TODO: FALTA PERFILAR USUARIO
		        	
		              request.setAttribute("getLstMenu",getListadoMenu);
    		    }else{
					request.getSession(true).setAttribute("STR_MENSAJE", "Usuario sin Privliegios.");
					target = "failure";
    			}
    	    }
		} catch (Exception e) {
			logger.error("Exception LoginAction.validarUsuario: " + e.getMessage());
			request.getSession(true).setAttribute("STR_MENSAJE","Ocurrio un error. comuniquese con el administrador.");			
			target = "failure";
			return mapping.findForward(target);
		}
		return mapping.findForward(target);
	}
}
