package bbva.pe.gpr.bean;

import java.math.BigDecimal;

public class Garantia {
    private BigDecimal codGarantia;
    private String nombre;
    private BigDecimal estado;
    private BigDecimal codProducto;
    
	public BigDecimal getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(BigDecimal codProducto) {
		this.codProducto = codProducto;
	}
	public BigDecimal getCodGarantia() {
        return codGarantia;
    }
    public void setCodGarantia(BigDecimal codGarantia) {
        this.codGarantia = codGarantia;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre == null ? null : nombre.trim();
    }
    public BigDecimal getEstado() {
        return estado;
    }
    public void setEstado(BigDecimal estado) {
        this.estado = estado;
    }
}