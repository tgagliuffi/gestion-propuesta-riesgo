package bbva.pe.gpr.dao;

import java.math.BigDecimal;
import java.util.List;

import bbva.pe.gpr.bean.ProductoDelegacion;

public interface CartasRiesgosDAO {

	BigDecimal montDelegacionSinRating(String codUsuario,String grupoPersona) throws Exception;
	BigDecimal montDelegacionRating(String codUsuario,String escala) throws Exception;
	List<ProductoDelegacion> getDelegacionPersonaNatural(String codUsuario) throws Exception;
	String montoDelegacionUsuario(String codUsuario,String tipoPersona) throws Exception;
}
