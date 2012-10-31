package bbva.pe.gpr.serviceImpl;

import java.util.List;

import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.bean.SolicitudDetalle;
import bbva.pe.gpr.dao.SolicitudDetalleDAO;
import bbva.pe.gpr.service.SolicitudDetalleService;

public class SolicituDetalleServiceImpl implements SolicitudDetalleService{

	
	private SolicitudDetalleDAO solicitudDetalleDAO;
	
    public SolicituDetalleServiceImpl(SolicitudDetalleDAO solicitudDetalleDAO){
    	super();
    	this.solicitudDetalleDAO=solicitudDetalleDAO;
    }
    
	public void setSolicitudDetalleDAO(SolicitudDetalleDAO solicitudDetalleDAO){this.solicitudDetalleDAO = solicitudDetalleDAO;}
	public List<SolicitudDetalle> getListSolicitudDetalleForId(Solicitud idsolicitud){return solicitudDetalleDAO.getListSolicitudDetalleForId(idsolicitud);}
	
	public SolicitudDetalleDAO getSolicitudDetalleDAO()throws Exception{
		return solicitudDetalleDAO;
	}




}
