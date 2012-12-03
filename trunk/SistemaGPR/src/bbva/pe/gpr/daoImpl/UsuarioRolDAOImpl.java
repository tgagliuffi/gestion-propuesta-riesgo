package bbva.pe.gpr.daoImpl;


import bbva.pe.gpr.bean.UsuarioRol;
import bbva.pe.gpr.bean.UsuarioRolKey;
import bbva.pe.gpr.dao.UsuarioRolDAO;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class UsuarioRolDAOImpl extends SqlMapClientDaoSupport implements UsuarioRolDAO {

    public UsuarioRolDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(UsuarioRolKey key) {
        int rows = getSqlMapClientTemplate().delete("DELGPR_TGPR_USUARIO_ROLES.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public void insert(UsuarioRol record) {
        getSqlMapClientTemplate().insert("DELGPR_TGPR_USUARIO_ROLES.ibatorgenerated_insert", record);
    }

    public void insertSelective(UsuarioRol record) {
        getSqlMapClientTemplate().insert("DELGPR_TGPR_USUARIO_ROLES.ibatorgenerated_insertSelective", record);
    }

    public UsuarioRol selectByPrimaryKey(UsuarioRolKey key) {
        UsuarioRol record = (UsuarioRol) getSqlMapClientTemplate().queryForObject("DELGPR_TGPR_USUARIO_ROLES.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByPrimaryKeySelective(UsuarioRol record) {
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_USUARIO_ROLES.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(UsuarioRol record) {
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_USUARIO_ROLES.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

	@SuppressWarnings("unchecked")
	public int getUsuarioAsignadoRol(UsuarioRol record) {
		UsuarioRol getUsuarioRoL=(UsuarioRol)getSqlMapClientTemplate().queryForObject("DELGPR_TGPR_USUARIO_ROLES.getUsuarioRolAsignado", record);
		if(getUsuarioRoL!=null){
			return 1;
		}else{
			return 0;		
		}
	}
}