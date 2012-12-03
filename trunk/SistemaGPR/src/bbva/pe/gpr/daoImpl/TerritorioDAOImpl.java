package bbva.pe.gpr.daoImpl;

import bbva.pe.gpr.bean.Territorio;
import bbva.pe.gpr.dao.TerritorioDAO;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class TerritorioDAOImpl extends SqlMapClientDaoSupport implements TerritorioDAO {

    public TerritorioDAOImpl() {
        super();
    }
    public int deleteByPrimaryKey(BigDecimal codTerritorio) {
        Territorio key = new Territorio();
        key.setCodTerritorio(codTerritorio);
        int rows = getSqlMapClientTemplate().delete("DELGPR_TGPR_TERRITORIOS.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }
    public void insert(Territorio record) {
        getSqlMapClientTemplate().insert("DELGPR_TGPR_TERRITORIOS.ibatorgenerated_insert", record);
    }

    public void insertSelective(Territorio record) {
        getSqlMapClientTemplate().insert("DELGPR_TGPR_TERRITORIOS.ibatorgenerated_insertSelective", record);
    }

    public Territorio selectByPrimaryKey(BigDecimal codTerritorio) {
        Territorio key = new Territorio();
        key.setCodTerritorio(codTerritorio);
        Territorio record = (Territorio) getSqlMapClientTemplate().queryForObject("DELGPR_TGPR_TERRITORIOS.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }
    public int updateByPrimaryKeySelective(Territorio record) {
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_TERRITORIOS.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(Territorio record) {
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_TERRITORIOS.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

   @SuppressWarnings("unchecked")
 	public List<Territorio> getLstTerritorioByCriteria(Territorio record) throws Exception {
 		return getSqlMapClientTemplate().queryForList("DELGPR_TGPR_TERRITORIOS.getLstTerritorioByCriteria",record);
 	}
}