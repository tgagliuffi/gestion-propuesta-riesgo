package bbva.pe.gpr.dao;

import bbva.pe.gpr.bean.Banca;
import java.math.BigDecimal;
import java.util.List;

public interface BancaDAO {
    int deleteByPrimaryKey(BigDecimal codBanca)throws Exception ;
	void insert(Banca record)throws Exception ;
	void insertSelective(Banca record)throws Exception ;
	Banca selectByPrimaryKey(BigDecimal codBanca)throws Exception ;
	int updateByPrimaryKeySelective(Banca record)throws Exception ;
	int updateByPrimaryKey(Banca record);
	List<Banca> getLstBancaByCriteria(Banca record)throws Exception ;
}