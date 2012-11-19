package bbva.pe.gpr.daoImpl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import bbva.pe.gpr.bean.ProductoDelegacion;
import bbva.pe.gpr.dao.CartasRiesgosDAO;

public class CartasRiesgoDAOImpl extends SqlMapClientDaoSupport implements CartasRiesgosDAO {

	public CartasRiesgoDAOImpl() {
	        super();
	}
	   
	public BigDecimal montoDelegacionPorRating(String codUsuario, String rating,String grupoPersona) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("codUsuario", codUsuario);
		map.put("rating", rating);
		map.put("grupoPersona", grupoPersona);
		String monto="";
		monto=getSqlMapClientTemplate().queryForList("",map).get(0).toString();
		BigDecimal montoRiesgos= new BigDecimal(monto);
		return montoRiesgos;
	}

	public BigDecimal montoDelegacionSinRating(String codUsuario, String grupoPersona) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("codUsuario", codUsuario);
		map.put("grupoPersona", grupoPersona);
		String monto="";
		monto=getSqlMapClientTemplate().queryForList("",map).get(0).toString();
		BigDecimal montoRiesgos= new BigDecimal(monto);
		return montoRiesgos;
	}

	@SuppressWarnings("unchecked")
	public List<ProductoDelegacion> getDelegacionPersonaNatural(String codUsuario, String grupoPersona) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("codUsuario", codUsuario);
		map.put("grupoPersona", grupoPersona);
		List<ProductoDelegacion> getLstProductoDelegacion=getSqlMapClientTemplate().queryForList("", map);
		return getLstProductoDelegacion;
	}
}