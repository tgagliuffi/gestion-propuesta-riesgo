package bbva.pe.gpr.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SolicitudMensaje implements Serializable {

	private static final long serialVersionUID = 1L;
	private BigDecimal codMensaje;
    private String desMensaje;
    private BigDecimal estado;
    private Long nroSolicitud;
    private String codCentral;
    private Date fechaIngreso;
    private String strFechaIngreso;
    private String codUsuario;
    private String nomUsuario;
    private String desRol;
    
	public BigDecimal getCodMensaje() {
		return codMensaje;
	}
	public void setCodMensaje(BigDecimal codMensaje) {
		this.codMensaje = codMensaje;
	}
	public String getDesMensaje() {
		return desMensaje;
	}
	public void setDesMensaje(String desMensaje) {
		this.desMensaje = desMensaje;
	}
	public BigDecimal getEstado() {
		return estado;
	}
	public void setEstado(BigDecimal estado) {
		this.estado = estado;
	}
	public Long getNroSolicitud() {
		return nroSolicitud;
	}
	public void setNroSolicitud(Long nroSolicitud) {
		this.nroSolicitud = nroSolicitud;
	}
	public String getCodCentral() {
		return codCentral;
	}
	public void setCodCentral(String codCentral) {
		this.codCentral = codCentral;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public String getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}
	public String getNomUsuario() {
		return nomUsuario;
	}
	public void setNomUsuario(String nomUsuario) {
		this.nomUsuario = nomUsuario;
	}
	public String getDesRol() {
		return desRol;
	}
	public void setDesRol(String desRol) {
		this.desRol = desRol;
	}
	public String getStrFechaIngreso() {
		return strFechaIngreso;
	}
	public void setStrFechaIngreso(String strFechaIngreso) {
		this.strFechaIngreso = strFechaIngreso;
	}

}