package bbva.pe.gpr.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import bbva.pe.gpr.bean.Banca;
import bbva.pe.gpr.bean.Campania;
import bbva.pe.gpr.bean.Funcion;
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
import bbva.pe.gpr.dao.BancaDAO;
import bbva.pe.gpr.dao.FuncionDAO;
import bbva.pe.gpr.dao.GerenteOficinaDAO;
import bbva.pe.gpr.dao.MultitablaDAO;
import bbva.pe.gpr.dao.MultitablaDetalleDAO;
import bbva.pe.gpr.dao.SolicitudRechazadaDAO;
import bbva.pe.gpr.dao.UsuarioOficinaDAO;
import bbva.pe.gpr.dao.OficinaDAO;
import bbva.pe.gpr.dao.ProductoDAO;
import bbva.pe.gpr.dao.RolDAO;
import bbva.pe.gpr.dao.TerritorioDAO;
import bbva.pe.gpr.dao.UsuarioDAO;
import bbva.pe.gpr.service.CatalogoService;
import bbva.pe.gpr.util.Constant;


public class CatalogoServiceImpl implements CatalogoService{
	  private BancaDAO bancaDAO;
	  private ProductoDAO productoDAO;
	  private OficinaDAO oficinaDAO;
	  private TerritorioDAO territorioDAO;
	  private MultitablaDAO multitablaDAO;
	  private MultitablaDetalleDAO multitablaDetalleDAO;
	  private UsuarioDAO usuarioDAO;
	  private RolDAO rolDAO;
	  private UsuarioOficinaDAO usuarioOficinaDAO;
      private GerenteOficinaDAO gerenteOficinaDAO;
      private SolicitudRechazadaDAO solicitudRechazadaDAO;
      private FuncionDAO funcionDAO; 
	public CatalogoServiceImpl(
			  BancaDAO bancaDAO,
			  ProductoDAO productoDAO,
			  OficinaDAO oficinaDAO,
			  TerritorioDAO territorioDAO,
			  MultitablaDAO multitablaDAO,
			  MultitablaDetalleDAO multitablaDetalleDAO,
			  UsuarioDAO usuarioDAO,
			  RolDAO rolDAO,
			  UsuarioOficinaDAO usuarioOficinaDAO,
			  GerenteOficinaDAO gerenteOficinaDAO,
			  SolicitudRechazadaDAO solicitudRechazadaDAO,
			  FuncionDAO funcionDAO
			  ) {
		  super();
		  this.bancaDAO=bancaDAO;
		  this.productoDAO=productoDAO;
		  this.oficinaDAO=oficinaDAO;
		  this.territorioDAO=territorioDAO;
		  this.multitablaDAO=multitablaDAO;
		  this.multitablaDetalleDAO=multitablaDetalleDAO;
		  this.usuarioDAO=usuarioDAO;
		  this.rolDAO=rolDAO;
		  this.usuarioOficinaDAO=usuarioOficinaDAO;
		  this.gerenteOficinaDAO=gerenteOficinaDAO;
		  this.solicitudRechazadaDAO =solicitudRechazadaDAO;
		  this.funcionDAO=funcionDAO;
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
		public RolDAO getRolDAO() {
			return rolDAO;
		}
		public void setRolDAO(RolDAO rolDAO) {
			this.rolDAO = rolDAO;
		}
		
		public UsuarioOficinaDAO getUsuarioOficinaDAO() {
			return usuarioOficinaDAO;
		}

		public void setUsuarioOficinaDAO(UsuarioOficinaDAO usuarioOficinaDAO) {
			this.usuarioOficinaDAO = usuarioOficinaDAO;
		}
		public GerenteOficinaDAO getGerenteOficinaDAO() {
			return gerenteOficinaDAO;
		}

		public void setGerenteOficinaDAO(GerenteOficinaDAO gerenteOficinaDAO) {
			this.gerenteOficinaDAO = gerenteOficinaDAO;
		}
		
		public SolicitudRechazadaDAO getSolicitudRechazadaDAO() {
			return solicitudRechazadaDAO;
		}
		
		public void setSolicitudRechazadaDAO(SolicitudRechazadaDAO solicitudRechazadaDAO) {
			this.solicitudRechazadaDAO = solicitudRechazadaDAO;
		}
		public FuncionDAO getFuncionDAO() {
			return funcionDAO;
		}

		public void setFuncionDAO(FuncionDAO funcionDAO) {
			this.funcionDAO = funcionDAO;
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
	  

	  public List<Producto> getLstProductoByCriteria(Producto record) throws Exception {
	        return productoDAO.getLstProductoByCriteria(record);
	  }
	  
	  public List<Campania> getlstCampaniaByCriteria(Campania campanbean) throws Exception{
		  return productoDAO.getlstCampaniaByCriteria(campanbean);
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

	  public List<Oficina> getLstOficinaByCriteria(Oficina record)throws Exception {
		return oficinaDAO.getLstOficinaByCriteria(record);
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
	  
	  public List<Territorio> getLstTerritorioByCriteria(Territorio record) throws Exception {
		return territorioDAO.getLstTerritorioByCriteria(record);
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

      public String getUsuarioExiste(Usuario usuarioBean) throws Exception {
			return usuarioDAO.getUsuarioExiste(usuarioBean);
	  }
      public int getDeleteUsuario(String codUsuario)throws Exception {
    	  int rows=usuarioDAO.getDeleteUsuario(codUsuario);
    	  return rows;
      };
      public List<Usuario> getLstUsuariosRiesgo(Usuario usuarioBean) throws Exception {
  		return usuarioDAO.getLstUsuariosRiesgo(usuarioBean);
  	  }
      public Usuario getUsuarioMontos(Usuario usuarioBean)throws Exception{
    	  return usuarioDAO.getUsuarioMontos(usuarioBean);
      }
 /*#####################################################################################################
   *  
   *                TGPR_ROLES	
   * 
   *##################################################################################################### */

	 public List<Rol> getLstRolesByCriteria(Rol rolBean) throws Exception {
		return rolDAO.getLstRolesByCriteria(rolBean);
	 }
 /*#####################################################################################################
   *  
   *                TGPR_OFICINA_ASIGNADA
   * 
   *##################################################################################################### */

	public List<UsuarioOficina> getLstOficinaAsignada(Usuario user)throws Exception {
		return usuarioOficinaDAO.getLstOficinaAsignada(user);
	}

	public void saveOficinaAsignada(UsuarioOficina oficinaAsignada) {
		usuarioOficinaDAO.saveOficinaAsignada(oficinaAsignada);
	}

	public void deleteOficinaAsignada(String codOficina) {
	    String [] getCodOficinas=codOficina.split(",");
	    for (String idCodOficina :getCodOficinas){
	    	usuarioOficinaDAO.deleteOficinaAsignada(idCodOficina);
	    }
	}

	public String getOficinaAsignadaExiste(UsuarioOficina oficinaAsignada) {
		return usuarioOficinaDAO.getOficinaAsignadaExiste(oficinaAsignada);
	}
	 /*#####################################################################################################
	   *  
	   *                TGPR_GERENTE_OFICINA
	   * 
	   *##################################################################################################### */

	public String getValidarUsuario(String codUsuario) {
		return gerenteOficinaDAO.getValidarUsuario(codUsuario);
	}
	 /*#####################################################################################################
	   *  
	   *                TGPR_SOLICITUD_RECHAZADA
	   * 
	   *##################################################################################################### */

	public void insertSolicitudRechazada(Solicitud solicitud) {
	 	solicitudRechazadaDAO.insertSolicitudRechazada(solicitud);
	}
	 /*#####################################################################################################
	   *  
	   *                TGPR_FUNCIONES
	   * 
	   *##################################################################################################### */
	 public  List<Funcion> getLstFuncionByCriteria(Funcion funcionBean) throws Exception{ 
		 return funcionDAO.getLstFuncionByCriteria(funcionBean);
	 }

	public String getJefeInmediatoOficina(String codUsuario) {
		return gerenteOficinaDAO.getJefeInmediatoOficina(codUsuario);
	}

	public String getJefeInmediatoRiesgo(String codUsuario) {
		return gerenteOficinaDAO.getJefeInmediatoRiesgo(codUsuario);
	}

	public String getUsuarioVerificar(String codUsuario) {
		String valor=gerenteOficinaDAO.getValidarUsuarioRiesgos(codUsuario);
		String valorOficina=gerenteOficinaDAO.getValidarUsuario(codUsuario);
		if(valor.equals("1")){
			return Constant.USUARIO_OFICINA;
		}else{
			return Constant.USUARIO_RIESGOS;
		}
	}
}