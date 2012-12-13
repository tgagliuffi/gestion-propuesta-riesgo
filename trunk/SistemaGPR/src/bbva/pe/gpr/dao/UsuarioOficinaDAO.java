package bbva.pe.gpr.dao;

import java.util.List;

import bbva.pe.gpr.bean.UsuarioOficina;
import bbva.pe.gpr.bean.Usuario;

public interface UsuarioOficinaDAO {

	List<UsuarioOficina> getLstOficinaAsignada(Usuario user);
	void saveOficinaAsignada(UsuarioOficina oficinaAsignada);
	void deleteOficinaAsignada(String codOficina);
	String getOficinaAsignadaExiste(UsuarioOficina oficinaAsignada );
	List<UsuarioOficina> getLstOficinasByUsuario(UsuarioOficina oficinaAsignada) throws Exception;
}
