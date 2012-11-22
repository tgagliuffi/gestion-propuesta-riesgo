package bbva.pe.gpr.daoImpl;

import bbva.pe.gpr.bean.Banca;
import bbva.pe.gpr.bean.BancaSub;
import bbva.pe.gpr.dao.BancaDAO;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;


public class BancaDAOImpl extends SqlMapClientDaoSupport implements BancaDAO {

    public BancaDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(BigDecimal codBanca) {
        Banca key = new Banca();
        key.setCodBanca(codBanca);
        int rows = getSqlMapClientTemplate().delete("CARDEL_TGPR_BANCAS.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public void insert(Banca record) {
        getSqlMapClientTemplate().insert("CARDEL_TGPR_BANCAS.ibatorgenerated_insert", record);
    }

    
    public void insertSelective(Banca record) {
        getSqlMapClientTemplate().insert("CARDEL_TGPR_BANCAS.ibatorgenerated_insertSelective", record);
    }

    public Banca selectByPrimaryKey(BigDecimal codBanca) {
        Banca key = new Banca();
        key.setCodBanca(codBanca);
        Banca record = (Banca) getSqlMapClientTemplate().queryForObject("CARDEL_TGPR_BANCAS.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    
    public int updateByPrimaryKeySelective(Banca record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_BANCAS.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(Banca record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_BANCAS.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }
    
    @SuppressWarnings("unchecked")
	public List<Banca> getLstBancaByCriteria(Banca record){
    	return   (List<Banca>)getSqlMapClientTemplate().queryForList("CARDEL_TGPR_BANCAS.getLstBancaByCriteria", record);
    }
    @SuppressWarnings("unchecked")
	public List<BancaSub> getLstSubBanca(BancaSub bancaSub){
    	return getSqlMapClientTemplate().queryForList("CARDEL_TGPR_BANCAS.getLstSubBanca", bancaSub);
    	
    	
    }
 
}