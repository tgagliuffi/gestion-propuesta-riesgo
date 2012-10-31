package bbva.pe.gpr.daoImpl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import bbva.pe.gpr.bean.Funcion;
import bbva.pe.gpr.dao.FuncionDAO;

public class FuncionDAOImpl extends SqlMapClientDaoSupport implements FuncionDAO {

    public FuncionDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(BigDecimal codFuncion) {
        Funcion key = new Funcion();
        key.setCodFuncion(codFuncion);
        int rows = getSqlMapClientTemplate().delete("CARDEL_TGPR_FUNCIONES.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public void insert(Funcion record) {
        getSqlMapClientTemplate().insert("CARDEL_TGPR_FUNCIONES.ibatorgenerated_insert", record);
    }

    public void insertSelective(Funcion record) {
        getSqlMapClientTemplate().insert("CARDEL_TGPR_FUNCIONES.ibatorgenerated_insertSelective", record);
    }

    public Funcion selectByPrimaryKey(BigDecimal codFuncion) {
        Funcion key = new Funcion();
        key.setCodFuncion(codFuncion);
        Funcion record = (Funcion) getSqlMapClientTemplate().queryForObject("CARDEL_TGPR_FUNCIONES.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByPrimaryKeySelective(Funcion record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_FUNCIONES.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(Funcion record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_FUNCIONES.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }
    @SuppressWarnings("unchecked")
	public List<Funcion> getLstFuncionByCriteria(Funcion record) throws Exception{
    	return   (List<Funcion>)getSqlMapClientTemplate().queryForList("CARDEL_TGPR_FUNCIONES.getLstFuncionByCriteria", record);
    }
}