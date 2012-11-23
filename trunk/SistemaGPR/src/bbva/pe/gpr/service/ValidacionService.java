package bbva.pe.gpr.service;

import bbva.pe.gpr.bean.Solicitud;

public interface ValidacionService {

	public int oficinaConRating(Solicitud solicitud);
	public int oficinaSinRating(Solicitud solicitud) ;
	public int oficinaPersonaNatural(Solicitud solicitud);
	public int riesgoPorRating(Solicitud solicitud,String codUsuario);
	public int riesgoSinRating(Solicitud solicitud,String codUsuario);
	public int riesgoPersonaNatural(Solicitud solicitud,String codUsuario);
	public int metodoEncapsulado(Solicitud solicitud,String codUsuario);
	
}