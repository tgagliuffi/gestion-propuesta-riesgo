package bbva.pe.gpr.daoImpl;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.dao.SolicitudRechazadaDAO;

public class SolicitudRechazadaDAOImpl extends SqlMapClientDaoSupport implements SolicitudRechazadaDAO{

	public void insertSolicitudRechazada(Solicitud solicitud) {
      getSqlMapClientTemplate().insert("CARDEL_TGPR_SOLICITUD_RECHAZADA.getInsertSolicitudRechazada", solicitud);		
	}

}
