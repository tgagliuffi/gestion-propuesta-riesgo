package bbva.pe.gpr.form;

import org.apache.struts.action.ActionForm;

public class RolesForm  extends ActionForm{
	private String codRolHdn;
	private String codRol;
	private String descripcion;
	private String Referencia;
	private String codFunciones;

	public String getCodFunciones() {
		return codFunciones;
	}
	public void setCodFunciones(String codFunciones) {
		this.codFunciones = codFunciones;
	}
	public String getCodRolHdn() {
		return codRolHdn;
	}
	public void setCodRolHdn(String codRolHdn) {
		this.codRolHdn = codRolHdn;
	}
	public String getCodRol() {
		return codRol;
	}
	public void setCodRol(String codRol) {
		this.codRol = codRol;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getReferencia() {
		return Referencia;
	}
	public void setReferencia(String referencia) {
		Referencia = referencia;
	}

}
