package bbva.pe.gpr.dao;

import java.util.List;

import bbva.pe.gpr.bean.Funcion;
import bbva.pe.gpr.bean.FuncionRol;
import bbva.pe.gpr.bean.FuncionRolKey;

public interface FuncionRolDAO {
     int deleteByPrimaryKey(FuncionRolKey key);     
     void insert(FuncionRol record);
     void insertSelective(FuncionRol record);
     FuncionRol selectByPrimaryKey(FuncionRolKey key);
     int updateByPrimaryKeySelective(FuncionRol record);
     int updateByPrimaryKey(FuncionRol record);
     List<FuncionRol> getLstFuncionRol(FuncionRol funcionRolBean) throws Exception ;
     void saveFuncionRol (FuncionRol funcionRolBean) throws Exception ;
     List<Funcion> getLstRolFuncionesUsuario(String codUsuario) throws Exception;
     List<Funcion> getNivelDictamen(String codUsuario)throws Exception;
}