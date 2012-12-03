package bbva.pe.gpr.dao;

import java.math.BigDecimal;
import java.util.List;

import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.bean.SolicitudOperacion;
import bbva.pe.gpr.bean.SolicitudOperacionKey;

public interface SolicitudOperacionDAO {
    int deleteByPrimaryKey(SolicitudOperacionKey key)throws Exception;
	void insert(SolicitudOperacion record)throws Exception;
	BigDecimal insertSelective(SolicitudOperacion record)throws Exception;
	SolicitudOperacion selectByPrimaryKey(SolicitudOperacionKey key)throws Exception;
	List<SolicitudOperacion> selectByNroSolicitud(Solicitud s)throws Exception;
	int updateByPrimaryKeySelective(SolicitudOperacion record)throws Exception;
	int updateByPrimaryKey(SolicitudOperacion record)throws Exception;
	SolicitudOperacion getListOperationsAjax(SolicitudOperacion key)throws Exception;
}