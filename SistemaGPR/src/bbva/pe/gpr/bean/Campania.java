package bbva.pe.gpr.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Campania {
	private Long CodCampania;
	private String nombre;
	private String descripcion;
	private Date fechaIniVigencia;
	private Date fechaFinVigencia;
	private BigDecimal codProducto;
	private BigDecimal estado;
	
	private Date fechaRegistro;
	
	public Long getCodCampania() {
		return CodCampania;
	}
	public void setCodCampania(Long codCampania) {
		CodCampania = codCampania;
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
	public Date getFechaIniVigencia() {
		return fechaIniVigencia;
	}
	public void setFechaIniVigencia(Date fechaIniVigencia) {
		this.fechaIniVigencia = fechaIniVigencia;
	}
	public Date getFechaFinVigencia() {
		return fechaFinVigencia;
	}
	public void setFechaFinVigencia(Date fechaFinVigencia) {
		this.fechaFinVigencia = fechaFinVigencia;
	}
	public BigDecimal getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(BigDecimal codProducto) {
		this.codProducto = codProducto;
	}
	public BigDecimal getEstado() {
		return estado;
	}
	public void setEstado(BigDecimal estado) {
		this.estado = estado;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	
	
	

}
