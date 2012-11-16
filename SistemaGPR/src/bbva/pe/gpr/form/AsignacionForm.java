package bbva.pe.gpr.form;

import org.apache.struts.action.ActionForm;

public class AsignacionForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private  String codRol;
	private String codCentral;
	private String nroProducto;

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
	
	
       
}
