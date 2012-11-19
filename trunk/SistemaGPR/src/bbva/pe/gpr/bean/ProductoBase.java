package bbva.pe.gpr.bean;

import java.math.BigDecimal;

public class ProductoBase {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column CARDEL.TGPR_PRODUCTO_BASES.COD_PRODUCTO_BASE
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    private BigDecimal codProductoBase;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column CARDEL.TGPR_PRODUCTO_BASES.NOMBRE
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    private String nombre;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column CARDEL.TGPR_PRODUCTO_BASES.ESTADO
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    private String estado;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column CARDEL.TGPR_PRODUCTO_BASES.DESCRIPCION
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    private String descripcion;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column CARDEL.TGPR_PRODUCTO_BASES.COD_PRODUCTO_BASE
     *
     * @return the value of CARDEL.TGPR_PRODUCTO_BASES.COD_PRODUCTO_BASE
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public BigDecimal getCodProductoBase() {
        return codProductoBase;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column CARDEL.TGPR_PRODUCTO_BASES.COD_PRODUCTO_BASE
     *
     * @param codProductoBase the value for CARDEL.TGPR_PRODUCTO_BASES.COD_PRODUCTO_BASE
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public void setCodProductoBase(BigDecimal codProductoBase) {
        this.codProductoBase = codProductoBase;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column CARDEL.TGPR_PRODUCTO_BASES.NOMBRE
     *
     * @return the value of CARDEL.TGPR_PRODUCTO_BASES.NOMBRE
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column CARDEL.TGPR_PRODUCTO_BASES.NOMBRE
     *
     * @param nombre the value for CARDEL.TGPR_PRODUCTO_BASES.NOMBRE
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public void setNombre(String nombre) {
        this.nombre = nombre == null ? null : nombre.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column CARDEL.TGPR_PRODUCTO_BASES.ESTADO
     *
     * @return the value of CARDEL.TGPR_PRODUCTO_BASES.ESTADO
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public String getEstado() {
        return estado;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column CARDEL.TGPR_PRODUCTO_BASES.ESTADO
     *
     * @param estado the value for CARDEL.TGPR_PRODUCTO_BASES.ESTADO
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public void setEstado(String estado) {
        this.estado = estado == null ? null : estado.trim();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column CARDEL.TGPR_PRODUCTO_BASES.DESCRIPCION
     *
     * @return the value of CARDEL.TGPR_PRODUCTO_BASES.DESCRIPCION
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column CARDEL.TGPR_PRODUCTO_BASES.DESCRIPCION
     *
     * @param descripcion the value for CARDEL.TGPR_PRODUCTO_BASES.DESCRIPCION
     *
     * @ibatorgenerated Thu Sep 27 15:02:44 COT 2012
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion == null ? null : descripcion.trim();
    }
}