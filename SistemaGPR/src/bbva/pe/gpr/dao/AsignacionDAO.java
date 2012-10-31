package bbva.pe.gpr.dao;

import java.math.BigDecimal;

import bbva.pe.gpr.bean.Asignacion;
import bbva.pe.gpr.bean.AsignacionKey;

public interface AsignacionDAO {
	int deleteByPrimaryKey(AsignacionKey key);
	Long insert(Asignacion record);
	void insertSelective(Asignacion record);
	Asignacion selectByPrimaryKey(AsignacionKey key);
	int updateByPrimaryKeySelective(Asignacion record);
	int updateByPrimaryKey(Asignacion record);
	String obtenerUsuarioPorBalance(BigDecimal codBanca, BigDecimal codRol);
}