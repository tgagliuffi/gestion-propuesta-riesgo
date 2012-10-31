package bbva.pe.gpr.dao;

import java.math.BigDecimal;
import java.util.HashMap;

import bbva.pe.gpr.bean.GarantiaDetalle;
import bbva.pe.gpr.bean.GarantiaDetalleKey;
import bbva.pe.gpr.bean.SolicitudDetalle;

public interface GarantiaDetalleDAO {
    int deleteByPrimaryKey(GarantiaDetalleKey key);
    void insert(GarantiaDetalle record);
    void insertSelective(GarantiaDetalle record);
    GarantiaDetalle selectByPrimaryKey(GarantiaDetalleKey key);
    int updateByPrimaryKeySelective(GarantiaDetalle record);
    int updateByPrimaryKey(GarantiaDetalle record);
	HashMap<String,BigDecimal> getlstProdGaranDeta(SolicitudDetalle bean);
}