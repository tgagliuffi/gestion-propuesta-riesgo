package bbva.pe.gpr.listener;




import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import bbva.pe.gpr.context.Context;


public class SpringInit implements ServletContextListener {

   

    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }

	
    public void contextInitialized(ServletContextEvent arg0) {
    	
    		Context.load();
        	System.out.println("Context Loaded!");
		
    	
    }

}
