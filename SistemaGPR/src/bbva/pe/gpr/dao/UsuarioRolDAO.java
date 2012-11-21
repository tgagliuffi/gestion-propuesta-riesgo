package bbva.pe.gpr.dao;

import bbva.pe.gpr.bean.UsuarioRol;
import bbva.pe.gpr.bean.UsuarioRolKey;

public interface UsuarioRolDAO {
    int deleteByPrimaryKey(UsuarioRolKey key);
    void insert(UsuarioRol record);
    void insertSelective(UsuarioRol record);
    UsuarioRol selectByPrimaryKey(UsuarioRolKey key);
    int updateByPrimaryKeySelective(UsuarioRol record);
    int updateByPrimaryKey(UsuarioRol record);
    int getUsuarioAsignadoRol(UsuarioRol record);
}