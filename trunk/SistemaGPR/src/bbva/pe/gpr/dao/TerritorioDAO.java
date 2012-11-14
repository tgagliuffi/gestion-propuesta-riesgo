package bbva.pe.gpr.dao;

import bbva.pe.gpr.bean.Territorio;
import java.math.BigDecimal;
import java.util.List;

public interface TerritorioDAO {
	int deleteByPrimaryKey(BigDecimal codTerritorio)throws Exception ;
	void insert(Territorio record)throws Exception ;
	void insertSelective(Territorio record)throws Exception ;
	Territorio selectByPrimaryKey(BigDecimal codTerritorio)throws Exception ;
	int updateByPrimaryKeySelective(Territorio record)throws Exception ;
	int updateByPrimaryKey(Territorio record)throws Exception ;
	List<Territorio> getLstTerritorioByCriteria(Territorio record)throws Exception ;
}