package bbva.pe.gpr.bean;

import java.math.BigDecimal;

public class Dictamen extends DictamenKey {
	private String codMultNivel;
	private String codMultMoneda;
	private String descripcionMoneda;
	private BigDecimal riesgoTotal;
	private BigDecimal mtoAprobado;
	private String cual;
	private String cuant;
	private String icon;
	private String calerta;
	private String proactividad;
	private String otro;
	private String ctrlScoring;
	private String fechaVencimiento;
	private BigDecimal estado;
	private String codCentral;
	private String strMensaje;
	
	public String getCodMultNivel() {
		return codMultNivel;
	}
	public void setCodMultNivel(String codMultNivel) {
		this.codMultNivel = codMultNivel;
	}
	public String getCodMultMoneda() {
		return codMultMoneda;
	}
	public void setCodMultMoneda(String codMultMoneda) {
		this.codMultMoneda = codMultMoneda;
	}
	public String getDescripcionMoneda() {
		return descripcionMoneda;
	}
	public void setDescripcionMoneda(String descripcionMoneda) {
		this.descripcionMoneda = descripcionMoneda;
	}
	public BigDecimal getRiesgoTotal() {
		return riesgoTotal;
	}
	public void setRiesgoTotal(BigDecimal riesgoTotal) {
		this.riesgoTotal = riesgoTotal;
	}
	public BigDecimal getMtoAprobado() {
		return mtoAprobado;
	}
	public void setMtoAprobado(BigDecimal mtoAprobado) {
		this.mtoAprobado = mtoAprobado;
	}
	public String getCual() {
		return cual;
	}
	public void setCual(String cual) {
		this.cual = cual;
	}
	public String getCuant() {
		return cuant;
	}
	public void setCuant(String cuant) {
		this.cuant = cuant;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getCalerta() {
		return calerta;
	}
	public void setCalerta(String calerta) {
		this.calerta = calerta;
	}
	public String getProactividad() {
		return proactividad;
	}
	public void setProactividad(String proactividad) {
		this.proactividad = proactividad;
	}
	public String getOtro() {
		return otro;
	}
	public void setOtro(String otro) {
		this.otro = otro;
	}
	public String getCtrlScoring() {
		return ctrlScoring;
	}
	public void setCtrlScoring(String ctrlScoring) {
		this.ctrlScoring = ctrlScoring;
	}
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
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
	public String getStrMensaje() {
		return strMensaje;
	}
	public void setStrMensaje(String strMensaje) {
		this.strMensaje = strMensaje;
	}
	
    
}