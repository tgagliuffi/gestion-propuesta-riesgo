package bbva.pe.gpr.dao;

public interface GerenteOficinaDAO {

	String  getJefeInmediatoOficina(String codUsuario)throws Exception;;
	String  getJefeInmediatoRiesgo(String codUsuario)throws Exception;;
	String  getUsuarioTipo(String codUsuario)throws Exception;;
}
