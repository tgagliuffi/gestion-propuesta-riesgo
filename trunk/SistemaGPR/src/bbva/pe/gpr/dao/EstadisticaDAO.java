package bbva.pe.gpr.dao;

import java.util.List;

import bbva.pe.gpr.bean.Estadistica;

public interface EstadisticaDAO {
	
	List<Estadistica> selectAsignacion(Estadistica filtro) throws Exception;
	List<Estadistica> selectCabeceraAsignacion(Estadistica filtro) throws Exception;
}
