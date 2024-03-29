package bbva.pe.gpr.serviceImpl;

import java.math.BigDecimal;

import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.service.AplicativoRCCService;

public class AplicativoRCCServiceImpl implements AplicativoRCCService{

	public Solicitud invokeDeudaSisFinanciero(Solicitud solicitudBean){
		if(solicitudBean.getDeudaSistemaFinanciero() != null) {
			solicitudBean.setDeudaSistemaFinanciero(BigDecimal.TEN.add(solicitudBean.getDeudaSistemaFinanciero()));
		} else {
			solicitudBean.setDeudaSistemaFinanciero(new BigDecimal(1000));
		}
		return solicitudBean; 
	}
}
