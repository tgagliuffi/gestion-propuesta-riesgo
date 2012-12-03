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

   
	   public int deleteByPrimaryKey(SolicitudOperacionKey key)throws Exception{
	        int rows = getSqlMapClientTemplate().delete("DELGPR_TGPR_SOLICITUD_OPERACIONES.ibatorgenerated_deleteByPrimaryKey", key);
	        return rows;
	   }
	
	   public void insert(SolicitudOperacion record)throws Exception {
	        getSqlMapClientTemplate().insert("DELGPR_TGPR_SOLICITUD_OPERACIONES.ibatorgenerated_insert", record);
	   }
	
	   public BigDecimal insertSelective(SolicitudOperacion record) throws Exception{
		   SolicitudOperacion rst = (SolicitudOperacion)getSqlMapClientTemplate().insert("DELGPR_TGPR_SOLICITUD_OPERACIONES.ibatorgenerated_insertSelective", record);
		   return rst.getCodSolicitudOperacion();
	   }
	
	   public SolicitudOperacion selectByPrimaryKey(SolicitudOperacionKey key)throws Exception {
	        SolicitudOperacion record = (SolicitudOperacion) getSqlMapClientTemplate().queryForObject("DELGPR_TGPR_SOLICITUD_OPERACIONES.ibatorgenerated_selectByPrimaryKey", key);
	        return record;
	   }
	
	   @SuppressWarnings("unchecked")
	   public List<SolicitudOperacion> selectByNroSolicitud(Solicitud s)throws Exception {
			List<SolicitudOperacion> record = (List<SolicitudOperacion>) getSqlMapClientTemplate().queryForList("DELGPR_TGPR_SOLICITUD_OPERACIONES.selectByNroSolicitud", s);
	        return record;
	   }
	   
	   public int updateByPrimaryKeySelective(SolicitudOperacion record)throws Exception {
	        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_SOLICITUD_OPERACIONES.ibatorgenerated_updateByPrimaryKeySelective", record);
	        return rows;
	   }
	
	   public int updateByPrimaryKey(SolicitudOperacion record)throws Exception {
	        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_SOLICITUD_OPERACIONES.ibatorgenerated_updateByPrimaryKey", record);
	        return rows;
	   }
	   
	/*#####################################################################################################
	 * 									METODOS AUTOGENERADOS
	 ######################################################################################################*/
	   
	   public SolicitudOperacion getListOperationsAjax(SolicitudOperacion key)throws Exception {
	        SolicitudOperacion record = (SolicitudOperacion) getSqlMapClientTemplate().queryForObject("DELGPR_TGPR_SOLICITUD_OPERACIONES.ibatorgenerated_selectByPrimaryKey", key);
	        return record;
	   }
}