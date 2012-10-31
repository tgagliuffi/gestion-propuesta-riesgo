package bbva.pe.gpr.daoImpl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import bbva.pe.gpr.bean.Asignacion;
import bbva.pe.gpr.bean.AsignacionKey;
import bbva.pe.gpr.dao.AsignacionDAO;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class AsignacionDAOImpl extends SqlMapClientDaoSupport implements AsignacionDAO {

    public AsignacionDAOImpl() {
        super();
    }
    
    /*#####################################################################################################
     * 									METODOS AUTOGENERADOS
     ######################################################################################################*/

    public int deleteByPrimaryKey(AsignacionKey key) {
        int rows = getSqlMapClientTemplate().delete("CARDEL_TGPR_ASIGNACIONES.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(Asignacion record) {
      return   (Long)getSqlMapClientTemplate().insert("CARDEL_TGPR_ASIGNACIONES.ibatorgenerated_insert", record);
    }

    public void insertSelective(Asignacion record) {
    	getSqlMapClientTemplate().insert("CARDEL_TGPR_ASIGNACIONES.ibatorgenerated_insertSelective", record);
    	
    }

    public Asignacion selectByPrimaryKey(AsignacionKey key) {
        return (Asignacion)getSqlMapClientTemplate().queryForObject("CARDEL_TGPR_ASIGNACIONES.ibatorgenerated_selectByPrimaryKey", key);
    }

    public int updateByPrimaryKeySelective(Asignacion record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_ASIGNACIONES.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(Asignacion record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_ASIGNACIONES.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }
    
    /*#####################################################################################################
     * 									METODOS DE NEGOCIO
     ######################################################################################################*/
    
    public String obtenerUsuarioPorBalance(BigDecimal codBanca, BigDecimal codRol){
    	Map<String,Object> map =  new HashMap<String,Object>();
    	map.put("result","");
    	map.put("pCOD_BANCA",codBanca);
    	map.put("pCOD_ROL",codRol);
    	getSqlMapClientTemplate().queryForList("CARDEL_TGPR_ASIGNACIONES.getUsuarioPorBalance",map);
    	return (String) map.get("result");
    }
    
 
    
    
}