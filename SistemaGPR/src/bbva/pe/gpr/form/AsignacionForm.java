package bbva.pe.gpr.form;

import org.apache.struts.action.ActionForm;

public class AsignacionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private  String codRol;
	private String codCentral;
	private String nroProducto;
	private String hdnArreglo;
	private String hdnRegistro;
	private String fueraRango;
	private String fechaIngresoIni;
	private String fechaIngresoFin;
	private String fechaVencimiento;
	private String codCargo;
	

	public String getCodRol() {
		return codRol;
	}

	public void setCodRol(String codRol) {
		this.codRol = codRol;
	}

	public String getCodCentral() {
		return codCentral;
	}

	public void setCodCentral(String codCentral) {
		this.codCentral = codCentral;
	}

	public String getNroProducto() {
		return nroProducto;
	}

	public void setNroProducto(String nroProducto) {
		this.nroProducto = nroProducto;
	}

	public String getHdnArreglo() {
		return hdnArreglo;
	}

	public void setHdnArreglo(String hdnArreglo) {
		this.hdnArreglo = hdnArreglo;
	}

	public String getHdnRegistro() {
		return hdnRegistro;
	}

	public void setHdnRegistro(String hdnRegistro) {
		this.hdnRegistro = hdnRegistro;
	}

	public String getFueraRango() {
		return fueraRango;
	}

	public void setFueraRango(String fueraRango) {
		this.fueraRango = fueraRango;
	}

	public String getFechaIngresoIni() {
		return fechaIngresoIni;
	}

	public void setFechaIngresoIni(String fechaIngresoIni) {
		this.fechaIngresoIni = fechaIngresoIni;
	}

	public String getFechaIngresoFin() {
		return fechaIngresoFin;
	}

	public void setFechaIngresoFin(String fechaIngresoFin) {
		this.fechaIngresoFin = fechaIngresoFin;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getCodCargo() {
		return codCargo;
	}

	public void setCodCargo(String codCargo) {
		this.codCargo = codCargo;
	}
	       
}
