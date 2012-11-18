package bbva.pe.gpr.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import bbva.pe.gpr.bean.Estadistica;
import bbva.pe.gpr.dao.EstadisticaDAO;
import bbva.pe.gpr.service.EstadisticaService;

public class EstadisticaServiceImpl implements EstadisticaService {

	private static Logger logger = Logger.getLogger(EstadisticaServiceImpl.class);
	private EstadisticaDAO estadisticaDAO;
	
	public EstadisticaServiceImpl(EstadisticaDAO estadisticaDAO) {
		super();
		this.estadisticaDAO = estadisticaDAO;
	}

	@Override
	public Map<String, Object> selectCabeceraAsignacion(Estadistica filtro) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> colModel = new HashMap<String, Object>();
		List<String> colsName = new ArrayList<String>();
		List<Map<String, Object>> colsModel = new ArrayList<Map<String, Object>>();
		List<Estadistica> listCabEstadistica;
		int i = 0;
		
		try {
			listCabEstadistica = estadisticaDAO.selectCabeceraAsignacion(filtro);
			colsName.add("BANCA");
			// colsName.add("TOTAL");
			
			colModel = new HashMap<String, Object>();
			colModel.put("index", "desBanca");
			colModel.put("name", "desBanca");
			colModel.put("width", 150);
			colsModel.add(colModel);
			
			colModel = new HashMap<String, Object>();
			colModel.put("index", "total");
			colModel.put("name", "total");
			colModel.put("width", 100);
			// colsModel.add(colModel);
			
			for(i = 0; i < listCabEstadistica.size(); i++) {
				colsName.add(listCabEstadistica.get(i).getDesEstadoSolicitud());
				
				colModel = new HashMap<String, Object>();
				colModel.put("index", "s_" + listCabEstadistica.get(i).getDesEstadoSolicitud().replaceAll(" ", "").toLowerCase());
				colModel.put("name", "s_" + listCabEstadistica.get(i).getDesEstadoSolicitud().replaceAll(" ", "").toLowerCase());
				colModel.put("width", 100);
				colModel.put("align", "right");
				colsModel.add(colModel);
			}
			map.put("colsName", colsName);
			map.put("colsModel", colsModel);
		} catch (Exception e) {
			logger.error("selectCabeceraAsignacion", e);
		}
	
		return map;
	}
	
	@Override
	public List<Map<String, Object>> selectAsignacion(Estadistica filtro) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		List<Estadistica> listEstadistica;
		List<Estadistica> listCabEstadistica;
		Map<String, Object> item;
		
		try {		
			listEstadistica = estadisticaDAO.selectAsignacion(filtro);
			listCabEstadistica = estadisticaDAO.selectCabeceraAsignacion(filtro);
			
			for(Estadistica e : listEstadistica) {
				item = new HashMap<String, Object>();
				item.put("desBanca", e.getDesBanca());
				
				for(Estadistica c : listCabEstadistica) {
					if(c.getDesEstadoSolicitud().equalsIgnoreCase(e.getDesEstadoSolicitud())) {
						item.put("s_" + c.getDesEstadoSolicitud().replaceAll(" ", "").toLowerCase(), e.getNroSolicitudes());
					} else {
						item.put("s_" + c.getDesEstadoSolicitud().replaceAll(" ", "").toLowerCase(), 0);
					}
				}
				
				result.add(item);
			}
			
		} catch (Exception e) {
			logger.error("selectAsignacion", e);
		}
 		
		return result;
	}

}
