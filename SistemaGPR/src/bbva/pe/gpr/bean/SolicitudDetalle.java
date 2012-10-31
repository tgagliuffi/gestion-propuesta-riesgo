package bbva.pe.gpr.bean;

import java.math.BigDecimal;

public class SolicitudDetalle extends SolicitudDetalleKey {
	
	private BigDecimal codProducto;
	private String desProducto;
    private String codProdBase;
    private String scoring;
	private String contratoVinculado;
	private String codPrevaluador;
	private String desMultCampania;
	private String tipo;
	private BigDecimal monto;
	private String desMultPlazo;
	private String desGarantia;
	
	private Short estado;
	private String codCentral;
	private String plazoGarantia;
	private String codMultMoneda;
	private String codMultPlazo;
	private String codMultCampania;
	private BigDecimal codGarantia;
	
	public BigDecimal getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(BigDecimal codProducto) {
		this.codProducto = codProducto;
	}
	public String getDesProducto() {
		return desProducto;
	}
	public void setDesProducto(String desProducto) {
		this.desProducto = desProducto;
	}
	public String getCodProdBase() {
		return codProdBase;
	}
	public void setCodProdBase(String codProdBase) {
		this.codProdBase = codProdBase;
	}
	public String getScoring() {
		return scoring;
	}
	public void setScoring(String scoring) {
		this.scoring = scoring;
	}
	public String getContratoVinculado() {
		return contratoVinculado;
	}
	public void setContratoVinculado(String contratoVinculado) {
		this.contratoVinculado = contratoVinculado;
	}
	public String getCodPrevaluador() {
		return codPrevaluador;
	}
	public void setCodPrevaluador(String codPrevaluador) {
		this.codPrevaluador = codPrevaluador;
	}
	public String getDesMultCampania() {
		return desMultCampania;
	}
	public void setDesMultCampania(String desMultCampania) {
		this.desMultCampania = desMultCampania;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public BigDecimal getMonto() {
		return monto;
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	public String getDesMultPlazo() {
		return desMultPlazo;
	}
	public void setDesMultPlazo(String desMultPlazo) {
		this.desMultPlazo = desMultPlazo;
	}
	public String getDesGarantia() {
		return desGarantia;
	}
	public void setDesGarantia(String desGarantia) {
		this.desGarantia = desGarantia;
	}
	public Short getEstado() {
		return estado;
	}
	public void setEstado(Short estado) {
		this.estado = estado;
	}
	public String getCodCentral() {
		return codCentral;
	}
	public void setCodCentral(String codCentral) {
		this.codCentral = codCentral;
	}
	public String getPlazoGarantia() {
		return plazoGarantia;
	}
	public void setPlazoGarantia(String plazoGarantia) {
		this.plazoGarantia = plazoGarantia;
	}
	public String getCodMultMoneda() {
		return codMultMoneda;
	}
	public void setCodMultMoneda(String codMultMoneda) {
		this.codMultMoneda = codMultMoneda;
	}
	public String getCodMultPlazo() {
		return codMultPlazo;
	}
	public void setCodMultPlazo(String codMultPlazo) {
		this.codMultPlazo = codMultPlazo;
	}
	public String getCodMultCampania() {
		return codMultCampania;
	}
	public void setCodMultCampania(String codMultCampania) {
		this.codMultCampania = codMultCampania;
	}
	public BigDecimal getCodGarantia() {
		return codGarantia;
	}
	public void setCodGarantia(BigDecimal codGarantia) {
		this.codGarantia = codGarantia;
	}

    
	
	
}