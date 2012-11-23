package bbva.pe.gpr.bean;

import java.math.BigDecimal;

public class Rol {
	private BigDecimal codRolHdn;
    private BigDecimal codRol;
	private String descripcion;
	private String referencia;
	private BigDecimal estado;
	
	public BigDecimal getCodRol() {
		return codRol;
	}
	public void setCodRol(BigDecimal codRol) {
		this.codRol = codRol;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public BigDecimal getEstado() {
		return estado;
	}
	public void setEstado(BigDecimal estado) {
		this.estado = estado;
	}
	public BigDecimal getCodRolHdn() {
		return codRolHdn;
	}
	public void setCodRolHdn(BigDecimal codRolHdn) {
		this.codRolHdn = codRolHdn;
	}
}