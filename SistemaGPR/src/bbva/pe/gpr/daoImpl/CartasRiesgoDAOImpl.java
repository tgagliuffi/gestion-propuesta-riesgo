package bbva.pe.gpr.daoImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import bbva.pe.gpr.bean.ProductoDelegacion;
import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.bean.Usuario;
import bbva.pe.gpr.dao.CartasRiesgosDAO;

public class CartasRiesgoDAOImpl extends SqlMapClientDaoSupport implements CartasRiesgosDAO {

	public CartasRiesgoDAOImpl() {
	        super();
	}

	@SuppressWarnings("unchecked")
	public List<ProductoDelegacion> getDelegacionPersonaNatural(String codUsuario) {
		List<ProductoDelegacion> getLstProducto=getSqlMapClientTemplate().queryForList("CARDEL_TGPR_RIESGOS.getLstProductoPersonaNatural", codUsuario);
		if(!getLstProducto.isEmpty()){
			return getLstProducto;
		}else{
			return new ArrayList<ProductoDelegacion>();	
		}
	}

	public BigDecimal montDelegacionSinRating(String codUsuario,String grupoPersona) {
		Solicitud solicitud= new Solicitud();
		solicitud.setGestorCod(codUsuario);
		solicitud.setGrupoPersona("1");
		String monto = getSqlMapClientTemplate().queryForList("CARDEL_TGPR_RIESGOS.getMontoSinRating", solicitud).get(0).toString();
		BigDecimal montodelegacion= new BigDecimal(monto);	
		return montodelegacion;
	}

	public BigDecimal montDelegacionRating(String codUsuario, String escala) {
		Solicitud solicitud= new Solicitud();
		solicitud.setGestorCod(codUsuario);
		solicitud.setGrupoPersona("1");
		String monto =getSqlMapClientTemplate().queryForList("CARDEL_TGPR_RIESGOS.getMontoRating", solicitud).get(0).toString();
		BigDecimal montodelegacion= new BigDecimal(monto);	
		return 	montodelegacion;
	}
	public String montoDelegacionUsuario(String codUsuario,String tipoPersona) {
		Solicitud solicitud= new Solicitud();
		solicitud.setGestorCod(codUsuario);
		solicitud.setGrupoPersona("1");
		String productoDelegacion = getSqlMapClientTemplate().queryForList("CARDEL_TGPR_RIESGOS.getMontoDelegacionUsuario", solicitud).get(0).toString();
	    return productoDelegacion;
	}
}