package bbva.pe.gpr.form;

import org.apache.struts.action.ActionForm;

public class ProductoForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	private String codProducto;
	private String desProducto;
	private String contratoVinculado;
	private String codPreEvaluador;
	private String campania;
	private String tipo;
	private String monto;
	private String plazo;
	private String garantia;
	private String valBanca;
	private String valMoneda;
	private String valMontoTotal;
	
	public ProductoForm() {
		codProducto = "";
		desProducto = "";
		contratoVinculado = "";
		codPreEvaluador = "";
		campania = "";
		tipo = "";
		monto = "";
		plazo = "";
		garantia = "";
		valBanca = "";
		valMoneda = "";
		valMontoTotal = "";
	}

	public String getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}

	public String getDesProducto() {
		return desProducto;
	}

	public void setDesProducto(String desProducto) {
		this.desProducto = desProducto;
	}

	public String getContratoVinculado() {
		return contratoVinculado;
	}

	public void setContratoVinculado(String contratoVinculado) {
		this.contratoVinculado = contratoVinculado;
	}

	public String getCodPreEvaluador() {
		return codPreEvaluador;
	}

	public void setCodPreEvaluador(String codPreEvaluador) {
		this.codPreEvaluador = codPreEvaluador;
	}

	public String getCampania() {
		return campania;
	}

	public void setCampania(String campania) {
		this.campania = campania;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getPlazo() {
		return plazo;
	}

	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	public String getGarantia() {
		return garantia;
	}

	public void setGarantia(String garantia) {
		this.garantia = garantia;
	}

	public String getValBanca() {
		return valBanca;
	}

	public void setValBanca(String valBanca) {
		this.valBanca = valBanca;
	}

	public String getValMoneda() {
		return valMoneda;
	}

	public void setValMoneda(String valMoneda) {
		this.valMoneda = valMoneda;
	}

	public String getValMontoTotal() {
		return valMontoTotal;
	}

	public void setValMontoTotal(String valMontoTotal) {
		this.valMontoTotal = valMontoTotal;
	}
}
