package bbva.pe.gpr.bean;

import java.math.BigDecimal;

public class FuncionRolKey {
    private BigDecimal codFuncion;
    private BigDecimal codRol;

    public BigDecimal getCodFuncion() {
        return codFuncion;
    }
    public void setCodFuncion(BigDecimal codFuncion) {
        this.codFuncion = codFuncion;
    }
    public BigDecimal getCodRol() {
        return codRol;
    }
    public void setCodRol(BigDecimal codRol) {
        this.codRol = codRol;
    }
}