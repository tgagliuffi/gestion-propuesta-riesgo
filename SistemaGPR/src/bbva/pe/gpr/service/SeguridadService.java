package bbva.pe.gpr.service;

import java.util.List;

import bbva.pe.gpr.bean.Funcion;
import bbva.pe.gpr.bean.Rol;

public interface SeguridadService {
	 List<Funcion> getLstFuncionByCriteria(Funcion record) throws Exception;
	 List<Rol> getLstRolesByCriteria(Rol rolBean)throws Exception;
}
