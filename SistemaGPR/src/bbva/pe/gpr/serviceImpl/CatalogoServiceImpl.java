package bbva.pe.gpr.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import bbva.pe.gpr.bean.Banca;
import bbva.pe.gpr.bean.Multitabla;
import bbva.pe.gpr.bean.MultitablaDetalle;
import bbva.pe.gpr.bean.MultitablaDetalleKey;
import bbva.pe.gpr.bean.Oficina;
import bbva.pe.gpr.bean.Producto;
import bbva.pe.gpr.bean.Territorio;
import bbva.pe.gpr.bean.Usuario;
import bbva.pe.gpr.dao.BancaDAO;
import bbva.pe.gpr.dao.MultitablaDAO;
import bbva.pe.gpr.dao.MultitablaDetalleDAO;
import bbva.pe.gpr.dao.OficinaDAO;
import bbva.pe.gpr.dao.ProductoDAO;
import bbva.pe.gpr.dao.TerritorioDAO;
import bbva.pe.gpr.dao.UsuarioDAO;
import bbva.pe.gpr.service.CatalogoService;


public class CatalogoServiceImpl implements CatalogoService{
	  private BancaDAO bancaDAO;
	  private ProductoDAO productoDAO;
	  private OficinaDAO oficinaDAO;
	  private TerritorioDAO territorioDAO;
	  private MultitablaDAO multitablaDAO;
	  private MultitablaDetalleDAO multitablaDetalleDAO;
	  private UsuarioDAO usuarioDAO;

	  
	  public CatalogoServiceImpl(
			  BancaDAO bancaDAO,
			  ProductoDAO productoDAO,
			  OficinaDAO oficinaDAO,
			  TerritorioDAO territorioDAO,
			  MultitablaDAO multitablaDAO,
			  MultitablaDetalleDAO multitablaDetalleDAO,
			  UsuarioDAO usuarioDAO
			  ) {
		  super();
		  this.bancaDAO=bancaDAO;
		  this.productoDAO=productoDAO;
		  this.oficinaDAO=oficinaDAO;
		  this.territorioDAO=territorioDAO;
		  this.multitablaDAO=multitablaDAO;
		  this.multitablaDetalleDAO=multitablaDetalleDAO;
		  this.usuarioDAO=usuarioDAO;
	  }
	public BancaDAO getBancaDAO(){return bancaDAO;}
	public void setBancaDAO(BancaDAO bancaDAO){this.bancaDAO = bancaDAO;}
	public ProductoDAO getProductoDAO(){return productoDAO;}
	public void setProductoDAO(ProductoDAO productoDAO){this.productoDAO = productoDAO;}
	public OficinaDAO getOficinaDAO() {return oficinaDAO;
		}
	
		public void setOficinaDAO(OficinaDAO oficinaDAO) {
			this.oficinaDAO = oficinaDAO;
		}
	
		public TerritorioDAO getTerritorioDAO() {
			return territorioDAO;
		}
	
		public void setTerritorioDAO(TerritorioDAO territorioDAO) {
			this.territorioDAO = territorioDAO;
		}
	
		public MultitablaDAO getMultitablaDAO() {
			return multitablaDAO;
		}
	
		public void setMultitablaDAO(MultitablaDAO multitablaDAO) {
			this.multitablaDAO = multitablaDAO;
		}
	
		public MultitablaDetalleDAO getMultitablaDetalleDAO() {
			return multitablaDetalleDAO;
		}
	
		public void setMultitablaDetalleDAO(MultitablaDetalleDAO multitablaDetalleDAO) {
			this.multitablaDetalleDAO = multitablaDetalleDAO;
		}
	
		public UsuarioDAO getUsuarioDAO() {
			return usuarioDAO;
		}
	
		public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
			this.usuarioDAO = usuarioDAO;
		}
  /*#####################################################################################################
   * 
   * 										TGRP_BANCA
   * 
   *##################################################################################################### */

	  public int deleteBancaByPrimaryKey(BigDecimal codBanca) throws Exception {
	      return bancaDAO.deleteByPrimaryKey(codBanca);
	  }
	   	    
	  public void insertBancaSelective(Banca record) throws Exception {
		  bancaDAO.insertSelective(record);
	  }

	  public Banca selectBancaByPrimaryKey(BigDecimal codBanca) throws Exception {
	    	return bancaDAO.selectByPrimaryKey(codBanca);
	  }

	    
	  public int updateBancaByPrimaryKeySelective(Banca record) throws Exception {
	        return bancaDAO.updateByPrimaryKeySelective(record);
	  }
	  
	  public List<Banca> getLstBancaByCriteria(Banca record) throws Exception {
	        return bancaDAO.getLstBancaByCriteria(record);
	  }
	  
	  
  /*#####################################################################################################
   * 
   * 									TGPR_PRODUCTO
   * 
   *##################################################################################################### */
	  
	  public int deleteProductoByPrimaryKey(BigDecimal codProducto) throws Exception {
	      return productoDAO.deleteByPrimaryKey(codProducto);
	  }
	   	    
	  public void insertProductoSelective(Producto record) throws Exception {
		  productoDAO.insertSelective(record);
	  }

	  public Producto selectProductoByPrimaryKey(BigDecimal codProducto) throws Exception {
	    	return productoDAO.selectByPrimaryKey(codProducto);
	  }

	    
	  public int updateProductoByPrimaryKeySelective(Producto record) throws Exception {
	        return productoDAO.updateByPrimaryKeySelective(record);
	  }
	  
	  public List<Producto> getLstProducto(Producto record) throws Exception {
	        return productoDAO.getLstProducto(record);
	  }
	  
  /*#####################################################################################################
   * 
   * 										TPGR_OFICINA
   * 
   *##################################################################################################### */
	 
	  public int deleteOficinaByPrimaryKey(String codOficina) throws Exception {
	      return oficinaDAO.deleteByPrimaryKey(codOficina);
	  }
	   	    
	  public void insertSelective(Oficina record) throws Exception {
		  oficinaDAO.insertSelective(record);
	  }

	  public Oficina selectByPrimaryKey(String codOficina) throws Exception {
	    	return oficinaDAO.selectByPrimaryKey(codOficina);
	  }

	    
	  public int updateByPrimaryKeySelective(Oficina record) throws Exception {
	        return oficinaDAO.updateByPrimaryKeySelective(record);
	  }
    
	  public List<Oficina> getLstOficinaxTerritorio(Oficina oficina) {
			return oficinaDAO.getLstOficinaxTerritorio(oficina);
		}


  
  /*#####################################################################################################
   * 
   * 									TPGR_TERRITORIO
   * 
   *##################################################################################################### */
	  
	  public int deleteTerritorioByPrimaryKey(BigDecimal codTerritorio) throws Exception {
	      return territorioDAO.deleteByPrimaryKey(codTerritorio);
	  }
	   	    
	  public void insertTerritorioSelective(Territorio record) throws Exception {
		  territorioDAO.insertSelective(record);
	  }

	  public Territorio selectTerritorioByPrimaryKey(BigDecimal codTerritorio) throws Exception {
	    	return territorioDAO.selectByPrimaryKey(codTerritorio);
	  }

	    
	  public int updateTerritorioByPrimaryKeySelective(Territorio record) throws Exception {
	        return territorioDAO.updateByPrimaryKeySelective(record);
	  }
  
  
  /*#####################################################################################################
   * 
   * 									TGPR_MULTITABLA
   * 
   *##################################################################################################### */
	  
	  public int deleteMultitablaByPrimaryKey(String codMultitabla) throws Exception {
	      return multitablaDAO.deleteByPrimaryKey(codMultitabla);
	  }
	   	    
	  public void insertMultitablaSelective(Multitabla record) throws Exception {
		  multitablaDAO.insertSelective(record);
	  }

	  public Multitabla selectMultitablaByPrimaryKey(String codMultitabla) throws Exception {
	    	return multitablaDAO.selectByPrimaryKey(codMultitabla);
	  }
	    
	  public int updateMultitablaByPrimaryKeySelective(Multitabla record) throws Exception {
	        return multitablaDAO.updateByPrimaryKeySelective(record);
	  }
  
  
  
  /*#####################################################################################################
   * 
   * 								    TGPR_MULTITABLA_DETALLE
   * 
   *##################################################################################################### */

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
	
  
  /*#####################################################################################################
   * 
   * 	
   * 
   *##################################################################################################### */
	  public int deleteUsuarioByPrimaryKey(String codUsuario)throws Exception {
		  return usuarioDAO.deleteByPrimaryKey(codUsuario); 
	  }
 	    
	  public void insertUsuarioSelective(Usuario usuarioBean)throws Exception {
		   usuarioDAO.insertSelective(usuarioBean);
	  }
	  
	  public Usuario selectUsuarioByPrimaryKey(String codUsuario)throws Exception {
		  return usuarioDAO.selectByPrimaryKey(codUsuario);
	  }
  
	  public int updateUsuarioByPrimaryKeySelective(Usuario usuarioBean)throws Exception {
		  return usuarioDAO.updateByPrimaryKeySelective(usuarioBean);
	  }
	  
	  public List<Usuario> getLstUsuario(Usuario usuarioBean)throws Exception {
		  return usuarioDAO.getLstUsuarios(usuarioBean);
	  }
	
}
