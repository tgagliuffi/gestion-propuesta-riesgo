package bbva.pe.gpr.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Producto {
    private BigDecimal codProducto;
	private String descripcion;
	private BigDecimal codBanca;
	private BigDecimal codProductoBase;
	private Date fechaCreacion;
	private Date vigencia;
	private BigDecimal estado;
	
	public BigDecimal getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(BigDecimal codProducto) {
		this.codProducto = codProducto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public BigDecimal getCodBanca() {
		return codBanca;
	}
	public void setCodBanca(BigDecimal codBanca) {
		this.codBanca = codBanca;
	}
	public BigDecimal getCodProductoBase() {
		return codProductoBase;
	}
	public void setCodProductoBase(BigDecimal codProductoBase) {
		this.codProductoBase = codProductoBase;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getVigencia() {
		return vigencia;
	}
	public void setVigencia(Date vigencia) {
		this.vigencia = vigencia;
	}
	public BigDecimal getEstado() {
		return estado;
	}
	public void setEstado(BigDecimal estado) {
		this.estado = estado;
	}

		
    
}