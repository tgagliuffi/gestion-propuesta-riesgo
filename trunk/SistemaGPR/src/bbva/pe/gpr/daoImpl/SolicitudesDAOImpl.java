package bbva.pe.gpr.daoImpl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.dao.SolicitudesDAO;


public class SolicitudesDAOImpl extends SqlMapClientDaoSupport implements SolicitudesDAO {
	
    public SolicitudesDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(Long nroSolicitud) {
        Solicitud key = new Solicitud();
        key.setNroSolicitud(nroSolicitud);
        int rows = getSqlMapClientTemplate().delete("DELGPR_TGPR_SOLICITUDES.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(Solicitud record) {
    	Long rst = (Long) getSqlMapClientTemplate().insert("DELGPR_TGPR_SOLICITUDES.ibatorgenerated_insert", record);
    	return rst;
    }

    public Long insertSelective(Solicitud record) {
    	Solicitud rst = (Solicitud)getSqlMapClientTemplate().insert("DELGPR_TGPR_SOLICITUDES.ibatorgenerated_insertSelective", record);
    	return rst.getNroSolicitud();
    }

    public Solicitud selectByPrimaryKey(Long nroSolicitud) throws Exception{
        Solicitud key = new Solicitud();
        key.setNroSolicitud(nroSolicitud);
        Solicitud record = (Solicitud) getSqlMapClientTemplate().queryForObject("DELGPR_TGPR_SOLICITUDES.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByPrimaryKeySelective(Solicitud record) {
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_SOLICITUDES.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(Solicitud record) {
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_SOLICITUDES.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }
    
    @SuppressWarnings("unchecked")
	public List<Solicitud> getLstSolicitudBandeja(Solicitud solicitudBean){
    	return (List<Solicitud>) getSqlMapClientTemplate().queryForList("DELGPR_TGPR_SOLICITUDES.getLstSolicitud", solicitudBean);	
    }

    @SuppressWarnings("unchecked")
	public List<Solicitud> getLstSolicitudes(Solicitud solicitudBean){
    	return (List<Solicitud>) getSqlMapClientTemplate().queryForList("DELGPR_TGPR_SOLICITUDES.getLstSolicitudByCriteria", solicitudBean);	
    }
    
}