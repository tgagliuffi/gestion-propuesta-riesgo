package bbva.pe.gpr.bean;


import bbva.pe.gpr.util.GenericObject;

public class Parametro extends GenericObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codParametro;
	private String desParametro;
	
	public Parametro() {
		codParametro = "";
		desParametro = "";
	}
	
	public String getCodParametro() {
		return codParametro;
	}
	public void setCodParametro(String codParametro) {
		this.codParametro = codParametro;
	}
	public String getDesParametro() {
		return desParametro;
	}
	public void setDesParametro(String desParametro) {
		this.desParametro = desParametro;
	}
}
