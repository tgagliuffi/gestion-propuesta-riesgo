package bbva.pe.gpr.bean;

import java.math.BigDecimal;

public class SolicitudMensaje {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column CARDEL.TGPR_SOLICITUD_MENSAJES.COD_MENSAJE
     *
     * @ibatorgenerated Tue Sep 25 16:58:42 COT 2012
     */
    private BigDecimal codMensaje;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column CARDEL.TGPR_SOLICITUD_MENSAJES.DES_MENSAJE
     *
     * @ibatorgenerated Tue Sep 25 16:58:42 COT 2012
     */
    private String desMensaje;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column CARDEL.TGPR_SOLICITUD_MENSAJES.ESTADO
     *
     * @ibatorgenerated Tue Sep 25 16:58:42 COT 2012
     */
    private BigDecimal estado;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column CARDEL.TGPR_SOLICITUD_MENSAJES.NRO_SOLICITUD
     *
     * @ibatorgenerated Tue Sep 25 16:58:42 COT 2012
     */
    private Long nroSolicitud;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column CARDEL.TGPR_SOLICITUD_MENSAJES.COD_CENTRAL
     *
     * @ibatorgenerated Tue Sep 25 16:58:42 COT 2012
     */
    private String codCentral;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column CARDEL.TGPR_SOLICITUD_MENSAJES.COD_MENSAJE
     *
     * @return the value of CARDEL.TGPR_SOLICITUD_MENSAJES.COD_MENSAJE
     *
     * @ibatorgenerated Tue Sep 25 16:58:42 COT 2012
     */
    public BigDecimal getCodMensaje() {
        return codMensaje;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column CARDEL.TGPR_SOLICITUD_MENSAJES.COD_MENSAJE
     *
     * @param codMensaje the value for CARDEL.TGPR_SOLICITUD_MENSAJES.COD_MENSAJE
     *
     * @ibatorgenerated Tue Sep 25 16:58:42 COT 2012
     */
    public void setCodMensaje(BigDecimal codMensaje) {
        this.codMensaje = codMensaje;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column CARDEL.TGPR_SOLICITUD_MENSAJES.DES_MENSAJE
     *
     * @return the value of CARDEL.TGPR_SOLICITUD_MENSAJES.DES_MENSAJE
     *
     * @ibatorgenerated Tue Sep 25 16:58:42 COT 2012
     */
    public String getDesMensaje() {
        return desMensaje;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column CARDEL.TGPR_SOLICITUD_MENSAJES.DES_MENSAJE
     *
     * @param desMensaje the value for CARDEL.TGPR_SOLICITUD_MENSAJES.DES_MENSAJE
     *
     * @ibatorgenerated Tue Sep 25 16:58:42 COT 2012
     */
    public void setDesMensaje(String desMensaje) {
        this.desMensaje = desMensaje == null ? null : desMensaje.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column CARDEL.TGPR_SOLICITUD_MENSAJES.ESTADO
     *
     * @return the value of CARDEL.TGPR_SOLICITUD_MENSAJES.ESTADO
     *
     * @ibatorgenerated Tue Sep 25 16:58:42 COT 2012
     */
    public BigDecimal getEstado() {
        return estado;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column CARDEL.TGPR_SOLICITUD_MENSAJES.ESTADO
     *
     * @param estado the value for CARDEL.TGPR_SOLICITUD_MENSAJES.ESTADO
     *
     * @ibatorgenerated Tue Sep 25 16:58:42 COT 2012
     */
    public void setEstado(BigDecimal estado) {
        this.estado = estado;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column CARDEL.TGPR_SOLICITUD_MENSAJES.NRO_SOLICITUD
     *
     * @return the value of CARDEL.TGPR_SOLICITUD_MENSAJES.NRO_SOLICITUD
     *
     * @ibatorgenerated Tue Sep 25 16:58:42 COT 2012
     */
    public Long getNroSolicitud() {
        return nroSolicitud;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column CARDEL.TGPR_SOLICITUD_MENSAJES.NRO_SOLICITUD
     *
     * @param nroSolicitud the value for CARDEL.TGPR_SOLICITUD_MENSAJES.NRO_SOLICITUD
     *
     * @ibatorgenerated Tue Sep 25 16:58:42 COT 2012
     */
    public void setNroSolicitud(Long nroSolicitud) {
        this.nroSolicitud = nroSolicitud;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column CARDEL.TGPR_SOLICITUD_MENSAJES.COD_CENTRAL
     *
     * @return the value of CARDEL.TGPR_SOLICITUD_MENSAJES.COD_CENTRAL
     *
     * @ibatorgenerated Tue Sep 25 16:58:42 COT 2012
     */
    public String getCodCentral() {
        return codCentral;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column CARDEL.TGPR_SOLICITUD_MENSAJES.COD_CENTRAL
     *
     * @param codCentral the value for CARDEL.TGPR_SOLICITUD_MENSAJES.COD_CENTRAL
     *
     * @ibatorgenerated Tue Sep 25 16:58:42 COT 2012
     */
    public void setCodCentral(String codCentral) {
        this.codCentral = codCentral == null ? null : codCentral.trim();
    }
}