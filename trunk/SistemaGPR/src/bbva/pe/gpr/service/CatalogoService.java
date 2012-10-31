package bbva.pe.gpr.service;

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

public interface CatalogoService {
	
  /*#####################################################################################################
   * 
   * 										TGRP_BANCA
   * 
   *##################################################################################################### */

	  int deleteBancaByPrimaryKey(BigDecimal codBanca)throws Exception ;   	    
	  void insertBancaSelective(Banca record)throws Exception ;
	  Banca selectBancaByPrimaryKey(BigDecimal codBanca)throws Exception ;
	  int updateBancaByPrimaryKeySelective(Banca record)throws Exception ;
	  List<Banca> getLstBancaByCriteria(Banca record)throws Exception ;

  /*#####################################################################################################
   * 
   * 									TGPR_PRODUCTO
   * 
   *##################################################################################################### */
	  
	  int deleteProductoByPrimaryKey(BigDecimal codProducto)throws Exception ;	   	    
	  void insertProductoSelective(Producto record)throws Exception ;
	  Producto selectProductoByPrimaryKey(BigDecimal codProducto)throws Exception ;
	  int updateProductoByPrimaryKeySelective(Producto record)throws Exception ;	  
	  List<Producto> getLstProducto(Producto record)throws Exception ;
	  
  /*#####################################################################################################
   * 
   * 										TPGR_OFICINA
   * 
   *##################################################################################################### */
	 
	  public int deleteOficinaByPrimaryKey(String codOficina)throws Exception ;	   	    
	  public void insertSelective(Oficina record)throws Exception ;
	  public Oficina selectByPrimaryKey(String codOficina)throws Exception ;
	  public int updateByPrimaryKeySelective(Oficina record)throws Exception ;
	  List<Oficina> getLstOficinaxTerritorio(Oficina oficina);
  
  /*#####################################################################################################
   * 
   * 									TPGR_TERRITORIO
   * 
   *##################################################################################################### */
	  
	  public int deleteTerritorioByPrimaryKey(BigDecimal codTerritorio)throws Exception ;	   	    
	  public void insertTerritorioSelective(Territorio record)throws Exception ;
	  public Territorio selectTerritorioByPrimaryKey(BigDecimal codTerritorio)throws Exception ;
	  public int updateTerritorioByPrimaryKeySelective(Territorio record)throws Exception ;
  
  
  /*#####################################################################################################
   * 
   * 									TGPR_MULTITABLA
   * 
   *##################################################################################################### */
	  
	  public int deleteMultitablaByPrimaryKey(String codMultitabla)throws Exception ;   	    
	  public void insertMultitablaSelective(Multitabla record)throws Exception ;
	  public Multitabla selectMultitablaByPrimaryKey(String codMultitabla)throws Exception ;
	  public int updateMultitablaByPrimaryKeySelective(Multitabla record)throws Exception ;
  
  
  
  /*#####################################################################################################
   * 
   * 								    TGPR_MULTITABLA_DETALLE
   * 
   *##################################################################################################### */

	  public int deleteMultitablaDTByPrimaryKey(MultitablaDetalleKey record)throws Exception ;	   	    
	  public void insertMultitablaDTSelective(MultitablaDetalle record)throws Exception ;
	  public MultitablaDetalle selectMultitablaDTByPrimaryKey(String codMult, String codelem)throws Exception ;	    
	  public int updateMultitablaDTByPrimaryKeySelective(MultitablaDetalle record)throws Exception ;	  
	  public List<MultitablaDetalle> getLstMultitablaDetalle(String codMult)throws Exception ;
	  int updateCondicionCliente(MultitablaDetalle record)throws Exception ;
  
  /*#####################################################################################################
   * 
   * 								TGPR_USUARIO
   * 
   *##################################################################################################### */

	  public int deleteUsuarioByPrimaryKey(String codUsuario)throws Exception ;	   	    
	  public void insertUsuarioSelective(Usuario record)throws Exception ;
	  public Usuario selectUsuarioByPrimaryKey(String codUsuario)throws Exception ;    
	  public int updateUsuarioByPrimaryKeySelective(Usuario usuarioBean)throws Exception ;	  
	  public List<Usuario> getLstUsuario(Usuario usuarioBean)throws Exception ;
  
  
  /*#####################################################################################################
   * 
   * 	                             TGPR_ROLES
   * 
   *##################################################################################################### */
	  
  /*#####################################################################################################
   * 
   * 	
   * 
   *##################################################################################################### */

  
  /*#####################################################################################################
   * 
   * 	
   * 
   *##################################################################################################### */

  
}
