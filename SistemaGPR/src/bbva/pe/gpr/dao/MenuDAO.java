package bbva.pe.gpr.dao;

import bbva.pe.gpr.bean.Menu;
import java.math.BigDecimal;
import java.util.List;

public interface MenuDAO {
    int deleteByPrimaryKey(BigDecimal codMenu);
    void insert(Menu record);
    void insertSelective(Menu record);
    Menu selectByPrimaryKey(BigDecimal codMenu);
    int updateByPrimaryKeySelective(Menu record);
    int updateByPrimaryKey(Menu record);
    List<Menu> getLstMenuxUsuario(String codUsuario);
}