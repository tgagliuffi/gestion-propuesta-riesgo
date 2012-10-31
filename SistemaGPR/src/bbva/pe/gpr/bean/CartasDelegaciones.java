package bbva.pe.gpr.bean;

import java.io.Serializable;

public class CartasDelegaciones implements Serializable {

	private static final long serialVersionUID = -1992365629518921724L;
	private String rating;
	private int porcentaje;
	private int plazos;
	private int montos;
	private String conGarantia;
	
	public int getPlazos() {
		return plazos;
	}
	public void setPlazos(int plazos) {
		this.plazos = plazos;
	}
	public int getMontos() {
		return montos;
	}
	public void setMontos(int montos) {
		this.montos = montos;
	}	
	private int montoPivot;

	public int getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}	
	public int getMontoPivot() {
		return montoPivot;
	}
	public void setMontoPivot(int montoPivot) {
		this.montoPivot = montoPivot;
	}	
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getConGarantia() {
		return conGarantia;
	}
	public void setConGarantia(String conGarantia) {
		this.conGarantia = conGarantia;
	}
}