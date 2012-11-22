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
import org.directwebremoting.WebContextFactory;

import bbva.pe.gpr.bean.Analisis;
import bbva.pe.gpr.bean.Asignacion;
import bbva.pe.gpr.bean.Banca;
import bbva.pe.gpr.bean.Dictamen;
import bbva.pe.gpr.bean.Funcion;
import bbva.pe.gpr.bean.MultitablaDetalle;
import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.bean.SolicitudDetalle;
import bbva.pe.gpr.bean.SolicitudOperacion;
import bbva.pe.gpr.context.Context;
import bbva.pe.gpr.service.AnalisiService;
import bbva.pe.gpr.service.AsignacionService;
import bbva.pe.gpr.service.CatalogoService;
import bbva.pe.gpr.service.ControlService;
import bbva.pe.gpr.service.DictaminarService;
import bbva.pe.gpr.service.SeguridadService;
import bbva.pe.gpr.service.SolicitudService;
import bbva.pe.gpr.service.ValidacionService;
import bbva.pe.gpr.serviceImpl.AplicativoPersonasServiceImpl;
import bbva.pe.gpr.serviceImpl.AplicativoRCCServiceImpl;
import bbva.pe.gpr.serviceImpl.AplicativoRCDServiceImpl;
import bbva.pe.gpr.util.Constant;

import com.grupobbva.bc.per.tele.ldap.serializable.IILDPeUsuario;

public class DictamenAction extends DispatchAction {

	private static Logger logger = Logger.getLogger(DictamenAction.class);
	private static SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
	private AnalisiService analisisService;
	private AsignacionService asignacionService;
	private SolicitudService solicitudService;
	private ControlService controlService;
	private CatalogoService catalogoService;
	private DictaminarService dictaminarService;
	private SeguridadService seguridadService;
	private AplicativoPersonasServiceImpl appPersonasService;
	private AplicativoRCDServiceImpl appRCDService;
	private AplicativoRCCServiceImpl appRCCService;
	private ValidacionService validacionService;

	public DictamenAction() {
		asignacionService = (AsignacionService) Context.getInstance().getBean("asignacionService");
		analisisService = (AnalisiService) Context.getInstance().getBean("analisisService");
		controlService = (ControlService) Context.getInstance().getBean("controlService");
		dictaminarService = (DictaminarService) Context.getInstance().getBean("dictaminarService");
		solicitudService = (SolicitudService) Context.getInstance().getBean("solicitudService");
		catalogoService = (CatalogoService) Context.getInstance().getBean("catalogoService");
		seguridadService = (SeguridadService) Context.getInstance().getBean("seguridadService");
		validacionService = (ValidacionService) Context.getInstance().getBean("validacionServiceImpl");
		
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
					
					jefe = obtenerJefeSuperior(usuario.getUID());
					
					request.setAttribute("imagen", imagen);
					
					if(jefe != null) {
						request.setAttribute("id_jefe", jefe.getUID());
						request.setAttribute("nombre_jefe", jefe.getApellido1() + Constant.ESPACIO + jefe.getApellido2() + Constant.ESPACIO + jefe.getNombre());
					}
					
					request.setAttribute("nivel", nivel.getCodFuncion());
					request.setAttribute("monto_delegacion", 0);
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

	private IILDPeUsuario obtenerJefeSuperior(String uid) {
		IILDPeUsuario jefe = null;
		String guid;
		try {
			guid = catalogoService.getJefeInmediatoOficina(uid);
			
			if(guid != null && guid.length() > 0) {
				jefe = com.grupobbva.bc.per.tele.ldap.directorio.IILDPeUsuario.recuperarUsuario(guid);
			} else {
				guid = catalogoService.getJefeInmediatoRiesgo(uid);
				if(guid != null && guid.length() > 0) {
					jefe = com.grupobbva.bc.per.tele.ldap.directorio.IILDPeUsuario.recuperarUsuario(guid);
				}
			}
		} catch(Exception e) {
			logger.error("", e);
		}
		return jefe;
	}
	
	private Solicitud obtenerSolicitud(Long nroSolicitud) {
		List<Solicitud> listaSolicitud = null;
		Solicitud s = new Solicitud();
		s.setNroSolicitud(nroSolicitud);
		try {
			listaSolicitud = solicitudService.getLstSolicitudes(s);
			if (listaSolicitud != null && listaSolicitud.size() > 0) {
				s = listaSolicitud.get(0);
				
				if(s.getCodMultTipoPersona() != null) {
					if(s.getCodMultTipoPersona().indexOf(Constant.PERSONA_NATURAL) > -1){
						s = appPersonasService.invokeScorating(s);
					}else{
						s = appPersonasService.invokeRating(s);
					}	
				}
				
				s = appRCCService.invokeDeudaSisFinanciero(s);
				s = appRCDService.invokeDedudasRCD(s);
			}
		} catch (Exception e) {
			logger.error("", e);
			s = null;
		}
		return s;
	}
	
	public Map<String, Object> buscarSolicitud(Asignacion uid) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<SolicitudDetalle> listSolicitudDetalle = null;
		List<Analisis> listAnalisis = null;
		List<SolicitudOperacion> listOperaciones = null;
		Solicitud s = null;
		Dictamen d = null;
		Analisis a = null;
		
		if (uid != null && uid.getNroSolicitud() != null) {
			try {		
				s = obtenerSolicitud(uid.getNroSolicitud());
				
				if(s != null) {
					listSolicitudDetalle = solicitudService.getListSolicitudDetalleForId(s);
					
					d = new Dictamen();
					d.setNroSolicitud(s.getNroSolicitud());
					d = dictaminarService.findForNroSolictud(d);
					
					a = new Analisis();
					a.setNroSolicitud(s.getNroSolicitud());
					listAnalisis = analisisService.buscarAnalisis(a);
					
					Solicitud m = new Solicitud();
					m.setGestorCod(uid.getCodUsuarioAsigno());
					m.setGrupoPersona(s.getGrupoPersona());
					BigDecimal montoDelegacion = dictaminarService.montoMaxDelegacion(m);
					
					listOperaciones = solicitudService.selectOperacionByNroSolicitud(s);
					
					map.put("monto_delegacion", montoDelegacion.intValue());
					map.put("solicitud", s);
					map.put("solicitudDetalle", listSolicitudDetalle);
					map.put("dictamen", d);
					map.put("analisis", listAnalisis);
					map.put("operaciones", listOperaciones);
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
	
	public Map<String, Object> agregarAnalisis(Analisis row) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Analisis> listAnalisis = null;
		List<SolicitudOperacion> listOperaciones = null;
		Solicitud s = null;
		try {
			Long result = analisisService.insertarAnalisis(row);
			if(result != null) {
				listAnalisis = analisisService.buscarAnalisis(row);
				
				s = new Solicitud();
				s.setNroSolicitud(row.getNroSolicitud());
				listOperaciones = solicitudService.selectOperacionByNroSolicitud(s);
				
				map.put("status", true);
				if(result == -1) {
					map.put("error", "Este proceso ya fue registrado.");
				} else {
					map.put("error", "Proceso registrado correctamente.");
				}
				map.put("analisis", listAnalisis);
				map.put("operaciones", listOperaciones);
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
		List<SolicitudOperacion> listOperaciones = null;
		Solicitud s = null;
		try {
			if(analisisService.eliminarAnalisis(row) != null) {
				listAnalisis = analisisService.buscarAnalisis(row);
				
				s = new Solicitud();
				s.setNroSolicitud(row.getNroSolicitud());
				listOperaciones = solicitudService.selectOperacionByNroSolicitud(s);
				
				map.put("status", true);
				map.put("error", "Proceso eliminado correctamente.");
				map.put("analisis", listAnalisis);
				map.put("operaciones", listOperaciones);
			}
		} catch (Exception e) {
			logger.error("", e);
			map.put("status", false);
			map.put("error", e.getMessage());
		}
		return map;
	}

	private IILDPeUsuario responsableSuperior(Solicitud s) {
		IILDPeUsuario jefe = null;
		int i;
		for(i = 0; i < 10; i++) {
			if(dictaminarService.montoMaxDelegacion(s).compareTo(s.getRiesgoTotal()) == 1) {
				break;
			}
			jefe = obtenerJefeSuperior(s.getGestorCod());
			if(jefe != null) {
				s.setGestorCod(jefe.getUID());
			}
		}
		
		return jefe;
	}
	
	public Map<String, Object> dictaminarSuperior(Dictamen row, Solicitud s) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			map = dictaminar(row, 1);
			
			if(((Long) map.get("type")).longValue() > 0) {
				String uid = s.getGestorCod();
				IILDPeUsuario jefe = responsableSuperior(s);
				if(asignacionService.asignarSolicitudMasiva("," + s.getNroSolicitud(), jefe.getUID(), uid)<=0){
					map.put("status", false);
					map.put("type", -1);
					map.put("error", "No se pudo completar la asignaci\u00F3n al responsable superior.");
				}
			}
			
		} catch (Exception e) {
			logger.error("", e);
			map.put("status", false);
			map.put("type", -1);
			map.put("error", e.getMessage());
		}
		
		return map;
	}

	public Map<String, Object> dictaminar(Dictamen row, int superior) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<SolicitudDetalle> sd = null;
		List<SolicitudOperacion> listOperaciones = null;
		
		Solicitud s;
		String condicion;
		String plazo;
		try {
			s = obtenerSolicitud(row.getNroSolicitud());
			if(solicitudService.updateDictaminaEnOficina(s) != 0) {
				if(s != null) {
					sd = solicitudService.getListSolicitudDetalleForId(s);
					
					if(controlService.condicionCliente(s) != 1) {
						condicion = controlService.mensajeCondicionCliente(s);
						map.put("warn", condicion);
					}
					
					if(sd != null && sd.size() > 0) {
						HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
						IILDPeUsuario usuario = (IILDPeUsuario) request.getSession().getAttribute("USUARIO_SESION");
						
						if(usuario != null && usuario.getUID() != null) {
							if(validacionService.metodoEncapsulado(s, usuario.getUID()) != 1 || superior == 0) {
								plazo = "El cliente no cumple con la validaci\u00F3n de montos y plazos. Ud. no pude dictaminar esta solicitud, \u00BF Desea enviarlo para su dictamen a un superior \u003F";
								map.put("status", false);
								map.put("type", -2);
								map.put("error", plazo);
								return map;
							}
						} else {
							map.put("status", false);
							map.put("type", -1);
							map.put("error", "No se pudo obtener el Id del usuario.");
							return map;
						}
					}
				}
				
				dictaminarService.delete(row);
				
				if(dictaminarService.dictaminarSolicitud(row) != null) {
					
					listOperaciones = solicitudService.selectOperacionByNroSolicitud(s);
					
					map.put("status", true);
					map.put("type", 1);
					map.put("error", "Dictamen registrado correctamente.");
					map.put("operaciones", listOperaciones);
				}	
			} else  {
				map.put("status", false);
				map.put("type", -1);
				map.put("error", "No se pudo actualizar la solicitud.");
			}
		} catch (Exception e) {
			logger.error("", e);
			map.put("status", false);
			map.put("type", -1);
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
	
	public List<MultitablaDetalle> cargarComboCondicionesScoring(){
		return cargarCombo(Constant.TABLA_SCORING);
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
	
	public List<SolicitudOperacion> listarOperaciones(Solicitud s) {
		return solicitudService.selectOperacionByNroSolicitud(s);
	}
}