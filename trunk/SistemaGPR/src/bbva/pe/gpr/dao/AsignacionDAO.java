package bbva.pe.gpr.dao;

import java.math.BigDecimal;
import java.util.List;

import bbva.pe.gpr.bean.Asignacion;
import bbva.pe.gpr.bean.AsignacionKey;

public interface AsignacionDAO {
	int deleteByPrimaryKey(AsignacionKey key)throws Exception;
	Long insert(Asignacion record)throws Exception;
	void insertSelective(Asignacion record)throws Exception;
	Asignacion selectByPrimaryKey(AsignacionKey key)throws Exception;
	int updateByPrimaryKeySelective(Asignacion record)throws Exception;
	int updateByPrimaryKey(Asignacion record) throws Exception;
	String obtenerUsuarioPorBalance(BigDecimal codBanca, BigDecimal codRol) throws Exception;
	List<Asignacion> getLstAsignaciones(Asignacion record) throws Exception;
	void insertAsignacion(Asignacion record)throws Exception;
}