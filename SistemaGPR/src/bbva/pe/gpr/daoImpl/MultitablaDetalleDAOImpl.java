
package bbva.pe.gpr.daoImpl;

import java.util.HashMap;
import java.util.List;

import bbva.pe.gpr.bean.MultitablaDetalle;
import bbva.pe.gpr.bean.MultitablaDetalleKey;
import bbva.pe.gpr.dao.MultitablaDetalleDAO;


import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class MultitablaDetalleDAOImpl extends SqlMapClientDaoSupport implements MultitablaDetalleDAO {

    public MultitablaDetalleDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(MultitablaDetalleKey key) throws Exception {
        int rows = getSqlMapClientTemplate().delete("DELGPR_TGPR_MULTITABLA_DETALLE.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public void insert(MultitablaDetalle record) throws Exception {
        getSqlMapClientTemplate().insert("DELGPR_TGPR_MULTITABLA_DETALLE.ibatorgenerated_insert", record);
    }

    public void insertSelective(MultitablaDetalle record) throws Exception {
        getSqlMapClientTemplate().insert("DELGPR_TGPR_MULTITABLA_DETALLE.ibatorgenerated_insertSelective", record);
    }

    public MultitablaDetalle selectByPrimaryKey(String codMult, String codElem) throws Exception {
    	MultitablaDetalleKey key = new MultitablaDetalleKey();
    	key.setCodMultitabla(codMult);
    	key.setCodElemento(codElem);
		MultitablaDetalle record = (MultitablaDetalle) getSqlMapClientTemplate().queryForObject("DELGPR_TGPR_MULTITABLA_DETALLE.ibatorgenerated_selectByPrimaryKey", key);
		return record;
    }

    public int updateByPrimaryKeySelective(MultitablaDetalle record) throws Exception {
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_MULTITABLA_DETALLE.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(MultitablaDetalle record) {
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_MULTITABLA_DETALLE.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }
    
    @SuppressWarnings("unchecked")
	public List<MultitablaDetalle> getLstMultitablaDetalle(String codMult) {
        List<MultitablaDetalle> lista = ( List<MultitablaDetalle>) getSqlMapClientTemplate().queryForList("DELGPR_TGPR_MULTITABLA_DETALLE.getLstMultitabla", codMult);
        return lista;
    }
    
    public String lstMultitablaDetalle(HashMap<String, String> getValores) {
		return  getSqlMapClientTemplate().queryForList("DELGPR_TGPR_MULTITABLA_DETALLE.getCountData", getValores).get(0).toString();
	}

	public int updateCondicionCliente(MultitablaDetalle record) {
	    int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_MULTITABLA_DETALLE.updatedCondicionCliente", record);
        return rows;
    }

	@SuppressWarnings("unchecked")
	public List<MultitablaDetalle> getLstValoresCondicion(String codMult) {
		return getSqlMapClientTemplate().queryForList("DELGPR_TGPR_MULTITABLA_DETALLE.getLstMultitablaMensaje", codMult);
	}

	public String getDescripcion(String codigo) throws Exception {
		return getSqlMapClientTemplate().queryForList("DELGPR_TGPR_MULTITABLA_DETALLE.getMensaje", codigo).get(0).toString();
	}
	
	public int update(MultitablaDetalle record) {
	    int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_MULTITABLA_DETALLE.updated", record);
        return rows;
    }
}