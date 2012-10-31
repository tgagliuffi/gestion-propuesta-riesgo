package bbva.pe.gpr.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Asignacion extends AsignacionKey {
    private String nombre;
    private BigDecimal codRol;
    private String dependiente;
    private String codUsuarioAsigno;
    private Date fechaAsignacion;
    private BigDecimal estado;
    private String codCentral;
    private String strMensaje;
	
    public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public BigDecimal getCodRol() {
		return codRol;
	}
	public void setCodRol(BigDecimal codRol) {
		this.codRol = codRol;
	}
	public String getDependiente() {
		return dependiente;
	}
	public void setDependiente(String dependiente) {
		this.dependiente = dependiente;
	}
	public String getCodUsuarioAsigno() {
		return codUsuarioAsigno;
	}
	public void setCodUsuarioAsigno(String codUsuarioAsigno) {
		this.codUsuarioAsigno = codUsuarioAsigno;
	}
	public Date getFechaAsignacion() {
		return fechaAsignacion;
	}
	public void setFechaAsignacion(Date fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}
	public BigDecimal getEstado() {
		return estado;
	}
	public void setEstado(BigDecimal estado) {
		this.estado = estado;
	}
	public String getCodCentral() {
		return codCentral;
	}
	public void setCodCentral(String codCentral) {
		this.codCentral = codCentral;
	}
	public String getStrMensaje() {
		return strMensaje;
	}
	public void setStrMensaje(String strMensaje) {
		this.strMensaje = strMensaje;
	}
	
    
}