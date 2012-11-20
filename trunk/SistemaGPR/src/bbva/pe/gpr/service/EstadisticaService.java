package bbva.pe.gpr.service;

import java.util.List;
import java.util.Map;

import bbva.pe.gpr.bean.Estadistica;

public interface EstadisticaService {
	
	List<Map<String, Object>> selectAsignacion(Estadistica filtro);
	List<Map<String, Object>> selectAtencion(Estadistica filtro);
	Map<String, Object> selectCabeceraAsignacion(Estadistica filtro);
	Map<String, Object> selectCabeceraAtencion(Estadistica filtro);
	
}
