package bbva.pe.gpr.dao;

import bbva.pe.gpr.bean.Rol;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface RolDAO {
	int deleteByPrimaryKey(BigDecimal codRol);
    void insert(Rol record);
    void insertSelective(Rol record);
    Rol selectByPrimaryKey(BigDecimal codRol);
    int updateByPrimaryKeySelective(Rol record);
    int updateByPrimaryKey(Rol record);
    List<Rol> getLstRolesByCriteria(Rol rolBean)throws Exception ;
    Map<String, String> saveRol(String codRolHdn,String codRol,String descripRol,String referRol);
    List<Rol> getLstRol();
}