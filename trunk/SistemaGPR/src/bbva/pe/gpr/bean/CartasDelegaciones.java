package bbva.pe.gpr.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartasDelegaciones implements Serializable {

	private static final long serialVersionUID = -1992365629518921724L;
	private String rating;
	private int porcentaje;
	private int plazos;
	private BigDecimal montos;
	private BigDecimal conGarantia;
	
	public int getPlazos() {
		return plazos;
	}
	public void setPlazos(int plazos) {
		this.plazos = plazos;
	}
	public BigDecimal getMontos() {
		return montos;
	}
	public void setMontos(BigDecimal montos) {
		this.montos = montos;
	}	
	private BigDecimal montoPivot;

	public int getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}	
	public BigDecimal getMontoPivot() {
		return montoPivot;
	}
	public void setMontoPivot(BigDecimal montoPivot) {
		this.montoPivot = montoPivot;
	}	
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public BigDecimal getConGarantia() {
		return conGarantia;
	}
	public void setConGarantia(BigDecimal conGarantia) {
		this.conGarantia = conGarantia;
	}

}