package bbva.pe.gpr.service;

import bbva.pe.gpr.bean.Delegacion;
import bbva.pe.gpr.bean.Solicitud;

public interface ControlService {

	String mensajeCondicionCliente(Solicitud solicitud)throws Exception;
	int condicionCliente(Solicitud solicitud)throws Exception;
	int validacionMontosPlazos(Solicitud solicitud,String codGestor )throws Exception;
	String metodoGenericoCondCliente(String codMultiTabla, String codValor)throws Exception;
	Delegacion getDelegacion(String idUsuario)throws Exception;
	String jefeInmediato(String codUsuario,Solicitud solicitud)throws Exception;
}