package bbva.pe.gpr.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class UtilGpr {
	public static String roundUp(String what, int howmuch) throws Exception{

	    try {
	        return new BigDecimal(what).setScale(howmuch, BigDecimal.ROUND_UP).toString();
	    } catch (NumberFormatException nfe) {
	        throw new Exception("BigDecimal cannot parse value : " + what, nfe);
	    }
	}
	
	public static String getNumber(String number) { 
	Long value; 
	String numberFormat = "###,###,###,###"; 
	DecimalFormat formatter = new DecimalFormat(numberFormat); 
		try { 
			value = new Long(number); 
		} catch (Throwable t) { 
			return null; 
		} 
		return formatter.format(value); 
	}
}
