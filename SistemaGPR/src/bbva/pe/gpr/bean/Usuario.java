package bbva.pe.gpr.bean;

import java.math.BigDecimal;


public class Usuario {
    private String codUsuario;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private BigDecimal codRol;
    private BigDecimal mtoDelegacion;
    private BigDecimal estado;
    private String codOficina;
    private String codOficinaAsignada;
    private String codCargo;
    private String genero;
    
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
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public BigDecimal getCodRol() {
		return codRol;
	}
	public void setCodRol(BigDecimal codRol) {
		this.codRol = codRol;
	}
	public BigDecimal getMtoDelegacion() {
		return mtoDelegacion;
	}
	public void setMtoDelegacion(BigDecimal mtoDelegacion) {
		this.mtoDelegacion = mtoDelegacion;
	}
	public BigDecimal getEstado() {
		return estado;
	}
	public void setEstado(BigDecimal estado) {
		this.estado = estado;
	}
	public String getCodOficina() {
		return codOficina;
	}
	public void setCodOficina(String codOficina) {
		this.codOficina = codOficina;
	}
	public String getCodOficinaAsignada() {
		return codOficinaAsignada;
	}
	public void setCodOficinaAsignada(String codOficinaAsignada) {
		this.codOficinaAsignada = codOficinaAsignada;
	}
	public String getCodCargo() {
		return codCargo;
	}
	public void setCodCargo(String codCargo) {
		this.codCargo = codCargo;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}

    
}