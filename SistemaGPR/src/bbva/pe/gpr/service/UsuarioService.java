package bbva.pe.gpr.service;


import java.util.List;

import bbva.pe.gpr.bean.Usuario;

public interface UsuarioService {
	int deleteByPrimaryKey(String codUsuario)throws Exception ;
    void insert(Usuario record)throws Exception ;
 	String insertSelective(Usuario record)throws Exception ;
 	Usuario selectByPrimaryKey(String codUsuario)throws Exception ;
 	int updateByPrimaryKeySelective(Usuario record)throws Exception ;
	int updateByPrimaryKey(Usuario record)throws Exception ;
	List<Usuario> getLstUsuarios(Usuario usuarioBean)throws Exception ;
	int getAsignarOficina(Usuario record)throws Exception ;
	public List<Usuario> getLstUsuariosRiesgo(String codRol)throws Exception  ;
}