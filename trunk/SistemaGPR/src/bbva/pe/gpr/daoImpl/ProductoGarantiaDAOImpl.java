package bbva.pe.gpr.daoImpl;

import bbva.pe.gpr.bean.Producto;
import bbva.pe.gpr.bean.ProductoGarantia;
import bbva.pe.gpr.bean.ProductoGarantiaKey;
import bbva.pe.gpr.dao.ProductoGarantiaDAO;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ProductoGarantiaDAOImpl extends SqlMapClientDaoSupport implements ProductoGarantiaDAO {

     public ProductoGarantiaDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(ProductoGarantiaKey key) {
        int rows = getSqlMapClientTemplate().delete("CARDEL_TGPR_PRODUCTO_GARANTIAS.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public void insert(ProductoGarantia record) {
        getSqlMapClientTemplate().insert("CARDEL_TGPR_PRODUCTO_GARANTIAS.ibatorgenerated_insert", record);
    }

    public void insertSelective(ProductoGarantia record) {
        getSqlMapClientTemplate().insert("CARDEL_TGPR_PRODUCTO_GARANTIAS.ibatorgenerated_insertSelective", record);
    }

    public ProductoGarantia selectByPrimaryKey(ProductoGarantiaKey key) {
        ProductoGarantia record = (ProductoGarantia) getSqlMapClientTemplate().queryForObject("CARDEL_TGPR_PRODUCTO_GARANTIAS.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

     public int updateByPrimaryKeySelective(ProductoGarantia record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_PRODUCTO_GARANTIAS.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }
    public int updateByPrimaryKey(ProductoGarantia record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_PRODUCTO_GARANTIAS.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }
}