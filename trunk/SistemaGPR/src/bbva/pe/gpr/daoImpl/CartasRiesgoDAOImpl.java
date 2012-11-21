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

	public BigDecimal montDelegacion(String codUsuario, String grupoPersona) {
		Solicitud solicitud= new Solicitud();
		solicitud.setGestorCod(codUsuario);
		solicitud.setGrupoPersona(grupoPersona);
		Usuario usuario = (Usuario) getSqlMapClientTemplate().queryForObject("CARDEL_TGPR_DICTAMENES.montoMaxDelegacion", solicitud);
			return 	usuario.getMtoMaxDelegacion();
	}

	@SuppressWarnings("unchecked")
	public List<ProductoDelegacion> getDelegacionPersonaNatural(String codUsuario) {
		List<ProductoDelegacion> getLstProducto=getSqlMapClientTemplate().queryForList("CARDEL_TGPR_RIESGOS.getLstProductoDelegacion", codUsuario);
		if(!getLstProducto.isEmpty()){
			return getLstProducto;
		}else{
			return new ArrayList<ProductoDelegacion>();	
		}
	}
}