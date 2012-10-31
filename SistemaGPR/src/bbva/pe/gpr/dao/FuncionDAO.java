package bbva.pe.gpr.dao;

import bbva.pe.gpr.bean.Funcion;
import java.math.BigDecimal;
import java.util.List;

public interface FuncionDAO {
    int deleteByPrimaryKey(BigDecimal codFuncion);
    void insert(Funcion record);
    void insertSelective(Funcion record);
    Funcion selectByPrimaryKey(BigDecimal codFuncion);
    int updateByPrimaryKeySelective(Funcion record);
    int updateByPrimaryKey(Funcion record);
    List<Funcion> getLstFuncionByCriteria(Funcion record) throws Exception;
}