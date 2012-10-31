package bbva.pe.gpr.daoImpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import bbva.pe.gpr.bean.Usuario;
import bbva.pe.gpr.dao.UsuarioDAO;

public class UsuarioDAOImpl extends SqlMapClientDaoSupport implements UsuarioDAO {

    public UsuarioDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(String codUsuario) {
        Usuario key = new Usuario();
        key.setCodUsuario(codUsuario);
        int rows = getSqlMapClientTemplate().delete("CARDEL_TGPR_USUARIOS.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public void insert(Usuario record) {
        getSqlMapClientTemplate().insert("CARDEL_TGPR_USUARIOS.ibatorgenerated_insert", record);
    }

    public String insertSelective(Usuario record) {
    	Usuario usuarioBean =  (Usuario)getSqlMapClientTemplate().insert("CARDEL_TGPR_USUARIOS.ibatorgenerated_insertSelective", record);
    	return usuarioBean.getCodUsuario();
    }

    public Usuario selectByPrimaryKey(String codUsuario) {
        Usuario key = new Usuario();
        key.setCodUsuario(codUsuario);
        Usuario record = (Usuario) getSqlMapClientTemplate().queryForObject("CARDEL_TGPR_USUARIOS.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByPrimaryKeySelective(Usuario record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_USUARIOS.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(Usuario record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_USUARIOS.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }
    
    @SuppressWarnings("unchecked")
	public List<Usuario> getLstUsuarios(Usuario usuarioBean){
    	return  (List<Usuario>)getSqlMapClientTemplate().queryForList("CARDEL_TGPR_USUARIOS.getLstUsuarios",usuarioBean);
    }

    public int getAsignarOficina(HashMap<String, String> record) {
		int rows=getSqlMapClientTemplate().update("CARDEL_TGPR_USUARIOS.getAsignarOficina",record);
		return rows;
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> getLstUsuarioAll() {
		return (List<Usuario>)getSqlMapClientTemplate().queryForList("CARDEL_TGPR_USUARIOS.getLstUsuarioAll");
	}
}