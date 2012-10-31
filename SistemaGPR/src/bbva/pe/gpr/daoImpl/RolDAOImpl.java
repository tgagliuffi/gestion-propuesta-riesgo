package bbva.pe.gpr.daoImpl;

import bbva.pe.gpr.bean.Rol;
import bbva.pe.gpr.dao.RolDAO;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class RolDAOImpl extends SqlMapClientDaoSupport implements RolDAO {

	public RolDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(BigDecimal codRol) {
        Rol key = new Rol();
        key.setCodRol(codRol);
        int rows = getSqlMapClientTemplate().delete("CARDEL_TGPR_ROLES.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public void insert(Rol record) {
        getSqlMapClientTemplate().insert("CARDEL_TGPR_ROLES.ibatorgenerated_insert", record);
    }

    public void insertSelective(Rol record) {
        getSqlMapClientTemplate().insert("CARDEL_TGPR_ROLES.ibatorgenerated_insertSelective", record);
    }

    public Rol selectByPrimaryKey(BigDecimal codRol) {
        Rol key = new Rol();
        key.setCodRol(codRol);
        Rol record = (Rol) getSqlMapClientTemplate().queryForObject("CARDEL_TGPR_ROLES.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

   public int updateByPrimaryKeySelective(Rol record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_ROLES.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(Rol record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_ROLES.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

	@SuppressWarnings("unchecked")
	public List<Rol> getLstRolesByCriteria(Rol rolBean) {
		return (List<Rol>)getSqlMapClientTemplate().queryForList("CARDEL_TGPR_ROLES.getLstRolesByCriteria", rolBean);
	}
}