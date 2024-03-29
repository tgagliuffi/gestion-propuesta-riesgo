package bbva.pe.gpr.bean;


import java.math.BigDecimal;
import java.util.Date;


public class Usuario {
   
	private Long idUsuario;
	private String codigoUsuario;
	private String nombres;
	private String estado;
	private String codUsuarioCreacion;
	private String codUsuarioModificacion;
	private Date   fechaCreacion;
	private Date   fechaModificacion;
	private String concatRoles;
	private BigDecimal mtoMaxDelegacion;
	private Long cantidad;
	private String dependiente;
	private BigDecimal codRol;
	private BigDecimal mtoMaxPerNatual;
	private BigDecimal mtoMaxRating;
	private BigDecimal mtoSinRating;
	
	private String codigoUsuarioSession;
	private String codSuBanca;
	
	private String codCargoGPR;
    private String desCargoGPR;
    private BigDecimal codBanca;
    
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
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
	public String getConcatRoles() {
		return concatRoles;
	}
	public void setConcatRoles(String concatRoles) {
		this.concatRoles = concatRoles;
	}
	public BigDecimal getMtoMaxDelegacion() {
		return mtoMaxDelegacion;
	}
	public void setMtoMaxDelegacion(BigDecimal mtoMaxDelegacion) {
		this.mtoMaxDelegacion = mtoMaxDelegacion;
	}
	public Long getCantidad() {
		return cantidad;
	}
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	public String getDependiente() {
		return dependiente;
	}
	public void setDependiente(String dependiente) {
		this.dependiente = dependiente;
	}
	public BigDecimal getCodRol() {
		return codRol;
	}
	public void setCodRol(BigDecimal codRol) {
		this.codRol = codRol;
	}
	public BigDecimal getMtoMaxPerNatual() {
		return mtoMaxPerNatual;
	}
	public void setMtoMaxPerNatual(BigDecimal mtoMaxPerNatual) {
		this.mtoMaxPerNatual = mtoMaxPerNatual;
	}
	public BigDecimal getMtoMaxRating() {
		return mtoMaxRating;
	}
	public void setMtoMaxRating(BigDecimal mtoMaxRating) {
		this.mtoMaxRating = mtoMaxRating;
	}
	public BigDecimal getMtoSinRating() {
		return mtoSinRating;
	}
	public void setMtoSinRating(BigDecimal mtoSinRating) {
		this.mtoSinRating = mtoSinRating;
	}
	public String getCodigoUsuarioSession() {
		return codigoUsuarioSession;
	}
	public void setCodigoUsuarioSession(String codigoUsuarioSession) {
		this.codigoUsuarioSession = codigoUsuarioSession;
	}
	public String getCodSuBanca() {
		return codSuBanca;
	}
	public void setCodSuBanca(String codSuBanca) {
		this.codSuBanca = codSuBanca;
	}
	public String getCodCargoGPR() {
		return codCargoGPR;
	}
	public void setCodCargoGPR(String codCargoGPR) {
		this.codCargoGPR = codCargoGPR;
	}
	public String getDesCargoGPR() {
		return desCargoGPR;
	}
	public void setDesCargoGPR(String desCargoGPR) {
		this.desCargoGPR = desCargoGPR;
	}
	public BigDecimal getCodBanca() {
		return codBanca;
	}
	public void setCodBanca(BigDecimal codBanca) {
		this.codBanca = codBanca;
	}
	
}