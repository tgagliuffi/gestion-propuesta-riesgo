/**
 * 
 */
package bbva.pe.gpr.service;

import bbva.pe.gpr.bean.Solicitud;

/**
 * @author INDRA S.A
 *
 */
public interface AplicativoPersonasService {
	Solicitud  invokeClient(String codCentral);
	Solicitud invokePE7CRUCE(Solicitud solicitudBean);
	String invokepPE2C5200(String rucEmpleador);
	Solicitud invokeGerenciaTerritorial(Solicitud solicitudBean);
	Solicitud invokeRelevancia(Solicitud solicitudBean);
	Solicitud invokeClasificacionCliente(Solicitud solicitudBean);

}
