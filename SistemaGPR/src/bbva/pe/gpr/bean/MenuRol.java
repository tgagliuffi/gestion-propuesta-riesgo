package bbva.pe.gpr.bean;

public class MenuRol extends MenuRolKey {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column DELGPR.TGPR_MENU_ROLES.ESTADO
     *
     * @ibatorgenerated Tue Sep 25 16:43:19 COT 2012
     */
    private String estado;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column DELGPR.TGPR_MENU_ROLES.ESTADO
     *
     * @return the value of DELGPR.TGPR_MENU_ROLES.ESTADO
     *
     * @ibatorgenerated Tue Sep 25 16:43:19 COT 2012
     */
    public String getEstado() {
        return estado;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column DELGPR.TGPR_MENU_ROLES.ESTADO
     *
     * @param estado the value for DELGPR.TGPR_MENU_ROLES.ESTADO
     *
     * @ibatorgenerated Tue Sep 25 16:43:19 COT 2012
     */
    public void setEstado(String estado) {
        this.estado = estado == null ? null : estado.trim();
    }
}