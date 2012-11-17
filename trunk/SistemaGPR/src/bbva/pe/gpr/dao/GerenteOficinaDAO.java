package bbva.pe.gpr.dao;

public interface GerenteOficinaDAO {

	String  getValidarUsuario(String codUsuario);
	String  getJefeInmediatoOficina(String codUsuario);
	String  getJefeInmediatoRiesgo(String codUsuario);
}
