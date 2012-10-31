package bbva.pe.gpr.dao;

import java.util.List;

import bbva.pe.gpr.bean.Oficina;

public interface OficinaDAO {
	int deleteByPrimaryKey(String codOficina)throws Exception ;
	void insert(Oficina record)throws Exception ;
	void insertSelective(Oficina record)throws Exception ;
	Oficina selectByPrimaryKey(String codOficina)throws Exception ;
	int updateByPrimaryKeySelective(Oficina record)throws Exception ;
	int updateByPrimaryKey(Oficina record)throws Exception ;
	Oficina getOficinaForUsuario(String idUsuario);
	List<Oficina> getLstOficinaxTerritorio(Oficina oficina);
}