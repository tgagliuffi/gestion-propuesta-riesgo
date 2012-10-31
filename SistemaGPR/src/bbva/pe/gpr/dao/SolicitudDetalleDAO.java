package bbva.pe.gpr.dao;

import java.util.List;

import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.bean.SolicitudDetalle;
import bbva.pe.gpr.bean.SolicitudDetalleKey;

public interface SolicitudDetalleDAO {
    int deleteByPrimaryKey(SolicitudDetalleKey key);
    void insert(SolicitudDetalle record);
    void insertSelective(SolicitudDetalle record);
    SolicitudDetalle selectByPrimaryKey(SolicitudDetalleKey key);
    int updateByPrimaryKeySelective(SolicitudDetalle record);
    int updateByPrimaryKey(SolicitudDetalle record);
	List<SolicitudDetalle> getListSolicitudDetalleForId(Solicitud  idsolicitud);

}