package bbva.pe.gpr.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import bbva.pe.gpr.bean.Usuario;
import bbva.pe.gpr.dao.UsuarioDAO;
import bbva.pe.gpr.service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService {
	
	private UsuarioDAO usuarioDao;
	
	public UsuarioServiceImpl(UsuarioDAO usuarioDAO) {
		super();
		this.usuarioDao=usuarioDAO;
		
	}
	
	public UsuarioDAO getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDAO usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public int deleteByPrimaryKey(String codUsuario)throws Exception  {
		return usuarioDao.deleteByPrimaryKey(codUsuario);
	}

	public void insert(Usuario record) throws Exception {
		usuarioDao.insert(record);		
	}
	
	public String insertSelective(Usuario record) throws Exception {
		return usuarioDao.insertSelective(record);
	}

	public Usuario selectByPrimaryKey(String codUsuario)throws Exception  {
		return usuarioDao.selectByPrimaryKey(codUsuario);
	}

	public int updateByPrimaryKeySelective(Usuario record) throws Exception {
		return usuarioDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Usuario record) throws Exception {
		return usuarioDao.updateByPrimaryKey(record);
	}
	
	public List<Usuario> getLstUsuarios(Usuario usuarioBean)throws Exception {
		return usuarioDao.getLstUsuarios(usuarioBean);
	}

	public int getAsignarOficina(Usuario record) throws Exception {
		return usuarioDao.updateOficinaAsignada(record);
	}
	
	public List<Usuario> getLstUsuariosRiesgo(String codRol) throws Exception {
		Usuario usuarioBean =  new Usuario();
		if(!codRol.equals("-1") && codRol!=null){
			usuarioBean.setCodRol(new BigDecimal(codRol));
		}else{
			usuarioBean.setCodRol(new BigDecimal(-1));
		}
		return usuarioDao.getLstUsuariosRiesgo(usuarioBean);
	}
}