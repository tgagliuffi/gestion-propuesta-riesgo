package bbva.pe.gpr.daoImpl;

import bbva.pe.gpr.bean.SolicitudMensaje;
import bbva.pe.gpr.dao.SolicitudMensajeDAO;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class SolicitudMensajeDAOImpl extends SqlMapClientDaoSupport implements SolicitudMensajeDAO {

    public SolicitudMensajeDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(BigDecimal codMensaje)throws Exception {
        SolicitudMensaje key = new SolicitudMensaje();
        key.setCodMensaje(codMensaje);
        int rows = getSqlMapClientTemplate().delete("DELGPR_TGPR_SOLICITUD_MENSAJES.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }
    
    public void insert(SolicitudMensaje record)throws Exception {
        getSqlMapClientTemplate().insert("DELGPR_TGPR_SOLICITUD_MENSAJES.ibatorgenerated_insert", record);
    }

    public void insertSelective(SolicitudMensaje record) throws Exception{
        getSqlMapClientTemplate().insert("DELGPR_TGPR_SOLICITUD_MENSAJES.ibatorgenerated_insertSelective", record);
    }

    public SolicitudMensaje selectByPrimaryKey(BigDecimal codMensaje) throws Exception{
        SolicitudMensaje key = new SolicitudMensaje();
        key.setCodMensaje(codMensaje);
        SolicitudMensaje record = (SolicitudMensaje) getSqlMapClientTemplate().queryForObject("DELGPR_TGPR_SOLICITUD_MENSAJES.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

   public int updateByPrimaryKeySelective(SolicitudMensaje record) throws Exception{
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_SOLICITUD_MENSAJES.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(SolicitudMensaje record) throws Exception{
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_SOLICITUD_MENSAJES.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }
    
    @SuppressWarnings("unchecked")
	public List<SolicitudMensaje> getListMessagesAjax(SolicitudMensaje record)throws Exception{
        List<SolicitudMensaje> lst = (List<SolicitudMensaje>) getSqlMapClientTemplate().queryForList("DELGPR_TGPR_SOLICITUD_MENSAJES.getListMessagesAjax", record);
        return lst;
    }
    
}