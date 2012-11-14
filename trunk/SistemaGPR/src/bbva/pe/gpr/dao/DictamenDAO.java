package bbva.pe.gpr.dao;

import bbva.pe.gpr.bean.Dictamen;
import bbva.pe.gpr.bean.DictamenKey;

public interface DictamenDAO {
    int deleteByPrimaryKey(DictamenKey key);
	Long insert(Dictamen record);
	void insertSelective(Dictamen record);
	Dictamen selectByPrimaryKey(DictamenKey key);
	Dictamen findForNumeroSolicitud(DictamenKey key);
	int updateByPrimaryKeySelective(Dictamen record);
	int updateByPrimaryKey(Dictamen record);
}