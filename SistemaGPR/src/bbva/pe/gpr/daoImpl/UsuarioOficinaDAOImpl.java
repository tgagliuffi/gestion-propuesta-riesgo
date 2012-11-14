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
		return (List<UsuarioOficina>)getSqlMapClientTemplate().queryForList("CARDEL_TGPR_USUARIO_OFICINA.getLstOficinaAsignada",user);
	}

	public void saveOficinaAsignada(UsuarioOficina oficinaAsignada) {
	getSqlMapClientTemplate().insert("CARDEL_TGPR_USUARIO_OFICINA.getLstSaveAsignarOficina", oficinaAsignada); 
	}
	
	public void deleteOficinaAsignada(String codOficina) {
    getSqlMapClientTemplate().update("CARDEL_TGPR_USUARIO_OFICINA.getUpdateEstado", codOficina);		
	}

	public String getOficinaAsignadaExiste(UsuarioOficina oficinaAsignada) {
		return getSqlMapClientTemplate().queryForList("CARDEL_TGPR_USUARIO_OFICINA.getCountOficinaAsignada", oficinaAsignada).get(0).toString();
	}
}