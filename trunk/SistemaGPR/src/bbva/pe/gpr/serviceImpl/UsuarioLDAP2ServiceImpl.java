package bbva.pe.gpr.serviceImpl;

import org.springframework.dao.DataAccessException;

import bbva.pe.gpr.bean.UsuarioLDAP2;
import bbva.pe.gpr.dao.UsuarioLDAP2DAO;
import bbva.pe.gpr.service.UsuarioLDAP2Service;

public class UsuarioLDAP2ServiceImpl implements UsuarioLDAP2Service {
	
	private UsuarioLDAP2DAO usuarioLDAP2DAO;
	
	public  UsuarioLDAP2ServiceImpl(UsuarioLDAP2DAO usuarioLDAP2DAO) {
		super();
		this.usuarioLDAP2DAO = usuarioLDAP2DAO;
	}
	
	public int deleteByPrimaryKey(String codusu) throws DataAccessException{
		return usuarioLDAP2DAO.deleteByPrimaryKey(codusu);
	}
	
	public void insert(UsuarioLDAP2 record) throws DataAccessException{
		usuarioLDAP2DAO.insert(record);
	}
	
	public void insertSelective(UsuarioLDAP2 record) throws DataAccessException{
		usuarioLDAP2DAO.insertSelective(record);
	}
	
	public UsuarioLDAP2 selectByPrimaryKey(String codusu) throws DataAccessException{
		return usuarioLDAP2DAO.selectByPrimaryKey(codusu);
	}
	
	public int updateByPrimaryKey(UsuarioLDAP2 record) throws DataAccessException{
		return usuarioLDAP2DAO.updateByPrimaryKey(record);
	}
	
	public int updateByPrimaryKeySelective(UsuarioLDAP2 record) throws DataAccessException{
		return usuarioLDAP2DAO.updateByPrimaryKeySelective(record);
	}
	
	public void setUsuarioLDAP2DAO(UsuarioLDAP2DAO usuarioLDAP2DAO) throws DataAccessException{
		this.usuarioLDAP2DAO = usuarioLDAP2DAO;
	}
	
	

}
