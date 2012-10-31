package bbva.pe.gpr.listener;

import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.PropertyConfigurator;

import bbva.pe.gpr.bean.MultitablaDetalle;
import bbva.pe.gpr.context.Context;
import bbva.pe.gpr.dao.MultitablaDetalleDAO;
import bbva.pe.gpr.service.CatalogoService;
import bbva.pe.gpr.util.Constant;


public class LogConfiguratorListener implements ServletContextListener {
	
	
	private MultitablaDetalleDAO multitablaDetalleDAO;
	
	public MultitablaDetalleDAO getMultitablaDAO() {
		return multitablaDetalleDAO;
	}

	public void setMultitablaDAO(MultitablaDetalleDAO multitablaDetalleDAO) {
		this.multitablaDetalleDAO = multitablaDetalleDAO;
	}

	public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }

    public void contextInitialized(ServletContextEvent arg0) {
    	try {
    	CatalogoService catalogoService=(CatalogoService)Context.getInstance().getBean("catalogoService");   
    	MultitablaDetalle par=catalogoService.selectMultitablaDTByPrimaryKey("T016", "16001");        
		Properties prop = new Properties();
    	prop.setProperty("log4j.rootCategory", "TRACE, LOGFILE, CONSOLE");
		prop.setProperty("log4j.appender.CONSOLE", "org.apache.log4j.ConsoleAppender");
		prop.setProperty("log4j.appender.CONSOLE.layout", "org.apache.log4j.PatternLayout");
		prop.setProperty("log4j.appender.CONSOLE.layout.ConversionPattern", "[%d{yyyy-MM-dd HH:mm:ss}] - [%5p] (%C{1}.%M:%L) - %m%n");
		prop.setProperty("log4j.appender.LOGFILE", "org.apache.log4j.DailyRollingFileAppender");
		prop.setProperty("log4j.appender.LOGFILE.file", par.getStrValor()  + Constant.NOMBRE_LOG);// /de/iist/online/pe/web/log/IIST.log
		prop.setProperty("log4j.appender.LOGFILE.DatePattern", "'.'yyyy-MM-dd'.log'");
		prop.setProperty("log4j.appender.LOGFILE.append", "true");
		prop.setProperty("log4j.appender.LOGFILE.layout", "org.apache.log4j.PatternLayout");
		prop.setProperty("log4j.appender.LOGFILE.layout.ConversionPattern", "[%d{yyyy-MM-dd HH:mm:ss}] - [%5p] (%C{1}.%M:%L) - %m%n");
	
		PropertyConfigurator.configure(prop);
	
		System.out.println("Log configurated!");
    	} catch (Exception e) {
			//System.out.println("Error configurando Log4j cobadeuWeb:"+StringUtil.getStackTrace(e));
		}
		
		
    }
	
}
