package bbva.pe.gpr.dao;

import bbva.pe.gpr.bean.Analisis;
import bbva.pe.gpr.bean.AnalisisKey;

public interface AnalisisDAO {
    int deleteByPrimaryKey(AnalisisKey key) throws Exception;
	Long insert(Analisis record) throws Exception;
	void insertSelective(Analisis record)throws Exception;
	Analisis selectByPrimaryKey(AnalisisKey key)throws Exception;
	int updateByPrimaryKeySelective(Analisis record)throws Exception;
	int updateByPrimaryKey(Analisis record)throws Exception;
}