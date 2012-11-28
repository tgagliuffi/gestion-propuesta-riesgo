package bbva.pe.gpr.service;

import java.util.List;

import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.bean.SolicitudDetalle;

public interface ValidacionService {

	public int oficinaConRating(Solicitud solicitud,String codUsuario);
	public int oficinaSinRating(Solicitud solicitud,String codUsuario) ;
	public int oficinaPersonaNatural(Solicitud solicitud,String codUsuario);
	public int riesgoPorRating(Solicitud solicitud,String codUsuario);
	public int riesgoSinRating(Solicitud solicitud,String codUsuario);
	public int riesgoPersonaNatural(Solicitud solicitud,String codUsuario);
	
	public int riesgoPorRatingIngresoSolicitud(Solicitud solicitud,List<SolicitudDetalle> solicitudDetalle,String codUsuario);
	public int riesgoSinRatingIngresoSolicitud(Solicitud solicitud,List<SolicitudDetalle> solicitudDetalle,String codUsuario);
	public int riesgoPersonaNaturalIngresoSolicitud(Solicitud solicitud,List<SolicitudDetalle> solicitudDetalle,String codUsuario);
	public int oficinaPorRatingIngresoSolicitud(Solicitud solicitud,List<SolicitudDetalle> solicitudDetalle,String codUsuario);
	public int oficinaSinRatingIngresoSolicitud(Solicitud solicitud,List<SolicitudDetalle> solicitudDetalle,String codUsuario);
	public int oficinaPersonaNaturalIngresoSolicitud(Solicitud solicitud,List<SolicitudDetalle> solicitudDetalle,String codUsuario);
	public int metodoEncapsulado(Solicitud solicitud,String codUsuario);
	public int metodoEncapsuladoIngresoSolicitud(Solicitud solicitud,List<SolicitudDetalle> solicitudDetalle,String codUsuario);
	
}