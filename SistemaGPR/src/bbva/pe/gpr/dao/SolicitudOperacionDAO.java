package bbva.pe.gpr.dao;

import java.math.BigDecimal;
import java.util.List;

import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.bean.SolicitudOperacion;
import bbva.pe.gpr.bean.SolicitudOperacionKey;

public interface SolicitudOperacionDAO {
    int deleteByPrimaryKey(SolicitudOperacionKey key);
	void insert(SolicitudOperacion record);
	BigDecimal insertSelective(SolicitudOperacion record);
	SolicitudOperacion selectByPrimaryKey(SolicitudOperacionKey key);
	List<SolicitudOperacion> selectByNroSolicitud(Solicitud s);
	int updateByPrimaryKeySelective(SolicitudOperacion record);
	int updateByPrimaryKey(SolicitudOperacion record);
}