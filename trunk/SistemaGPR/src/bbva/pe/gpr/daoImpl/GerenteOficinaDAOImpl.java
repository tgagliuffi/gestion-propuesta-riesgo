package bbva.pe.gpr.daoImpl;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.bean.Usuario;
import bbva.pe.gpr.dao.GerenteOficinaDAO;

public class GerenteOficinaDAOImpl extends SqlMapClientDaoSupport implements
		GerenteOficinaDAO {

	public GerenteOficinaDAOImpl() {
		super();
	}

	public String getJefeInmediatoOficina(String codUsuario) {
		if(!getSqlMapClientTemplate().queryForList("CARDEL_TGPR_RIESGOS.getJefeInmediatoOficina", codUsuario).isEmpty()){
			return getSqlMapClientTemplate().queryForList("CARDEL_TGPR_RIESGOS.getJefeInmediatoOficina", codUsuario).get(0).toString();
		}else{
			return "";
		}    
	}

	public String getJefeInmediatoRiesgo(String codUsuario) {
		if(!getSqlMapClientTemplate().queryForList("CARDEL_TGPR_RIESGOS.getJefeInmediatoRiesgo", codUsuario).isEmpty()){
			return getSqlMapClientTemplate().queryForList("CARDEL_TGPR_RIESGOS.getJefeInmediatoRiesgo", codUsuario).get(0).toString();
		}else{
			return "";
		}
	}

	public String getUsuarioTipo(String codUsuario) {
		Usuario record = (Usuario) getSqlMapClientTemplate().queryForObject("CARDEL_TGPR_USUARIOS.getTipoPersona", codUsuario);
	    return record.getCodigoUsuario();	
	    }
}