package bbva.pe.gpr.service;

import bbva.pe.gpr.bean.CondicionCliente;
import bbva.pe.gpr.bean.Solicitud;

public interface ControlService {

	int condicionCliente(CondicionCliente datosCondicionCliente)throws Exception;
	int validacionMontosPlazos(Solicitud solicitud)throws Exception;
	int metodoGenericoCondCliente(String codMultiTabla, String codValor)throws Exception;
}