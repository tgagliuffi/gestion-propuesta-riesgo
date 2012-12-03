package bbva.pe.gpr.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class Banca implements Serializable{

	private static final long serialVersionUID = 1L;
	private BigDecimal codBanca;
    private String nombre;
    private String descripcion;
    private BigDecimal estado;
    private BigDecimal flagAsignacion;
    private BigDecimal codRol;
    
	public BigDecimal getCodBanca() {
		return codBanca;
	}
	public void setCodBanca(BigDecimal codBanca) {
		this.codBanca = codBanca;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public BigDecimal getEstado() {
		return estado;
	}
	public void setEstado(BigDecimal estado) {
		this.estado = estado;
	}
	public BigDecimal getFlagAsignacion() {
		return flagAsignacion;
	}
	public void setFlagAsignacion(BigDecimal flagAsignacion) {
		this.flagAsignacion = flagAsignacion;
	}
	public BigDecimal getCodRol() {
		return codRol;
	}
	public void setCodRol(BigDecimal codRol) {
		this.codRol = codRol;
	}
    
    
}