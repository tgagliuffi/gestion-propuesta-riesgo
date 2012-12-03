package bbva.pe.gpr.action;

import java.math.BigDecimal;
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

import bbva.pe.gpr.bean.Asignacion;
import bbva.pe.gpr.bean.Delegacion;
import bbva.pe.gpr.context.Context;
import bbva.pe.gpr.service.AsignacionService;
import bbva.pe.gpr.service.ControlService;

import com.grupobbva.bc.per.tele.ldap.serializable.IILDPeUsuario;

public class BandejaEvaluacionAction extends DispatchAction {

	private static Logger logger = Logger.getLogger(BandejaEvaluacionAction.class);
	private AsignacionService asignacionService;
	private ControlService controlService;
	
	public BandejaEvaluacionAction() {
		asignacionService = (AsignacionService) Context.getInstance().getBean("asignacionService");
		controlService = (ControlService) Context.getInstance().getBean("controlService");
	}
	
	public ActionForward index(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		IILDPeUsuario usuario = (IILDPeUsuario) request.getSession().getAttribute("USUARIO_SESION");
		if(usuario != null) {
			request.setAttribute("id_usuario", usuario.getUID());
			try {
				String codCliente = request.getParameter("codCliente");
				Delegacion delegacion = controlService.getDelegacion(usuario.getUID());
				request.setAttribute("monto_delegacion", delegacion.getMontoMaximo());
				request.setAttribute("codCliente", codCliente != null ? codCliente : "");
			} catch (Exception e) {
				logger.error("", e);
				request.setAttribute("error", e.getMessage());
				request.setAttribute("monto_delegacion", "-1");
			}
		} else {
			request.setAttribute("id_usuario", "-1");
			request.setAttribute("monto_delegacion", "-1");
			request.setAttribute("error", "No se pudo obtener el identificador de usuario de la sesión.");
		}
		
		return mapping.findForward("bandeja");
	}
		
	public Map<String, Object> buscarSolicitud(Asignacion uid) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Asignacion> listAsignacion = null;
		HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
		IILDPeUsuario bean = (IILDPeUsuario)request.getSession().getAttribute("USUARIO_SESION");
		
		try {
			if(uid != null) {
				uid.setCodUsuario(bean.getUID());
				Delegacion delegacion = controlService.getDelegacion(uid.getCodUsuario());
				uid.setMtoDelegacionMax(new BigDecimal(delegacion.getMontoMaximo()));
				listAsignacion = asignacionService.getLstAsignaciones(uid);
				
				map.put("monto_delegacion", delegacion.getMontoMaximo());
				map.put("rows", listAsignacion);
				map.put("status", true);
			} else {
				map.put("monto_delegacion", "-1");
				map.put("error", "Parametros invalidos");
				map.put("status", false);
			}
		} catch (Exception e) {
			logger.error("", e);
			map.put("monto_delegacion", "-1");
			map.put("error", e.getMessage());
			map.put("status", false);
		}

		return map;
	}
}
