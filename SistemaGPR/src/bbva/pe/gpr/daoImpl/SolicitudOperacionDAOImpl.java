package bbva.pe.gpr.daoImpl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.bean.SolicitudOperacion;
import bbva.pe.gpr.bean.SolicitudOperacionKey;
import bbva.pe.gpr.dao.SolicitudOperacionDAO;

public class SolicitudOperacionDAOImpl extends SqlMapClientDaoSupport implements SolicitudOperacionDAO {

   public SolicitudOperacionDAOImpl() {
        super();
    }

/*#####################################################################################################
* 									METODOS AUTOGENERADOS
######################################################################################################*/

   
	   public int deleteByPrimaryKey(SolicitudOperacionKey key) {
	        int rows = getSqlMapClientTemplate().delete("CARDEL_TGPR_SOLICITUD_OPERACIONES.ibatorgenerated_deleteByPrimaryKey", key);
	        return rows;
	   }
	
	   public void insert(SolicitudOperacion record) {
	        getSqlMapClientTemplate().insert("CARDEL_TGPR_SOLICITUD_OPERACIONES.ibatorgenerated_insert", record);
	   }
	
	   public BigDecimal insertSelective(SolicitudOperacion record) {
		   SolicitudOperacion rst = (SolicitudOperacion)getSqlMapClientTemplate().insert("CARDEL_TGPR_SOLICITUD_OPERACIONES.ibatorgenerated_insertSelective", record);
		   return rst.getCodSolicitudOperacion();
	   }
	
	   public SolicitudOperacion selectByPrimaryKey(SolicitudOperacionKey key) {
	        SolicitudOperacion record = (SolicitudOperacion) getSqlMapClientTemplate().queryForObject("CARDEL_TGPR_SOLICITUD_OPERACIONES.ibatorgenerated_selectByPrimaryKey", key);
	        return record;
	   }
	
	   @SuppressWarnings("unchecked")
	   public List<SolicitudOperacion> selectByNroSolicitud(Solicitud s) {
			List<SolicitudOperacion> record = (List<SolicitudOperacion>) getSqlMapClientTemplate().queryForList("CARDEL_TGPR_SOLICITUD_OPERACIONES.selectByNroSolicitud", s);
	        return record;
	   }
	   
	   public int updateByPrimaryKeySelective(SolicitudOperacion record) {
	        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_SOLICITUD_OPERACIONES.ibatorgenerated_updateByPrimaryKeySelective", record);
	        return rows;
	   }
	
	   public int updateByPrimaryKey(SolicitudOperacion record) {
	        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_SOLICITUD_OPERACIONES.ibatorgenerated_updateByPrimaryKey", record);
	        return rows;
	   }
	   
/*#####################################################################################################
 * 									METODOS AUTOGENERADOS
 ######################################################################################################*/

}