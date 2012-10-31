package bbva.pe.gpr.daoImpl;

import bbva.pe.gpr.bean.Configuracion;
import bbva.pe.gpr.dao.ConfiguracionDAO;

import java.math.BigDecimal;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ConfiguracionDAOImpl extends SqlMapClientDaoSupport implements ConfiguracionDAO {

    public ConfiguracionDAOImpl() {
        super();
    }
    
    public int deleteByPrimaryKey(BigDecimal codConfiguracion) {
        Configuracion key = new Configuracion();
        key.setCodConfiguracion(codConfiguracion);
        int rows = getSqlMapClientTemplate().delete("CARDEL_TGPR_CONFIGURACIONES.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public void insert(Configuracion record) {
        getSqlMapClientTemplate().insert("CARDEL_TGPR_CONFIGURACIONES.ibatorgenerated_insert", record);
    }

    public void insertSelective(Configuracion record) {
        getSqlMapClientTemplate().insert("CARDEL_TGPR_CONFIGURACIONES.ibatorgenerated_insertSelective", record);
    }

    public Configuracion selectByPrimaryKey(BigDecimal codConfiguracion) {
        Configuracion key = new Configuracion();
        key.setCodConfiguracion(codConfiguracion);
        Configuracion record = (Configuracion) getSqlMapClientTemplate().queryForObject("CARDEL_TGPR_CONFIGURACIONES.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByPrimaryKeySelective(Configuracion record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_CONFIGURACIONES.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(Configuracion record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_CONFIGURACIONES.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }
}