package bbva.pe.gpr.serviceImpl;

import java.util.List;

import bbva.pe.gpr.bean.MultitablaDetalle;
import bbva.pe.gpr.bean.MultitablaDetalleKey;
import bbva.pe.gpr.dao.MultitablaDetalleDAO;
import bbva.pe.gpr.service.MultitablaDetalleService;

public class MultitablaDetalleServiceImpl implements MultitablaDetalleService{
	MultitablaDetalleDAO multitablaDetalleDAO;
	
	public MultitablaDetalleServiceImpl(MultitablaDetalleDAO multitablaDetalleDAO) {
		super();
		this.multitablaDetalleDAO=multitablaDetalleDAO;
	}
	public MultitablaDetalleDAO getMultitablaDetalleDAO(){return multitablaDetalleDAO;}
	public void setMultitablaDetalleDAO(MultitablaDetalleDAO multitablaDetalleDAO){this.multitablaDetalleDAO = multitablaDetalleDAO;}
	
	public int deleteMultitablaDTByPrimaryKey(MultitablaDetalleKey record)throws Exception  {
	      return multitablaDetalleDAO.deleteByPrimaryKey(record);
	  }
	   	    
	  public void insertMultitablaDTSelective(MultitablaDetalle record) throws Exception {
		  multitablaDetalleDAO.insertSelective(record);
	  }

	  public MultitablaDetalle selectMultitablaDTByPrimaryKey(String codMult, String codelem) throws Exception {
	    	return multitablaDetalleDAO.selectByPrimaryKey(codMult, codelem);
	  }

	    
	  public int updateMultitablaDTByPrimaryKeySelective(MultitablaDetalle record) throws Exception {
	        return multitablaDetalleDAO.updateByPrimaryKeySelective(record);
	  }
	  
	  public List<MultitablaDetalle> getLstMultitablaDetalle(String codMult)throws Exception  {
	        return multitablaDetalleDAO.getLstMultitablaDetalle(codMult);
	  }
	  public int updateCondicionCliente(MultitablaDetalle record) throws Exception {
			int rows=multitablaDetalleDAO.updateCondicionCliente(record);
	    	  return rows;
	  }
    
}
