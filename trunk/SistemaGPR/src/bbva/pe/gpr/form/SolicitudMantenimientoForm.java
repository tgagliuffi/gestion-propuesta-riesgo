package bbva.pe.gpr.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bbva.pe.gpr.util.Constant;

public class SolicitudMantenimientoForm extends ActionForm
{

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
	private String fechaIngreso;
	private String strFechaIngreso;
	private String codBanca="-1";
	private String codMultMoneda;
	private String rating;
	private String clasificacion;
	private String reelevancia;
	private String deudaDirecta=Constant.RESET_MONTO;
	private String deudaIndirecta=Constant.RESET_MONTO;
	private String castigo=Constant.RESET_MONTO;
	private String deudaSistemaFinanciero=Constant.RESET_MONTO;
	private String riesgoGrupal=Constant.RESET_MONTO;
	private String riesgoActual=Constant.RESET_MONTO;
	private String riesgoTotal=Constant.RESET_MONTO;
	private String otroRiesgo=Constant.RESET_MONTO;
	private String prefijoIngreso;
	private String estado="1";
	private String lugarDictamina;
	private String prioridad;
	private String scorating;
	private String scoring;
	private String listaDocumentos;
	private String revisadoSubGerente="0";
	private String desOficina;
	private String strMensaje;	
	private String mtoTotal=Constant.RESET_MONTO;
	private String relevPublica1;
	private String relevPublica2;
	private String relevPublica3;
	private String relevPublica4;
	private String relevPublica5;
	
	private String fechaIngresoIni;
	private String fechaIngresoFin;
	private String codEstadoMult;
	private String codRol;
	
	private String codProducto;
	private String desProducto;
	private String contratoVinculado;
	private String codPreEvaluador;
	private String campania;
	private String tipo;
	private String monto;
	private String plazo;
	private String garantia;
	private String valBanca;
	private String valMoneda;
	private String valMontoTotal;
	private String codProdBase;
	private String flagPopUP;
	private String strMensajePopUP;
	private String condicion="init";
	private String hdnCodCentral;
	
	private String valDeudaDirecta;
	private String valDeudaIndirecta;
	private String valCastigo;
	private String valDeudaSisFinan;
	private String valRiesgoGrupal;
	private String valOtroRiesgo;
	
	
	
	public String getValDeudaDirecta() {
		return valDeudaDirecta;
	}


	public void setValDeudaDirecta(String valDeudaDirecta) {
		this.valDeudaDirecta = valDeudaDirecta;
	}


	public String getValDeudaIndirecta() {
		return valDeudaIndirecta;
	}


	public void setValDeudaIndirecta(String valDeudaIndirecta) {
		this.valDeudaIndirecta = valDeudaIndirecta;
	}


	public String getValCastigo() {
		return valCastigo;
	}


	public void setValCastigo(String valCastigo) {
		this.valCastigo = valCastigo;
	}


	public String getValDeudaSisFinan() {
		return valDeudaSisFinan;
	}


	public void setValDeudaSisFinan(String valDeudaSisFinan) {
		this.valDeudaSisFinan = valDeudaSisFinan;
	}


	public String getValRiesgoGrupal() {
		return valRiesgoGrupal;
	}


	public void setValRiesgoGrupal(String valRiesgoGrupal) {
		this.valRiesgoGrupal = valRiesgoGrupal;
	}


	public String getValOtroRiesgo() {
		return valOtroRiesgo;
	}


	public void setValOtroRiesgo(String valOtroRiesgo) {
		this.valOtroRiesgo = valOtroRiesgo;
	}


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


	public String getFechaIngreso() {
		return fechaIngreso;
	}


	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}


	public String getStrFechaIngreso() {
		return strFechaIngreso;
	}


	public void setStrFechaIngreso(String strFechaIngreso) {
		this.strFechaIngreso = strFechaIngreso;
	}


	public String getCodBanca() {
		return codBanca;
	}


	public void setCodBanca(String codBanca) {
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


	public String getDeudaDirecta() {
		return deudaDirecta;
	}


	public void setDeudaDirecta(String deudaDirecta) {
		this.deudaDirecta = deudaDirecta;
	}


	public String getDeudaIndirecta() {
		return deudaIndirecta;
	}


	public void setDeudaIndirecta(String deudaIndirecta) {
		this.deudaIndirecta = deudaIndirecta;
	}


	public String getCastigo() {
		return castigo;
	}


	public void setCastigo(String castigo) {
		this.castigo = castigo;
	}


	public String getDeudaSistemaFinanciero() {
		return deudaSistemaFinanciero;
	}


	public void setDeudaSistemaFinanciero(String deudaSistemaFinanciero) {
		this.deudaSistemaFinanciero = deudaSistemaFinanciero;
	}


	public String getRiesgoGrupal() {
		return riesgoGrupal;
	}


	public void setRiesgoGrupal(String riesgoGrupal) {
		this.riesgoGrupal = riesgoGrupal;
	}


	public String getRiesgoActual() {
		return riesgoActual;
	}


	public void setRiesgoActual(String riesgoActual) {
		this.riesgoActual = riesgoActual;
	}


	public String getRiesgoTotal() {
		return riesgoTotal;
	}


	public void setRiesgoTotal(String riesgoTotal) {
		this.riesgoTotal = riesgoTotal;
	}


	public String getOtroRiesgo() {
		return otroRiesgo;
	}


	public void setOtroRiesgo(String otroRiesgo) {
		this.otroRiesgo = otroRiesgo;
	}


	public String getPrefijoIngreso() {
		return prefijoIngreso;
	}


	public void setPrefijoIngreso(String prefijoIngreso) {
		this.prefijoIngreso = prefijoIngreso;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
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


	public String getRevisadoSubGerente() {
		return revisadoSubGerente;
	}


	public void setRevisadoSubGerente(String revisadoSubGerente) {
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


	public String getMtoTotal() {
		return mtoTotal;
	}


	public void setMtoTotal(String mtoTotal) {
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


	public String getCodProducto() {
		return codProducto;
	}


	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}


	public String getDesProducto() {
		return desProducto;
	}


	public void setDesProducto(String desProducto) {
		this.desProducto = desProducto;
	}


	public String getContratoVinculado() {
		return contratoVinculado;
	}


	public void setContratoVinculado(String contratoVinculado) {
		this.contratoVinculado = contratoVinculado;
	}


	public String getCodPreEvaluador() {
		return codPreEvaluador;
	}


	public void setCodPreEvaluador(String codPreEvaluador) {
		this.codPreEvaluador = codPreEvaluador;
	}


	public String getCampania() {
		return campania;
	}


	public void setCampania(String campania) {
		this.campania = campania;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getMonto() {
		return monto;
	}


	public void setMonto(String monto) {
		this.monto = monto;
	}


	public String getPlazo() {
		return plazo;
	}


	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}


	public String getGarantia() {
		return garantia;
	}


	public void setGarantia(String garantia) {
		this.garantia = garantia;
	}


	public String getValBanca() {
		return valBanca;
	}


	public void setValBanca(String valBanca) {
		this.valBanca = valBanca;
	}


	public String getValMoneda() {
		return valMoneda;
	}


	public void setValMoneda(String valMoneda) {
		this.valMoneda = valMoneda;
	}


	public String getValMontoTotal() {
		return valMontoTotal;
	}


	public void setValMontoTotal(String valMontoTotal) {
		this.valMontoTotal = valMontoTotal;
	}


	public String getCodProdBase() {
		return codProdBase;
	}


	public void setCodProdBase(String codProdBase) {
		this.codProdBase = codProdBase;
	}


	public String getFlagPopUP() {
		return flagPopUP;
	}


	public void setFlagPopUP(String flagPopUP) {
		this.flagPopUP = flagPopUP;
	}


	public String getStrMensajePopUP() {
		return strMensajePopUP;
	}


	public void setStrMensajePopUP(String strMensajePopUP) {
		this.strMensajePopUP = strMensajePopUP;
	}


	public String getCondicion() {
		return condicion;
	}


	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}


	public String getHdnCodCentral() {
		return hdnCodCentral;
	}


	public void setHdnCodCentral(String hdnCodCentral) {
		this.hdnCodCentral = hdnCodCentral;
	}


	
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		nroSolicitud=Constant.STR_VACIO;
		codCentral = Constant.STR_VACIO;
		codMultTipoPersona = Constant.STR_VACIO;
		desMultTipoPersona = Constant.STR_VACIO;
		numeroDocumento = Constant.STR_VACIO;
		desSolicitante = Constant.STR_VACIO;
		codOficina = Constant.STR_VACIO;
		desOficina = Constant.STR_VACIO;
		codBanca="-1";
		gestorCod = Constant.STR_VACIO;
		gestorNom = Constant.STR_VACIO;
		empleadorCod = Constant.STR_VACIO;
		empleadorNom = Constant.STR_VACIO;
		ejecutivoCtaCod = Constant.STR_VACIO;
		ejecutivoCtaNom = Constant.STR_VACIO;
		oficinaAltaCod = Constant.STR_VACIO;
		oficinaAltaNom = Constant.STR_VACIO;
		gerenciaTerritorialCod = Constant.STR_VACIO;
		gerenciaTerritorialNom = Constant.STR_VACIO;
		strFechaIngreso = Constant.STR_VACIO;
		
		rating = Constant.STR_VACIO;
		reelevancia = Constant.STR_VACIO;
		scorating = Constant.STR_VACIO;
		clasificacion = Constant.STR_VACIO; 
		
		deudaDirecta=Constant.RESET_MONTO;
		deudaIndirecta=Constant.RESET_MONTO;
		castigo=Constant.RESET_MONTO;
		deudaSistemaFinanciero=Constant.RESET_MONTO;
		riesgoGrupal=Constant.RESET_MONTO;
		riesgoActual=Constant.RESET_MONTO;
		riesgoTotal=Constant.RESET_MONTO;
		otroRiesgo=Constant.RESET_MONTO;
		
		relevPublica1=Constant.STR_VACIO;
		relevPublica2=Constant.STR_VACIO;
		relevPublica3=Constant.STR_VACIO;
		relevPublica4=Constant.STR_VACIO;
		relevPublica5=Constant.STR_VACIO;
		flagPopUP=Constant.STR_VACIO;
		condicion="init";
		hdnCodCentral=Constant.STR_VACIO;
		mtoTotal=Constant.RESET_MONTO;
		
		super.reset(mapping, request);
	}
	
}
