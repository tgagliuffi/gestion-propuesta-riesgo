package bbva.pe.gpr.form;

import org.apache.struts.action.ActionForm;

public class CondicionClienteForm  extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4949900148225467426L;
    private String valores;
    
	public String getValores() {
		return valores;
	}
	public void setValores(String valores) {
		this.valores = valores;
	}
}