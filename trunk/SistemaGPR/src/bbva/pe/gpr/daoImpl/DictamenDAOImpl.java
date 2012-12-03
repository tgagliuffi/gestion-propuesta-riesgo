package bbva.pe.gpr.daoImpl;


import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import bbva.pe.gpr.bean.Dictamen;
import bbva.pe.gpr.bean.DictamenKey;
import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.bean.Usuario;
import bbva.pe.gpr.dao.DictamenDAO;

public class DictamenDAOImpl extends SqlMapClientDaoSupport implements DictamenDAO {

    public DictamenDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(DictamenKey key) {
        int rows = getSqlMapClientTemplate().delete("DELGPR_TGPR_DICTAMENES.delete", key);
        return rows;
    }

    public Long insert(Dictamen record) {
        return (Long)getSqlMapClientTemplate().insert("DELGPR_TGPR_DICTAMENES.ibatorgenerated_insert", record);
    }

    public void insertSelective(Dictamen record) {
    	getSqlMapClientTemplate().insert("DELGPR_TGPR_DICTAMENES.ibatorgenerated_insertSelective", record);
    }

    public Dictamen selectByPrimaryKey(DictamenKey key) {
        Dictamen record = (Dictamen) getSqlMapClientTemplate().queryForObject("DELGPR_TGPR_DICTAMENES.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByPrimaryKeySelective(Dictamen record) {
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_DICTAMENES.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(Dictamen record) {
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_DICTAMENES.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    @SuppressWarnings("unchecked")
	public Dictamen findForNumeroSolicitud(DictamenKey key) {
		Dictamen record = null;
        List<Dictamen> records = (List<Dictamen>) getSqlMapClientTemplate().queryForList("DELGPR_TGPR_DICTAMENES.findForNroSolicitud", key);
        
        if(records != null && records.size() >0) {
        	record = records.get(0);
        }
        
        return record;
	}

	
	public Usuario montoMaxDelegacion(Solicitud s) {
		Usuario usuario = (Usuario) getSqlMapClientTemplate().queryForObject("DELGPR_TGPR_DICTAMENES.montoMaxDelegacion", s);
		return usuario;
	}
}