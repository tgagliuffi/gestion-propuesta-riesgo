package bbva.pe.gpr.daoImpl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import bbva.pe.gpr.bean.Asignacion;
import bbva.pe.gpr.bean.AsignacionKey;
import bbva.pe.gpr.dao.AsignacionDAO;

public class AsignacionDAOImpl extends SqlMapClientDaoSupport implements AsignacionDAO {

    public AsignacionDAOImpl() {
        super();
    }
    
    /*#####################################################################################################
     * 									METODOS AUTOGENERADOS
     ######################################################################################################*/

    public int deleteByPrimaryKey(AsignacionKey key)throws Exception {
        int rows = getSqlMapClientTemplate().delete("DELGPR_TGPR_ASIGNACIONES.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(Asignacion record)throws Exception {
      return   (Long)getSqlMapClientTemplate().insert("DELGPR_TGPR_ASIGNACIONES.ibatorgenerated_insert", record);
    }
    
    public void insertAsignacion(Asignacion record)throws Exception {
       getSqlMapClientTemplate().insert("DELGPR_TGPR_ASIGNACIONES.ibatorgenerated_insert", record);
    }
    public void insertSelective(Asignacion record)throws Exception {
    	getSqlMapClientTemplate().insert("DELGPR_TGPR_ASIGNACIONES.ibatorgenerated_insertSelective", record);
    	
    }

    public Asignacion selectByPrimaryKey(AsignacionKey key)throws Exception {
        return (Asignacion)getSqlMapClientTemplate().queryForObject("DELGPR_TGPR_ASIGNACIONES.ibatorgenerated_selectByPrimaryKey", key);
    }

    public int updateByPrimaryKeySelective(Asignacion record)throws Exception {
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_ASIGNACIONES.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(Asignacion record)throws Exception {
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_ASIGNACIONES.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }
    
    /*#####################################################################################################
     * 									METODOS DE NEGOCIO
     ######################################################################################################*/
    
    public String obtenerUsuarioPorBalance(BigDecimal codBanca, BigDecimal codRol)throws Exception{
    	Map<String,Object> map =  new HashMap<String,Object>();
    	map.put("result","");
    	map.put("pCOD_BANCA",codBanca);
    	map.put("pCOD_ROL",codRol);
    	getSqlMapClientTemplate().queryForList("DELGPR_TGPR_ASIGNACIONES.getUsuarioPorBalance",map);
    	return (String) map.get("result");
    }
    
    @SuppressWarnings("unchecked")
	public List<Asignacion> getLstAsignaciones(Asignacion record)throws Exception{
    	return (List<Asignacion>) getSqlMapClientTemplate().queryForList("DELGPR_TGPR_ASIGNACIONES.getLstAsignaciones", record);	
    }
 
    
    
}