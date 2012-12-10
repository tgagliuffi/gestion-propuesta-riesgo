package bbva.pe.gpr.serviceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

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
import bbva.pe.gpr.util.UtilGpr;

import com.grupobbva.bc.per.tele.ldap.comunes.IILDPeExcepcion;
import com.grupobbva.bc.per.tele.ldap.serializable.IILDPeUsuario;


public class SolicitudServiceImpl implements SolicitudService{

	private static Logger logger = Logger.getLogger(SolicitudServiceImpl.class);
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
	public String validaGrupoPersona(String tipoPersona, String numDocumento, String rating){
		String rpta=Constant.STR_VACIO;
		if(tipoPersona.equals(Constant.TABLA_NATURALEZA+Constant.CHAR_GUION+Constant.PERSONA_JURIDICA)){
			if(rating!=null && rating.equals(Constant.STR_VACIO)){
				rpta = Constant.GRUPO_CON_RATING;
			}else{
				rpta = Constant.GRUPO_SIN_RATING;
			}
			
		}
		if(tipoPersona.equals(Constant.TABLA_NATURALEZA+Constant.CHAR_GUION+Constant.PERSONA_NATURAL)){
			if(numDocumento.length()==8){
				rpta = Constant.GRUPO_PER_NATUAL;
			}
			if(numDocumento.length()==11){
				if(rating!=null && rating.equals(Constant.STR_VACIO)){
					rpta = Constant.GRUPO_CON_RATING;
				}else{
					rpta = Constant.GRUPO_SIN_RATING;
				}
			}
		}
		return rpta;
	}
	
	public Long registraSolicitud(Solicitud solicitudBean, 
										List<SolicitudDetalle> getLstSolicitudDetalle
										) throws Exception{
		Long nroSolicitud=new Long(0);
		//TODO: VALIDAR MTO DELEGACION DE GERENTE OFICINA
		if(getLstSolicitudDetalle.size()>0){
			solicitudBean.setPrioridad(Constant.PRIORIDAD_NORMAL);
			nroSolicitud = solicitudesDAO.insert(solicitudBean);
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
			if(solicitudBean.getStrMensaje()!=null && !solicitudBean.getStrMensaje().equals("")){
				solicitudMensajeDAO.insert(seteaMensajeBean(solicitudBean));	
			}
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
			solicitudOperacionBean.setNroSolicitud(solicitudBean.getNroSolicitud());
			solicitudOperacionBean.setEstado(new BigDecimal(1));
			solicitudOperacionBean.setCodUsuario(solicitudBean.getCodUsuarioSession());
			solicitudOperacionBean.setNomUsuario(solicitudBean.getNomUsuarioSession());
			solicitudOperacionDAO.insert(solicitudOperacionBean);
		}	
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
				}else {
					
					solicitudBean.setEstadoSolicitud(Constant.TABLA_ESTADOS_SOLCITUD+Constant.CHAR_GUION+Constant.ESTADO_SOLICITUD_ASIGNADO);
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
			asignacionBean.setCodUsuario(usuarioBean.getCodigoUsuario());
			asignacionBean.setNombre(usuarioBean.getNombres());
			asignacionBean.setDependiente("N");
			asignacionBean.setCodUsuarioAsigno("APP-GPR");
			
			asignacionDAO.insertAsignacion(asignacionBean);
			solicitudesDAO.updateByPrimaryKeySelective(solicitudBean);
			
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
	
	public int asignarPrioridadSolicitud(String nroSolicitud, 
			String value) throws Exception{
	int result = 0; 	
	Solicitud solicitudBean = new Solicitud();
	
	solicitudBean.setNroSolicitud(new Long(nroSolicitud));
		if(value.equals("1")){
			solicitudBean.setPrioridad(Constant.PRIORIDAD_ALTA);
		}else if(value.equals("2")){
			solicitudBean.setPrioridad(Constant.PRIORIDAD_NORMAL);
		}else{
			solicitudBean.setPrioridad(Constant.PRIORIDAD_BAJA);
		}
	
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
		
	public int anularSolicitud(String nroSolicitud) throws Exception{
		int result = 0; 	
		Solicitud solicitudBean = new Solicitud();
		solicitudBean.setNroSolicitud(new Long(nroSolicitud));
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
		solicitudMensajeBean.setCodUsuario(solicitudBean.getCodUsuarioSession());
		solicitudMensajeBean.setNomUsuario(solicitudBean.getNomUsuarioSession());
		return solicitudMensajeBean;
	}
	
	public List<Solicitud> getLstSolicitudes(Solicitud solicitudBean) throws Exception{
		return solicitudesDAO.getLstSolicitudes(solicitudBean);
	}
	
	public List<SolicitudDetalle> getListSolicitudDetalleForId(Solicitud idsolicitud) throws Exception {
		return solicitudDetalleDAO.getListSolicitudDetalleForId(idsolicitud);
	}
	public String changeMtoTotalRowAjax(String value, String pMtoGarantia, HttpServletRequest oRequest)throws Exception{
		BigDecimal bigValue = new BigDecimal(0);
		BigDecimal bigMtoGarantia = new BigDecimal(0);
		BigDecimal bigMtoTotalRow = new BigDecimal(0);
		if(value!=null && !value.equals(Constant.STR_VACIO)){
			bigValue = new BigDecimal(value);
		}if(pMtoGarantia!=null && !pMtoGarantia.equals(Constant.STR_VACIO)){
			bigMtoGarantia = new BigDecimal(pMtoGarantia);
		}
		bigMtoTotalRow = bigValue.add(bigMtoGarantia);
		return UtilGpr.roundUp(bigMtoTotalRow.toString(), 2);
	}
	
	
	@SuppressWarnings("unchecked")
	public String changeMtoTotalAjax(String value, HttpServletRequest oRequest)throws Exception{
		BigDecimal bigMtoTotalProd = new BigDecimal(0);
		String rpta=Constant.RESET_MONTO;
		if(oRequest.getSession().getAttribute("lstDetalleProdSession")!=null){
			List<SolicitudDetalle> lstSolicitudDetalle = (List<SolicitudDetalle>)oRequest.getSession().getAttribute("lstDetalleProdSession");
			for(int i=0; i<lstSolicitudDetalle.size(); i++){
				 SolicitudDetalle bean = lstSolicitudDetalle.get(i);
				 bigMtoTotalProd=bigMtoTotalProd.add(bean.getMtoProducto());
				
			}
		}
		bigMtoTotalProd=bigMtoTotalProd.add(new BigDecimal(value));
		rpta = UtilGpr.roundUp(bigMtoTotalProd.toString(), 2);
		
		return rpta;
	}
	public String changeRiesgoTotalAjax(String pdeudaDirecta, 
										String pdeudaIndirecta, 
										String pdeudaCastigo, 
										String pdeudaSisFinanciero, 
										String pOtroRiesgo, 
										String pRiesgoGrupal, 
										String pMtoTotalProd,
										HttpServletRequest oRequest)throws Exception{
		BigDecimal bigRiesgoTotal = new BigDecimal(0);
		if(pdeudaDirecta!=null && !pdeudaDirecta.equals(Constant.STR_VACIO)){
			bigRiesgoTotal = bigRiesgoTotal.add(new BigDecimal(pdeudaDirecta));
		}if(pdeudaIndirecta!=null && !pdeudaIndirecta.equals(Constant.STR_VACIO)){
			bigRiesgoTotal = bigRiesgoTotal.add(new BigDecimal(pdeudaIndirecta));
		}if(pdeudaCastigo!=null && !pdeudaCastigo.equals(Constant.STR_VACIO)){
			bigRiesgoTotal = bigRiesgoTotal.add(new BigDecimal(pdeudaCastigo));
		}if(pdeudaSisFinanciero!=null && ! pdeudaSisFinanciero.equals(Constant.STR_VACIO)){
			bigRiesgoTotal = bigRiesgoTotal.add(new BigDecimal(pdeudaSisFinanciero));
		}if(pOtroRiesgo!=null && ! pOtroRiesgo.equals(Constant.STR_VACIO)){
			bigRiesgoTotal = bigRiesgoTotal.add(new BigDecimal(pOtroRiesgo));
		}if(pRiesgoGrupal!=null && ! pRiesgoGrupal.equals(Constant.STR_VACIO)){
			bigRiesgoTotal = bigRiesgoTotal.add(new BigDecimal(pRiesgoGrupal));
		}if(pMtoTotalProd!=null && ! pMtoTotalProd.equals(Constant.STR_VACIO)){
			bigRiesgoTotal = bigRiesgoTotal.add(new BigDecimal(pMtoTotalProd));
		}			
		 return UtilGpr.roundUp(bigRiesgoTotal.toString(), 2);
	}		

	@SuppressWarnings("unchecked")
	public String changeOtroRiesgoAjax(String value, HttpServletRequest oRequest)throws Exception{
		BigDecimal bigOtroRiesgo = new BigDecimal(0);
		if(oRequest.getSession().getAttribute("lstDetalleProdSession")!=null){
			List<SolicitudDetalle> lstSolicitudDetalle = ((List<SolicitudDetalle>)oRequest.getSession().getAttribute("lstDetalleProdSession"));
				for(int i=0; i<lstSolicitudDetalle.size(); i++){
					 SolicitudDetalle bean = lstSolicitudDetalle.get(i);
					 bigOtroRiesgo=bigOtroRiesgo.add(bean.getMtoGarantia());
				}
			}
			if(value!=null && ! value.equals(Constant.STR_VACIO)){
				bigOtroRiesgo=bigOtroRiesgo.add(new BigDecimal(value));
			}		 
			return UtilGpr.roundUp(bigOtroRiesgo.toString(), 2);
	}

	public String  changeRiesgoActualAjax(String pdeudaDirecta, 
										  String pdeudaIndirecta, 
										  String pdeudaCastigo, 
										  String pdeudaSisFinanciero, 
										  String pOtroRiesgo, 
										  String value, 
										  HttpServletRequest oRequest)throws Exception{
		BigDecimal bigRiesgoActual = new BigDecimal(0);
		if(pdeudaDirecta!=null && !pdeudaDirecta.equals(Constant.STR_VACIO)){
			bigRiesgoActual = bigRiesgoActual.add(new BigDecimal(pdeudaDirecta));
		}if(pdeudaIndirecta!=null && !pdeudaIndirecta.equals(Constant.STR_VACIO)){
			bigRiesgoActual = bigRiesgoActual.add(new BigDecimal(pdeudaIndirecta));
		}if(pdeudaCastigo!=null && !pdeudaCastigo.equals(Constant.STR_VACIO)){
			bigRiesgoActual = bigRiesgoActual.add(new BigDecimal(pdeudaCastigo));
		}if(pdeudaSisFinanciero!=null && ! pdeudaSisFinanciero.equals(Constant.STR_VACIO)){
			bigRiesgoActual = bigRiesgoActual.add(new BigDecimal(pdeudaSisFinanciero));
		}if(pOtroRiesgo!=null && ! pOtroRiesgo.equals(Constant.STR_VACIO)){
			bigRiesgoActual = bigRiesgoActual.add(new BigDecimal(pOtroRiesgo));
		}if(value!=null && ! value.equals(Constant.STR_VACIO)){
			bigRiesgoActual = bigRiesgoActual.add(new BigDecimal(value));
		}			
		 return UtilGpr.roundUp(bigRiesgoActual.toString(), 2);
	}
	
	 public int actualizarSolicitud(Solicitud solicitudBean) throws Exception
	  {
		  return solicitudesDAO.updateByPrimaryKeySelective(solicitudBean);
	  }
	 
	 public int priorizarSolicitud(Solicitud solicitudBean) throws Exception
	  {
		  return solicitudesDAO.updateByPrimaryKeySelective(solicitudBean);
	  }
	 
	 public List<SolicitudDetalle> eliminarProducto(String arraySolitcitudes, List<SolicitudDetalle> lstSolicitudDetalle)throws Exception{
		 arraySolitcitudes = arraySolitcitudes.substring(1, arraySolitcitudes.length());
	    	String[] arregloSol = arraySolitcitudes.split(",");
	    	
	    	
	    		for(int y=0; y<lstSolicitudDetalle.size(); y++){
	    			SolicitudDetalle solicitudDetBean = new SolicitudDetalle();
	    			solicitudDetBean = lstSolicitudDetalle.get(y);
	    			for (int i=0; i< arregloSol.length; i++){
	    				if(solicitudDetBean.getIndice()==Integer.parseInt(arregloSol[i])){
		    				lstSolicitudDetalle.remove(y);
		    			}
	    		}
	    	}
	    	return lstSolicitudDetalle;
	 }
	 
	 public List<SolicitudOperacion> selectOperacionByNroSolicitud(Solicitud s) {
		 try {
			 return solicitudOperacionDAO.selectByNroSolicitud(s);
		 } catch(Exception e) {
			 logger.error("", e);
			 return new ArrayList<SolicitudOperacion>() ;
		 }
	 }
	 
	 public List<SolicitudMensaje> getListMessagesAjax(SolicitudMensaje record)throws Exception{
		 return solicitudMensajeDAO.getListMessagesAjax(record);
	 }
}
