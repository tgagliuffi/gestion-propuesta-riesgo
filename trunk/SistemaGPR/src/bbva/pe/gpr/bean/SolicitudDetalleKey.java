package bbva.pe.gpr.bean;


public class SolicitudDetalleKey {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column DELGPR.TGPR_SOLICITUD_DETALLES.COD_DT_SOLICITUD
     *
     * @ibatorgenerated Tue Sep 25 16:58:42 COT 2012
     */
    private String codDtSolicitud;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column DELGPR.TGPR_SOLICITUD_DETALLES.NRO_SOLICITUD
     *
     * @ibatorgenerated Tue Sep 25 16:58:42 COT 2012
     */
    private Long nroSolicitud;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column DELGPR.TGPR_SOLICITUD_DETALLES.COD_DT_SOLICITUD
     *
     * @return the value of DELGPR.TGPR_SOLICITUD_DETALLES.COD_DT_SOLICITUD
     *
     * @ibatorgenerated Tue Sep 25 16:58:42 COT 2012
     */
    public String getCodDtSolicitud() {
        return codDtSolicitud;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column DELGPR.TGPR_SOLICITUD_DETALLES.COD_DT_SOLICITUD
     *
     * @param codDtSolicitud the value for DELGPR.TGPR_SOLICITUD_DETALLES.COD_DT_SOLICITUD
     *
     * @ibatorgenerated Tue Sep 25 16:58:42 COT 2012
     */
    public void setCodDtSolicitud(String codDtSolicitud) {
        this.codDtSolicitud = codDtSolicitud == null ? null : codDtSolicitud.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column DELGPR.TGPR_SOLICITUD_DETALLES.NRO_SOLICITUD
     *
     * @return the value of DELGPR.TGPR_SOLICITUD_DETALLES.NRO_SOLICITUD
     *
     * @ibatorgenerated Tue Sep 25 16:58:42 COT 2012
     */
    public Long getNroSolicitud() {
        return nroSolicitud;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column DELGPR.TGPR_SOLICITUD_DETALLES.NRO_SOLICITUD
     *
     * @param nroSolicitud the value for DELGPR.TGPR_SOLICITUD_DETALLES.NRO_SOLICITUD
     *
     * @ibatorgenerated Tue Sep 25 16:58:42 COT 2012
     */
    public void setNroSolicitud(Long nroSolicitud) {
        this.nroSolicitud = nroSolicitud;
    }
}