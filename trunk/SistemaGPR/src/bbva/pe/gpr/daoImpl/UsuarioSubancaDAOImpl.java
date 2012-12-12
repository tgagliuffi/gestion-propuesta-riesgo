package bbva.pe.gpr.daoImpl;


import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import bbva.pe.gpr.bean.BancaSub;
import bbva.pe.gpr.bean.UsuarioSubanca;
import bbva.pe.gpr.bean.UsuarioSubancaKey;
import bbva.pe.gpr.dao.UsuarioSubancaDAO;

public class UsuarioSubancaDAOImpl extends SqlMapClientDaoSupport implements UsuarioSubancaDAO {

    public UsuarioSubancaDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(UsuarioSubancaKey key) {
        int rows = getSqlMapClientTemplate().delete("DELGPR_TGPR_USUARIO_SUBANCAS.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public void insert(UsuarioSubanca record) {
        getSqlMapClientTemplate().insert("DELGPR_TGPR_USUARIO_SUBANCAS.ibatorgenerated_insert", record);
    }

    public void insertSelective(UsuarioSubanca record) {
        getSqlMapClientTemplate().insert("DELGPR_TGPR_USUARIO_SUBANCAS.ibatorgenerated_insertSelective", record);
    }

    public UsuarioSubanca selectByPrimaryKey(UsuarioSubancaKey key) {
        UsuarioSubanca record = (UsuarioSubanca) getSqlMapClientTemplate().queryForObject("DELGPR_TGPR_USUARIO_SUBANCAS.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByPrimaryKeySelective(UsuarioSubanca record) {
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_USUARIO_SUBANCAS.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(UsuarioSubanca record) {
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_USUARIO_SUBANCAS.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

	public UsuarioSubanca getSubBancaPorUsuario(BancaSub subanca)throws Exception {
		return (UsuarioSubanca)getSqlMapClientTemplate().queryForObject("DELGPR_TGPR_USUARIO_SUBANCAS.getSubBancaPorUsuario", subanca);
	}
}