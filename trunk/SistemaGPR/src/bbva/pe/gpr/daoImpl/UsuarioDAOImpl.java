package bbva.pe.gpr.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import bbva.pe.gpr.bean.Usuario;
import bbva.pe.gpr.dao.UsuarioDAO;

public class UsuarioDAOImpl extends SqlMapClientDaoSupport implements UsuarioDAO {

    public UsuarioDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(String codUsuario) {
        Usuario key = new Usuario();
        key.setCodigoUsuario(codUsuario);
        int rows = getSqlMapClientTemplate().delete("CARDEL_TCARDEL_USUARIOS.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public void insert(Usuario record) {
        getSqlMapClientTemplate().insert("CARDEL_TCARDEL_USUARIOS.ibatorgenerated_insert", record);
    }

    public String insertSelective(Usuario record) {
    	Usuario usuarioBean =  (Usuario)getSqlMapClientTemplate().insert("CARDEL_TCARDEL_USUARIOS.ibatorgenerated_insertSelective", record);
    	return usuarioBean.getCodigoUsuario();
    }

    public Usuario selectByPrimaryKey(String codUsuario) {
        Usuario key = new Usuario();
        key.setCodigoUsuario(codUsuario);
        Usuario record = (Usuario) getSqlMapClientTemplate().queryForObject("CARDEL_TCARDEL_USUARIOS.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByPrimaryKeySelective(Usuario record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TCARDEL_USUARIOS.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(Usuario record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TCARDEL_USUARIOS.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }
    
    @SuppressWarnings("unchecked")
	public List<Usuario> getLstUsuarios(Usuario usuarioBean){
    	return  (List<Usuario>)getSqlMapClientTemplate().queryForList("CARDEL_TCARDEL_USUARIOS.getLstUsuarios",usuarioBean);
    }

	public int updateOficinaAsignada(Usuario record) throws Exception {
		int rows=getSqlMapClientTemplate().update("CARDEL_TCARDEL_USUARIOS.getAsignarOficina",record);
		return rows;
	}

	public String getUsuarioExiste(Usuario usuarioBean) {
		return getSqlMapClientTemplate().queryForList("CARDEL_TCARDEL_USUARIOS.getUsuarioExiste", usuarioBean).get(0).toString();
	}

	public int getDeleteUsuario(String codUsuario) throws Exception {
        int rows=getSqlMapClientTemplate().update("CARDEL_TCARDEL_USUARIOS.getDeleteUsuario", codUsuario);
		return rows;
	}
	
	 @SuppressWarnings("unchecked")
	public List<Usuario> getLstUsuariosRiesgo(Usuario usuarioBean)throws Exception {
	    	return  (List<Usuario>)getSqlMapClientTemplate().queryForList("CARDEL_TCARDEL_USUARIOS.getLstUsuariosRiesgo",usuarioBean);
	}
    public Usuario getUsuarioMontos(Usuario usuarioBean){
        Usuario record = (Usuario) getSqlMapClientTemplate().queryForObject("CARDEL_TCARDEL_USUARIOS.getUsuarioMontos", usuarioBean);
        return record;
    }

	public String getTipoPersona(String codUsuario) {
	       Usuario record = (Usuario) getSqlMapClientTemplate().queryForObject("CARDEL_TCARDEL_USUARIOS.getTipoPersona", codUsuario);
	       if(record==null){return "";}else
	       return record.getCodigoUsuario();
	}
	
	public String getJefeOficina(String codCargo,String codOficina) {
		Map<String, String> map= new HashMap<String, String>();
		map.put("codCargo", codCargo);
		map.put("CodOficina", codOficina);
		
	       Usuario record = (Usuario) getSqlMapClientTemplate().queryForObject("CARDEL_TCARDEL_USUARIOS.getJefeOficina", map);
	       if(record==null){return "";}else
	       return record.getCodigoUsuario();
	}

	public String codJefeInmediatoRiesgos(String codUsuario) {
	       Usuario record = (Usuario) getSqlMapClientTemplate().queryForObject("CARDEL_TCARDEL_USUARIOS.getJefeInmediatoRiesgos", codUsuario);
	       if(record==null)
	       {return "";}
	       else  return record.getCodigoUsuario();
	}
}