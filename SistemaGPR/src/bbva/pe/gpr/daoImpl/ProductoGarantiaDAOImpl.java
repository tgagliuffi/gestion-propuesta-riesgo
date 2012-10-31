package bbva.pe.gpr.daoImpl;

import bbva.pe.gpr.bean.ProductoGarantia;
import bbva.pe.gpr.bean.ProductoGarantiaKey;
import bbva.pe.gpr.dao.ProductoGarantiaDAO;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ProductoGarantiaDAOImpl extends SqlMapClientDaoSupport implements ProductoGarantiaDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CARDEL.TGPR_PRODUCTO_GARANTIAS
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public ProductoGarantiaDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CARDEL.TGPR_PRODUCTO_GARANTIAS
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public int deleteByPrimaryKey(ProductoGarantiaKey key) {
        int rows = getSqlMapClientTemplate().delete("CARDEL_TGPR_PRODUCTO_GARANTIAS.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CARDEL.TGPR_PRODUCTO_GARANTIAS
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public void insert(ProductoGarantia record) {
        getSqlMapClientTemplate().insert("CARDEL_TGPR_PRODUCTO_GARANTIAS.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CARDEL.TGPR_PRODUCTO_GARANTIAS
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public void insertSelective(ProductoGarantia record) {
        getSqlMapClientTemplate().insert("CARDEL_TGPR_PRODUCTO_GARANTIAS.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CARDEL.TGPR_PRODUCTO_GARANTIAS
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public ProductoGarantia selectByPrimaryKey(ProductoGarantiaKey key) {
        ProductoGarantia record = (ProductoGarantia) getSqlMapClientTemplate().queryForObject("CARDEL_TGPR_PRODUCTO_GARANTIAS.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CARDEL.TGPR_PRODUCTO_GARANTIAS
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public int updateByPrimaryKeySelective(ProductoGarantia record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_PRODUCTO_GARANTIAS.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CARDEL.TGPR_PRODUCTO_GARANTIAS
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public int updateByPrimaryKey(ProductoGarantia record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_PRODUCTO_GARANTIAS.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }
}