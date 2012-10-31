package bbva.pe.gpr.form;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bbva.pe.gpr.util.Constant;

public class SolicitudForm extends ActionForm {
	
	

	private static final long serialVersionUID = 1L;

	private String nroSolicitud;
	private String codCentral;
	private String codMultTipoPersona;
	private String desMultTipoPersona;	
	private String numeroDocumento;
	private String desSolicitante;
	private String codOficina;
	private String gestorCod;
	private String gestorNom;
	private String empleadorCod;
	private String empleadorNom;
	private String ejecutivoCtaCod;
	private String ejecutivoCtaNom;
	private String oficinaAltaCod;
	private String oficinaAltaNom;
	private String gerenciaTerritorialCod;
	private String gerenciaTerritorialNom;
	private Date fechaIngreso;
	private String strFechaIngreso;
	private BigDecimal codBanca=new BigDecimal(-1);
	private String codMultMoneda;
	private String rating;
	private String clasificacion;
	private String reelevancia;
	private BigDecimal deudaDirecta=new BigDecimal(00.00);
	private BigDecimal deudaIndirecta=new BigDecimal(00.00);
	private BigDecimal castigo=new BigDecimal(00.00);
	private BigDecimal deudaSistemaFinanciero=new BigDecimal(00.00);
	private BigDecimal riesgoGrupal=new BigDecimal(00.00);
	private BigDecimal riesgoActual=new BigDecimal(00.00);
	private BigDecimal riesgoTotal=new BigDecimal(00.00);
	private BigDecimal otroRiesgo=new BigDecimal(00.00);
	private String prefijoIngreso;
	private BigDecimal estado=new BigDecimal(1);
	private String lugarDictamina;
	private String prioridad;
	private String scorating;
	private String scoring;
	private String listaDocumentos;
	private BigDecimal revisadoSubGerente=new BigDecimal(0);
	private String desOficina;
	private String strMensaje;	
	private BigDecimal mtoTotal=new BigDecimal(00.00);
	private String relevPublica1;
	private String relevPublica2;
	private String relevPublica3;
	private String relevPublica4;
	private String relevPublica5;
	
	private String fechaIngresoIni;
	private String fechaIngresoFin;
	private String codEstadoMult;
	private String codRol;
	
	public String getNroSolicitud() {
		return nroSolicitud;
	}


	public void setNroSolicitud(String nroSolicitud) {
		this.nroSolicitud = nroSolicitud;
	}


	public String getCodCentral() {
		return codCentral;
	}


	public void setCodCentral(String codCentral) {
		this.codCentral = codCentral;
	}


	public String getCodMultTipoPersona() {
		return codMultTipoPersona;
	}


	public void setCodMultTipoPersona(String codMultTipoPersona) {
		this.codMultTipoPersona = codMultTipoPersona;
	}


	public String getDesMultTipoPersona() {
		return desMultTipoPersona;
	}


	public void setDesMultTipoPersona(String desMultTipoPersona) {
		this.desMultTipoPersona = desMultTipoPersona;
	}


	public String getNumeroDocumento() {
		return numeroDocumento;
	}


	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}


	public String getDesSolicitante() {
		return desSolicitante;
	}


	public void setDesSolicitante(String desSolicitante) {
		this.desSolicitante = desSolicitante;
	}


	public String getCodOficina() {
		return codOficina;
	}


	public void setCodOficina(String codOficina) {
		this.codOficina = codOficina;
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


	public BigDecimal getCodBanca() {
		return codBanca;
	}


	public void setCodBanca(BigDecimal codBanca) {
		this.codBanca = codBanca;
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


	public BigDecimal getOtroRiesgo() {
		return otroRiesgo;
	}


	public void setOtroRiesgo(BigDecimal otroRiesgo) {
		this.otroRiesgo = otroRiesgo;
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


	public String getLugarDictamina() {
		return lugarDictamina;
	}


	public void setLugarDictamina(String lugarDictamina) {
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


	public String getDesOficina() {
		return desOficina;
	}


	public void setDesOficina(String desOficina) {
		this.desOficina = desOficina;
	}


	public String getStrMensaje() {
		return strMensaje;
	}


	public void setStrMensaje(String strMensaje) {
		this.strMensaje = strMensaje;
	}


	public BigDecimal getMtoTotal() {
		return mtoTotal;
	}


	public void setMtoTotal(BigDecimal mtoTotal) {
		this.mtoTotal = mtoTotal;
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


	public String getFechaIngresoIni() {
		return fechaIngresoIni;
	}


	public void setFechaIngresoIni(String fechaIngresoIni) {
		this.fechaIngresoIni = fechaIngresoIni;
	}


	public String getFechaIngresoFin() {
		return fechaIngresoFin;
	}


	public void setFechaIngresoFin(String fechaIngresoFin) {
		this.fechaIngresoFin = fechaIngresoFin;
	}


	public String getCodEstadoMult() {
		return codEstadoMult;
	}


	public void setCodEstadoMult(String codEstadoMult) {
		this.codEstadoMult = codEstadoMult;
	}
	

	public String getCodRol() {
		return codRol;
	}


	public void setCodRol(String codRol) {
		this.codRol = codRol;
	}


	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		nroSolicitud=Constant.VACIO;
		codCentral = Constant.VACIO;
		codMultTipoPersona = Constant.VACIO;
		desMultTipoPersona = Constant.VACIO;
		numeroDocumento = Constant.VACIO;
		desSolicitante = Constant.VACIO;
		codOficina = Constant.VACIO;
		codBanca=new BigDecimal(-1);
		gestorCod = Constant.VACIO;
		gestorNom = Constant.VACIO;
		empleadorCod = Constant.VACIO;
		empleadorNom = Constant.VACIO;
		ejecutivoCtaCod = Constant.VACIO;
		ejecutivoCtaNom = Constant.VACIO;
		oficinaAltaCod = Constant.VACIO;
		oficinaAltaNom = Constant.VACIO;
		gerenciaTerritorialCod = Constant.VACIO;
		gerenciaTerritorialNom = Constant.VACIO;
		strFechaIngreso = Constant.VACIO;
		
		deudaDirecta=new BigDecimal(00.00);
		deudaIndirecta=new BigDecimal(00.00);
		castigo=new BigDecimal(00.00);
		deudaSistemaFinanciero=new BigDecimal(00.00);
		riesgoGrupal=new BigDecimal(00.00);
		riesgoActual=new BigDecimal(00.00);
		riesgoTotal=new BigDecimal(00.00);
		otroRiesgo= new BigDecimal(00.00);
		
		relevPublica1=Constant.VACIO;
		relevPublica2=Constant.VACIO;
		relevPublica3=Constant.VACIO;
		relevPublica4=Constant.VACIO;
		relevPublica5=Constant.VACIO;
		super.reset(mapping, request);
	}

}
