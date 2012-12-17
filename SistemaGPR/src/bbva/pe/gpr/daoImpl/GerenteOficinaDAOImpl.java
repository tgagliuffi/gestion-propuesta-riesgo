package bbva.pe.gpr.daoImpl;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import bbva.pe.gpr.bean.Usuario;
import bbva.pe.gpr.dao.GerenteOficinaDAO;

public class GerenteOficinaDAOImpl extends SqlMapClientDaoSupport implements
		GerenteOficinaDAO {

	public GerenteOficinaDAOImpl() {
		super();
	}

	public String getJefeInmediatoOficina(String codUsuario) throws Exception{
		if(!getSqlMapClientTemplate().queryForList("DELGPR_TGPR_RIESGOS.getJefeInmediatoOficina", codUsuario).isEmpty()){
			return getSqlMapClientTemplate().queryForList("DELGPR_TGPR_RIESGOS.getJefeInmediatoOficina", codUsuario).get(0).toString();
		}else{
			return "";
		}    
	}

	public String getJefeInmediatoRiesgo(String codUsuario) throws Exception{
		if(!getSqlMapClientTemplate().queryForList("DELGPR_TGPR_RIESGOS.getJefeInmediatoRiesgo", codUsuario).isEmpty()){
			return getSqlMapClientTemplate().queryForList("DELGPR_TGPR_RIESGOS.getJefeInmediatoRiesgo", codUsuario).get(0).toString();
		}else{
			return "";
		}
	}

	public String getUsuarioTipo(String codUsuario)throws Exception {
		Usuario record = (Usuario) getSqlMapClientTemplate().queryForObject("CARDEL_TCARDEL_USUARIOS.getTipoPersona", codUsuario);
		if(record==null){ return "";}else
	    return record.getCodigoUsuario();	
	    }
	
	public String getCargoChekSolicitud(String codUsuario) throws Exception{
		if(!getSqlMapClientTemplate().queryForList("DELGPR_TGPR_RIESGOS.getCargoChekSolicitud", codUsuario).isEmpty()){
			return getSqlMapClientTemplate().queryForList("DELGPR_TGPR_RIESGOS.getJefeInmediatoRiesgo", codUsuario).get(0).toString();
		}else{
			return "";
		}
	}
}