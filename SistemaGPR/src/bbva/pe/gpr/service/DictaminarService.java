package bbva.pe.gpr.service;

import bbva.pe.gpr.bean.Dictamen;


public interface DictaminarService {
	Long dictaminarSolicitud(Dictamen dictamenBean) throws Exception;
}