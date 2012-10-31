package bbva.pe.gpr.dao;

import java.math.BigDecimal;

import bbva.pe.gpr.bean.SolicitudOperacion;
import bbva.pe.gpr.bean.SolicitudOperacionKey;

public interface SolicitudOperacionDAO {
    int deleteByPrimaryKey(SolicitudOperacionKey key);
	void insert(SolicitudOperacion record);
	BigDecimal insertSelective(SolicitudOperacion record);
	SolicitudOperacion selectByPrimaryKey(SolicitudOperacionKey key);
	int updateByPrimaryKeySelective(SolicitudOperacion record);
	int updateByPrimaryKey(SolicitudOperacion record);
}