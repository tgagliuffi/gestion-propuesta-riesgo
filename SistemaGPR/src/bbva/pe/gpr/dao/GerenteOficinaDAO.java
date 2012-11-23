package bbva.pe.gpr.dao;

public interface GerenteOficinaDAO {

	String  getJefeInmediatoOficina(String codUsuario);
	String  getJefeInmediatoRiesgo(String codUsuario);
	String  getUsuarioTipo(String codUsuario);
}
