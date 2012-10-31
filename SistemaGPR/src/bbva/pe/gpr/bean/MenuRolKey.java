package bbva.pe.gpr.bean;

import java.math.BigDecimal;

public class MenuRolKey {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column CARDEL.TGPR_MENU_ROLES.COD_MENU
     *
     * @ibatorgenerated Tue Sep 25 16:43:19 COT 2012
     */
    private BigDecimal codMenu;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column CARDEL.TGPR_MENU_ROLES.COD_ROL
     *
     * @ibatorgenerated Tue Sep 25 16:43:19 COT 2012
     */
    private BigDecimal codRol;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column CARDEL.TGPR_MENU_ROLES.COD_MENU
     *
     * @return the value of CARDEL.TGPR_MENU_ROLES.COD_MENU
     *
     * @ibatorgenerated Tue Sep 25 16:43:19 COT 2012
     */
    public BigDecimal getCodMenu() {
        return codMenu;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column CARDEL.TGPR_MENU_ROLES.COD_MENU
     *
     * @param codMenu the value for CARDEL.TGPR_MENU_ROLES.COD_MENU
     *
     * @ibatorgenerated Tue Sep 25 16:43:19 COT 2012
     */
    public void setCodMenu(BigDecimal codMenu) {
        this.codMenu = codMenu;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column CARDEL.TGPR_MENU_ROLES.COD_ROL
     *
     * @return the value of CARDEL.TGPR_MENU_ROLES.COD_ROL
     *
     * @ibatorgenerated Tue Sep 25 16:43:19 COT 2012
     */
    public BigDecimal getCodRol() {
        return codRol;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column CARDEL.TGPR_MENU_ROLES.COD_ROL
     *
     * @param codRol the value for CARDEL.TGPR_MENU_ROLES.COD_ROL
     *
     * @ibatorgenerated Tue Sep 25 16:43:19 COT 2012
     */
    public void setCodRol(BigDecimal codRol) {
        this.codRol = codRol;
    }
}