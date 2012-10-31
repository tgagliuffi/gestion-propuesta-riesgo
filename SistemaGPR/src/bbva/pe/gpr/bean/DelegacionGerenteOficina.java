package bbva.pe.gpr.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class DelegacionGerenteOficina implements Serializable{

	private static final long serialVersionUID = 6421966427613460523L;

	private BigDecimal codProducto;
	private BigDecimal montoProducto;
	private BigDecimal plazoProducto;
	
	public BigDecimal getPlazoProducto() {
		return plazoProducto;
	}
	public void setPlazoProducto(BigDecimal plazoProducto) {
		this.plazoProducto = plazoProducto;
	}
	public BigDecimal getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(BigDecimal codProducto) {
		this.codProducto = codProducto;
	}
	public BigDecimal getMontoProducto() {
		return montoProducto;
	}
	public void setMontoProducto(BigDecimal montoProducto) {
		this.montoProducto = montoProducto;
	}
	

		
}
