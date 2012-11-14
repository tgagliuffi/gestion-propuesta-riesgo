package bbva.pe.gpr.form;

import org.apache.struts.action.ActionForm;

public class AsignarOficinaForm  extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4949900148225467426L;
    private String codUsuario;
   	private String bancaUsuario;
    private String cargoUsuario;
    private String nombreUsuario;
    private String apeMaterno;
    private String apePaterno;
    private String idTerritorio;
    private String codAsignar;
    private String codOficinas;
  
    public String getCodOficinas() {
		return codOficinas;
	}
	public void setCodOficinas(String codOficinas) {
		this.codOficinas = codOficinas;
	}
	public String getCodAsignar() {
		return codAsignar;
	}
	public void setCodAsignar(String codAsignar) {
		this.codAsignar = codAsignar;
	}
	public String getIdTerritorio() {
		return idTerritorio;
	}
	public void setIdTerritorio(String idTerritorio) {
		this.idTerritorio = idTerritorio;
	}
	public String getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}
	public String getBancaUsuario() {
		return bancaUsuario;
	}
	public void setBancaUsuario(String bancaUsuario) {
		this.bancaUsuario = bancaUsuario;
	}
	public String getCargoUsuario() {
		return cargoUsuario;
	}
	public void setCargoUsuario(String cargoUsuario) {
		this.cargoUsuario = cargoUsuario;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getApeMaterno() {
		return apeMaterno;
	}
	public void setApeMaterno(String apeMaterno) {
		this.apeMaterno = apeMaterno;
	}
	public String getApePaterno() {
		return apePaterno;
	}
	public void setApePaterno(String apePaterno) {
		this.apePaterno = apePaterno;
	}
}