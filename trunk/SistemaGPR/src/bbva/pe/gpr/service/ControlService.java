package bbva.pe.gpr.service;

import java.util.List;

import bbva.pe.gpr.bean.Delegacion;
import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.bean.SolicitudDetalle;

public interface ControlService {

	String mensajeCondicionCliente(Solicitud solicitud)throws Exception;
	int condicionCliente(Solicitud solicitud)throws Exception;
	int validacionMontosPlazos(List<SolicitudDetalle> solicitud )throws Exception;
	String metodoGenericoCondCliente(String codMultiTabla, String codValor)throws Exception;
	String getLstValoresCondicion(String codMultiTabla)throws Exception;
	int validarMontoDelegacion(Solicitud solicitud)throws Exception;
	Delegacion getDelegacion(String idUsuario)throws Exception;
}