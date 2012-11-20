package bbva.pe.gpr.action;

import java.io.OutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
import bbva.pe.gpr.util.DocumentoExcel;
import bbva.pe.gpr.util.Grafico;

public class EstadisticaAction extends DispatchAction {

	private static Logger logger = Logger.getLogger(EstadisticaAction.class);
	private EstadisticaService estadisticaService;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public EstadisticaAction() {
		estadisticaService = (EstadisticaService) Context.getInstance().getBean("estadisticaService");
	}

	public ActionForward asignacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("asignacion") ;
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward generarPieChartAsignacion(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String index = request.getParameter("index");
		
		List<byte[]> data = (List<byte[]>) request.getSession().getAttribute("graf");
		
		if(index != null && data != null) {
			OutputStream out = response.getOutputStream();
			
			try {
				response.setContentType("image/png");
				out.write(data.get(Integer.parseInt(index)));
			} catch (Exception e) {
				logger.error("generarPieChart", e);
			} finally {
				out.close();
				out.flush();
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward generarExcelAsignacion(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		int i;
		int j;
		int row;
		int col;
		byte [] outArray = null;
		List<byte[]> graf = (List<byte[]>) request.getSession().getAttribute("graf");
		Map<String, Object> map = (Map<String, Object>) request.getSession().getAttribute("data");
		List<String> colsName = (List<String>) map.get("colsName");
		List<Map<String, Object>> colsModel = (List<Map<String, Object>>) map.get("colsModel");
		List<Map<String, Object>> data = (List<Map<String, Object>>) map.get("data");
		String colName;
		
		DocumentoExcel doc = new DocumentoExcel();
		doc.getWorkbook().setSheetName(0, "Solicitudes Asignadas");
		
		doc.setContentValue(0, 0, 10, "Solicitudes Asignadas");
		row = 2;
		
		for(i = 0; i < colsName.size(); i++) {
			doc.setContentValue(row, i, colsName.get(i));
		}
		row++;
		
		for(i = 0; i < data.size(); i++) {
			for(j = 0; j < colsModel.size(); j++) {
				colName = colsModel.get(j).get("index").toString();
				doc.setContentValue(row, j, data.get(i).get(colName).toString());
			}
			row++;
		}
		row++;
		
		col = 0;
		for(i = 0; i < graf.size(); i++) {
			doc.setImagen(row, col, graf.get(i));
			col += 7;
			
			if(col == 14) {
				col = 0;
				row += 23;
			}
		}
		
		outArray = doc.getExcelToByteArray();
		
		response.setContentType("application/ms-excel");
		response.setHeader("Expires:", "0");
		response.setHeader("Content-Disposition", "attachment; filename=SolicitudesAsignadas.xls");
		if(outArray != null) { 
			response.setContentLength(outArray.length);
			response.getOutputStream().write(outArray);
			response.getOutputStream().flush();
		}
		return null;
		
	}
	
	public Map<String, Object> listarEstadisticasAsignacion(Estadistica e, String fechaInicio, String fechaFin) {
		HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			e.setFechaInicio(new Date(formatter.parse(fechaInicio).getTime()));
			e.setFechaFin(new Date(formatter.parse(fechaFin).getTime()));
			
			List<Map<String, Object>> data = estadisticaService.selectAsignacion(e);
			map = estadisticaService.selectCabeceraAsignacion(e);
			map.put("data", data);
			request.getSession().setAttribute("graf", grafAsignacion(data));
			request.getSession().setAttribute("data", map);
		} catch (Exception ex) {
			logger.error("listarEstadisticasAsignacion", ex);
		}
		return map;
	}

	private List<byte[]> grafAsignacion(List<Map<String, Object>> data) {
		List<byte[]> toByte = new ArrayList<byte[]>();
		Map<String, Object> item;
		int i;
		int total = 0;
		int parcial = 0;
		int mayor = 0;
		String keyExplodePercent = "";
		String key = "";
		Grafico graf = null;
		DefaultPieDataset dataset;
		Set<Entry<String, Object>> set;
		Iterator<Entry<String, Object>> items;
		Entry<String, Object> entry;
		
		
		for(i = 0; i < data.size(); i++) {
			item = data.get(i);
			
			if(item != null) {
				graf = new Grafico();
				graf.setTitle(item.get("desBanca").toString());
				total = 0;
				parcial = 0;
				mayor = 0;
				keyExplodePercent = "";
				key = "";
				
				dataset = graf.getDatasetPie();
				set = item.entrySet();
				items = set.iterator();
				
				while(items.hasNext()) {
					entry = items.next();
					if(!(entry.getKey().equalsIgnoreCase("s_fueraderango")
						|| entry.getKey().equalsIgnoreCase("s_priorizadas")
						|| entry.getKey().equalsIgnoreCase("desBanca")
						|| entry.getKey().equalsIgnoreCase("total"))) {
						
						try {
							parcial = Integer.parseInt(entry.getValue().toString());
						} catch(NumberFormatException e) {
							parcial = 0;
						}
						
						key = entry.getKey().replaceAll("s_", "");
						key = key.replaceAll("_", " ").toUpperCase();
						
						if(parcial > mayor) {
							mayor = parcial;
							keyExplodePercent = key;
						}
						
						total += parcial;
						if(parcial > 0) {
							dataset.setValue(key, parcial);
						}
					}
				}
				
				graf.setSubtitle(total + " solicitudes");
				graf.setExplodePercent(keyExplodePercent);
				
				try {
					byte[] toByteGraf = graf.generaPieChart();
					toByte.add(toByteGraf);
				} catch (Exception e) {
					logger.error("generarPieChart", e);
				}
			}
		}		
		
		return toByte;
	}
}