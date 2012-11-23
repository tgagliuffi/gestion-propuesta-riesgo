package bbva.pe.gpr.daoImpl;

import bbva.pe.gpr.bean.Rol;
import bbva.pe.gpr.dao.RolDAO;
import bbva.pe.gpr.util.Constant;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class RolDAOImpl extends SqlMapClientDaoSupport implements RolDAO {

	public RolDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(BigDecimal codRol) {
        Rol key = new Rol();
        key.setCodRol(codRol);
        int rows = getSqlMapClientTemplate().delete("CARDEL_TGPR_ROLES.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public void insert(Rol record) {
        getSqlMapClientTemplate().insert("CARDEL_TGPR_ROLES.ibatorgenerated_insert", record);
    }

    public void insertSelective(Rol record) {
        getSqlMapClientTemplate().insert("CARDEL_TGPR_ROLES.ibatorgenerated_insertSelective", record);
    }

    public Rol selectByPrimaryKey(BigDecimal codRol) {
        Rol key = new Rol();
        key.setCodRol(codRol);
        Rol record = (Rol) getSqlMapClientTemplate().queryForObject("CARDEL_TGPR_ROLES.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

   public int updateByPrimaryKeySelective(Rol record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_ROLES.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(Rol record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_ROLES.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

	@SuppressWarnings("unchecked")
	public List<Rol> getLstRolesByCriteria(Rol rolBean) {
		return (List<Rol>)getSqlMapClientTemplate().queryForList("CARDEL_TGPR_ROLES.getLstRolesByCriteria", rolBean);
	}

	public List<Rol> getLstRol() {
		return (List<Rol>)getSqlMapClientTemplate().queryForList("CARDEL_TGPR_ROLES.getLstRoles");
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> saveRol(String codRolHdn,String codRol, String descripRol,String referRol) {
		
		Map<String, String> mapResult = new HashMap<String, String>();
		try {
	        Rol rol= new Rol();
			rol.setCodRolHdn(new BigDecimal(codRolHdn));
			rol.setCodRol(new BigDecimal(codRol));
			rol.setDescripcion(descripRol);
			rol.setReferencia(referRol);
			rol.setEstado(Constant.ESTADO_ACTIVO);
            Rol  rolHdn= new Rol();
            rolHdn.setCodRol(new BigDecimal(codRolHdn));
            rolHdn.setEstado(Constant.ESTADO_ACTIVO);
			List<Rol> listRol = getSqlMapClientTemplate().queryForList("CARDEL_TGPR_ROLES.getRoles",rolHdn);
			if(!listRol.isEmpty()){
			int rows=getSqlMapClientTemplate().update("CARDEL_TGPR_ROLES.ibatorgenerated_updateByPrimaryKey",rol);	
			System.out.print(""+rows);
			}else{
				getSqlMapClientTemplate().insert("CARDEL_TGPR_ROLES.ibatorgenerated_insert",rol);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			
			try{
				if(e.getCause().getMessage().contains("ORA-00001")){
					mapResult.put("msgError", "etiqueta.error.adminform.canal.unique.constraint");
				}else if(e.getCause().getCause().getMessage().contains("ORA-02292")){
					mapResult.put("msgError", "etiqueta.error.adminform.canal.foreign.constraint");
				}else{
					mapResult.put("msgError", "etiqueta.error.save");
				}
			}catch(Exception excep){
				mapResult.put("msgError", "etiqueta.error.save");
			}
		}
		
		return mapResult;
	}
}