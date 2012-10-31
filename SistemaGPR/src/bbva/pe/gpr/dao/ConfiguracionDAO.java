package bbva.pe.gpr.dao;

import bbva.pe.gpr.bean.Configuracion;
import java.math.BigDecimal;

public interface ConfiguracionDAO {
    int deleteByPrimaryKey(BigDecimal codConfiguracion);
    void insert(Configuracion record);
    void insertSelective(Configuracion record);
    Configuracion selectByPrimaryKey(BigDecimal codConfiguracion);
    int updateByPrimaryKeySelective(Configuracion record);
    int updateByPrimaryKey(Configuracion record);
}