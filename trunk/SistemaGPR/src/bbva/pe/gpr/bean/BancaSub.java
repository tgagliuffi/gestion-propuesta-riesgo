package bbva.pe.gpr.bean;

import java.math.BigDecimal;

public class BancaSub {
    private String  codSubanca;
    private String descripcion;
    private String detalle;
    private BigDecimal estado;
    private BigDecimal codBanca;
    
	public String getCodSubanca() {
		return codSubanca;
	}
	public void setCodSubanca(String codSubanca) {
		this.codSubanca = codSubanca;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public BigDecimal getEstado() {
		return estado;
	}
	public void setEstado(BigDecimal estado) {
		this.estado = estado;
	}
	public BigDecimal getCodBanca() {
		return codBanca;
	}
	public void setCodBanca(BigDecimal codBanca) {
		this.codBanca = codBanca;
	}
	
    
}