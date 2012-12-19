package bbva.pe.gpr.service;

import java.util.List;

import bbva.pe.gpr.bean.Garantia;
import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.bean.SolicitudDetalle;

public interface ValidacionService {

	public int oficinaConRating(Solicitud solicitud,String codUsuario) throws Exception;
	public int oficinaSinRating(Solicitud solicitud,String codUsuario)  throws Exception;
	public int oficinaPersonaNatural(Solicitud solicitud,String codUsuario) throws Exception;
	public int riesgoPorRating(Solicitud solicitud,String codUsuario) throws Exception;
	public int riesgoSinRating(Solicitud solicitud,String codUsuario) throws Exception;
	public int riesgoPersonaNatural(Solicitud solicitud,String codUsuario) throws Exception;
	
	public int riesgoPorRatingIngresoSolicitud(Solicitud solicitud,List<SolicitudDetalle> solicitudDetalle,String codUsuario) throws Exception;
	public int riesgoSinRatingIngresoSolicitud(Solicitud solicitud,List<SolicitudDetalle> solicitudDetalle,String codUsuario) throws Exception;
	public int riesgoPersonaNaturalIngresoSolicitud(Solicitud solicitud,List<SolicitudDetalle> solicitudDetalle,String codUsuario) throws Exception;
	public int oficinaPorRatingIngresoSolicitud(Solicitud solicitud,List<SolicitudDetalle> solicitudDetalle,String codUsuario) throws Exception;
	public int oficinaSinRatingIngresoSolicitud(Solicitud solicitud,List<SolicitudDetalle> solicitudDetalle,String codUsuario) throws Exception;
	public int oficinaPersonaNaturalIngresoSolicitud(Solicitud solicitud,List<SolicitudDetalle> solicitudDetalle,String codUsuario) throws Exception;
	public int metodoEncapsulado(Solicitud solicitud,String codUsuario) throws Exception;
	public int metodoEncapsuladoIngresoSolicitud(Solicitud solicitud,List<SolicitudDetalle> solicitudDetalle,String codUsuario) throws Exception;
	Garantia getProductoGarantia(String codProducto)throws Exception;
}