package bbva.pe.gpr.daoImpl;

import bbva.pe.gpr.bean.BancaSub;
import bbva.pe.gpr.dao.BancaSubDAO;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class BancaSubDAOImpl extends SqlMapClientDaoSupport implements BancaSubDAO {

     public BancaSubDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(String codSubbanca) {
        BancaSub key = new BancaSub();
        key.setCodSubanca(codSubbanca);
        int rows = getSqlMapClientTemplate().delete("DELGPR_TGPR_BANCA_SUB.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public void insert(BancaSub record) {
        getSqlMapClientTemplate().insert("DELGPR_TGPR_BANCA_SUB.ibatorgenerated_insert", record);
    }

    public void insertSelective(BancaSub record) {
        getSqlMapClientTemplate().insert("DELGPR_TGPR_BANCA_SUB.ibatorgenerated_insertSelective", record);
    }

    public BancaSub selectByPrimaryKey(String codSubbanca) {
        BancaSub key = new BancaSub();
        key.setCodSubanca(codSubbanca);
        BancaSub record = (BancaSub) getSqlMapClientTemplate().queryForObject("DELGPR_TGPR_BANCA_SUB.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByPrimaryKeySelective(BancaSub record) {
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_BANCA_SUB.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(BancaSub record) {
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_BANCA_SUB.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }
}