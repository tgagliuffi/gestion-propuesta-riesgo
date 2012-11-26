package bbva.pe.gpr.service;

import java.util.List;

import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.bean.SolicitudDetalle;

public interface ValidacionService {

	public int oficinaConRating(Solicitud solicitud);
	public int oficinaSinRating(Solicitud solicitud) ;
	public int oficinaPersonaNatural(Solicitud solicitud);
	public int riesgoPorRating(Solicitud solicitud,String codUsuario);
	public int riesgoSinRating(Solicitud solicitud,String codUsuario);
	public int riesgoPersonaNatural(Solicitud solicitud,String codUsuario);
	
	public int riesgoPorRatingIngresoSolicitud(List<SolicitudDetalle> solicitudDetalle,String codUsuario);
	public int riesgoSinRatingIngresoSolicitud(List<SolicitudDetalle> solicitudDetalle,String codUsuario);
	public int riesgoPersonaNaturalIngresoSolicitud(List<SolicitudDetalle> solicitudDetalle,String codUsuario);
	public int metodoEncapsulado(Solicitud solicitud,String codUsuario);
	public int metodoEncapsuladoIngresoSolicitud(Solicitud solicitud,List<SolicitudDetalle> solicitudDetalle,String codUsuario);
	
}