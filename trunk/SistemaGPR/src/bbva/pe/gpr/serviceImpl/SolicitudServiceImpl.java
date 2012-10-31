package bbva.pe.gpr.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import bbva.pe.gpr.bean.Asignacion;
import bbva.pe.gpr.bean.Banca;
import bbva.pe.gpr.bean.MultitablaDetalle;
import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.bean.SolicitudDetalle;
import bbva.pe.gpr.bean.SolicitudMensaje;
import bbva.pe.gpr.bean.SolicitudOperacion;
import bbva.pe.gpr.bean.Usuario;
import bbva.pe.gpr.dao.AsignacionDAO;
import bbva.pe.gpr.dao.BancaDAO;
import bbva.pe.gpr.dao.MultitablaDetalleDAO;
import bbva.pe.gpr.dao.SolicitudDetalleDAO;
import bbva.pe.gpr.dao.SolicitudMensajeDAO;
import bbva.pe.gpr.dao.SolicitudOperacionDAO;
import bbva.pe.gpr.dao.SolicitudesDAO;
import bbva.pe.gpr.dao.UsuarioDAO;
import bbva.pe.gpr.service.SolicitudService;
import bbva.pe.gpr.util.Constant;

import com.grupobbva.bc.per.tele.ldap.comunes.IILDPeExcepcion;
import com.grupobbva.bc.per.tele.ldap.serializable.IILDPeUsuario;


public class SolicitudServiceImpl implements SolicitudService{

	private SolicitudesDAO solicitudesDAO;
	private SolicitudDetalleDAO solicitudDetalleDAO;
	private SolicitudOperacionDAO solicitudOperacionDAO;
	private SolicitudMensajeDAO solicitudMensajeDAO;
	private UsuarioDAO usuarioDAO;
	private BancaDAO bancaDAO;
	private AsignacionDAO asignacionDAO;
	private MultitablaDetalleDAO multitablaDetalleDAO;
	
	public SolicitudServiceImpl(
			SolicitudesDAO solicitudesDAO,
			SolicitudDetalleDAO solicitudDetalleDAO,
			SolicitudOperacionDAO solicitudOperacionDAO,
			SolicitudMensajeDAO solicitudMensajeDAO,
			UsuarioDAO usuarioDAO,
			BancaDAO bancaDAO,
			AsignacionDAO asignacionDAO,
			MultitablaDetalleDAO multitablaDetalleDAO
			) {
		super();
		this.solicitudesDAO=solicitudesDAO;
		this.solicitudDetalleDAO=solicitudDetalleDAO;
		this.solicitudOperacionDAO=solicitudOperacionDAO;
		this.solicitudMensajeDAO=solicitudMensajeDAO;
		this.bancaDAO=bancaDAO;
		this.usuarioDAO=usuarioDAO;
		this.asignacionDAO=asignacionDAO;
		this.multitablaDetalleDAO=multitablaDetalleDAO;
		// TODO Auto-generated constructor stub
	}
	
	public Long registraSolicitud(Solicitud solicitudBean, 
										List<SolicitudDetalle> getLstSolicitudDetalle
										) throws Exception{
		
		//TODO: VALIDAR MTO DELEGACION DE GERENTE OFICINA
		
		Long nroSolicitud = solicitudesDAO.insert(solicitudBean);
		//TODO: INSERTANDO CABECERA DE SOLICITUD

		if(nroSolicitud !=null){
			
			//TODO: INSERTANDO DETALLE DE SOLICITUD
			for(int i=0; i < getLstSolicitudDetalle.size(); i++){
				SolicitudDetalle solicitudDetalleBean =  getLstSolicitudDetalle.get(i);
				//TODO: SETEANDO NROSOLICITUD
				solicitudDetalleBean.setNroSolicitud(nroSolicitud);
				solicitudDetalleDAO.insert(solicitudDetalleBean);
			}
			ingresaSolicitudOperacion(solicitudBean, Constant.TABLA_PROCESO, Constant.MULT_PROCESO_INGRESO);
			
			//TODO INSERTANDO MENSAJE
			if(solicitudBean.getStrMensaje()!=null){
				solicitudMensajeDAO.insert(seteaMensajeBean(solicitudBean));	
			}
			
		}
		
		return nroSolicitud;
		
	}
	
	public void ingresaSolicitudOperacion(Solicitud solicitudBean, String codTabla, String codProceso) throws Exception{
		MultitablaDetalle multDetalleBean = multitablaDetalleDAO.selectByPrimaryKey(codTabla, codProceso) ;
		
		SolicitudOperacion solicitudOperacionBean = new SolicitudOperacion();
		//TODO: INSERTANDO OPERACION
		solicitudOperacionBean.setCodCentral(solicitudBean.getCodCentral());
		if(multDetalleBean != null){
			solicitudOperacionBean.setCodMultOperacion(multDetalleBean.getStrValor());
			solicitudOperacionBean.setDesOperacion(multDetalleBean.getStrValor2());				
		}
		
		solicitudOperacionBean.setNroSolicitud(solicitudBean.getNroSolicitud());
		solicitudOperacionBean.setEstado(new BigDecimal(1));
		solicitudOperacionDAO.insert(solicitudOperacionBean);
	}
	public int updateDictaminaEnOficina(Solicitud solicitudBean){
		solicitudBean.setLugarDictamina(Constant.DICTAMINA_OFICINA);
		if(solicitudesDAO.updateByPrimaryKeySelective(solicitudBean)>0){
			return 1;
		}
		return 0;
	}
	public int asignacionAutomatica(Solicitud solicitudBean) throws Exception {
		Banca bancaBean;
		bancaBean = bancaDAO.selectByPrimaryKey(solicitudBean.getCodBanca());
		if(bancaBean != null){
			Usuario usuarioBean;
			String codUsuario;
			Asignacion asignacionBean = new Asignacion();
			if(bancaBean.getFlagAsignacion().compareTo(new BigDecimal(1))==0){
				//retorn usuario con menor carga
				
				codUsuario = asignacionDAO.obtenerUsuarioPorBalance(solicitudBean.getCodBanca(), new BigDecimal(1));
				if(codUsuario.equals("0")){
					return -1;
				}
				usuarioBean = usuarioDAO.selectByPrimaryKey(codUsuario);			
			}else{
				//TODO: Asigna al reponsable de la banca
				codUsuario = asignacionDAO.obtenerUsuarioPorBalance(solicitudBean.getCodBanca(), bancaBean.getCodRol());
				if(codUsuario.equals("0")){
					return -1;
				}
				usuarioBean = usuarioDAO.selectByPrimaryKey(codUsuario);
			}
			
			asignacionBean.setCodCentral(solicitudBean.getCodCentral());
			asignacionBean.setNroSolicitud(solicitudBean.getNroSolicitud());
			asignacionBean.setCodUsuario(usuarioBean.getCodUsuario());
			asignacionBean.setNombre(usuarioBean.getNombre() + " " + usuarioBean.getApellidoPaterno() + " " + usuarioBean.getApellidoMaterno());
			asignacionBean.setDependiente("N");
			asignacionBean.setCodUsuarioAsigno("APP-GPR");
			
			asignacionDAO.insert(asignacionBean);
			
			return 1;
		}
		
		return 0;
	}
	
	public List<Solicitud> getLstSolicitudBandeja(Solicitud solicitudBean){
		return solicitudesDAO.getLstSolicitudBandeja(solicitudBean);
	}
	
	public Solicitud obtenerDatosOficina(String codUsuario, Solicitud solicitudBean){
		//TODO: obteber datos según codCentral	
		IILDPeUsuario usuarioLdap;
		try {
			usuarioLdap = com.grupobbva.bc.per.tele.ldap.directorio.IILDPeUsuario.recuperarUsuario(codUsuario);
			solicitudBean.setGestorCod(usuarioLdap.getUID());
			solicitudBean.setGestorNom(usuarioLdap.getApellido1() + Constant.ESPACIO + usuarioLdap.getApellido2() +
									   Constant.ESPACIO + usuarioLdap.getNombre());
			
			solicitudBean.setOficinaAltaCod(usuarioLdap.getBancoOficina().getCodigo());
			solicitudBean.setOficinaAltaNom(usuarioLdap.getBancoOficina().getDescripcion());
		} catch (IILDPeExcepcion e) {
			e.printStackTrace();
		}
	
		return solicitudBean;
	}
	
	public int asignarPrioridadSolicitud(Solicitud solicitudBean, 
							   int tipoUpdate) throws Exception{
	int result = 0; 	
		if(solicitudesDAO.updateByPrimaryKeySelective(solicitudBean)>0){
			//TODO: INSERTANDO OPERACION
			ingresaSolicitudOperacion(solicitudBean, Constant.TABLA_PROCESO, Constant.MULT_PROCESO_PRIORIZAR);
			//TODO INSERTANDO MENSAJE
			if(solicitudBean.getStrMensaje()!=null){
				solicitudMensajeDAO.insert(seteaMensajeBean(solicitudBean));	
			}
			result = 1;
		}
		return result;
	}
	
	
	public int anularSolicitud(Solicitud solicitudBean) throws Exception{
		int result = 0; 	
		solicitudBean.setEstado(Constant.ESTADO_INACTIVO);
		if(solicitudesDAO.updateByPrimaryKeySelective(solicitudBean)>0){
			//TODO: INSERTANDO OPERACION
			ingresaSolicitudOperacion(solicitudBean, Constant.TABLA_PROCESO, Constant.MULT_PROCESO_ANULAR);
			//TODO INSERTANDO MENSAJE
			if(solicitudBean.getStrMensaje()!=null){
				solicitudMensajeDAO.insert(seteaMensajeBean(solicitudBean));	
			}
			
			result = 1;
		}
		return result;
		}
	
	public SolicitudMensaje seteaMensajeBean(Solicitud solicitudBean){
		SolicitudMensaje solicitudMensajeBean = new SolicitudMensaje();
		solicitudMensajeBean.setCodCentral(solicitudBean.getCodCentral());
		solicitudMensajeBean.setCodMensaje(new BigDecimal(1));
		solicitudMensajeBean.setDesMensaje(solicitudBean.getStrMensaje());
		solicitudMensajeBean.setEstado( new BigDecimal(1));
		solicitudMensajeBean.setNroSolicitud(solicitudBean.getNroSolicitud());
		return solicitudMensajeBean;
	}
	
	public List<Solicitud> getLstSolicitudes(Solicitud solicitudBean) throws Exception{
		return solicitudesDAO.getLstSolicitudes(solicitudBean);
	}
	

}
