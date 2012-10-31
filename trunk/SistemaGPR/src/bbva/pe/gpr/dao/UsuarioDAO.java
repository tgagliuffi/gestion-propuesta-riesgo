package bbva.pe.gpr.dao;

import java.util.HashMap;
import java.util.List;

import bbva.pe.gpr.bean.Usuario;

public interface UsuarioDAO {
    int deleteByPrimaryKey(String codUsuario)throws Exception ;
    void insert(Usuario record)throws Exception ;
    String insertSelective(Usuario record)throws Exception ;
 	Usuario selectByPrimaryKey(String codUsuario)throws Exception ;
 	int updateByPrimaryKeySelective(Usuario record)throws Exception ;
	int updateByPrimaryKey(Usuario record)throws Exception ;
	List<Usuario> getLstUsuarios(Usuario usuarioBean)throws Exception ;
	int getAsignarOficina(HashMap<String, String> record)throws Exception ;
	List<Usuario> getLstUsuarioAll()throws Exception ;
}