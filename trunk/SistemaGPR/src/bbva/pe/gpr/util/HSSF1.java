package bbva.pe.gpr.util;



import java.io.FileInputStream;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


/**
 * @author tgagliuffi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
//este es el codigo para leer el excel!!

public class HSSF1{
	private static Logger logger = Logger.getLogger(HSSF1.class);
	private HSSFWorkbook workbook=null;
	private FileInputStream inputfile;


public HSSFWorkbook getWorkbook(String s){
	try{
			inputfile=new FileInputStream(s);
			logger.info("File Name : "  + s);
			workbook=new HSSFWorkbook(inputfile);
		}catch(Exception e ){
			logger.info(e.getMessage(),e);
		}
		return workbook;	
	}

	}





