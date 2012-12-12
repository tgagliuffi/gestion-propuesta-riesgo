package bbva.pe.gpr.dao;

import bbva.pe.gpr.bean.BancaSub;

public interface BancaSubDAO {
	int deleteByPrimaryKey(String codSubbanca);
	void insert(BancaSub record);
	void insertSelective(BancaSub record);
	BancaSub selectByPrimaryKey(String codSubbanca);
	int updateByPrimaryKeySelective(BancaSub record);
	int updateByPrimaryKey(BancaSub record);
}