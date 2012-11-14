package bbva.pe.gpr.form;

import java.math.BigDecimal;

import org.apache.struts.action.ActionForm;

public class UsuarioForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9214871458253164892L;
	private String codUsuario;
	private String nombre;
	private String apeMaterno;
	private String apePaterno;
	private String codBanca;
	private String codCargo;
	private String codOficina;
	private String nombreOficina;
	private String jefeInmediato;
	private String idEvaluador;
	private String idEvaDicta;
	private String idAsigAsiMismo;
	private String idAsiEquNivelInfe;
	private String idAsiOtroEquNivelInfe;
	private String idAsigEquiMisNivel;
	private String idAsigOtroEquiMisNivel;
	private String idModifOperDicta;
	private BigDecimal codRol;
	private String descripcionRol;
	private String idFunciones;
	
	public String getJefeInmediato() {
		return jefeInmediato;
	}
	public void setJefeInmediato(String jefeInmediato) {
		this.jefeInmediato = jefeInmediato;
	}
	public String getIdEvaluador() {
		return idEvaluador;
	}
	public void setIdEvaluador(String idEvaluador) {
		this.idEvaluador = idEvaluador;
	}
	public String getIdEvaDicta() {
		return idEvaDicta;
	}
	public void setIdEvaDicta(String idEvaDicta) {
		this.idEvaDicta = idEvaDicta;
	}
	public String getIdAsigAsiMismo() {
		return idAsigAsiMismo;
	}
	public void setIdAsigAsiMismo(String idAsigAsiMismo) {
		this.idAsigAsiMismo = idAsigAsiMismo;
	}
	public String getIdAsiEquNivelInfe() {
		return idAsiEquNivelInfe;
	}
	public void setIdAsiEquNivelInfe(String idAsiEquNivelInfe) {
		this.idAsiEquNivelInfe = idAsiEquNivelInfe;
	}
	public String getIdAsiOtroEquNivelInfe() {
		return idAsiOtroEquNivelInfe;
	}
	public void setIdAsiOtroEquNivelInfe(String idAsiOtroEquNivelInfe) {
		this.idAsiOtroEquNivelInfe = idAsiOtroEquNivelInfe;
	}
	public String getIdAsigEquiMisNivel() {
		return idAsigEquiMisNivel;
	}
	public void setIdAsigEquiMisNivel(String idAsigEquiMisNivel) {
		this.idAsigEquiMisNivel = idAsigEquiMisNivel;
	}
	public String getIdAsigOtroEquiMisNivel() {
		return idAsigOtroEquiMisNivel;
	}
	public void setIdAsigOtroEquiMisNivel(String idAsigOtroEquiMisNivel) {
		this.idAsigOtroEquiMisNivel = idAsigOtroEquiMisNivel;
	}
	public String getIdModifOperDicta() {
		return idModifOperDicta;
	}
	public void setIdModifOperDicta(String idModifOperDicta) {
		this.idModifOperDicta = idModifOperDicta;
	}
	public String getCodOficina() {
		return codOficina;
	}
	public void setCodOficina(String codOficina) {
		this.codOficina = codOficina;
	}
	public String getNombreOficina() {
		return nombreOficina;
	}
	public void setNombreOficina(String nombreOficina) {
		this.nombreOficina = nombreOficina;
	}
	public String getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public String getCodBanca() {
		return codBanca;
	}
	public void setCodBanca(String codBanca) {
		this.codBanca = codBanca;
	}
	public String getCodCargo() {
		return codCargo;
	}
	public void setCodCargo(String codCargo) {
		this.codCargo = codCargo;
	}
	public String getDescripcionRol() {
	    return descripcionRol;
	}
	public void setDescripcionRol(String descripcionRol) {
		this.descripcionRol = descripcionRol;
	}
	public BigDecimal getCodRol() {
		return codRol;
	}
	public void setCodRol(BigDecimal codRol) {
		this.codRol = codRol;
	}
	public String getIdFunciones() {
		return idFunciones;
	}
	public void setIdFunciones(String idFunciones) {
		this.idFunciones = idFunciones;
	}

}