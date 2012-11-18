package bbva.pe.gpr.bean;

import java.math.BigDecimal;

public class MultitablaDetalle extends MultitablaDetalleKey {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column CARDEL.TGPR_MULTITABLA_DETALLE.STR_VALOR
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    private String strValor;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column CARDEL.TGPR_MULTITABLA_DETALLE.NUMBER_VALOR
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    private BigDecimal numberValor;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column CARDEL.TGPR_MULTITABLA_DETALLE.STR_VALOR2
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    private String strValor2;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column CARDEL.TGPR_MULTITABLA_DETALLE.STR_VALOR3
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    private String strValor3;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column CARDEL.TGPR_MULTITABLA_DETALLE.ESTADO
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    private BigDecimal estado;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column CARDEL.TGPR_MULTITABLA_DETALLE.STR_VALOR
     *
     * @return the value of CARDEL.TGPR_MULTITABLA_DETALLE.STR_VALOR
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public String getStrValor() {
        return strValor;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column CARDEL.TGPR_MULTITABLA_DETALLE.STR_VALOR
     *
     * @param strValor the value for CARDEL.TGPR_MULTITABLA_DETALLE.STR_VALOR
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public void setStrValor(String strValor) {
        this.strValor = strValor == null ? null : strValor.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column CARDEL.TGPR_MULTITABLA_DETALLE.NUMBER_VALOR
     *
     * @return the value of CARDEL.TGPR_MULTITABLA_DETALLE.NUMBER_VALOR
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public BigDecimal getNumberValor() {
        return numberValor;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column CARDEL.TGPR_MULTITABLA_DETALLE.NUMBER_VALOR
     *
     * @param numberValor the value for CARDEL.TGPR_MULTITABLA_DETALLE.NUMBER_VALOR
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public void setNumberValor(BigDecimal numberValor) {
        this.numberValor = numberValor;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column CARDEL.TGPR_MULTITABLA_DETALLE.STR_VALOR2
     *
     * @return the value of CARDEL.TGPR_MULTITABLA_DETALLE.STR_VALOR2
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public String getStrValor2() {
        return strValor2;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column CARDEL.TGPR_MULTITABLA_DETALLE.STR_VALOR2
     *
     * @param strValor2 the value for CARDEL.TGPR_MULTITABLA_DETALLE.STR_VALOR2
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public void setStrValor2(String strValor2) {
        this.strValor2 = strValor2 == null ? null : strValor2.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column CARDEL.TGPR_MULTITABLA_DETALLE.STR_VALOR3
     *
     * @return the value of CARDEL.TGPR_MULTITABLA_DETALLE.STR_VALOR3
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public String getStrValor3() {
        return strValor3;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column CARDEL.TGPR_MULTITABLA_DETALLE.STR_VALOR3
     *
     * @param strValor3 the value for CARDEL.TGPR_MULTITABLA_DETALLE.STR_VALOR3
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public void setStrValor3(String strValor3) {
        this.strValor3 = strValor3 == null ? null : strValor3.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column CARDEL.TGPR_MULTITABLA_DETALLE.ESTADO
     *
     * @return the value of CARDEL.TGPR_MULTITABLA_DETALLE.ESTADO
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public BigDecimal getEstado() {
        return estado;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column CARDEL.TGPR_MULTITABLA_DETALLE.ESTADO
     *
     * @param estado the value for CARDEL.TGPR_MULTITABLA_DETALLE.ESTADO
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public void setEstado(BigDecimal estado) {
        this.estado = estado;
    }
}