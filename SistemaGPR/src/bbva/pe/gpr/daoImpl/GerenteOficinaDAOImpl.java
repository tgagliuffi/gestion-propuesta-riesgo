package bbva.pe.gpr.daoImpl;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import bbva.pe.gpr.dao.GerenteOficinaDAO;

public class GerenteOficinaDAOImpl extends SqlMapClientDaoSupport implements
		GerenteOficinaDAO {

	public GerenteOficinaDAOImpl() {
		super();
	}

	public String getValidarUsuario(String codUsuario) {
			return getSqlMapClientTemplate().queryForList("CARDEL_TCARDEL_GERENTE.getValidarUsuario", codUsuario).get(0).toString();
	}

	public String getJefeInmediatoOficina(String codUsuario) {
		if(!getSqlMapClientTemplate().queryForList("CARDEL_TCARDEL_GERENTE.getJefeInmediatoOficina", codUsuario).isEmpty()){
			return getSqlMapClientTemplate().queryForList("CARDEL_TCARDEL_GERENTE.getJefeInmediatoOficina", codUsuario).get(0).toString();
		}else{
			return "";
		}    
	}

	public String getJefeInmediatoRiesgo(String codUsuario) {
		if(!getSqlMapClientTemplate().queryForList("CARDEL_TCARDEL_GERENTE.getJefeInmediatoRiesgo", codUsuario).isEmpty()){
			return getSqlMapClientTemplate().queryForList("CARDEL_TCARDEL_GERENTE.getJefeInmediatoRiesgo", codUsuario).get(0).toString();
		}else{
			return "";
		}
	}

	public String getValidarUsuarioRiesgos(String codUsuario) {
			return getSqlMapClientTemplate().queryForList("CARDEL_TCARDEL_GERENTE.getValidarUsuarioRiesgos", codUsuario).get(0).toString();
	}
}