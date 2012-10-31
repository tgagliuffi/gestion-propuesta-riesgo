package bbva.pe.gpr.daoImpl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.bean.SolicitudDetalle;
import bbva.pe.gpr.bean.SolicitudDetalleKey;
import bbva.pe.gpr.dao.SolicitudDetalleDAO;


public class SolicitudDetalleDAOImpl extends SqlMapClientDaoSupport implements SolicitudDetalleDAO {

    public SolicitudDetalleDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(SolicitudDetalleKey key) {
        int rows = getSqlMapClientTemplate().delete("CARDEL_TGPR_SOLICITUD_DETALLES.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public void insert(SolicitudDetalle record) {
        getSqlMapClientTemplate().insert("CARDEL_TGPR_SOLICITUD_DETALLES.ibatorgenerated_insert", record);
    }

    public void insertSelective(SolicitudDetalle record) {
        getSqlMapClientTemplate().insert("CARDEL_TGPR_SOLICITUD_DETALLES.ibatorgenerated_insertSelective", record);
    }

    public SolicitudDetalle selectByPrimaryKey(SolicitudDetalleKey key) {
        SolicitudDetalle record = (SolicitudDetalle) getSqlMapClientTemplate().queryForObject("CARDEL_TGPR_SOLICITUD_DETALLES.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByPrimaryKeySelective(SolicitudDetalle record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_SOLICITUD_DETALLES.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(SolicitudDetalle record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_SOLICITUD_DETALLES.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }
    @SuppressWarnings("unchecked")
  	public List<Solicitud> getLstSolicitudBandeja(Solicitud solicitudBean){
      	return (List<Solicitud>) getSqlMapClientTemplate().queryForList("CARDEL_TGPR_SOLICITUDES.getLstSolicitud", solicitudBean);	
      }

	@SuppressWarnings("unchecked")
	public List<SolicitudDetalle> getListSolicitudDetalleForId(Solicitud idsolicitud) {
		 return (List<SolicitudDetalle>) getSqlMapClientTemplate().queryForList("CARDEL_TGPR_SOLICITUD_DETALLES.getListSolicitudForId",idsolicitud);
	}
}