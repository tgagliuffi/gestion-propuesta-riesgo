package bbva.pe.gpr.dao;


import bbva.pe.gpr.bean.Multitabla;

public interface MultitablaDAO {
    int deleteByPrimaryKey(String codMultitabla)throws Exception ;
	void insert(Multitabla record)throws Exception ;
	void insertSelective(Multitabla record)throws Exception ;
	Multitabla selectByPrimaryKey(String codMultitabla)throws Exception ;
	int updateByPrimaryKeySelective(Multitabla record)throws Exception ;

}