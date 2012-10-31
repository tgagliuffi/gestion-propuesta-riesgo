package bbva.pe.gpr.dao;

import bbva.pe.gpr.bean.UsuarioLDAP2;


public interface UsuarioLDAP2DAO {
    int deleteByPrimaryKey(String codusu);
	void insert(UsuarioLDAP2 record);
	void insertSelective(UsuarioLDAP2 record);
	UsuarioLDAP2 selectByPrimaryKey(String codusu);
	int updateByPrimaryKeySelective(UsuarioLDAP2 record);
	int updateByPrimaryKey(UsuarioLDAP2 record);
}