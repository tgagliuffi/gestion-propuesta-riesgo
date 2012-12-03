package bbva.pe.gpr.daoImpl;

import bbva.pe.gpr.bean.Menu;
import bbva.pe.gpr.dao.MenuDAO;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class MenuDAOImpl extends SqlMapClientDaoSupport implements MenuDAO {

    public MenuDAOImpl() {
        super();
    }
    public int deleteByPrimaryKey(BigDecimal codMenu) {
        Menu key = new Menu();
        key.setCodMenu(codMenu);
        int rows = getSqlMapClientTemplate().delete("DELGPR_TGPR_MENUS.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }
    public void insert(Menu record) {
        getSqlMapClientTemplate().insert("DELGPR_TGPR_MENUS.ibatorgenerated_insert", record);
    }
    public void insertSelective(Menu record) {
        getSqlMapClientTemplate().insert("DELGPR_TGPR_MENUS.ibatorgenerated_insertSelective", record);
    }
    public Menu selectByPrimaryKey(BigDecimal codMenu) {
        Menu key = new Menu();
        key.setCodMenu(codMenu);
        Menu record = (Menu) getSqlMapClientTemplate().queryForObject("DELGPR_TGPR_MENUS.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }
    public int updateByPrimaryKeySelective(Menu record) {
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_MENUS.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }
    public int updateByPrimaryKey(Menu record) {
        int rows = getSqlMapClientTemplate().update("DELGPR_TGPR_MENUS.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }
	@SuppressWarnings("unchecked")
	public List<Menu> getLstMenuxUsuario(String codUsuario) {
		List<Menu> getLstMenuxUsuario=getSqlMapClientTemplate().queryForList("DELGPR_TGPR_MENUS.getLstMenuxUsuario", codUsuario);
		return getLstMenuxUsuario;
	}
}