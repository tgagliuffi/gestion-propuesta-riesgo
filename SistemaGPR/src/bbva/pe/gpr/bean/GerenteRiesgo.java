package bbva.pe.gpr.bean;

import java.math.BigDecimal;
import java.util.Date;

public class GerenteRiesgo {

	private BigDecimal id_gerente;
	private String id_tt_tipo_funcionario;
	private String cod_funcionario_superior;
	private String cod_registro;
	private String nombres;
	private String ap_materno;
	private String ap_paterno;
	private String cargo;
	private String cod_oficina;
	private String nombre_oficina;
	private String correo;
	private String id_tt_tipo_banca;
	private String estado;
	private String codUsuarioCreacion;
	private String codUsuarioModificacion;
	private Date fechaCreacion;
	private Date fechaModificacion;
	
	public BigDecimal getId_gerente() {
		return id_gerente;
	}
	public void setId_gerente(BigDecimal id_gerente) {
		this.id_gerente = id_gerente;
	}
	public String getId_tt_tipo_funcionario() {
		return id_tt_tipo_funcionario;
	}
	public void setId_tt_tipo_funcionario(String id_tt_tipo_funcionario) {
		this.id_tt_tipo_funcionario = id_tt_tipo_funcionario;
	}
	public String getCod_funcionario_superior() {
		return cod_funcionario_superior;
	}
	public void setCod_funcionario_superior(String cod_funcionario_superior) {
		this.cod_funcionario_superior = cod_funcionario_superior;
	}
	public String getCod_registro() {
		return cod_registro;
	}
	public void setCod_registro(String cod_registro) {
		this.cod_registro = cod_registro;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getAp_materno() {
		return ap_materno;
	}
	public void setAp_materno(String ap_materno) {
		this.ap_materno = ap_materno;
	}
	public String getAp_paterno() {
		return ap_paterno;
	}
	public void setAp_paterno(String ap_paterno) {
		this.ap_paterno = ap_paterno;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getCod_oficina() {
		return cod_oficina;
	}
	public void setCod_oficina(String cod_oficina) {
		this.cod_oficina = cod_oficina;
	}
	public String getNombre_oficina() {
		return nombre_oficina;
	}
	public void setNombre_oficina(String nombre_oficina) {
		this.nombre_oficina = nombre_oficina;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getId_tt_tipo_banca() {
		return id_tt_tipo_banca;
	}
	public void setId_tt_tipo_banca(String id_tt_tipo_banca) {
		this.id_tt_tipo_banca = id_tt_tipo_banca;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCodUsuarioCreacion() {
		return codUsuarioCreacion;
	}
	public void setCodUsuarioCreacion(String codUsuarioCreacion) {
		this.codUsuarioCreacion = codUsuarioCreacion;
	}
	public String getCodUsuarioModificacion() {
		return codUsuarioModificacion;
	}
	public void setCodUsuarioModificacion(String codUsuarioModificacion) {
		this.codUsuarioModificacion = codUsuarioModificacion;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
}
