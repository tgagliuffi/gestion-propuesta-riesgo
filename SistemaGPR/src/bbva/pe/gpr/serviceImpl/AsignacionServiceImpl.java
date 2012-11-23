package bbva.pe.gpr.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import bbva.pe.gpr.bean.Asignacion;
import bbva.pe.gpr.bean.MultitablaDetalle;
import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.bean.SolicitudMensaje;
import bbva.pe.gpr.bean.SolicitudOperacion;
import bbva.pe.gpr.bean.Usuario;
import bbva.pe.gpr.dao.AsignacionDAO;
import bbva.pe.gpr.dao.MultitablaDetalleDAO;
import bbva.pe.gpr.dao.SolicitudMensajeDAO;
import bbva.pe.gpr.dao.SolicitudOperacionDAO;
import bbva.pe.gpr.dao.SolicitudesDAO;
import bbva.pe.gpr.dao.UsuarioDAO;
import bbva.pe.gpr.service.AsignacionService;
import bbva.pe.gpr.util.Constant;


public class AsignacionServiceImpl  implements AsignacionService {
	private AsignacionDAO asignacionDAO;
	private SolicitudOperacionDAO solicitudOperacionDAO;
	private SolicitudMensajeDAO solicitudMensajeDAO;
	private MultitablaDetalleDAO multitablaDetalleDAO;
	private SolicitudesDAO solicitudesDAO;
	private UsuarioDAO usuarioDAO;
 
	public AsignacionServiceImpl(AsignacionDAO asignacionDAO,
			SolicitudOperacionDAO solicitudOperacionDAO,
			SolicitudMensajeDAO solicitudMensajeDAO,
			MultitablaDetalleDAO multitablaDetalleDAO,
			SolicitudesDAO solicitudDAO,
			UsuarioDAO usuarioDAO){
		super();
		this.asignacionDAO=asignacionDAO;
		this.solicitudOperacionDAO=solicitudOperacionDAO;
		this.solicitudMensajeDAO=solicitudMensajeDAO;
		this.multitablaDetalleDAO=multitablaDetalleDAO;
		this.solicitudesDAO=solicitudDAO;
		this.usuarioDAO=usuarioDAO;
	}
	
	
	public AsignacionDAO getAsignacionDAO(){return asignacionDAO;}
	public void setAsignacionDAO(AsignacionDAO asignacionDAO){this.asignacionDAO = asignacionDAO;}
	
	public SolicitudOperacionDAO getSolicitudOperacionDAO(){return solicitudOperacionDAO;}
	public void setSolicitudOperacionDAO(SolicitudOperacionDAO solicitudOperacionDAO) {this.solicitudOperacionDAO = solicitudOperacionDAO;}
	public SolicitudMensajeDAO getSolicitudMensajeDAO(){return solicitudMensajeDAO;}
	public void setSolicitudMensajeDAO(SolicitudMensajeDAO solicitudMensajeDAO) {this.solicitudMensajeDAO = solicitudMensajeDAO;}
	public MultitablaDetalleDAO getMultitablaDetalleDAO(){return multitablaDetalleDAO;}
	public void setMultitablaDetalleDAO(MultitablaDetalleDAO multitablaDetalleDAO){this.multitablaDetalleDAO = multitablaDetalleDAO;}
	
	

    public int asignarSolicitud(Asignacion asignacionBean)throws Exception{
    	int result = 0; 
    	if(asignacionDAO.insert(asignacionBean)!=null){
    		ingresaSolicitudOperacion(asignacionBean, Constant.TABLA_PROCESO, Constant.MULT_PROCESO_ASIGNAR);
    		if(asignacionBean.getStrMensaje()!=null){
    			solicitudMensajeDAO.insert(seteaMensajeBean(asignacionBean));
    		}
    		result = 1;
    	}
    	return result;
    }
    
    public int asignarSolicitudMasiva(String arraySolitcitudes, String registro, String codUsuarioAsigno)throws Exception{
    	int result = 0; 
    	Asignacion asignacionBean = null;
    	Solicitud solicitudBean = null;
    	arraySolitcitudes = arraySolitcitudes.substring(1, arraySolitcitudes.length());
    	String[] arregloSol = arraySolitcitudes.split(",");
    	
    	for (int i=0; i< arregloSol.length; i++){
    		solicitudBean = new Solicitud();
    		solicitudBean.setAsignacionBean(new Asignacion());
    		solicitudBean = solicitudesDAO.selectByPrimaryKey(new Long(arregloSol[i]));
    		Usuario usuarioBean = usuarioDAO.selectByPrimaryKey(registro);
    		asignacionBean = new Asignacion();
    		asignacionBean.setCodCentral(solicitudBean.getCodCentral());
    		asignacionBean.setCodUsuario(registro);
    		asignacionBean.setCodUsuarioAsigno(codUsuarioAsigno);
    		asignacionBean.setDependiente("SI");
    		asignacionBean.setEstado(Constant.ESTADO_ACTIVO);
    		asignacionBean.setNombre(usuarioBean.getNombres());
    		asignacionBean.setNroSolicitud(solicitudBean.getNroSolicitud());
    		asignacionBean.setPrioridad(solicitudBean.getPrioridad());
    		asignacionBean.setEstadoAsignacion(Constant.TABLA_ESTADOS_SOLCITUD+Constant.CHAR_GUION+Constant.ESTADO_SOLICITUD_ASIGNADO);
    		//asignacionBean.setMtoDelegacionMax(mtoDelegacionMax)
    		if(asignacionDAO.insert(asignacionBean)!=null){
        		ingresaSolicitudOperacion(asignacionBean, Constant.TABLA_PROCESO, Constant.MULT_PROCESO_ASIGNAR);
        		solicitudBean.setEstadoSolicitud(Constant.TABLA_ESTADOS_SOLCITUD+Constant.CHAR_GUION+Constant.ESTADO_SOLICITUD_ASIGNADO);
				solicitudesDAO.updateByPrimaryKeySelective(solicitudBean);
        		if(asignacionBean.getStrMensaje()!=null){
        			solicitudMensajeDAO.insert(seteaMensajeBean(asignacionBean));
        		}result = 1;
        	}
    	}
    	
    	return result;
    }
    
    public void ingresaSolicitudOperacion(Asignacion asignacionBean, String codTabla, String codProceso) throws Exception{
		MultitablaDetalle multDetalleBean = multitablaDetalleDAO.selectByPrimaryKey(codTabla, codProceso) ;
		
		SolicitudOperacion solicitudOperacionBean = new SolicitudOperacion();
		solicitudOperacionBean.setCodCentral(asignacionBean.getCodCentral());
		if(multDetalleBean != null){
			solicitudOperacionBean.setCodMultOperacion(multDetalleBean.getStrValor());
			solicitudOperacionBean.setDesOperacion(multDetalleBean.getStrValor2());				
		}
		
		solicitudOperacionBean.setNroSolicitud(asignacionBean.getNroSolicitud());
		solicitudOperacionBean.setEstado(new BigDecimal(1));
		solicitudOperacionDAO.insert(solicitudOperacionBean);
	}
	
	public SolicitudMensaje seteaMensajeBean(Asignacion asignacionBean){
		SolicitudMensaje solicitudMensajeBean = new SolicitudMensaje();
		solicitudMensajeBean.setCodCentral(asignacionBean.getCodCentral());
		solicitudMensajeBean.setCodMensaje(new BigDecimal(1));
		solicitudMensajeBean.setDesMensaje(asignacionBean.getStrMensaje());
		solicitudMensajeBean.setEstado( new BigDecimal(1));
		solicitudMensajeBean.setNroSolicitud(asignacionBean.getNroSolicitud());
		return solicitudMensajeBean;
	}

	public List<Asignacion> getLstAsignaciones(Asignacion record)throws Exception{
		return asignacionDAO.getLstAsignaciones(record);
	}
  
  
}