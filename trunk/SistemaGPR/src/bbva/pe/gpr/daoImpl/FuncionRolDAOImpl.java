package bbva.pe.gpr.daoImpl;

import java.util.List;

import bbva.pe.gpr.bean.Funcion;
import bbva.pe.gpr.bean.FuncionRol;
import bbva.pe.gpr.bean.FuncionRolKey;
import bbva.pe.gpr.dao.FuncionRolDAO;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class FuncionRolDAOImpl extends SqlMapClientDaoSupport implements FuncionRolDAO {

    public FuncionRolDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(FuncionRolKey key) {
        int rows = getSqlMapClientTemplate().delete("DELGPR_TGPR_FUNCION_ROLES.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public void insert(FuncionRol record) {
        getSqlMapClientTemplate().insert("DELGPR_TGPR_FUNCION_ROLES.ibatorgenerated_insert", record);
    }

    public void insertSelective(FuncionRol record) {
        getSqlMapClientTemplate().insert("DELGPR_TGPR_FUNCION_ROLES.ibatorgenerated_insertSelective", record);
    }

    public FuncionRol selectByPrimaryKey(FuncionRolKey key) {
        FuncionRol record = (FuncionRol) getSqlMapClientTemplate().queryForObject("DELGPR_TGPR_FUNCION_ROLES.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByPrimaryKeySelective(FuncionRol record) {
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_FUNCION_ROLES.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }
    public int updateByPrimaryKey(FuncionRol record) {
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_FUNCION_ROLES.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

	@SuppressWarnings("unchecked")
	public List<FuncionRol> getLstFuncionRol(FuncionRol funcionRolBean) {
		return getSqlMapClientTemplate().queryForList("DELGPR_TGPR_FUNCION_ROLES.getLstFuncionRolByCriteria", funcionRolBean);
	}

	public void saveFuncionRol(FuncionRol funcionRolBean) throws Exception {
     getSqlMapClientTemplate().insert("DELGPR_TGPR_FUNCION_ROLES.getLstSaveFuncionRoles", funcionRolBean);		
	}
	
	@SuppressWarnings("unchecked")
	public List<Funcion> getLstRolFuncionesUsuario(String codUsuario)throws Exception {
		return getSqlMapClientTemplate().queryForList("DELGPR_TGPR_FUNCION_ROLES.getRolesFuncionxUsuario", codUsuario);
	}

	@SuppressWarnings("unchecked")
	public List<Funcion> getNivelDictamen(String codUsuario)throws Exception {
		return getSqlMapClientTemplate().queryForList("DELGPR_TGPR_FUNCION_ROLES.getNivelDictamen", codUsuario);
	}
}