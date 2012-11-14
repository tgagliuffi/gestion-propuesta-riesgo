package bbva.pe.gpr.daoImpl;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import bbva.pe.gpr.dao.GerenteOficinaDAO;

public class GerenteOficinaDAOImpl extends SqlMapClientDaoSupport implements GerenteOficinaDAO{

	 public GerenteOficinaDAOImpl() {
	        super();
	    }

	public String getValidarUsuario(String codUsuario) {
		return getSqlMapClientTemplate().queryForList("CARDEL_TCARDEL_GERENTE.getValidarUsuario", codUsuario).get(0).toString();
	}
}
