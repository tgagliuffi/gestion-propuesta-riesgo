package bbva.pe.gpr.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import bbva.pe.gpr.bean.Banca;
import bbva.pe.gpr.bean.BancaSub;
import bbva.pe.gpr.bean.Campania;
import bbva.pe.gpr.bean.Funcion;
import bbva.pe.gpr.bean.FuncionRol;
import bbva.pe.gpr.bean.Multitabla;
import bbva.pe.gpr.bean.MultitablaDetalle;
import bbva.pe.gpr.bean.MultitablaDetalleKey;
import bbva.pe.gpr.bean.Oficina;
import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.bean.UsuarioOficina;
import bbva.pe.gpr.bean.Producto;
import bbva.pe.gpr.bean.Rol;
import bbva.pe.gpr.bean.Territorio;
import bbva.pe.gpr.bean.Usuario;
import bbva.pe.gpr.bean.UsuarioSubanca;
import bbva.pe.gpr.bean.UsuarioSubancaKey;

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
	  List<BancaSub> getLstSubBanca(BancaSub bancaSub)throws Exception;
	  
  /*#####################################################################################################
   * 
   * 									TGPR_PRODUCTO
   * 
   *##################################################################################################### */
	  
	  int deleteProductoByPrimaryKey(BigDecimal codProducto)throws Exception ;	   	    
	  void insertProductoSelective(Producto record)throws Exception ;
	  Producto selectProductoByPrimaryKey(BigDecimal codProducto)throws Exception ;
	  int updateProductoByPrimaryKeySelective(Producto record)throws Exception ;	  
	  List<Producto> getLstProductoByCriteria(Producto record)throws Exception;
	  List<Campania> getlstCampaniaByCriteria(Campania campaniabean)throws Exception;
	  
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
	  List<Oficina> getLstOficinaByCriteria(Oficina record)throws Exception ;
  
  /*#####################################################################################################
   * 
   * 									TPGR_TERRITORIO
   * 
   *##################################################################################################### */
	  
	  public int deleteTerritorioByPrimaryKey(BigDecimal codTerritorio)throws Exception ;	   	    
	  public void insertTerritorioSelective(Territorio record)throws Exception ;
	  public Territorio selectTerritorioByPrimaryKey(BigDecimal codTerritorio)throws Exception ;
	  public int updateTerritorioByPrimaryKeySelective(Territorio record)throws Exception ;
	  public List<Territorio> getLstTerritorioByCriteria(Territorio record)throws Exception ;

  
  
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
      int update(MultitablaDetalle record)throws Exception ; 
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
	  String getUsuarioExiste(Usuario usuarioBean)throws Exception ;
	  int getDeleteUsuario(String codUsuario)throws Exception ;
	  List<Usuario> getLstUsuariosRiesgo(Usuario usuarioBean) throws Exception ;
	  Usuario getUsuarioMontos(Usuario usuarioBean)throws Exception; 
  /*#####################################################################################################
   * 
   * 	                             TGPR_ROLES
   * 
   *##################################################################################################### */
	  public  List<Rol> getLstRolesByCriteria(Rol rolBean)throws Exception;
	  public Rol getRolSelectByPrimaryKey(BigDecimal codRol)throws Exception;
	  public Map<String, String> saveRol(String codRolHdn, String codRol, String desRol,String refeRol)throws Exception;
	  List<Rol> getLsRoles()throws Exception;
  /*#####################################################################################################
   * 
   * 	                               TGPR_OFICINA_ASIGNADA
   *                                   
   *##################################################################################################### */
   
	  List<UsuarioOficina> getLstOficinaAsignada(Usuario user)throws Exception;
	  void saveOficinaAsignada(UsuarioOficina oficinaAsignada);
	  void deleteOficinaAsignada(String codOficina);
	  String getOficinaAsignadaExiste(UsuarioOficina oficinaAsignada );
  /*#####################################################################################################
   * 
   * 	
   *                         TGPR_GERENTE_OFICINA
   *##################################################################################################### */
	  String getJefeInmediatoOficina(String codUsuario);
	  String getJefeInmediatoRiesgo(String codUsuario);
	  String getUsuarioTipo(String codUsuario);
   /*#####################################################################################################
	   * 
	   * 	
	   *                         TGPR_SOLICITUD_RECHAZADA
	*##################################################################################################### */
	  void insertSolicitudRechazada(Solicitud solicitud);
	/*#####################################################################################################
		   * 
		   * 	
		   *                         TGPR_FUNCIONES
	*##################################################################################################### */
	  public  List<Funcion> getLstFuncionByCriteria(Funcion funcionBean)throws Exception;
	  List<Funcion> getLstRolesFuncion(String codRol)throws Exception;
		/*#####################################################################################################
	   * 
	   * 	
	   *                         TGPR_USUARIO_SUBBANCA
*##################################################################################################### */
	  public UsuarioSubanca selectByPrimaryKey(UsuarioSubancaKey key);
	  public BancaSub selectByPrimaryKeyBancaSub(String codSubbanca);
	  public void insert(UsuarioSubanca record);
	  public void saveRolFunciones(FuncionRol record);
	  public String rolFuncionExistente(FuncionRol record);
}
