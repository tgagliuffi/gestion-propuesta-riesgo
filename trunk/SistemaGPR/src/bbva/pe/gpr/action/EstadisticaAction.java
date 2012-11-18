package bbva.pe.gpr.action;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.jfree.data.general.DefaultPieDataset;

import bbva.pe.gpr.util.Grafico;

public class EstadisticaAction extends DispatchAction {

	private static Logger logger = Logger.getLogger(EstadisticaAction.class);
	
	public ActionForward generarPieChart(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Grafico graf = new Grafico();
		graf.setTitle("Banca");
		graf.setSubtitle("15000 solicitudes");
		DefaultPieDataset dataset = graf.getDatasetPie();
		dataset.setValue("asignados", 74);
		dataset.setValue("sin asignar", 87);
		dataset.setValue("anuladas", 62);
		dataset.setValue("rechazadas", 92);
		dataset.setValue("test", 51);
		graf.setExplodePercent("rechazadas");
		
		OutputStream out = response.getOutputStream();
		
		try {
			byte[] toByte = graf.generaPieChart();
			response.setContentType("image/png");
			out.write(toByte);
		} catch (Exception e) {
			logger.error("generarPieChart", e);
		} finally {
			out.close();
			out.flush();
		}

		return null;
	}
}
