package bbva.pe.gpr.bean;

import java.math.BigDecimal;

public class FuncionRol extends FuncionRolKey {
    private BigDecimal posicion;
    private BigDecimal estado;

    public BigDecimal getPosicion() {
        return posicion;
    }
    public void setPosicion(BigDecimal posicion) {
        this.posicion = posicion;
    }
    public BigDecimal getEstado() {
        return estado;
    }
    public void setEstado(BigDecimal estado) {
        this.estado = estado;
    }
}