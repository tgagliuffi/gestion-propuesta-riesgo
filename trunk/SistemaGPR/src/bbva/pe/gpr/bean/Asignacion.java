package bbva.pe.gpr.bean;

import java.math.BigDecimal;
import java.util.Date;



public class Asignacion extends AsignacionKey{
	private String nombre;
	private BigDecimal codRol;
	private String dependiente;
	private String codUsuarioAsigno;
	private Date fechaAsignacion;
	private BigDecimal estado;
	private String codCentral;
	private String strMensaje;

	private Date fechaIngresoIni;
	private Date FechaIngresoFin;
	private int flagFueraRango;
	private String flagFueraRangoSi;
	private BigDecimal mtoDelegacionMax;

	private BigDecimal riesgoActual; // Aumentar
	private BigDecimal riesgoTotal; // Aumentar
	private String prioridad; // Aumentar
	private String codMoneda; // Aumentar
	private String strFechaAsignacion;
	private Date fechaIngreso;
	
	private String estadoAsignacion;
	private String usuarioAsignado;
	
	private String codUsuarioSession;
    private String nomUsuarioSession;
    
    private String codCargo;
    private String desCargo;
	
	public String getUsuarioAsignado() {
		return usuarioAsignado;
	}
	public void setUsuarioAsignado(String usuarioAsignado) {
		this.usuarioAsignado = usuarioAsignado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public BigDecimal getCodRol() {
		return codRol;
	}
	public void setCodRol(BigDecimal codRol) {
		this.codRol = codRol;
	}
	public String getDependiente() {
		return dependiente;
	}
	public void setDependiente(String dependiente) {
		this.dependiente = dependiente;
	}
	public String getCodUsuarioAsigno() {
		return codUsuarioAsigno;
	}
	public void setCodUsuarioAsigno(String codUsuarioAsigno) {
		this.codUsuarioAsigno = codUsuarioAsigno;
	}
	public Date getFechaAsignacion() {
		return fechaAsignacion;
	}
	public void setFechaAsignacion(Date fechaAsignacion) {
		this.fechaAsignacion = fechaAsignacion;
	}
	public BigDecimal getEstado() {
		return estado;
	}
	public void setEstado(BigDecimal estado) {
		this.estado = estado;
	}
	public String getCodCentral() {
		return codCentral;
	}
	public void setCodCentral(String codCentral) {
		this.codCentral = codCentral;
	}
	public String getStrMensaje() {
		return strMensaje;
	}
	public void setStrMensaje(String strMensaje) {
		this.strMensaje = strMensaje;
	}
	public Date getFechaIngresoIni() {
		return fechaIngresoIni;
	}
	public void setFechaIngresoIni(Date fechaIngresoIni) {
		this.fechaIngresoIni = fechaIngresoIni;
	}
	public Date getFechaIngresoFin() {
		return FechaIngresoFin;
	}
	public void setFechaIngresoFin(Date fechaIngresoFin) {
		FechaIngresoFin = fechaIngresoFin;
	}
	public int getFlagFueraRango() {
		return flagFueraRango;
	}
	public void setFlagFueraRango(int flagFueraRango) {
		this.flagFueraRango = flagFueraRango;
	}
	public BigDecimal getMtoDelegacionMax() {
		return mtoDelegacionMax;
	}
	public void setMtoDelegacionMax(BigDecimal mtoDelegacionMax) {
		this.mtoDelegacionMax = mtoDelegacionMax;
	}
	public BigDecimal getRiesgoActual() {
		return riesgoActual;
	}
	public void setRiesgoActual(BigDecimal riesgoActual) {
		this.riesgoActual = riesgoActual;
	}
	public BigDecimal getRiesgoTotal() {
		return riesgoTotal;
	}
	public void setRiesgoTotal(BigDecimal riesgoTotal) {
		this.riesgoTotal = riesgoTotal;
	}
	public String getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}
	public String getCodMoneda() {
		return codMoneda;
	}
	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}
	public String getFlagFueraRangoSi() {
		return flagFueraRangoSi;
	}
	public void setFlagFueraRangoSi(String flagFueraRangoSi) {
		this.flagFueraRangoSi = flagFueraRangoSi;
	}
	public String getStrFechaAsignacion() {
		return strFechaAsignacion;
	}
	public void setStrFechaAsignacion(String strFechaAsignacion) {
		this.strFechaAsignacion = strFechaAsignacion;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public String getEstadoAsignacion() {
		return estadoAsignacion;
	}
	public void setEstadoAsignacion(String estadoAsignacion) {
		this.estadoAsignacion = estadoAsignacion;
	}
	public String getCodUsuarioSession() {
		return codUsuarioSession;
	}
	public void setCodUsuarioSession(String codUsuarioSession) {
		this.codUsuarioSession = codUsuarioSession;
	}
	public String getNomUsuarioSession() {
		return nomUsuarioSession;
	}
	public void setNomUsuarioSession(String nomUsuarioSession) {
		this.nomUsuarioSession = nomUsuarioSession;
	}
	public String getCodCargo() {
		return codCargo;
	}
	public void setCodCargo(String codCargo) {
		this.codCargo = codCargo;
	}
	public String getDesCargo() {
		return desCargo;
	}
	public void setDesCargo(String desCargo) {
		this.desCargo = desCargo;
	}
	
}