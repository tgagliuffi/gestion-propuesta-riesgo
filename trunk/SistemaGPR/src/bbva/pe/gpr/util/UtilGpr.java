package bbva.pe.gpr.util;

import java.math.BigDecimal;

public class UtilGpr {
	public static String roundUp(String what, int howmuch) throws Exception{

	    try {
	        return new BigDecimal(what).setScale(howmuch, BigDecimal.ROUND_UP).toString();
	    } catch (NumberFormatException nfe) {
	        throw new Exception("BigDecimal cannot parse value : " + what, nfe);
	    }
	}
}
