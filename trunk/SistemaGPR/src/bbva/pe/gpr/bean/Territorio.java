package bbva.pe.gpr.bean;

import java.math.BigDecimal;

public class Territorio {
    private BigDecimal codTerritorio;
    private String nombre;
    private BigDecimal estado;
	private String detalle;
	
    public BigDecimal getCodTerritorio() {
		return codTerritorio;
	}
	public void setCodTerritorio(BigDecimal codTerritorio) {
		this.codTerritorio = codTerritorio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public BigDecimal getEstado() {
		return estado;
	}
	public void setEstado(BigDecimal estado) {
		this.estado = estado;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}   
}