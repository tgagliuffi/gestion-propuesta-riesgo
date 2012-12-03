package bbva.pe.gpr.dao;

import bbva.pe.gpr.bean.SolicitudMensaje;
import java.math.BigDecimal;
import java.util.List;

public interface SolicitudMensajeDAO {
    int deleteByPrimaryKey(BigDecimal codMensaje) throws Exception;
	void insert(SolicitudMensaje record) throws Exception;
	void insertSelective(SolicitudMensaje record) throws Exception;
	SolicitudMensaje selectByPrimaryKey(BigDecimal codMensaje) throws Exception;
	int updateByPrimaryKeySelective(SolicitudMensaje record) throws Exception;
	int updateByPrimaryKey(SolicitudMensaje record) throws Exception;
	List<SolicitudMensaje> getListMessagesAjax(SolicitudMensaje record) throws Exception;
}