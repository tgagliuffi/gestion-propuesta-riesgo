package bbva.pe.gpr.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import bbva.pe.gpr.bean.Analisis;
import bbva.pe.gpr.bean.Asignacion;
import bbva.pe.gpr.bean.Banca;
import bbva.pe.gpr.bean.Delegacion;
import bbva.pe.gpr.bean.Dictamen;
import bbva.pe.gpr.bean.Funcion;
import bbva.pe.gpr.bean.MultitablaDetalle;
import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.bean.SolicitudDetalle;
import bbva.pe.gpr.context.Context;
import bbva.pe.gpr.service.AnalisiService;
import bbva.pe.gpr.service.CatalogoService;
import bbva.pe.gpr.service.ControlService;
import bbva.pe.gpr.service.DictaminarService;
import bbva.pe.gpr.service.SeguridadService;
import bbva.pe.gpr.service.SolicitudService;
import bbva.pe.gpr.serviceImpl.AplicativoPersonasServiceImpl;
import bbva.pe.gpr.serviceImpl.AplicativoRCCServiceImpl;
import bbva.pe.gpr.serviceImpl.AplicativoRCDServiceImpl;
import bbva.pe.gpr.util.Constant;

import com.grupobbva.bc.per.tele.ldap.serializable.IILDPeUsuario;

public class DictamenAction extends DispatchAction {

	private static Logger logger = Logger.getLogger(DictamenAction.class);
	private static SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
	private AnalisiService analisisService;
	private SolicitudService solicitudService;
	private ControlService controlService;
	private CatalogoService catalogoService;
	private DictaminarService dictaminarService;
	private SeguridadService seguridadService;
	private AplicativoPersonasServiceImpl appPersonasService;
	private AplicativoRCDServiceImpl appRCDService;
	private AplicativoRCCServiceImpl appRCCService;

	public DictamenAction() {
		analisisService = (AnalisiService) Context.getInstance().getBean("analisisService");
		controlService = (ControlService) Context.getInstance().getBean("controlService");
		dictaminarService = (DictaminarService) Context.getInstance().getBean("dictaminarService");
		solicitudService = (SolicitudService) Context.getInstance().getBean("solicitudService");
		catalogoService = (CatalogoService) Context.getInstance().getBean("catalogoService");
		seguridadService = (SeguridadService) Context.getInstance().getBean("seguridadService");
		
		appPersonasService = new AplicativoPersonasServiceImpl();
		appRCDService = new AplicativoRCDServiceImpl();
		appRCCService = new AplicativoRCCServiceImpl();
	}

	public ActionForward index(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String imagen = "rojo";
		IILDPeUsuario jefe;
		IILDPeUsuario usuario = (IILDPeUsuario) request.getSession().getAttribute("USUARIO_SESION");
		if (usuario != null) {
			request.setAttribute("id_usuario", usuario.getUID());
			request.setAttribute("nombre_usuario", usuario.getApellido1() + Constant.ESPACIO + usuario.getApellido2() + Constant.ESPACIO + usuario.getNombre());
			try {
				Funcion nivel = seguridadService.getNivelDictamen(usuario.getUID());
				
				if(nivel != null) {
					if(Constant.EVALUADOR.compareTo(nivel.getCodFuncion()) != 0) {
						imagen = "verde";
					}
					
					jefe = com.grupobbva.bc.per.tele.ldap.directorio.IILDPeUsuario.recuperarUsuario(usuario.getUIDJefe());
					
					request.setAttribute("imagen", imagen);
					request.setAttribute("id_jefe", jefe.getUID());
					request.setAttribute("nombre_jefe", jefe.getApellido1() + Constant.ESPACIO + jefe.getApellido2() + Constant.ESPACIO + jefe.getNombre());
					
					Delegacion delegacion = controlService.getDelegacion(usuario.getUID());
					request.setAttribute("monto_delegacion", delegacion.getMontoMaximo());
					request.setAttribute("nroSolicitud", request.getParameter("nroSolicitud"));
					request.setAttribute("codAsignacion", request.getParameter("codAsignacion"));
				} else {
					request.setAttribute("error", "No tiene el perfil de evaluador o dictaminador.");
					request.setAttribute("monto_delegacion", "-1");
				}
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

		return mapping.findForward("atender");
	}

	public Map<String, Object> buscarSolicitud(Asignacion uid) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Solicitud> listaSolicitud = null;
		List<SolicitudDetalle> listSolicitudDetalle = null;
		List<Analisis> listAnalisis = null;
		Solicitud s = null;
		Dictamen d = null;
		Analisis a = null;
		
		if (uid != null && uid.getNroSolicitud() != null) {
			s = new Solicitud();
			s.setNroSolicitud(uid.getNroSolicitud());
			try {
				listaSolicitud = solicitudService.getLstSolicitudes(s);
				if (listaSolicitud != null && listaSolicitud.size() > 0) {					
					s = listaSolicitud.get(0);
					
					// TODO: Solo para test
					s = appPersonasService.invokeClient(s.getCodCentral());
					s = appPersonasService.invokePE7CRUCE(s);
					s = appPersonasService.invokeGerenciaTerritorial(s);
					s = appPersonasService.invokeRelevancia(s);
					s = appPersonasService.invokeClasificacionCliente(s);
					s.setNroSolicitud(uid.getNroSolicitud());
					// TODO: Solo para test
					
					if(s.getCodMultTipoPersona().equals(Constant.PERSONA_NATURAL)){
						s = appPersonasService.invokeScorating(s);
					}else{
						s = appPersonasService.invokeRating(s);
					}
					
					s = appRCCService.invokeDeudaSisFinanciero(s);
					s = appRCDService.invokeDedudasRCD(s);
					listSolicitudDetalle = solicitudService.getListSolicitudDetalleForId(s);
					
					d = new Dictamen();
					d.setNroSolicitud(s.getNroSolicitud());
					d = dictaminarService.findForNroSolictud(d);
					
					a = new Analisis();
					a.setNroSolicitud(s.getNroSolicitud());
					listAnalisis = analisisService.buscarAnalisis(a);
					
					map.put("solicitud", s);
					map.put("solicitudDetalle", listSolicitudDetalle);
					map.put("dictamen", d);
					map.put("analisis", listAnalisis);
					map.put("hoy", formater.format(new Date()));
					map.put("status", true);
				} else {
					map.put("status", false);
					map.put("error", "No se recupero ninguna solicitud con los parametros ingresados.");	
				}
			} catch (Exception e) {
				logger.error("", e);
				map.put("status", false);
				map.put("error", e.getMessage());
			}
		} else {
			map.put("status", false);
			map.put("error", "Parametros invalidos.");
		}

		return map;
	}

	public Map<String, Object> buscarAnalisis() {
		Map<String, Object> map = new HashMap<String, Object>();

		return map;		
	}
	
	public Map<String, Object> agregarAnalisis(Analisis row) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Analisis> listAnalisis = null;
		try {
			Long result = analisisService.insertarAnalisis(row);
			if(result != null) {
				listAnalisis = analisisService.buscarAnalisis(row);
				
				map.put("status", true);
				if(result == -1) {
					map.put("error", "Este proceso ya fue registrado.");
				} else {
					map.put("error", "Proceso registrado correctamente.");
				}
				map.put("analisis", listAnalisis);
			}
		} catch (Exception e) {
			logger.error("", e);
			map.put("status", false);
			map.put("error", e.getMessage());
		}
		return map;
	}

	public Map<String, Object> eliminarAnalisis(Analisis row) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Analisis> listAnalisis = null;
		try {
			if(analisisService.eliminarAnalisis(row) != null) {
				listAnalisis = analisisService.buscarAnalisis(row);
				
				map.put("status", true);
				map.put("error", "Proceso eliminado correctamente.");
				map.put("analisis", listAnalisis);
			}
		} catch (Exception e) {
			logger.error("", e);
			map.put("status", false);
			map.put("error", e.getMessage());
		}
		return map;
	}

	public Map<String, Object> dictaminarSuperior(Dictamen row) {
		Map<String, Object> map = new HashMap<String, Object>();

		return map;
	}

	public Map<String, Object> dictaminar(Dictamen row) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(dictaminarService.dictaminarSolicitud(row) != null) {
				map.put("status", true);
				map.put("error", "Dictamen registrado correctamente.");
			}
		} catch (Exception e) {
			logger.error("", e);
			map.put("status", false);
			map.put("error", e.getMessage());
		}
		return map;
	}
	
	private List<MultitablaDetalle> cargarCombo(String tabla) {
		try {
			return catalogoService.getLstMultitablaDetalle(tabla);
		} catch (Exception e) {
			logger.error("Exception DictamenAction.cargarComboProceso: ", e);
		} 
		return new ArrayList<MultitablaDetalle>();
	}
	
	public List<MultitablaDetalle> cargarComboProceso(){
		return cargarCombo(Constant.TABLA_PROCESO);
	}
	
	public List<MultitablaDetalle> cargarComboMotivo(){
		return cargarCombo(Constant.TABLA_MOTIVO);
	}
	
	public List<MultitablaDetalle> cargarComboDictamen(){
		return cargarCombo(Constant.TABLA_DICTAMEN);
	}

	public List<MultitablaDetalle> cargarComboNivelAprobacion(){
		return cargarCombo(Constant.TABLA_NIVEL_APROBACION);
	}
	
	public List<MultitablaDetalle> cargarComboProactividad(){
		return cargarCombo(Constant.TABLA_PROACTIVIDAD);
	}
	
	public List<MultitablaDetalle> cargarComboMoneda(){
		return cargarCombo(Constant.TABLA_MONEDA);
	}
	
	public List<Banca> cargarComboBanca() {
		Banca bancaBean = new Banca();
		bancaBean.setEstado(BigDecimal.ONE);
		try {
			return catalogoService.getLstBancaByCriteria(bancaBean);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}
}