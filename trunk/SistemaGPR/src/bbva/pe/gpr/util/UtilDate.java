package bbva.pe.gpr.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilDate {

	private final static String FORMAT = "dd/MM/yyyy";

	
	public static String fechaActual(){
	 	SimpleDateFormat formato = new SimpleDateFormat(FORMAT);
	 	Calendar c1 = Calendar.getInstance(); 
	 	return formato.format(c1.getTime());
	 }
	
	
	public static Date primerDíaDelAño(){
		return stringToUtilDate("01/01/" + fechaActual().substring(6, 10), null);
	 }
	
	public static Date ultimoDíaDelAño(){
		return stringToUtilDate("31/12/" + fechaActual().substring(6, 10), null);
	}
	
	public static Date stringToUtilDate(String str, String pattern) {
        java.util.Date date = null;

        if(str != null && !str.trim().equals("")) {
            if (pattern == null) {
                pattern = "dd/MM/yyyy";
            }
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            try {
				date = new java.util.Date(formatter.parse(str).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
        }
        return date;
    }
	
	public static String utilDateToString(java.util.Date sqlDateFecha, String pattern) {
		String str = null;
		if (sqlDateFecha != null) {
			if (pattern == null) {
                pattern = "dd/MM/yyyy";
            }
   			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
   			str = formatter.format(sqlDateFecha);
		}
		
		return str;
	}
	
	public static String utilDateJquery(String input) {
		return input.substring(3, 5)+"/"+input.substring(0, 2)+"/"+input.substring(6, 10);
	}
}
