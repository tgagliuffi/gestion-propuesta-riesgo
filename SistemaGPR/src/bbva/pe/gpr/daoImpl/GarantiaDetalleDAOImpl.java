package bbva.pe.gpr.daoImpl;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import bbva.pe.gpr.bean.GarantiaDetalle;
import bbva.pe.gpr.bean.GarantiaDetalleKey;
import bbva.pe.gpr.bean.SolicitudDetalle;
import bbva.pe.gpr.dao.GarantiaDetalleDAO;

public class GarantiaDetalleDAOImpl extends SqlMapClientDaoSupport implements GarantiaDetalleDAO {

    public GarantiaDetalleDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(GarantiaDetalleKey key) {
        int rows = getSqlMapClientTemplate().delete("CARDEL_TGPR_GARANTIA_DETALLES.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public void insert(GarantiaDetalle record) {
        getSqlMapClientTemplate().insert("CARDEL_TGPR_GARANTIA_DETALLES.ibatorgenerated_insert", record);
    }

    public void insertSelective(GarantiaDetalle record) {
        getSqlMapClientTemplate().insert("CARDEL_TGPR_GARANTIA_DETALLES.ibatorgenerated_insertSelective", record);
    }

    public GarantiaDetalle selectByPrimaryKey(GarantiaDetalleKey key) {
        GarantiaDetalle record = (GarantiaDetalle) getSqlMapClientTemplate().queryForObject("CARDEL_TGPR_GARANTIA_DETALLES.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByPrimaryKeySelective(GarantiaDetalle record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_GARANTIA_DETALLES.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(GarantiaDetalle record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_GARANTIA_DETALLES.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public HashMap<String,BigDecimal> getlstProdGaranDeta(SolicitudDetalle bean) {
		return (HashMap)getSqlMapClientTemplate().queryForObject("CARDEL_TGPR_GARANTIA_DETALLES.getlstProdGaranDeta",bean);
	}
}