package bbva.pe.gpr.bean;

import java.math.BigDecimal;
import java.sql.Date;

public class Estadistica {
	
	private BigDecimal codBanca;
	private String desBanca;
	private String codTerritorio;
	private String desTerritorio;
	private String codOficina;
	private String desOficina;
	private String desEstadoSolicitud;
	private String codDictamen;
	private String desDictamen;
	private BigDecimal nroSolicitudes;
	private BigDecimal priorizadas;
	private BigDecimal fueraDeRango;
	private Date fechaInicio;
	private Date fechaFin;

	public BigDecimal getCodBanca() {
		return codBanca;
	}

	public void setCodBanca(BigDecimal codBanca) {
		this.codBanca = codBanca;
	}

	public String getDesBanca() {
		return desBanca;
	}

	public void setDesBanca(String desBanca) {
		this.desBanca = desBanca;
	}

	public String getCodTerritorio() {
		return codTerritorio;
	}

	public void setCodTerritorio(String codTerritorio) {
		this.codTerritorio = codTerritorio;
	}

	public String getDesTerritorio() {
		return desTerritorio;
	}

	public void setDesTerritorio(String desTerritorio) {
		this.desTerritorio = desTerritorio;
	}

	public String getCodOficina() {
		return codOficina;
	}

	public void setCodOficina(String codOficina) {
		this.codOficina = codOficina;
	}

	public String getDesOficina() {
		return desOficina;
	}

	public void setDesOficina(String desOficina) {
		this.desOficina = desOficina;
	}

	public String getDesEstadoSolicitud() {
		return desEstadoSolicitud;
	}

	public void setDesEstadoSolicitud(String desEstadoSolicitud) {
		this.desEstadoSolicitud = desEstadoSolicitud;
	}

	public String getCodDictamen() {
		return codDictamen;
	}

	public void setCodDictamen(String codDictamen) {
		this.codDictamen = codDictamen;
	}

	public String getDesDictamen() {
		return desDictamen;
	}

	public void setDesDictamen(String desDictamen) {
		this.desDictamen = desDictamen;
	}

	public BigDecimal getNroSolicitudes() {
		return nroSolicitudes;
	}

	public void setNroSolicitudes(BigDecimal nroSolicitudes) {
		this.nroSolicitudes = nroSolicitudes;
	}

	public BigDecimal getPriorizadas() {
		return priorizadas;
	}

	public void setPriorizadas(BigDecimal priorizadas) {
		this.priorizadas = priorizadas;
	}

	public BigDecimal getFueraDeRango() {
		return fueraDeRango;
	}

	public void setFueraDeRango(BigDecimal fueraDeRango) {
		this.fueraDeRango = fueraDeRango;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
}
