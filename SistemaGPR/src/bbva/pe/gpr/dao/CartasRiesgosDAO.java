package bbva.pe.gpr.dao;

import java.math.BigDecimal;
import java.util.List;

import bbva.pe.gpr.bean.ProductoDelegacion;

public interface CartasRiesgosDAO {

	BigDecimal montDelegacionSinRating(String codUsuario,String grupoPersona);
	BigDecimal montDelegacionRating(String codUsuario,String escala);
	List<ProductoDelegacion> getDelegacionPersonaNatural(String codUsuario);
	String montoDelegacionUsuario(String codUsuario,String tipoPersona);
}
