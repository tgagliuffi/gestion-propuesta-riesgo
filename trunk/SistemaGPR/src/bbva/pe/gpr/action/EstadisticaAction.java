package bbva.pe.gpr.action;

import java.io.OutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.directwebremoting.WebContextFactory;
import org.jfree.data.general.DefaultPieDataset;

import bbva.pe.gpr.bean.Estadistica;
import bbva.pe.gpr.context.Context;
import bbva.pe.gpr.service.EstadisticaService;
import bbva.pe.gpr.util.Grafico;

public class EstadisticaAction extends DispatchAction {

	private static Logger logger = Logger.getLogger(EstadisticaAction.class);
	private EstadisticaService estadisticaService;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public EstadisticaAction() {
		estadisticaService = (EstadisticaService) Context.getInstance().getBean("estadisticaService");
	}

	public ActionForward generarPieChart(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String index = request.getParameter("index");
		
		if(index != null) {
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
		}
		
		return null;
	}
	
	public Map<String, Object> listarEstadisticasAsignacion(Estadistica e, String fechaInicio, String fechaFin) {
		HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> data = estadisticaService.selectAsignacion(e);
			e.setFechaInicio(new Date(formatter.parse(fechaInicio).getTime()));
			e.setFechaFin(new Date(formatter.parse(fechaInicio).getTime()));
			map = estadisticaService.selectCabeceraAsignacion(e);
			map.put("data", data);
			request.getSession().setAttribute("data", data);
		} catch (Exception ex) {
			logger.error("listarEstadisticasAsignacion", ex);
		}
		return map;
	}
}
