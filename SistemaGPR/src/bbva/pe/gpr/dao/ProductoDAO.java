package bbva.pe.gpr.dao;

import bbva.pe.gpr.bean.Producto;
import bbva.pe.gpr.bean.SolicitudDetalle;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;


public interface ProductoDAO {
    int deleteByPrimaryKey(BigDecimal codProducto)throws Exception ;
	void insert(Producto record)throws Exception ; 
	void insertSelective(Producto record)throws Exception ;
	Producto selectByPrimaryKey(BigDecimal codProducto)throws Exception ;
	int updateByPrimaryKeySelective(Producto record)throws Exception ;
	int updateByPrimaryKey(Producto record)throws Exception ;
	List<Producto> getLstProducto(Producto record)throws Exception ; 
	@SuppressWarnings("rawtypes")
	HashMap getlstProdGaranDeta(SolicitudDetalle bean)throws Exception ;
}