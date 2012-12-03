package bbva.pe.gpr.daoImpl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import bbva.pe.gpr.bean.Estadistica;
import bbva.pe.gpr.dao.EstadisticaDAO;

public class EstadisticaDAOImpl extends SqlMapClientDaoSupport  implements EstadisticaDAO {

	public EstadisticaDAOImpl() {
		super();
	}

	@SuppressWarnings("unchecked")
	
	public List<Estadistica> selectAsignacion(Estadistica filtro)
			throws Exception {
		return getSqlMapClientTemplate().queryForList("DELGPR_TGPR_ESTADISTICAS.selectAsignacion", filtro);
	}

	@SuppressWarnings("unchecked")
	
	public List<Estadistica> selectAtencion(Estadistica filtro)
			throws Exception {
		return getSqlMapClientTemplate().queryForList("DELGPR_TGPR_ESTADISTICAS.selectAtencion", filtro);
	}

	@SuppressWarnings("unchecked")
	
	public List<Estadistica> selectCabeceraAsignacion(Estadistica filtro)
			throws Exception {
		return getSqlMapClientTemplate().queryForList("DELGPR_TGPR_ESTADISTICAS.selectCabeceraAsignacion", filtro);
	}	

	@SuppressWarnings("unchecked")
	
	public List<Estadistica> selectCabeceraAtencion(Estadistica filtro)
			throws Exception {
		return getSqlMapClientTemplate().queryForList("DELGPR_TGPR_ESTADISTICAS.selectCabeceraAtencion", filtro);
	}
}
