package bbva.pe.gpr.dao;

import java.math.BigDecimal;
import java.util.List;

import bbva.pe.gpr.bean.ProductoDelegacion;

public interface CartasRiesgosDAO {

	BigDecimal montDelegacion(String codUsuario,String grupoPersona);
	List<ProductoDelegacion> getDelegacionPersonaNatural(String codUsuario);
}
