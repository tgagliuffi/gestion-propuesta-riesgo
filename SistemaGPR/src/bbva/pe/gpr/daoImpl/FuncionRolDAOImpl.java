package bbva.pe.gpr.daoImpl;

import bbva.pe.gpr.bean.FuncionRol;
import bbva.pe.gpr.bean.FuncionRolKey;
import bbva.pe.gpr.dao.FuncionRolDAO;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class FuncionRolDAOImpl extends SqlMapClientDaoSupport implements FuncionRolDAO {

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CARDEL.TGPR_FUNCION_ROLES
     *
     * @ibatorgenerated Tue Sep 25 16:43:19 COT 2012
     */
    public FuncionRolDAOImpl() {
        super();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CARDEL.TGPR_FUNCION_ROLES
     *
     * @ibatorgenerated Tue Sep 25 16:43:19 COT 2012
     */
    public int deleteByPrimaryKey(FuncionRolKey key) {
        int rows = getSqlMapClientTemplate().delete("CARDEL_TGPR_FUNCION_ROLES.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CARDEL.TGPR_FUNCION_ROLES
     *
     * @ibatorgenerated Tue Sep 25 16:43:19 COT 2012
     */
    public void insert(FuncionRol record) {
        getSqlMapClientTemplate().insert("CARDEL_TGPR_FUNCION_ROLES.ibatorgenerated_insert", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CARDEL.TGPR_FUNCION_ROLES
     *
     * @ibatorgenerated Tue Sep 25 16:43:19 COT 2012
     */
    public void insertSelective(FuncionRol record) {
        getSqlMapClientTemplate().insert("CARDEL_TGPR_FUNCION_ROLES.ibatorgenerated_insertSelective", record);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CARDEL.TGPR_FUNCION_ROLES
     *
     * @ibatorgenerated Tue Sep 25 16:43:19 COT 2012
     */
    public FuncionRol selectByPrimaryKey(FuncionRolKey key) {
        FuncionRol record = (FuncionRol) getSqlMapClientTemplate().queryForObject("CARDEL_TGPR_FUNCION_ROLES.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CARDEL.TGPR_FUNCION_ROLES
     *
     * @ibatorgenerated Tue Sep 25 16:43:19 COT 2012
     */
    public int updateByPrimaryKeySelective(FuncionRol record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_FUNCION_ROLES.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table CARDEL.TGPR_FUNCION_ROLES
     *
     * @ibatorgenerated Tue Sep 25 16:43:19 COT 2012
     */
    public int updateByPrimaryKey(FuncionRol record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_FUNCION_ROLES.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }
}