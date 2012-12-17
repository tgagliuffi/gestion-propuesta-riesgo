package bbva.pe.gpr.bean;

import java.math.BigDecimal;

public class ProductoBase {
    private BigDecimal codProductoBase;
	private String nombre;
	private String estado;
	private String descripcion;
	private String referencia;

    public BigDecimal getCodProductoBase() {
        return codProductoBase;
    }

    public void setCodProductoBase(BigDecimal codProductoBase) {
        this.codProductoBase = codProductoBase;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre == null ? null : nombre.trim();
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado == null ? null : estado.trim();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion == null ? null : descripcion.trim();
    }

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
}