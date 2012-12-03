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
        int rows = getSqlMapClientTemplate().delete("DELGPR_TGPR_GARANTIAS.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public void insert(Garantia record) {
        getSqlMapClientTemplate().insert("DELGPR_TGPR_GARANTIAS.ibatorgenerated_insert", record);
    }

    public void insertSelective(Garantia record) {
        getSqlMapClientTemplate().insert("DELGPR_TGPR_GARANTIAS.ibatorgenerated_insertSelective", record);
    }

    public Garantia selectByPrimaryKey(BigDecimal codGarantia) {
        Garantia key = new Garantia();
        key.setCodGarantia(codGarantia);
        Garantia record = (Garantia) getSqlMapClientTemplate().queryForObject("DELGPR_TGPR_GARANTIAS.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByPrimaryKeySelective(Garantia record) {
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_GARANTIAS.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(Garantia record) {
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_GARANTIAS.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }
//	@SuppressWarnings("unchecked")
//	public HashMap<String, String> getlstProdGaranDeta(SolicitudDetalle bean) {
//		return (HashMap<String, String>)getSqlMapClientTemplate().queryForList("DELGPR_TGPR_PRODUCTOS.getlstProdGaranDeta",bean).get(0);
//	}

	public Garantia getProductoGarantia(String idProducto) {
		return (Garantia)getSqlMapClientTemplate().queryForObject("DELGPR_TGPR_GARANTIAS.getProductoGarantia", idProducto);
	}
}