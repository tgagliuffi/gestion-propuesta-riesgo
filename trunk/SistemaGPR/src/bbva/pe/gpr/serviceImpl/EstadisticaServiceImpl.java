package bbva.pe.gpr.serviceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import bbva.pe.gpr.bean.Estadistica;
import bbva.pe.gpr.dao.EstadisticaDAO;
import bbva.pe.gpr.service.EstadisticaService;
import bbva.pe.gpr.util.Constant;

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
		String colName;
		int i = 0;
		
		try {
			listCabEstadistica = estadisticaDAO.selectCabeceraAsignacion(filtro);
			colsName.add("BANCA");
			colsName.add("TOTAL");
			
			colModel = new HashMap<String, Object>();
			colModel.put("index", "desBanca");
			colModel.put("name", "desBanca");
			colModel.put("width", 150);
			colsModel.add(colModel);
			
			colModel = new HashMap<String, Object>();
			colModel.put("index", "total");
			colModel.put("name", "total");
			colModel.put("width", 100);
			colsModel.add(colModel);
			
			for(i = 0; i < listCabEstadistica.size(); i++) {
				colsName.add(listCabEstadistica.get(i).getDesEstadoSolicitud());
				
				colName = "s_" + listCabEstadistica.get(i).getDesEstadoSolicitud().replaceAll(" ", "_").toLowerCase();
				
				colModel = new HashMap<String, Object>();
				colModel.put("index", colName);
				colModel.put("name", colName);
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
	public Map<String, Object> selectCabeceraAtencion(Estadistica filtro) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> colModel = new HashMap<String, Object>();
		List<String> colsName = new ArrayList<String>();
		List<Map<String, Object>> colsModel = new ArrayList<Map<String, Object>>();
		List<Estadistica> listCabEstadistica;
		int i = 0;
		
		try {
			listCabEstadistica = estadisticaDAO.selectCabeceraAsignacion(filtro);
			colsName.add("BANCA");
			colsName.add("DICTAMEN");
			colsName.add("TERRITORIO");
			colsName.add("OFICINA");
			colsName.add("TOTAL");
			
			colModel = new HashMap<String, Object>();
			colModel.put("index", "desBanca");
			colModel.put("name", "desBanca");
			colModel.put("width", 150);
			colsModel.add(colModel);
			
			colModel = new HashMap<String, Object>();
			colModel.put("index", "desDictamen");
			colModel.put("name", "desDictamen");
			colModel.put("width", 150);
			colsModel.add(colModel);
			
			colModel = new HashMap<String, Object>();
			colModel.put("index", "desTerritorio");
			colModel.put("name", "desTerritorio");
			colModel.put("width", 150);
			colsModel.add(colModel);
			
			colModel = new HashMap<String, Object>();
			colModel.put("index", "desOficina");
			colModel.put("name", "desOficina");
			colModel.put("width", 150);
			colsModel.add(colModel);
			
			colModel = new HashMap<String, Object>();
			colModel.put("index", "total");
			colModel.put("name", "total");
			colModel.put("width", 100);
			colsModel.add(colModel);
			
			for(i = 0; i < listCabEstadistica.size(); i++) {
				colsName.add(listCabEstadistica.get(i).getDesEstadoSolicitud());
				
				colModel = new HashMap<String, Object>();
				colModel.put("index", "s_" + listCabEstadistica.get(i).getDesEstadoSolicitud().replaceAll(" ", "_").toLowerCase());
				colModel.put("name", "s_" + listCabEstadistica.get(i).getDesEstadoSolicitud().replaceAll(" ", "_").toLowerCase());
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
		Map<String, Map<String, Object>> position = new HashMap<String, Map<String,Object>>();
		BigDecimal tmp = BigDecimal.ZERO;
		String colName;
		
		try {		
			listEstadistica = estadisticaDAO.selectAsignacion(filtro);
			listCabEstadistica = estadisticaDAO.selectCabeceraAsignacion(filtro);
			
			for(Estadistica e : listEstadistica) {
				
				if(position.containsKey(e.getDesBanca())) {
					item = position.get(e.getDesBanca());
				} else {
					item = new HashMap<String, Object>();
					item.put("desBanca", e.getDesBanca());
					item.put("total", 0);
					item.put("s_anuladas", 0);
					item.put("s_priorizadas", 0);
					item.put("s_fuera_de_rango", 0);
				}

				for(Estadistica c : listCabEstadistica) {
					colName = "s_" + c.getDesEstadoSolicitud().replaceAll(" ", "_").toLowerCase();
					tmp = Constant.StringToBigDecimal(item.get(colName));
					if(c.getDesEstadoSolicitud().equalsIgnoreCase("ANULADAS")) {
						item.put(colName, e.getAnuladas().add(tmp).intValue());
					} else if(c.getDesEstadoSolicitud().equalsIgnoreCase("PRIORIZADAS")) {
						item.put(colName, e.getPriorizadas().add(tmp).intValue());
					} else if(c.getDesEstadoSolicitud().equalsIgnoreCase("FUERA_DE_RANGO")) {
						item.put(colName, e.getFueraDeRango().add(tmp).intValue());
					} else if(c.getDesEstadoSolicitud().equalsIgnoreCase(e.getDesEstadoSolicitud())) {
						tmp = Constant.StringToBigDecimal(item.get("total"));
						item.put(colName, e.getNroSolicitudes());
						item.put("total", e.getNroSolicitudes().add(tmp).intValue());
					} else {
						if(!item.containsKey(colName)) {
							item.put(colName, 0);
						}
					}
				}
				
				position.put(e.getDesBanca(), item);
			}
			
			Set<Entry<String, Map<String, Object>>> set = position.entrySet();
			Iterator<Entry<String, Map<String, Object>>> items = set.iterator();
			Entry<String, Map<String, Object>> entry;
			
			while(items.hasNext()) {
				entry = items.next();
				result.add(entry.getValue());
			}
			
		} catch (Exception e) {
			logger.error("selectAsignacion", e);
		}
 		
		return result;
	}

	@Override
	public List<Map<String, Object>> selectAtencion(Estadistica filtro) {
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		List<Estadistica> listEstadistica;
		List<Estadistica> listCabEstadistica;
		Map<String, Object> item;
		Map<String, Map<String, Object>> position = new HashMap<String, Map<String,Object>>();
		
		try {		
			listEstadistica = estadisticaDAO.selectAtencion(filtro);
			listCabEstadistica = estadisticaDAO.selectCabeceraAsignacion(filtro);
			
			for(Estadistica e : listEstadistica) {
				
				if(position.containsKey(e.getDesBanca())) {
					item = position.get(e.getDesBanca());
				} else {
					item = new HashMap<String, Object>();
					item.put("desBanca", e.getDesBanca());
					item.put("desDictamen", e.getDesBanca());
					item.put("desTerritorio", e.getDesBanca());
					item.put("desOficina", e.getDesBanca());
					
				}

				for(Estadistica c : listCabEstadistica) {
					if(c.getDesEstadoSolicitud().equalsIgnoreCase(e.getDesEstadoSolicitud())) {
						item.put("s_" + c.getDesEstadoSolicitud().replaceAll(" ", "_").toLowerCase(), e.getNroSolicitudes());
					} else {
						if(!item.containsKey("s_" + c.getDesEstadoSolicitud().replaceAll(" ", "_").toLowerCase())) {
							item.put("s_" + c.getDesEstadoSolicitud().replaceAll(" ", "_").toLowerCase(), 0);
						}
					}
				}
				
				position.put(e.getDesBanca(), item);
			}
			
			Set<Entry<String, Map<String, Object>>> set = position.entrySet();
			Iterator<Entry<String, Map<String, Object>>> items = set.iterator();
			Entry<String, Map<String, Object>> entry;
			
			while(items.hasNext()) {
				entry = items.next();
				result.add(entry.getValue());
			}
			
		} catch (Exception e) {
			logger.error("selectAtencion", e);
		}
 		
		return result;
	}
}
