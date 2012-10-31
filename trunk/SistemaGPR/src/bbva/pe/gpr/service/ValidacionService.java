package bbva.pe.gpr.service;

import bbva.pe.gpr.bean.Solicitud;

public interface ValidacionService {

	public int oficinaConRating(Solicitud solicitud);
	public int oficinaSinRating(Solicitud solicitud) ;
	public int oficinaPersonaNatural(Solicitud solicitud);
	public int riesgoPorRating(Solicitud solicitud);
	public int riesgoSinRating(Solicitud solicitud);
	public int riesgoPersonaNatural(Solicitud solicitud);
}