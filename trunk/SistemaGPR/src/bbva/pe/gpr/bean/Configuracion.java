package bbva.pe.gpr.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Configuracion {
    private BigDecimal codConfiguracion;
	private String codMultPersona;
	private BigDecimal estado;
	private String descripcion;
	private Date fechaCreacion;
	private BigDecimal monto01;
	private BigDecimal monto02;
	private BigDecimal monto03;
	private BigDecimal monto04;
	private BigDecimal plazoMeses;
	private String vigente;
	
	public BigDecimal getCodConfiguracion() {
		return codConfiguracion;
	}
	public void setCodConfiguracion(BigDecimal codConfiguracion) {
		this.codConfiguracion = codConfiguracion;
	}
	public String getCodMultPersona() {
		return codMultPersona;
	}
	public void setCodMultPersona(String codMultPersona) {
		this.codMultPersona = codMultPersona;
	}
	public BigDecimal getEstado() {
		return estado;
	}
	public void setEstado(BigDecimal estado) {
		this.estado = estado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public BigDecimal getMonto01() {
		return monto01;
	}
	public void setMonto01(BigDecimal monto01) {
		this.monto01 = monto01;
	}
	public BigDecimal getMonto02() {
		return monto02;
	}
	public void setMonto02(BigDecimal monto02) {
		this.monto02 = monto02;
	}
	public BigDecimal getMonto03() {
		return monto03;
	}
	public void setMonto03(BigDecimal monto03) {
		this.monto03 = monto03;
	}
	public BigDecimal getMonto04() {
		return monto04;
	}
	public void setMonto04(BigDecimal monto04) {
		this.monto04 = monto04;
	}
	public BigDecimal getPlazoMeses() {
		return plazoMeses;
	}
	public void setPlazoMeses(BigDecimal plazoMeses) {
		this.plazoMeses = plazoMeses;
	}
	public String getVigente() {
		return vigente;
	}
	public void setVigente(String vigente) {
		this.vigente = vigente;
	}
	
	

    
}