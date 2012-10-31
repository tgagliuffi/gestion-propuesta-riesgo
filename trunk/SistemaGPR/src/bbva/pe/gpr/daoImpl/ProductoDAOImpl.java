package bbva.pe.gpr.daoImpl;

import bbva.pe.gpr.bean.Producto;
import bbva.pe.gpr.bean.SolicitudDetalle;
import bbva.pe.gpr.dao.ProductoDAO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ProductoDAOImpl extends SqlMapClientDaoSupport implements ProductoDAO {

    public ProductoDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(BigDecimal codProducto) {
        Producto key = new Producto();
        key.setCodProducto(codProducto);
        int rows = getSqlMapClientTemplate().delete("CARDEL_TGPR_PRODUCTOS.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public void insert(Producto record) {
        getSqlMapClientTemplate().insert("CARDEL_TGPR_PRODUCTOS.ibatorgenerated_insert", record);
    }

    public void insertSelective(Producto record) {
        getSqlMapClientTemplate().insert("CARDEL_TGPR_PRODUCTOS.ibatorgenerated_insertSelective", record);
    }

    public Producto selectByPrimaryKey(BigDecimal codProducto) {
        Producto key = new Producto();
        key.setCodProducto(codProducto);
        Producto record = (Producto) getSqlMapClientTemplate().queryForObject("CARDEL_TGPR_PRODUCTOS.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByPrimaryKeySelective(Producto record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_PRODUCTOS.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(Producto record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_PRODUCTOS.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

	@SuppressWarnings("unchecked")
	public List<Producto> getLstProducto(Producto record) {
		List<Producto> rpts = (List<Producto>) getSqlMapClientTemplate().queryForList("CARDEL_TGPR_PRODUCTOS.getLstProducto", record);    
		return rpts;
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, String> getlstProdGaranDeta(SolicitudDetalle bean) {
		return (HashMap<String, String>)getSqlMapClientTemplate().queryForList("getlstProdGaranDeta",bean).get(0);
	}
}