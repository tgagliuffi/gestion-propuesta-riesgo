package bbva.pe.gpr.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import bbva.pe.gpr.bean.Parametro;
import bbva.pe.gpr.bean.Producto;
import bbva.pe.gpr.context.Context;
import bbva.pe.gpr.form.ProductoForm;
import bbva.pe.gpr.service.CatalogoService;
import bbva.pe.gpr.util.ReadProperties;

public class ProductoAction extends DispatchAction{
	private static Logger logger = Logger.getLogger(ProductoAction.class);
	
	CatalogoService catalogoService;
	
	public ProductoAction() {
		catalogoService  = (CatalogoService)Context.getInstance().getBean("catalogoService");
	}
	
	public List<Producto> consultarAjax(BigDecimal codBanca){
		try {
			if(codBanca!=null && !(codBanca.compareTo(new BigDecimal(-1))==0)){
				Producto productoBean = new Producto();
				productoBean.setCodBanca(codBanca);
			return catalogoService.getLstProducto(productoBean);
			}
		
		} catch (Exception e) {
			logger.error("Exception ProductoAction.consultarAjax: " + e.getMessage());
		} 
		return new ArrayList<Producto>();
	}
	
	public String eliminarProductoAjax(String selecciones){
			
		ReadProperties readProperties = new ReadProperties();
		
		try {
			
			StringTokenizer st = new StringTokenizer(selecciones, "**");
			String concatIds = "";
			
			while (st.hasMoreTokens()) {
				String elemento = st.nextToken();
				StringTokenizer stComas = new StringTokenizer(elemento, "-");
				while (stComas.hasMoreTokens()) {
					stComas.nextToken();
					if(stComas.hasMoreTokens()){
						String id = stComas.nextToken();
						concatIds = concatIds + "'"+id+"',";
					}
				}					
			}
			
			if(!"".equals(concatIds)){
				concatIds = concatIds.substring(0, concatIds.length()-1);
				//productoService.deleteProductsByCode(concatIds);
				return readProperties.getProperty("etiqueta.success.delete");
			}
			
			return "";
			
		} catch (Exception e) {
			logger.error("Exception ProductoAction.eliminarProductoAjax: " + e.getMessage());
			return readProperties.getProperty("etiqueta.error.delete");
		}
	}
	
	@SuppressWarnings("unused")
	public ActionForward guardarProductoAjax(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ProductoForm productoForm =(ProductoForm)form;
		String codProducto = productoForm.getCodProducto();
		String desProducto = productoForm.getDesProducto();
		String contratoVinculado = productoForm.getContratoVinculado();
		String codPreEvaluador = productoForm.getCodPreEvaluador();
		String campania = productoForm.getCampania();
		String tipo = productoForm.getTipo();
		String monto = productoForm.getMonto();
		String plazo = productoForm.getPlazo();
		String garantia = productoForm.getGarantia();
		String montoTotal = productoForm.getValMontoTotal();
		String moneda = productoForm.getValMoneda();
		String banca = productoForm.getValBanca();	
		
		ReadProperties readProperties = new ReadProperties();
//		Map<String, String> mapResult = productoService.saveProduct(
//		codProducto, desProducto, contratoVinculado, codPreEvaluador, 
//		campania, tipo, monto, plazo, garantia);
		
		Map<String, String> mapResult = new HashMap<String, String>();
		mapResult.put("idGenerado", "1");
		
		
		productoForm.setGarantia("");
		productoForm.setPlazo("");
		productoForm.setMonto("");
		productoForm.setTipo("");
		productoForm.setCampania("");
		productoForm.setCodPreEvaluador("");
		productoForm.setContratoVinculado("");
		productoForm.setDesProducto("");
		productoForm.setCodProducto("");
		
		if(mapResult.get("msgError") != null) {
			response.setStatus(500);
			response.getWriter().write(readProperties.getProperty(mapResult.get("msgError")));
		}else if(mapResult.get("idGenerado") != null) {
			response.setStatus(200);
			response.getWriter().write(mapResult.get("idGenerado"));
		}
		
		return null;
	}
	
	public List<Parametro> cargarProductosPorBanca(String codBanca){
		
		Parametro bean = new Parametro();
		List<Parametro> listParametros = new ArrayList<Parametro>();
		
		bean.setCodParametro("-1");
		bean.setDesParametro("-- Seleccionar un producto --");
		listParametros.add(bean);
		
		//frk: Pasar la logica de negocio al service
		if(codBanca.equalsIgnoreCase("BP")){
			for (int i = 1; i <= 3; i++) {
				bean = new Parametro();
				bean.setCodParametro(""+i);
				bean.setDesParametro("Producto "+i);
				listParametros.add(bean);
			}
		}else if(codBanca.equalsIgnoreCase("BC")){
			for (int i = 4; i <= 6; i++) {
				bean = new Parametro();
				bean.setCodParametro(""+i);
				bean.setDesParametro("Producto "+i);
				listParametros.add(bean);
			}
		}else if(codBanca.equalsIgnoreCase("BM")){
			for (int i = 7; i <= 9; i++) {
				bean = new Parametro();
				bean.setCodParametro(""+i);
				bean.setDesParametro("Producto "+i);
				listParametros.add(bean);
			}
		}else if(codBanca.equalsIgnoreCase("BE")){
			for (int i = 10; i <= 11; i++) {
				bean = new Parametro();
				bean.setCodParametro(""+i);
				bean.setDesParametro("Producto "+i);
				listParametros.add(bean);
			}
		}
		
		return listParametros;
	}
	
	public List<Parametro> cargarCampaniasPorProducto(String codProducto){
		
		Parametro bean = new Parametro();
		List<Parametro> listParametros = new ArrayList<Parametro>();
		
		bean.setCodParametro("-1");
		bean.setDesParametro("-- Seleccionar una campaña --");
		listParametros.add(bean);
		
		//frk: Pasar la logica de negocio al service
		if(codProducto.equalsIgnoreCase("1")){
			for (int i = 1; i <= 3; i++) {
				bean = new Parametro();
				bean.setCodParametro(""+i);
				bean.setDesParametro("Campaña "+i);
				listParametros.add(bean);
			}
		}else if(codProducto.equalsIgnoreCase("4")){
			for (int i = 4; i <= 6; i++) {
				bean = new Parametro();
				bean.setCodParametro(""+i);
				bean.setDesParametro("Campaña "+i);
				listParametros.add(bean);
			}
		}else if(codProducto.equalsIgnoreCase("7")){
			for (int i = 7; i <= 9; i++) {
				bean = new Parametro();
				bean.setCodParametro(""+i);
				bean.setDesParametro("Campaña "+i);
				listParametros.add(bean);
			}
		}else if(codProducto.equalsIgnoreCase("10")){
			for (int i = 10; i <= 11; i++) {
				bean = new Parametro();
				bean.setCodParametro(""+i);
				bean.setDesParametro("Campaña "+i);
				listParametros.add(bean);
			}
		}
		
		return listParametros;
	}
	
	public List<Parametro> cargarContratosVincPorBanca(String codBanca){
		
		Parametro bean = new Parametro();
		List<Parametro> listParametros = new ArrayList<Parametro>();
		
		bean.setCodParametro("-1");
		bean.setDesParametro("-- Seleccionar un contrato --");
		listParametros.add(bean);
		
		//frk: Pasar la logica de negocio al service
		if(codBanca.equalsIgnoreCase("BP")){
			for (int i = 1; i <= 3; i++) {
				bean = new Parametro();
				bean.setCodParametro(""+i);
				bean.setDesParametro("Contrato "+i);
				listParametros.add(bean);
			}
		}
		
		return listParametros;
	}
	
	public List<Parametro> cargarTipos(){
		
		Parametro bean = new Parametro();
		List<Parametro> listParametros = new ArrayList<Parametro>();
		
		//frk: Pasar la logica de negocio al service
		for (int i = 1; i <= 3; i++) {
			bean = new Parametro();
			bean.setCodParametro(""+i);
			bean.setDesParametro("Tipo "+i);
			listParametros.add(bean);
		}
		
		return listParametros;
	}
}
