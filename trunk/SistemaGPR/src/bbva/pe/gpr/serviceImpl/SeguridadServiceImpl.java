package bbva.pe.gpr.serviceImpl;

import java.util.List;

import bbva.pe.gpr.bean.Funcion;
import bbva.pe.gpr.bean.Rol;
import bbva.pe.gpr.dao.FuncionDAO;
import bbva.pe.gpr.dao.FuncionRolDAO;
import bbva.pe.gpr.dao.MenuDAO;
import bbva.pe.gpr.dao.MenuRolDAO;
import bbva.pe.gpr.dao.RolDAO;
import bbva.pe.gpr.dao.UsuarioRolDAO;
import bbva.pe.gpr.service.SeguridadService;

public class SeguridadServiceImpl implements SeguridadService {
	private FuncionDAO funcionDAO;
	private RolDAO rolDAO;
	private UsuarioRolDAO usuarioRolDAO;
	private FuncionRolDAO funcionRolDAO; 
	private MenuDAO menuDAO;
	private MenuRolDAO menuRolDAO;
	
	
	public SeguridadServiceImpl(FuncionDAO funcionDAO,
								RolDAO rolDAO,
								UsuarioRolDAO usuarioRolDAO,
								FuncionRolDAO funcionRolDAO,
								MenuDAO menuDAO,
								MenuRolDAO menuRolDAO) {
		super();
		this.funcionDAO=funcionDAO;
		this.rolDAO=rolDAO;
		this.usuarioRolDAO=usuarioRolDAO;
		this.funcionRolDAO=funcionRolDAO;
		this.menuDAO=menuDAO;
		this.menuRolDAO=menuRolDAO;
	}
	
	
	public FuncionDAO getFuncionDAO(){return funcionDAO;}
	public void setFuncionDAO(FuncionDAO funcionDAO){this.funcionDAO = funcionDAO;}
	public RolDAO getRolDAO(){return rolDAO;}
	public void setRolDAO(RolDAO rolDAO){this.rolDAO = rolDAO;}
	public UsuarioRolDAO getUsuarioRolDAO(){return usuarioRolDAO;}
	public void setUsuarioRolDAO(UsuarioRolDAO usuarioRolDAO){this.usuarioRolDAO = usuarioRolDAO;}
	public FuncionRolDAO getFuncionRolDAO(){return funcionRolDAO;}
	public MenuRolDAO getMenuRolDAO(){return menuRolDAO;}
	public void setMenuRolDAO(MenuRolDAO menuRolDAO){this.menuRolDAO = menuRolDAO;}
	public void setFuncionRolDAO(FuncionRolDAO funcionRolDAO){this.funcionRolDAO = funcionRolDAO;}
	public MenuDAO getMenuDAO(){return menuDAO;}
	public void setMenuDAO(MenuDAO menuDAO){this.menuDAO = menuDAO;}

  /*#####################################################################################################
  * 
  * 										TGRP_BANCA
  * 
  *##################################################################################################### */

  public List<Funcion> getLstFuncionByCriteria(Funcion record) throws Exception {
		        return funcionDAO.getLstFuncionByCriteria(record);
  }
  public List<Rol> getLstRolesByCriteria(Rol rolBean) throws Exception{
		return rolDAO.getLstRolesByCriteria(rolBean);
  }
		  
		   
}
