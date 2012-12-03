package bbva.pe.gpr.bean;

import java.math.BigDecimal;
import java.util.Date;

public class SolicitudOperacion extends SolicitudOperacionKey {
    
	private String codMultOperacion;
    private String desOperacion;
    private BigDecimal estado;
    private String codCentral;
    private Date fechaIngreso;
    private String strFechaIngreso;
    private String codUsuario;
    private String nomUsuario;
    private String desRol;
    private String subBancaUsuario;
    
	public String getCodMultOperacion() {
		return codMultOperacion;
	}
	public void setCodMultOperacion(String codMultOperacion) {
		this.codMultOperacion = codMultOperacion;
	}
	public String getDesOperacion() {
		return desOperacion;
	}
	public void setDesOperacion(String desOperacion) {
		this.desOperacion = desOperacion;
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
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public String getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}
	public String getNomUsuario() {
		return nomUsuario;
	}
	public void setNomUsuario(String nomUsuario) {
		this.nomUsuario = nomUsuario;
	}
	public String getDesRol() {
		return desRol;
	}
	public void setDesRol(String desRol) {
		this.desRol = desRol;
	}
	public String getSubBancaUsuario() {
		return subBancaUsuario;
	}
	public void setSubBancaUsuario(String subBancaUsuario) {
		this.subBancaUsuario = subBancaUsuario;
	}
	public String getStrFechaIngreso() {
		return strFechaIngreso;
	}
	public void setStrFechaIngreso(String strFechaIngreso) {
		this.strFechaIngreso = strFechaIngreso;
	}
  
}