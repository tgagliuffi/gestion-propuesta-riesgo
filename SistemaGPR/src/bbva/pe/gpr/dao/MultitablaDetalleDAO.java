package bbva.pe.gpr.dao;

import java.util.HashMap;
import java.util.List;

import bbva.pe.gpr.bean.MultitablaDetalle;
import bbva.pe.gpr.bean.MultitablaDetalleKey;

public interface MultitablaDetalleDAO {
    int deleteByPrimaryKey(MultitablaDetalleKey key)throws Exception ;
	void insert(MultitablaDetalle record)throws Exception ;
	void insertSelective(MultitablaDetalle record)throws Exception ;
	MultitablaDetalle selectByPrimaryKey(String codMult, String codElem)throws Exception ;
	int updateByPrimaryKeySelective(MultitablaDetalle record)throws Exception ;
	int updateByPrimaryKey(MultitablaDetalle record)throws Exception ;
	List<MultitablaDetalle> getLstMultitablaDetalle(String codMult)throws Exception ;
	String lstMultitablaDetalle(HashMap< String, String> getValores)throws Exception ;
	int updateCondicionCliente(MultitablaDetalle record)throws Exception ;
	List<MultitablaDetalle> getLstValoresCondicion(String codMult );
}