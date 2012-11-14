package bbva.pe.gpr.bean;

import java.math.BigDecimal;

public class Funcion {
    private BigDecimal codFuncion;
    private String nombre;
    private String descripcion;
    private String estado;

    public BigDecimal getCodFuncion() {
        return codFuncion;
    }
    public void setCodFuncion(BigDecimal codFuncion) {
        this.codFuncion = codFuncion;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre == null ? null : nombre.trim();
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion == null ? null : descripcion.trim();
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado == null ? null : estado.trim();
    }
}