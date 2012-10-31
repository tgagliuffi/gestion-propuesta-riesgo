package bbva.pe.gpr.serviceImpl;

import java.math.BigDecimal;

import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.service.AplicativoRCDService;


public class AplicativoRCDServiceImpl implements AplicativoRCDService{
	public Solicitud invokeDedudasRCD(Solicitud solicitudBean){
		solicitudBean.setDeudaDirecta(new BigDecimal(200));
		solicitudBean.setDeudaIndirecta(new BigDecimal(300));
		solicitudBean.setCastigo(new BigDecimal(400));
		return solicitudBean;
	}
}
