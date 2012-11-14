package bbva.pe.gpr.service;

import java.util.List;

import bbva.pe.gpr.bean.Analisis;

public interface AnalisiService {
	public Long insertarAnalisis(Analisis record) throws Exception;
	public Long eliminarAnalisis(Analisis record) throws Exception;
	public Long actualizarAnalisis(Analisis record) throws Exception;
	public List<Analisis> buscarAnalisis(Analisis record) throws Exception;
}