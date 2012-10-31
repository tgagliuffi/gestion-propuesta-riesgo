package bbva.pe.gpr.dao;

import java.util.List;

import bbva.pe.gpr.bean.Solicitud;

public interface SolicitudesDAO {
		int deleteByPrimaryKey(Long nroSolicitud);
		Long insert(Solicitud record);
		Long insertSelective(Solicitud record);
		Solicitud selectByPrimaryKey(Long nroSolicitud);
		int updateByPrimaryKeySelective(Solicitud record);
		int updateByPrimaryKey(Solicitud record);
		List<Solicitud> getLstSolicitudBandeja(Solicitud solicitudBean);
		List<Solicitud> getLstSolicitudes(Solicitud solicitudBean) throws Exception;
}