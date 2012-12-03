package bbva.pe.gpr.daoImpl;

import java.util.List;

import bbva.pe.gpr.bean.Analisis;
import bbva.pe.gpr.bean.AnalisisKey;
import bbva.pe.gpr.dao.AnalisisDAO;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class AnalisisDAOImpl extends SqlMapClientDaoSupport implements AnalisisDAO {

    public AnalisisDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(AnalisisKey key) throws Exception{
        int rows = getSqlMapClientTemplate().delete("DELGPR_TGPR_ANALISIS.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(Analisis record)throws Exception {
    	Long rst = (Long)  getSqlMapClientTemplate().insert("DELGPR_TGPR_ANALISIS.ibatorgenerated_insert", record);
    	return rst;
    }

    public void insertSelective(Analisis record) throws Exception{
    	getSqlMapClientTemplate().insert("DELGPR_TGPR_ANALISIS.ibatorgenerated_insertSelective", record);
    }

    public Analisis selectByPrimaryKey(AnalisisKey key) throws Exception{
        Analisis record = (Analisis) getSqlMapClientTemplate().queryForObject("DELGPR_TGPR_ANALISIS.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByPrimaryKeySelective(Analisis record) throws Exception {
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_ANALISIS.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(Analisis record) throws Exception {
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_ANALISIS.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

	@SuppressWarnings("unchecked")
	public List<Analisis> selectByNroSolicitud(AnalisisKey key) throws Exception {
        return (List<Analisis>) getSqlMapClientTemplate().queryForList("DELGPR_TGPR_ANALISIS.selectByNroSolicitud", key);
	}
	
	@SuppressWarnings("unchecked")
	public List<Analisis> selectByAnalisis(Analisis analisis) throws Exception {
        return (List<Analisis>) getSqlMapClientTemplate().queryForList("DELGPR_TGPR_ANALISIS.selectByAnalisis", analisis);
	}

}