package bbva.pe.gpr.dao;

import bbva.pe.gpr.bean.ProductoGarantia;
import bbva.pe.gpr.bean.ProductoGarantiaKey;

public interface ProductoGarantiaDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table DELGPR.TGPR_PRODUCTO_GARANTIAS
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    int deleteByPrimaryKey(ProductoGarantiaKey key);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table DELGPR.TGPR_PRODUCTO_GARANTIAS
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    void insert(ProductoGarantia record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table DELGPR.TGPR_PRODUCTO_GARANTIAS
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    void insertSelective(ProductoGarantia record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table DELGPR.TGPR_PRODUCTO_GARANTIAS
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    ProductoGarantia selectByPrimaryKey(ProductoGarantiaKey key);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table DELGPR.TGPR_PRODUCTO_GARANTIAS
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    int updateByPrimaryKeySelective(ProductoGarantia record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table DELGPR.TGPR_PRODUCTO_GARANTIAS
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    int updateByPrimaryKey(ProductoGarantia record);
}