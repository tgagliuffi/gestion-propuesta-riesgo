package bbva.pe.gpr.serviceImpl;

import java.math.BigDecimal;

import bbva.pe.gpr.bean.Asignacion;
import bbva.pe.gpr.bean.MultitablaDetalle;
import bbva.pe.gpr.bean.SolicitudMensaje;
import bbva.pe.gpr.bean.SolicitudOperacion;
import bbva.pe.gpr.dao.AsignacionDAO;
import bbva.pe.gpr.dao.MultitablaDetalleDAO;
import bbva.pe.gpr.dao.SolicitudMensajeDAO;
import bbva.pe.gpr.dao.SolicitudOperacionDAO;
import bbva.pe.gpr.service.AsignacionService;
import bbva.pe.gpr.util.Constant;


public class AsignacionServiceImpl  implements AsignacionService {
	private AsignacionDAO asignacionDAO;
	private SolicitudOperacionDAO solicitudOperacionDAO;
	private SolicitudMensajeDAO solicitudMensajeDAO;
	private MultitablaDetalleDAO multitablaDetalleDAO;
 
	public AsignacionServiceImpl(AsignacionDAO asignacionDAO,
			SolicitudOperacionDAO solicitudOperacionDAO,
			SolicitudMensajeDAO solicitudMensajeDAO,
			MultitablaDetalleDAO multitablaDetalleDAO){
		super();
		this.asignacionDAO=asignacionDAO;
		this.solicitudOperacionDAO=solicitudOperacionDAO;
		this.solicitudMensajeDAO=solicitudMensajeDAO;
		this.multitablaDetalleDAO=multitablaDetalleDAO;
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

    
  

    
    
    
    
}