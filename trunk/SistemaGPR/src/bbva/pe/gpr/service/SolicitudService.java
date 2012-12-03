package bbva.pe.gpr.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.bean.SolicitudDetalle;
import bbva.pe.gpr.bean.SolicitudMensaje;
import bbva.pe.gpr.bean.SolicitudOperacion;

public interface SolicitudService{
	Long registraSolicitud(Solicitud solicitudBean, 
								List<SolicitudDetalle> getLstSolicitudDetalle)throws Exception ;
	
	int asignacionAutomatica(Solicitud solicitudBean)throws Exception ;
	
	int updateDictaminaEnOficina(Solicitud solicitudBean)throws Exception ;
	
	int actualizarSolicitud(Solicitud solicitudBean) throws Exception;	
	
	List<Solicitud> getLstSolicitudes(Solicitud solicitudBean) throws Exception;
	
	List<SolicitudDetalle> getListSolicitudDetalleForId(Solicitud idsolicitud)throws Exception;
	
	String changeMtoTotalRowAjax(String value, 
										String pMtoGarantia, 
										HttpServletRequest oRequest)throws Exception;
	
	String changeMtoTotalAjax(String value,
									 HttpServletRequest oRequest)throws Exception;
	
	String changeRiesgoTotalAjax(String pdeudaDirecta, 
										String pdeudaIndirecta, 
										String pdeudaCastigo, 
										String pdeudaSisFinanciero, 
										String pOtroRiesgo, 
										String pRiesgoGrupal, 
										String pMtoTotalProd,
										 HttpServletRequest oRequest)throws Exception;
	
	String changeOtroRiesgoAjax(String value, HttpServletRequest oRequest)throws Exception;
	
	String changeRiesgoActualAjax(String pdeudaDirecta, 
										  String pdeudaIndirecta, 
										  String pdeudaCastigo, 
										  String pdeudaSisFinanciero, 
										  String pOtroRiesgo, 
										  String value,
										  HttpServletRequest oRequest)throws Exception;
	
	int anularSolicitud(String nroSolicitud) throws Exception;
	
	int asignarPrioridadSolicitud(String nroSolicitud, 
			String tipoUpdate) throws Exception;

	List<SolicitudDetalle> eliminarProducto(String arraySolitcitudes, List<SolicitudDetalle> lstSolicitudDetalle)throws Exception;
	
	List<SolicitudOperacion> selectOperacionByNroSolicitud(Solicitud s) throws Exception;
	
	String validaGrupoPersona(String tipoPersona, String numDocumento, String rating) throws Exception;
	
	 List<SolicitudMensaje> getListMessagesAjax(SolicitudMensaje record) throws Exception;
	 

}
