package bbva.pe.gpr.dao;

import bbva.pe.gpr.bean.ProductoBase;
import java.math.BigDecimal;

public interface ProductoBaseDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CARDEL.TGPR_PRODUCTO_BASES
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    int deleteByPrimaryKey(BigDecimal codProductoBase);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CARDEL.TGPR_PRODUCTO_BASES
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    void insert(ProductoBase record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CARDEL.TGPR_PRODUCTO_BASES
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    void insertSelective(ProductoBase record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CARDEL.TGPR_PRODUCTO_BASES
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    ProductoBase selectByPrimaryKey(BigDecimal codProductoBase);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CARDEL.TGPR_PRODUCTO_BASES
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    int updateByPrimaryKeySelective(ProductoBase record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CARDEL.TGPR_PRODUCTO_BASES
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    int updateByPrimaryKey(ProductoBase record);
}