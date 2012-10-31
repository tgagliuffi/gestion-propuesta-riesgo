package bbva.pe.gpr.service;

import java.util.List;

import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.bean.SolicitudDetalle;

public interface SolicitudService{
	Long registraSolicitud(Solicitud solicitudBean, 
								List<SolicitudDetalle> getLstSolicitudDetalle)throws Exception ;
	int asignacionAutomatica(Solicitud solicitudBean)throws Exception ;
	int updateDictaminaEnOficina(Solicitud solicitudBean)throws Exception ;
	List<Solicitud> getLstSolicitudes(Solicitud solicitudBean) throws Exception;
	
}
