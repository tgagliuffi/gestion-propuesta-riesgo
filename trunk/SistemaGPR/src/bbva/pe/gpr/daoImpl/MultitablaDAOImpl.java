package bbva.pe.gpr.daoImpl;

import bbva.pe.gpr.bean.Multitabla;
import bbva.pe.gpr.dao.MultitablaDAO;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class MultitablaDAOImpl extends SqlMapClientDaoSupport implements MultitablaDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table DELGPR.TGPR_MULTITABLA
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public MultitablaDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table DELGPR.TGPR_MULTITABLA
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public int deleteByPrimaryKey(String codMultitabla) {
        Multitabla key = new Multitabla();
        key.setCodMultitabla(codMultitabla);
        int rows = getSqlMapClientTemplate().delete("DELGPR_TGPR_MULTITABLA.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table DELGPR.TGPR_MULTITABLA
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public void insert(Multitabla record) {
        getSqlMapClientTemplate().insert("DELGPR_TGPR_MULTITABLA.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table DELGPR.TGPR_MULTITABLA
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public void insertSelective(Multitabla record) {
        getSqlMapClientTemplate().insert("DELGPR_TGPR_MULTITABLA.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table DELGPR.TGPR_MULTITABLA
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public Multitabla selectByPrimaryKey(String codMultitabla) {
        Multitabla key = new Multitabla();
        key.setCodMultitabla(codMultitabla);
        Multitabla record = (Multitabla) getSqlMapClientTemplate().queryForObject("DELGPR_TGPR_MULTITABLA.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table DELGPR.TGPR_MULTITABLA
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public int updateByPrimaryKeySelective(Multitabla record) {
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_MULTITABLA.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table DELGPR.TGPR_MULTITABLA
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public int updateByPrimaryKey(Multitabla record) {
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_MULTITABLA.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }
}