package bbva.pe.gpr.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Solicitud implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long nroSolicitud;
    private Date fechaIngreso;
    private String strFechaIngreso;
    private String codCentral;
    private String desMultTipoPersona;
    private String desSolicitante;
    private String desMultMoneda;
    private BigDecimal mtoSolicitud;
    private BigDecimal riesgoActual;
    private BigDecimal riesgoTotal;
    private String oficinaAltaCod;
    private String oficinaAltaNom;
    private BigDecimal otroRiesgo;
     
    private String hdnCodigo;
    
    private String codMultTipoPersona;
    private String numeroDocumento;   
    private String gestorCod;
    private String gestorNom;
    private String empleadorCod;
    private String empleadorNom;
    private String ejecutivoCtaCod;
    private String ejecutivoCtaNom;
    private String codOficina;
    private String desOficina;
    private String gerenciaTerritorialCod;
    private String gerenciaTerritorialNom;    
    private String codMultMoneda;
    private String rating;
    private String clasificacion;
    private String reelevancia;
    private String[] arrayReelevancia;
    private BigDecimal deudaDirecta;
    private BigDecimal deudaIndirecta;
    private BigDecimal castigo;
    private BigDecimal deudaSistemaFinanciero;
    private BigDecimal riesgoGrupal;
    private String prefijoIngreso;
    private BigDecimal estado;
    private int lugarDictamina;
    private String prioridad;
    private String scorating;
    private String scoring;
    private String listaDocumentos;
    private BigDecimal revisadoSubGerente;   
    private String strMensaje;
    
    private Date fechaIngresoIni;
    private Date fechaIngresoFin;
    private String codEstadoMult;
    private String desEstadoMult;
    private String estadoSolicitud;
    
    private Asignacion asignacionBean;
    private List<SolicitudDetalle> lstSolicitudDetalle;
    private CondicionCliente condicionCliente;
    
	private String relevPublica1;
	private String relevPublica2;
	private String relevPublica3;
	private String relevPublica4;
	private String relevPublica5;
	
	private String grupoPersona;
    private String desBanca;
    private String codSubanca;
    private BigDecimal codBanca;
    private String descripcionSubanca;
    
    private String codUsuarioSession;
    private String nomUsuarioSession;
    private List<String> strLstOficinas;
    private String usuarioAsignacion; 
    
	public Long getNroSolicitud() {
		return nroSolicitud;
	}
	public void setNroSolicitud(Long nroSolicitud) {
		this.nroSolicitud = nroSolicitud;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public String getStrFechaIngreso() {
		return strFechaIngreso;
	}
	public void setStrFechaIngreso(String strFechaIngreso) {
		this.strFechaIngreso = strFechaIngreso;
	}
	public String getCodCentral() {
		return codCentral;
	}
	public void setCodCentral(String codCentral) {
		this.codCentral = codCentral;
	}
	public String getDesMultTipoPersona() {
		return desMultTipoPersona;
	}
	public void setDesMultTipoPersona(String desMultTipoPersona) {
		this.desMultTipoPersona = desMultTipoPersona;
	}
	public String getDesSolicitante() {
		return desSolicitante;
	}
	public void setDesSolicitante(String desSolicitante) {
		this.desSolicitante = desSolicitante;
	}
	public String getDesMultMoneda() {
		return desMultMoneda;
	}
	public void setDesMultMoneda(String desMultMoneda) {
		this.desMultMoneda = desMultMoneda;
	}
	public BigDecimal getMtoSolicitud() {
		return mtoSolicitud;
	}
	public void setMtoSolicitud(BigDecimal mtoSolicitud) {
		this.mtoSolicitud = mtoSolicitud;
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
	public String getOficinaAltaCod() {
		return oficinaAltaCod;
	}
	public void setOficinaAltaCod(String oficinaAltaCod) {
		this.oficinaAltaCod = oficinaAltaCod;
	}
	public String getOficinaAltaNom() {
		return oficinaAltaNom;
	}
	public void setOficinaAltaNom(String oficinaAltaNom) {
		this.oficinaAltaNom = oficinaAltaNom;
	}
	public BigDecimal getOtroRiesgo() {
		return otroRiesgo;
	}
	public void setOtroRiesgo(BigDecimal otroRiesgo) {
		this.otroRiesgo = otroRiesgo;
	}
	public String getHdnCodigo() {
		return hdnCodigo;
	}
	public void setHdnCodigo(String hdnCodigo) {
		this.hdnCodigo = hdnCodigo;
	}
	public String getCodMultTipoPersona() {
		return codMultTipoPersona;
	}
	public void setCodMultTipoPersona(String codMultTipoPersona) {
		this.codMultTipoPersona = codMultTipoPersona;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getGestorCod() {
		return gestorCod;
	}
	public void setGestorCod(String gestorCod) {
		this.gestorCod = gestorCod;
	}
	public String getGestorNom() {
		return gestorNom;
	}
	public void setGestorNom(String gestorNom) {
		this.gestorNom = gestorNom;
	}
	public String getEmpleadorCod() {
		return empleadorCod;
	}
	public void setEmpleadorCod(String empleadorCod) {
		this.empleadorCod = empleadorCod;
	}
	public String getEmpleadorNom() {
		return empleadorNom;
	}
	public void setEmpleadorNom(String empleadorNom) {
		this.empleadorNom = empleadorNom;
	}
	public String getEjecutivoCtaCod() {
		return ejecutivoCtaCod;
	}
	public void setEjecutivoCtaCod(String ejecutivoCtaCod) {
		this.ejecutivoCtaCod = ejecutivoCtaCod;
	}
	public String getEjecutivoCtaNom() {
		return ejecutivoCtaNom;
	}
	public void setEjecutivoCtaNom(String ejecutivoCtaNom) {
		this.ejecutivoCtaNom = ejecutivoCtaNom;
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
	public String getGerenciaTerritorialCod() {
		return gerenciaTerritorialCod;
	}
	public void setGerenciaTerritorialCod(String gerenciaTerritorialCod) {
		this.gerenciaTerritorialCod = gerenciaTerritorialCod;
	}
	public String getGerenciaTerritorialNom() {
		return gerenciaTerritorialNom;
	}
	public void setGerenciaTerritorialNom(String gerenciaTerritorialNom) {
		this.gerenciaTerritorialNom = gerenciaTerritorialNom;
	}
	public String getCodMultMoneda() {
		return codMultMoneda;
	}
	public void setCodMultMoneda(String codMultMoneda) {
		this.codMultMoneda = codMultMoneda;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	public String getReelevancia() {
		return reelevancia;
	}
	public void setReelevancia(String reelevancia) {
		this.reelevancia = reelevancia;
	}
	public String[] getArrayReelevancia() {
		return arrayReelevancia;
	}
	public void setArrayReelevancia(String[] arrayReelevancia) {
		this.arrayReelevancia = arrayReelevancia;
	}
	public BigDecimal getDeudaDirecta() {
		return deudaDirecta;
	}
	public void setDeudaDirecta(BigDecimal deudaDirecta) {
		this.deudaDirecta = deudaDirecta;
	}
	public BigDecimal getDeudaIndirecta() {
		return deudaIndirecta;
	}
	public void setDeudaIndirecta(BigDecimal deudaIndirecta) {
		this.deudaIndirecta = deudaIndirecta;
	}
	public BigDecimal getCastigo() {
		return castigo;
	}
	public void setCastigo(BigDecimal castigo) {
		this.castigo = castigo;
	}
	public BigDecimal getDeudaSistemaFinanciero() {
		return deudaSistemaFinanciero;
	}
	public void setDeudaSistemaFinanciero(BigDecimal deudaSistemaFinanciero) {
		this.deudaSistemaFinanciero = deudaSistemaFinanciero;
	}
	public BigDecimal getRiesgoGrupal() {
		return riesgoGrupal;
	}
	public void setRiesgoGrupal(BigDecimal riesgoGrupal) {
		this.riesgoGrupal = riesgoGrupal;
	}
	public String getPrefijoIngreso() {
		return prefijoIngreso;
	}
	public void setPrefijoIngreso(String prefijoIngreso) {
		this.prefijoIngreso = prefijoIngreso;
	}
	public BigDecimal getEstado() {
		return estado;
	}
	public void setEstado(BigDecimal estado) {
		this.estado = estado;
	}
	public int getLugarDictamina() {
		return lugarDictamina;
	}
	public void setLugarDictamina(int lugarDictamina) {
		this.lugarDictamina = lugarDictamina;
	}
	public String getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}
	public String getScorating() {
		return scorating;
	}
	public void setScorating(String scorating) {
		this.scorating = scorating;
	}
	public String getScoring() {
		return scoring;
	}
	public void setScoring(String scoring) {
		this.scoring = scoring;
	}
	public String getListaDocumentos() {
		return listaDocumentos;
	}
	public void setListaDocumentos(String listaDocumentos) {
		this.listaDocumentos = listaDocumentos;
	}
	public BigDecimal getRevisadoSubGerente() {
		return revisadoSubGerente;
	}
	public void setRevisadoSubGerente(BigDecimal revisadoSubGerente) {
		this.revisadoSubGerente = revisadoSubGerente;
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
		return fechaIngresoFin;
	}
	public void setFechaIngresoFin(Date fechaIngresoFin) {
		this.fechaIngresoFin = fechaIngresoFin;
	}
	public String getCodEstadoMult() {
		return codEstadoMult;
	}
	public void setCodEstadoMult(String codEstadoMult) {
		this.codEstadoMult = codEstadoMult;
	}
	public String getDesEstadoMult() {
		return desEstadoMult;
	}
	public void setDesEstadoMult(String desEstadoMult) {
		this.desEstadoMult = desEstadoMult;
	}
	public String getEstadoSolicitud() {
		return estadoSolicitud;
	}
	public void setEstadoSolicitud(String estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}
	public Asignacion getAsignacionBean() {
		return asignacionBean;
	}
	public void setAsignacionBean(Asignacion asignacionBean) {
		this.asignacionBean = asignacionBean;
	}
	public List<SolicitudDetalle> getLstSolicitudDetalle() {
		return lstSolicitudDetalle;
	}
	public void setLstSolicitudDetalle(List<SolicitudDetalle> lstSolicitudDetalle) {
		this.lstSolicitudDetalle = lstSolicitudDetalle;
	}
	public CondicionCliente getCondicionCliente() {
		return condicionCliente;
	}
	public void setCondicionCliente(CondicionCliente condicionCliente) {
		this.condicionCliente = condicionCliente;
	}
	public String getRelevPublica1() {
		return relevPublica1;
	}
	public void setRelevPublica1(String relevPublica1) {
		this.relevPublica1 = relevPublica1;
	}
	public String getRelevPublica2() {
		return relevPublica2;
	}
	public void setRelevPublica2(String relevPublica2) {
		this.relevPublica2 = relevPublica2;
	}
	public String getRelevPublica3() {
		return relevPublica3;
	}
	public void setRelevPublica3(String relevPublica3) {
		this.relevPublica3 = relevPublica3;
	}
	public String getRelevPublica4() {
		return relevPublica4;
	}
	public void setRelevPublica4(String relevPublica4) {
		this.relevPublica4 = relevPublica4;
	}
	public String getRelevPublica5() {
		return relevPublica5;
	}
	public void setRelevPublica5(String relevPublica5) {
		this.relevPublica5 = relevPublica5;
	}
	public String getGrupoPersona() {
		return grupoPersona;
	}
	public void setGrupoPersona(String grupoPersona) {
		this.grupoPersona = grupoPersona;
	}
	public String getDesBanca() {
		return desBanca;
	}
	public void setDesBanca(String desBanca) {
		this.desBanca = desBanca;
	}
	public String getCodSubanca() {
		return codSubanca;
	}
	public void setCodSubanca(String codSubanca) {
		this.codSubanca = codSubanca;
	}
	public BigDecimal getCodBanca() {
		return codBanca;
	}
	public void setCodBanca(BigDecimal codBanca) {
		this.codBanca = codBanca;
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
	public String getDescripcionSubanca() {
		return descripcionSubanca;
	}
	public void setDescripcionSubanca(String descripcionSubanca) {
		this.descripcionSubanca = descripcionSubanca;
	}
	public String getUsuarioAsignacion() {
		return usuarioAsignacion;
	}
	public List<String> getStrLstOficinas() {
		return strLstOficinas;
	}
	public void setStrLstOficinas(List<String> strLstOficinas) {
		this.strLstOficinas = strLstOficinas;
	}
	public void setUsuarioAsignacion(String usuarioAsignacion) {
		this.usuarioAsignacion = usuarioAsignacion;
	}
	
}