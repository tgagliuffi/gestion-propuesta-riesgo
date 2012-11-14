package bbva.pe.gpr.dao;

import bbva.pe.gpr.bean.Campania;
import bbva.pe.gpr.bean.Producto;

import java.math.BigDecimal;
import java.util.List;


public interface ProductoDAO {
    int deleteByPrimaryKey(BigDecimal codProducto)throws Exception ;
	void insert(Producto record)throws Exception ; 
	void insertSelective(Producto record)throws Exception ;
	Producto selectByPrimaryKey(BigDecimal codProducto)throws Exception ;
	int updateByPrimaryKeySelective(Producto record)throws Exception ;
	int updateByPrimaryKey(Producto record)throws Exception ;
	List<Producto> getLstProductoByCriteria(Producto record)throws Exception ; 
	List<Campania> getlstCampaniaByCriteria(Campania campanbean);
}