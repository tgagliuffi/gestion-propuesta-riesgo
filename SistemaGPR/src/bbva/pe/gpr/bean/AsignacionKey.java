package bbva.pe.gpr.bean;

import java.math.BigDecimal;

public class AsignacionKey {
    private BigDecimal codAsignacion;
    private String codUsuario;
    private Long nroSolicitud;
    
	public BigDecimal getCodAsignacion() {
		return codAsignacion;
	}
	public void setCodAsignacion(BigDecimal codAsignacion) {
		this.codAsignacion = codAsignacion;
	}
	public String getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}
	public Long getNroSolicitud() {
		return nroSolicitud;
	}
	public void setNroSolicitud(Long nroSolicitud) {
		this.nroSolicitud = nroSolicitud;
	}

    
}