package bbva.pe.gpr.service;

import java.math.BigDecimal;

import bbva.pe.gpr.bean.Dictamen;
import bbva.pe.gpr.bean.Solicitud;


public interface DictaminarService {
	Long delete(Dictamen dictamen) throws Exception;
	Long dictaminarSolicitud(Dictamen dictamenBean) throws Exception;
	Dictamen findForNroSolictud(Dictamen d) throws Exception;
	BigDecimal montoMaxDelegacion(Solicitud s);
}