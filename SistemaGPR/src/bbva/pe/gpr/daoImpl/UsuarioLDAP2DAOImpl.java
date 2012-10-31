package bbva.pe.gpr.daoImpl;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import bbva.pe.gpr.bean.UsuarioLDAP2;
import bbva.pe.gpr.dao.UsuarioLDAP2DAO;



public class UsuarioLDAP2DAOImpl extends SqlMapClientDaoSupport implements UsuarioLDAP2DAO {

   public UsuarioLDAP2DAOImpl() {
        super();
    }

   public int deleteByPrimaryKey(String codusu) {
        UsuarioLDAP2 key = new UsuarioLDAP2();
        key.setCodusu(codusu);
        int rows = getSqlMapClientTemplate().delete("IIWX_LDAPPERU2.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

   public void insert(UsuarioLDAP2 record) {
        getSqlMapClientTemplate().insert("IIWX_LDAPPERU2.ibatorgenerated_insert", record);
    }

   
   
   
   public void insertSelective(UsuarioLDAP2 record) {
        getSqlMapClientTemplate().insert("IIWX_LDAPPERU2.ibatorgenerated_insertSelective", record);
    }

 
   
   
   public UsuarioLDAP2 selectByPrimaryKey(String codusu) {
        UsuarioLDAP2 key = new UsuarioLDAP2();
        key.setCodusu(codusu);
        UsuarioLDAP2 record = (UsuarioLDAP2) getSqlMapClientTemplate().queryForObject("IIWX_LDAPPERU2.ibatorgenerated_selectByPrimaryKey", key);
       return record;
    }

   
   
   public int updateByPrimaryKeySelective(UsuarioLDAP2 record) {
        int rows = getSqlMapClientTemplate().update("IIWX_LDAPPERU2.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

   
   
   public int updateByPrimaryKey(UsuarioLDAP2 record) {
        int rows = getSqlMapClientTemplate().update("IIWX_LDAPPERU2.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }
   
   
   
}