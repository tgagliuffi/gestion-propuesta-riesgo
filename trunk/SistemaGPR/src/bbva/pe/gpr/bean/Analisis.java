package bbva.pe.gpr.bean;

import java.math.BigDecimal;

public class Analisis extends AnalisisKey {
    private String codMutlProceso;
	private String codMultMotivo;
	private String comentario;
	private BigDecimal estado;
	private Long codAsignacion;
	private String codCentral;
	private String codUsuario;
	private String strMensaje;
	
	
	public String getCodMutlProceso() {
		return codMutlProceso;
	}
	public void setCodMutlProceso(String codMutlProceso) {
		this.codMutlProceso = codMutlProceso;
	}
	public String getCodMultMotivo() {
		return codMultMotivo;
	}
	public void setCodMultMotivo(String codMultMotivo) {
		this.codMultMotivo = codMultMotivo;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public BigDecimal getEstado() {
		return estado;
	}
	public void setEstado(BigDecimal estado) {
		this.estado = estado;
	}
	public Long getCodAsignacion() {
		return codAsignacion;
	}
	public void setCodAsignacion(Long codAsignacion) {
		this.codAsignacion = codAsignacion;
	}
	public String getCodCentral() {
		return codCentral;
	}
	public void setCodCentral(String codCentral) {
		this.codCentral = codCentral;
	}
	public String getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}
	public String getStrMensaje() {
		return strMensaje;
	}
	public void setStrMensaje(String strMensaje) {
		this.strMensaje = strMensaje;
	}
	
    
}