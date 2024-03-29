package bbva.pe.gpr.dao;

import bbva.pe.gpr.bean.BancaSub;
import bbva.pe.gpr.bean.UsuarioSubanca;
import bbva.pe.gpr.bean.UsuarioSubancaKey;

public interface UsuarioSubancaDAO {
    int deleteByPrimaryKey(UsuarioSubancaKey key);
    void insert(UsuarioSubanca record);
    void insertSelective(UsuarioSubanca record);
    UsuarioSubanca selectByPrimaryKey(UsuarioSubancaKey key);
    int updateByPrimaryKeySelective(UsuarioSubanca record);
    int updateByPrimaryKey(UsuarioSubanca record);
    UsuarioSubanca getSubBancaPorUsuario(BancaSub subanca) throws Exception;
}