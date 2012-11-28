package bbva.pe.gpr.daoImpl;

import java.util.List;

import bbva.pe.gpr.bean.Oficina;
import bbva.pe.gpr.dao.OficinaDAO;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class OficinaDAOImpl extends SqlMapClientDaoSupport implements OficinaDAO {

    public OficinaDAOImpl() {
        super();
    }

    public int deleteByPrimaryKey(String codOficina)throws Exception  {
        Oficina key = new Oficina();
        key.setCodOficina(codOficina);
        int rows = getSqlMapClientTemplate().delete("DELGPR_TGPR_OFICINAS.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

     public void insert(Oficina record)throws Exception  {
        getSqlMapClientTemplate().insert("DELGPR_TGPR_OFICINAS.ibatorgenerated_insert", record);
    }

    public void insertSelective(Oficina record)throws Exception  {
        getSqlMapClientTemplate().insert("DELGPR_TGPR_OFICINAS.ibatorgenerated_insertSelective", record);
    }

    public Oficina selectByPrimaryKey(String codOficina)throws Exception  {
        Oficina key = new Oficina();
        key.setCodOficina(codOficina);
        Oficina record = (Oficina) getSqlMapClientTemplate().queryForObject("DELGPR_TGPR_OFICINAS.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

   public int updateByPrimaryKeySelective(Oficina record)throws Exception  {
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_OFICINAS.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(Oficina record) throws Exception {
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_OFICINAS.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

	public Oficina getOficinaForUsuario(String idUsuario)throws Exception  {
		return (Oficina)getSqlMapClientTemplate().queryForObject("DELGPR_TGPR_OFICINAS.getOficinaForUsuario",idUsuario);
		}

	@SuppressWarnings("unchecked")
	public List<Oficina> getLstOficinaxTerritorio(Oficina oficina)throws Exception  {
		return (List<Oficina>)getSqlMapClientTemplate().queryForList("DELGPR_TGPR_OFICINAS.getLstOficina",oficina);
	}

	@SuppressWarnings("unchecked")
	public List<Oficina> getLstOficinaByCriteria(Oficina record) throws Exception {
		return (List<Oficina>)getSqlMapClientTemplate().queryForList("DELGPR_TGPR_OFICINAS.getLstOficinasByCriteria",record);
	}
}