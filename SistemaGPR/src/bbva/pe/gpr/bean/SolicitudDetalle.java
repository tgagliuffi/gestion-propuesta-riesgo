package bbva.pe.gpr.bean;

import java.math.BigDecimal;

public class SolicitudDetalle extends SolicitudDetalleKey {
	
	private int indice;
	private BigDecimal codProducto;
	private String desProducto;
    private String codProdBase;
    private String desProdBase;
    private String scoring;
	private String contratoVinculado;
	private String codPrevaluador;
	private String desCampania;
	private String tipo;
	private String desTipo;
	private BigDecimal mtoProducto;
	private Integer plazo;
	private BigDecimal mtoGarantia;
	
	private Short estado;
	private String codCentral;
	private String plazoGarantia;


	private BigDecimal codCampania;
	private BigDecimal codGarantia;
	private BigDecimal mtoTotalRow;
	
	public int getIndice() {
		return indice;
	}
	public void setIndice(int indice) {
		this.indice = indice;
	}
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
	public String getDesProdBase() {
		return desProdBase;
	}
	public void setDesProdBase(String desProdBase) {
		this.desProdBase = desProdBase;
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
	public String getDesCampania() {
		return desCampania;
	}
	public void setDesCampania(String desCampania) {
		this.desCampania = desCampania;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDesTipo() {
		return desTipo;
	}
	public void setDesTipo(String desTipo) {
		this.desTipo = desTipo;
	}
	public BigDecimal getMtoProducto() {
		return mtoProducto;
	}
	public void setMtoProducto(BigDecimal mtoProducto) {
		this.mtoProducto = mtoProducto;
	}
	public Integer getPlazo() {
		return plazo;
	}
	public void setPlazo(Integer plazo) {
		this.plazo = plazo;
	}
	public BigDecimal getMtoGarantia() {
		return mtoGarantia;
	}
	public void setMtoGarantia(BigDecimal mtoGarantia) {
		this.mtoGarantia = mtoGarantia;
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
	public BigDecimal getCodCampania() {
		return codCampania;
	}
	public void setCodCampania(BigDecimal codCampania) {
		this.codCampania = codCampania;
	}
	public BigDecimal getCodGarantia() {
		return codGarantia;
	}
	public void setCodGarantia(BigDecimal codGarantia) {
		this.codGarantia = codGarantia;
	}
	public BigDecimal getMtoTotalRow() {
		return mtoTotalRow;
	}
	public void setMtoTotalRow(BigDecimal mtoTotalRow) {
		this.mtoTotalRow = mtoTotalRow;
	}
	
	

}