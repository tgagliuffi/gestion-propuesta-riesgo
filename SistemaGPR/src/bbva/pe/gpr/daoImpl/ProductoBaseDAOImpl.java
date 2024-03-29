package bbva.pe.gpr.daoImpl;

import bbva.pe.gpr.bean.ProductoBase;
import bbva.pe.gpr.dao.ProductoBaseDAO;

import java.math.BigDecimal;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ProductoBaseDAOImpl extends SqlMapClientDaoSupport implements ProductoBaseDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table DELGPR.TGPR_PRODUCTO_BASES
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public ProductoBaseDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table DELGPR.TGPR_PRODUCTO_BASES
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public int deleteByPrimaryKey(BigDecimal codProductoBase) {
        ProductoBase key = new ProductoBase();
        key.setCodProductoBase(codProductoBase);
        int rows = getSqlMapClientTemplate().delete("DELGPR_TGPR_PRODUCTO_BASES.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table DELGPR.TGPR_PRODUCTO_BASES
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public void insert(ProductoBase record) {
        getSqlMapClientTemplate().insert("DELGPR_TGPR_PRODUCTO_BASES.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table DELGPR.TGPR_PRODUCTO_BASES
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public void insertSelective(ProductoBase record) {
        getSqlMapClientTemplate().insert("DELGPR_TGPR_PRODUCTO_BASES.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table DELGPR.TGPR_PRODUCTO_BASES
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public ProductoBase selectByPrimaryKey(BigDecimal codProductoBase) {
        ProductoBase key = new ProductoBase();
        key.setCodProductoBase(codProductoBase);
        ProductoBase record = (ProductoBase) getSqlMapClientTemplate().queryForObject("DELGPR_TGPR_PRODUCTO_BASES.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table DELGPR.TGPR_PRODUCTO_BASES
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public int updateByPrimaryKeySelective(ProductoBase record) {
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_PRODUCTO_BASES.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table DELGPR.TGPR_PRODUCTO_BASES
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public int updateByPrimaryKey(ProductoBase record) {
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_PRODUCTO_BASES.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }
}