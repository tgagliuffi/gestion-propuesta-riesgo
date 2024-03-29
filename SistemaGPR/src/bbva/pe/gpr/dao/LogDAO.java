package bbva.pe.gpr.dao;

import bbva.pe.gpr.bean.Log;
import java.math.BigDecimal;

public interface LogDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table DELGPR.TGPR_LOGS
     *
     * @ibatorgenerated Tue Sep 25 16:33:28 COT 2012
     */
    int deleteByPrimaryKey(BigDecimal codLog);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table DELGPR.TGPR_LOGS
     *
     * @ibatorgenerated Tue Sep 25 16:33:28 COT 2012
     */
    void insert(Log record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table DELGPR.TGPR_LOGS
     *
     * @ibatorgenerated Tue Sep 25 16:33:28 COT 2012
     */
    void insertSelective(Log record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table DELGPR.TGPR_LOGS
     *
     * @ibatorgenerated Tue Sep 25 16:33:28 COT 2012
     */
    Log selectByPrimaryKey(BigDecimal codLog);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table DELGPR.TGPR_LOGS
     *
     * @ibatorgenerated Tue Sep 25 16:33:28 COT 2012
     */
    int updateByPrimaryKeySelective(Log record);

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table DELGPR.TGPR_LOGS
     *
     * @ibatorgenerated Tue Sep 25 16:33:28 COT 2012
     */
    int updateByPrimaryKey(Log record);
}