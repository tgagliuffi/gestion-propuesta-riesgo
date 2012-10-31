package bbva.pe.gpr.bean;

import java.math.BigDecimal;

public class Oficina {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column CARDEL.TGPR_OFICINAS.COD_OFICINA
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    private String codOficina;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column CARDEL.TGPR_OFICINAS.COD_TERRITORIO
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    private BigDecimal codTerritorio;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column CARDEL.TGPR_OFICINAS.NOMBRE
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    private String nombre;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column CARDEL.TGPR_OFICINAS.ESTADO
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    private BigDecimal estado;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column CARDEL.TGPR_OFICINAS.DIRECCION
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    private String direccion;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column CARDEL.TGPR_OFICINAS.COD_OFICINA
     *
     * @return the value of CARDEL.TGPR_OFICINAS.COD_OFICINA
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public String getCodOficina() {
        return codOficina;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column CARDEL.TGPR_OFICINAS.COD_OFICINA
     *
     * @param codOficina the value for CARDEL.TGPR_OFICINAS.COD_OFICINA
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public void setCodOficina(String codOficina) {
        this.codOficina = codOficina;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column CARDEL.TGPR_OFICINAS.COD_TERRITORIO
     *
     * @return the value of CARDEL.TGPR_OFICINAS.COD_TERRITORIO
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public BigDecimal getCodTerritorio() {
        return codTerritorio;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column CARDEL.TGPR_OFICINAS.COD_TERRITORIO
     *
     * @param codTerritorio the value for CARDEL.TGPR_OFICINAS.COD_TERRITORIO
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public void setCodTerritorio(BigDecimal codTerritorio) {
        this.codTerritorio = codTerritorio;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column CARDEL.TGPR_OFICINAS.NOMBRE
     *
     * @return the value of CARDEL.TGPR_OFICINAS.NOMBRE
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column CARDEL.TGPR_OFICINAS.NOMBRE
     *
     * @param nombre the value for CARDEL.TGPR_OFICINAS.NOMBRE
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public void setNombre(String nombre) {
        this.nombre = nombre == null ? null : nombre.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column CARDEL.TGPR_OFICINAS.ESTADO
     *
     * @return the value of CARDEL.TGPR_OFICINAS.ESTADO
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public BigDecimal getEstado() {
        return estado;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column CARDEL.TGPR_OFICINAS.ESTADO
     *
     * @param estado the value for CARDEL.TGPR_OFICINAS.ESTADO
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public void setEstado(BigDecimal estado) {
        this.estado = estado;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column CARDEL.TGPR_OFICINAS.DIRECCION
     *
     * @return the value of CARDEL.TGPR_OFICINAS.DIRECCION
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column CARDEL.TGPR_OFICINAS.DIRECCION
     *
     * @param direccion the value for CARDEL.TGPR_OFICINAS.DIRECCION
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion == null ? null : direccion.trim();
    }
}