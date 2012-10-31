package bbva.pe.gpr.bean;

import java.math.BigDecimal;

public class SolicitudOperacion extends SolicitudOperacionKey {
    
	private String codMultOperacion;
    private String desOperacion;
    private BigDecimal estado;
    private String codCentral;
	
    public Object getCodMultOperacion() {
		return codMultOperacion;
	}
	public void setCodMultOperacion(String codMultOperacion) {
		this.codMultOperacion = codMultOperacion;
	}
	public String getDesOperacion() {
		return desOperacion;
	}
	public void setDesOperacion(String desOperacion) {
		this.desOperacion = desOperacion;
	}
	public BigDecimal getEstado() {
		return estado;
	}
	public void setEstado(BigDecimal estado) {
		this.estado = estado;
	}
	public String getCodCentral() {
		return codCentral;
	}
	public void setCodCentral(String codCentral) {
		this.codCentral = codCentral;
	}
    
   
}