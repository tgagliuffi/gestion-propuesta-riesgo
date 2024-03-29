package bbva.pe.gpr.service;

import java.util.List;

import bbva.pe.gpr.bean.Funcion;
import bbva.pe.gpr.bean.FuncionRol;
import bbva.pe.gpr.bean.Menu;
import bbva.pe.gpr.bean.Rol;
import bbva.pe.gpr.bean.UsuarioRol;
import bbva.pe.gpr.bean.UsuarioRolKey;

public interface SeguridadService {
	 List<Funcion> getLstFuncionByCriteria(Funcion record) throws Exception;
	 List<Rol> getLstRolesByCriteria(Rol rolBean)throws Exception;
	 List<FuncionRol> getLstFuncionRol(FuncionRol funcionRolBean)throws Exception;
	 void saveFuncionRol(FuncionRol funcionRolBean) throws Exception;
	 void saveUsuarioRol(String codUsuario, String codRoles)throws Exception;
	 List<Menu> getListadoMenu(String codUsuario);
	 List<Funcion> getLstRolFuncionesxUsuario(String codUsuario) throws Exception;
	 Funcion getNivelDictamen(String codUsuario) throws Exception;
	 void insert(UsuarioRol record)throws Exception;
	 UsuarioRol selectByPrimaryKeyUsuarioRol(UsuarioRolKey key);
}
