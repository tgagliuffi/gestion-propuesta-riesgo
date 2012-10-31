package bbva.pe.gpr.service;

import java.util.List;

import bbva.pe.gpr.bean.MultitablaDetalle;
import bbva.pe.gpr.bean.MultitablaDetalleKey;

public interface MultitablaDetalleService {
	public int deleteMultitablaDTByPrimaryKey(MultitablaDetalleKey record)throws Exception ;	   	    
	public void insertMultitablaDTSelective(MultitablaDetalle record)throws Exception ;
	public MultitablaDetalle selectMultitablaDTByPrimaryKey(String codMult, String codelem)throws Exception ;	    
	public int updateMultitablaDTByPrimaryKeySelective(MultitablaDetalle record)throws Exception ;	  
	public List<MultitablaDetalle> getLstMultitablaDetalle(String codMult)throws Exception ;
	int updateCondicionCliente(MultitablaDetalle record)throws Exception ;

}
