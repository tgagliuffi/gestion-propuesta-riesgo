package bbva.pe.gpr.bean;

import java.math.BigDecimal;

public class SolicitudOperacionKey {
    private BigDecimal codSolicitudOperacion;
    private Long nroSolicitud;
    
	public BigDecimal getCodSolicitudOperacion() {
		return codSolicitudOperacion;
	}
	public void setCodSolicitudOperacion(BigDecimal codSolicitudOperacion) {
		this.codSolicitudOperacion = codSolicitudOperacion;
	}
	public Long getNroSolicitud() {
		return nroSolicitud;
	}
	public void setNroSolicitud(Long nroSolicitud) {
		this.nroSolicitud = nroSolicitud;
	}
    
    

}