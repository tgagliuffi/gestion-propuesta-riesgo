package bbva.pe.gpr.dao;

import java.math.BigDecimal;
import java.util.List;

import bbva.pe.gpr.bean.ProductoDelegacion;

public interface CartasRiesgosDAO {

	BigDecimal montoDelegacionPorRating(String codUsuario,String rating,String grupoPersona);
	BigDecimal montoDelegacionSinRating(String codUsuario,String grupoPersona);
	List<ProductoDelegacion> getDelegacionPersonaNatural(String codUsuario,String grupoPersona);
	
}
