package bbva.pe.gpr.serviceImpl;

import java.math.BigDecimal;

import bbva.pe.gpr.service.AplicativoDelegacionService;

public class AplicativoDelegacionServiceImpl implements AplicativoDelegacionService {

	public BigDecimal obtenerMontoDelegación(String codUsuario){
		return new BigDecimal(200000);
		
	}
	
}
