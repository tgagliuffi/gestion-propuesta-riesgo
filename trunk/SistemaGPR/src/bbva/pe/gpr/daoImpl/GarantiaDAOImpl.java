package bbva.pe.gpr.daoImpl;

import bbva.pe.gpr.bean.Garantia;
import bbva.pe.gpr.dao.GarantiaDAO;

import java.math.BigDecimal;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class GarantiaDAOImpl extends SqlMapClientDaoSupport implements GarantiaDAO {

    public GarantiaDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(BigDecimal codGarantia) {
        Garantia key = new Garantia();
        key.setCodGarantia(codGarantia);
        int rows = getSqlMapClientTemplate().delete("CARDEL_TGPR_GARANTIAS.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public void insert(Garantia record) {
        getSqlMapClientTemplate().insert("CARDEL_TGPR_GARANTIAS.ibatorgenerated_insert", record);
    }

    public void insertSelective(Garantia record) {
        getSqlMapClientTemplate().insert("CARDEL_TGPR_GARANTIAS.ibatorgenerated_insertSelective", record);
    }

    public Garantia selectByPrimaryKey(BigDecimal codGarantia) {
        Garantia key = new Garantia();
        key.setCodGarantia(codGarantia);
        Garantia record = (Garantia) getSqlMapClientTemplate().queryForObject("CARDEL_TGPR_GARANTIAS.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByPrimaryKeySelective(Garantia record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_GARANTIAS.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(Garantia record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_GARANTIAS.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }
//	@SuppressWarnings("unchecked")
//	public HashMap<String, String> getlstProdGaranDeta(SolicitudDetalle bean) {
//		return (HashMap<String, String>)getSqlMapClientTemplate().queryForList("CARDEL_TGPR_PRODUCTOS.getlstProdGaranDeta",bean).get(0);
//	}
}