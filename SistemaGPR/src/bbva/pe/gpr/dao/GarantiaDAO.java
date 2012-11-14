package bbva.pe.gpr.dao;

import bbva.pe.gpr.bean.Garantia;
import java.math.BigDecimal;

public interface GarantiaDAO {
	int deleteByPrimaryKey(BigDecimal codGarantia);
	void insert(Garantia record);
	void insertSelective(Garantia record);
	Garantia selectByPrimaryKey(BigDecimal codGarantia);
	int updateByPrimaryKeySelective(Garantia record);
	int updateByPrimaryKey(Garantia record);
	Garantia getProductoGarantia(String idProducto);
}