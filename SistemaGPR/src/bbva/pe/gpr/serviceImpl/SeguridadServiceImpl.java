package bbva.pe.gpr.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import bbva.pe.gpr.bean.Funcion;
import bbva.pe.gpr.bean.FuncionRol;
import bbva.pe.gpr.bean.Menu;
import bbva.pe.gpr.bean.Rol;
import bbva.pe.gpr.bean.UsuarioRol;
import bbva.pe.gpr.bean.UsuarioRolKey;
import bbva.pe.gpr.dao.FuncionDAO;
import bbva.pe.gpr.dao.FuncionRolDAO;
import bbva.pe.gpr.dao.MenuDAO;
import bbva.pe.gpr.dao.MenuRolDAO;
import bbva.pe.gpr.dao.RolDAO;
import bbva.pe.gpr.dao.UsuarioRolDAO;
import bbva.pe.gpr.service.SeguridadService;
import bbva.pe.gpr.util.Constant;

public class SeguridadServiceImpl implements SeguridadService {
	private FuncionDAO funcionDAO;
	private RolDAO rolDAO;
	private UsuarioRolDAO usuarioRolDAO;
	private FuncionRolDAO funcionRolDAO;
	private MenuDAO menuDAO;
	private MenuRolDAO menuRolDAO;
    
	public SeguridadServiceImpl(FuncionDAO funcionDAO, RolDAO rolDAO,
			UsuarioRolDAO usuarioRolDAO, FuncionRolDAO funcionRolDAO,
			MenuDAO menuDAO, MenuRolDAO menuRolDAO) {
		super();
		this.funcionDAO = funcionDAO;
		this.rolDAO = rolDAO;
		this.usuarioRolDAO = usuarioRolDAO;
		this.funcionRolDAO = funcionRolDAO;
		this.menuDAO = menuDAO;
		this.menuRolDAO = menuRolDAO;
	}

	public FuncionDAO getFuncionDAO() {
		return funcionDAO;
	}

	public void setFuncionDAO(FuncionDAO funcionDAO) {
		this.funcionDAO = funcionDAO;
	}

	public RolDAO getRolDAO() {
		return rolDAO;
	}

	public void setRolDAO(RolDAO rolDAO) {
		this.rolDAO = rolDAO;
	}

	public UsuarioRolDAO getUsuarioRolDAO() {
		return usuarioRolDAO;
	}

	public void setUsuarioRolDAO(UsuarioRolDAO usuarioRolDAO) {
		this.usuarioRolDAO = usuarioRolDAO;
	}

	public FuncionRolDAO getFuncionRolDAO() {
		return funcionRolDAO;
	}

	public MenuRolDAO getMenuRolDAO() {
		return menuRolDAO;
	}

	public void setMenuRolDAO(MenuRolDAO menuRolDAO) {
		this.menuRolDAO = menuRolDAO;
	}

	public void setFuncionRolDAO(FuncionRolDAO funcionRolDAO) {
		this.funcionRolDAO = funcionRolDAO;
	}

	public MenuDAO getMenuDAO() {
		return menuDAO;
	}

	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}

	/*
	 * ##########################################################################
	 * ###########################
	 * 
	 * TGRP_BANCA
	 * 
	 * ##########################################################################
	 * ###########################
	 */

	public List<Funcion> getLstFuncionByCriteria(Funcion record)
			throws Exception {
		return funcionDAO.getLstFuncionByCriteria(record);
	}

	public List<Rol> getLstRolesByCriteria(Rol rolBean) throws Exception {
		return rolDAO.getLstRolesByCriteria(rolBean);
	}

	public List<FuncionRol> getLstFuncionRol(FuncionRol funcionRolBean)
			throws Exception {
		return funcionRolDAO.getLstFuncionRol(funcionRolBean);
	}

	public void saveFuncionRol(FuncionRol funcionRolBean) throws Exception {
		funcionRolDAO.saveFuncionRol(funcionRolBean);
	}

	public void saveUsuarioRol(String codUsuario, String codRoles)
			throws Exception {
		UsuarioRol usuarioRol = new UsuarioRol();
		int valor=0;
		String[] valores = codRoles.split(",");
		for (String idRoles : valores) {
			if (!idRoles.equals("")) {
				usuarioRol.setCodRol(new BigDecimal(idRoles));
				usuarioRol.setEstado(Constant.ESTADO_ACTIVO);
				usuarioRol.setCodUsuario(codUsuario);
				usuarioRol.setDescripcion("ingreso");
			UsuarioRol user=usuarioRolDAO.selectByPrimaryKey(usuarioRol);
			if(user==null){
				usuarioRolDAO.insertSelective(usuarioRol);
				}
				}
		}
	}

	public List<Menu> getListadoMenu(String codUsuario) {
		return menuDAO.getLstMenuxUsuario(codUsuario);
	}

	public List<Funcion> getLstRolFuncionesxUsuario(String codUsuario)
			throws Exception {
		return funcionRolDAO.getLstRolFuncionesUsuario(codUsuario);
	}

	public Funcion getNivelDictamen(String codUsuario)
			throws Exception {
		Funcion nivel = null;
		List<Funcion> niveles = funcionRolDAO.getNivelDictamen(codUsuario);
		
		if(niveles != null && niveles.size() > 0) {
			nivel = niveles.get(0);
		}

		return nivel;
	}
	public void insert(UsuarioRol record) throws Exception {
		 usuarioRolDAO.insert(record);	
	}

	public UsuarioRol selectByPrimaryKeyUsuarioRol(UsuarioRolKey key) {
		return usuarioRolDAO.selectByPrimaryKey(key);
	}
}