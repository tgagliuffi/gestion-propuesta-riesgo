package bbva.pe.gpr.bean;

import java.util.List;

public class Delegacion {

	private String codUsuario;
	private String montoMaximo;	
	private List<ProductoDelegacion> getLstProductoDelegacion;
	
	public String getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}
	public String getMontoMaximo() {
		return montoMaximo;
	}
	public void setMontoMaximo(String montoMaximo) {
		this.montoMaximo = montoMaximo;
	}
	public List<ProductoDelegacion> getGetLstProductoDelegacion() {
		return getLstProductoDelegacion;
	}
	public void setGetLstProductoDelegacion(
			List<ProductoDelegacion> getLstProductoDelegacion) {
		this.getLstProductoDelegacion = getLstProductoDelegacion;
	}
	

}