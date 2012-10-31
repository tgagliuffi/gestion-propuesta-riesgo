package bbva.pe.gpr.daoImpl;

import java.util.List;

import bbva.pe.gpr.bean.Oficina;
import bbva.pe.gpr.dao.OficinaDAO;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class OficinaDAOImpl extends SqlMapClientDaoSupport implements OficinaDAO {

    public OficinaDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(String codOficina) {
        Oficina key = new Oficina();
        key.setCodOficina(codOficina);
        int rows = getSqlMapClientTemplate().delete("CARDEL_TGPR_OFICINAS.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

     public void insert(Oficina record) {
        getSqlMapClientTemplate().insert("CARDEL_TGPR_OFICINAS.ibatorgenerated_insert", record);
    }

    public void insertSelective(Oficina record) {
        getSqlMapClientTemplate().insert("CARDEL_TGPR_OFICINAS.ibatorgenerated_insertSelective", record);
    }

    public Oficina selectByPrimaryKey(String codOficina) {
        Oficina key = new Oficina();
        key.setCodOficina(codOficina);
        Oficina record = (Oficina) getSqlMapClientTemplate().queryForObject("CARDEL_TGPR_OFICINAS.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

   public int updateByPrimaryKeySelective(Oficina record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_OFICINAS.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(Oficina record) {
        int rows = getSqlMapClientTemplate().update("CARDEL_TGPR_OFICINAS.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

	public Oficina getOficinaForUsuario(String idUsuario) {
		return (Oficina)getSqlMapClientTemplate().queryForObject("CARDEL_TGPR_OFICINAS.getOficinaForUsuario",idUsuario);
		}

	@SuppressWarnings("unchecked")
	public List<Oficina> getLstOficinaxTerritorio(Oficina oficina) {
		return (List<Oficina>)getSqlMapClientTemplate().queryForList("CARDEL_TGPR_OFICINAS.getLstOficina",oficina);
	}
}