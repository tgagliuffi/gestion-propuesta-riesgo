package bbva.pe.gpr.daoImpl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import bbva.pe.gpr.bean.UsuarioOficina;
import bbva.pe.gpr.bean.Usuario;
import bbva.pe.gpr.dao.UsuarioOficinaDAO;

public class UsuarioOficinaDAOImpl extends SqlMapClientDaoSupport implements UsuarioOficinaDAO{

	public UsuarioOficinaDAOImpl() {
        super();
    }
	
	@SuppressWarnings("unchecked")
	public List<UsuarioOficina> getLstOficinaAsignada(Usuario user) {
		return (List<UsuarioOficina>)getSqlMapClientTemplate().queryForList("DELGPR_TGPR_USUARIO_OFICINA.getLstOficinaAsignada",user);
	}

	public void saveOficinaAsignada(UsuarioOficina oficinaAsignada) {
	getSqlMapClientTemplate().insert("DELGPR_TGPR_USUARIO_OFICINA.getLstSaveAsignarOficina", oficinaAsignada); 
	}
	
	public void deleteOficinaAsignada(String codOficina) {
    getSqlMapClientTemplate().update("DELGPR_TGPR_USUARIO_OFICINA.getUpdateEstado", codOficina);		
	}

	public String getOficinaAsignadaExiste(UsuarioOficina oficinaAsignada) {
		return getSqlMapClientTemplate().queryForList("DELGPR_TGPR_USUARIO_OFICINA.getCountOficinaAsignada", oficinaAsignada).get(0).toString();
	}
	
	@SuppressWarnings("unchecked")
	public List<UsuarioOficina> getLstOficinasByUsuario(UsuarioOficina oficinaAsignada)throws Exception {
		return getSqlMapClientTemplate().queryForList("DELGPR_TGPR_USUARIO_OFICINA.getLstOficinasByUsuario", oficinaAsignada);
	}
}