package bbva.pe.gpr.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Context {
	private static ApplicationContext ctx = null;

	private Context() {
	}

	public static void load() {
		ctx = new ClassPathXmlApplicationContext(
				"bbva/pe/gpr/sqlmapdao/spring-app.xml");
	}

	public static ApplicationContext getInstance() {
		return ctx;
	}
}