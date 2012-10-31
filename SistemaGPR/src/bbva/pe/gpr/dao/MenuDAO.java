package bbva.pe.gpr.dao;

import bbva.pe.gpr.bean.Menu;
import java.math.BigDecimal;

public interface MenuDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CARDEL.TGPR_MENUS
     *
     * @ibatorgenerated Tue Sep 25 16:33:28 COT 2012
     */
    int deleteByPrimaryKey(BigDecimal codMenu);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CARDEL.TGPR_MENUS
     *
     * @ibatorgenerated Tue Sep 25 16:33:28 COT 2012
     */
    void insert(Menu record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CARDEL.TGPR_MENUS
     *
     * @ibatorgenerated Tue Sep 25 16:33:28 COT 2012
     */
    void insertSelective(Menu record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CARDEL.TGPR_MENUS
     *
     * @ibatorgenerated Tue Sep 25 16:33:28 COT 2012
     */
    Menu selectByPrimaryKey(BigDecimal codMenu);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CARDEL.TGPR_MENUS
     *
     * @ibatorgenerated Tue Sep 25 16:33:28 COT 2012
     */
    int updateByPrimaryKeySelective(Menu record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CARDEL.TGPR_MENUS
     *
     * @ibatorgenerated Tue Sep 25 16:33:28 COT 2012
     */
    int updateByPrimaryKey(Menu record);
}