package bbva.pe.gpr.service;

import java.util.List;

import bbva.pe.gpr.bean.Funcion;
import bbva.pe.gpr.bean.FuncionRol;
import bbva.pe.gpr.bean.Menu;
import bbva.pe.gpr.bean.Rol;

public interface SeguridadService {
	 List<Funcion> getLstFuncionByCriteria(Funcion record) throws Exception;
	 List<Rol> getLstRolesByCriteria(Rol rolBean)throws Exception;
	 List<FuncionRol> getLstFuncionRol(FuncionRol funcionRolBean)throws Exception;
	 void saveFuncionRol(FuncionRol funcionRolBean) throws Exception;
	 void saveUsuarioRol(String codUsuario, String codRoles)throws Exception;
	 List<Menu> getListadoMenu(String codUsuario);
}
