package bbva.pe.gpr.daoImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import bbva.pe.gpr.bean.ProductoDelegacion;
import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.bean.Usuario;
import bbva.pe.gpr.dao.CartasRiesgosDAO;
import bbva.pe.gpr.util.Constant;

public class CartasRiesgoDAOImpl extends SqlMapClientDaoSupport implements CartasRiesgosDAO {

	public CartasRiesgoDAOImpl() {
	        super();
	}

	@SuppressWarnings("unchecked")
	public List<ProductoDelegacion> getDelegacionPersonaNatural(String codUsuario) {
    	Map parm = new HashMap();
    	parm.put("codUsuario", codUsuario);
		getSqlMapClientTemplate().queryForList("DELGPR_TGPR_RIESGOS.getLstProductoPersonaNatural",parm);
		ArrayList<ProductoDelegacion> lista =(ArrayList<ProductoDelegacion>)parm.get("cCursor");
         return lista;
	}

	public BigDecimal montDelegacionSinRating(String codUsuario,String grupoPersona) {
		Solicitud solicitud= new Solicitud();
		solicitud.setGestorCod(codUsuario);
		solicitud.setGrupoPersona(grupoPersona);
		Usuario user =(Usuario) getSqlMapClientTemplate().queryForObject("DELGPR_TGPR_RIESGOS.getMontoSinRating", solicitud);
		if(user.getMtoMaxDelegacion().compareTo(new BigDecimal("-1"))==0){
			return BigDecimal.ZERO;
		}
		return user.getMtoMaxDelegacion();
	}

	public BigDecimal montDelegacionRating(String codUsuario, String escala) {
		Solicitud solicitud= new Solicitud();
		solicitud.setGestorCod(codUsuario);
		solicitud.setGrupoPersona(escala);
		Usuario user =(Usuario)getSqlMapClientTemplate().queryForObject("DELGPR_TGPR_RIESGOS.getMontoRating", solicitud);
		if(user.getMtoMaxDelegacion().compareTo(new BigDecimal("-1"))==0){
			return BigDecimal.ZERO;
		}
		return 	user.getMtoMaxDelegacion();
	}
	
	public String montoDelegacionUsuario(String codUsuario,String tipoPersona) {
		Solicitud solicitud= new Solicitud();
		solicitud.setGestorCod(codUsuario);
		solicitud.setGrupoPersona(tipoPersona);
		Usuario user =(Usuario)getSqlMapClientTemplate().queryForObject("DELGPR_TGPR_RIESGOS.getMontoDelegacionUsuario", solicitud);
		if(user.getMtoMaxDelegacion().compareTo(new BigDecimal("-1"))==0){
			return "0";
		}
		return user.getMtoMaxDelegacion().toString();
	}
}