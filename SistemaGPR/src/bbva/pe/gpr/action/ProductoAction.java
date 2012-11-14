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

import bbva.pe.gpr.bean.Campania;
import bbva.pe.gpr.bean.Contrato;
import bbva.pe.gpr.bean.MultitablaDetalle;
import bbva.pe.gpr.bean.Producto;
import bbva.pe.gpr.bean.SolicitudDetalle;
import bbva.pe.gpr.context.Context;
import bbva.pe.gpr.form.ProductoForm;
import bbva.pe.gpr.service.CatalogoService;
import bbva.pe.gpr.util.Constant;
import bbva.pe.gpr.util.ReadProperties;

public class ProductoAction extends DispatchAction{
	private static Logger logger = Logger.getLogger(ProductoAction.class);
	
	CatalogoService catalogoService;
	List<SolicitudDetalle> lstSolicitudDetalle = new ArrayList<SolicitudDetalle>();
	public ProductoAction() {
		catalogoService  = (CatalogoService)Context.getInstance().getBean("catalogoService");
	}
	
	public List<Producto> consultarAjax(BigDecimal codBanca){
		try {
			Producto productoBean = new Producto();
			List<Producto> lstProducto = new ArrayList<Producto>();
			productoBean.setCodProducto(new BigDecimal(-1));
			productoBean.setDescripcion("-- Seleccionar un producto --");
			lstProducto.add(productoBean);
			
			if(codBanca!=null && !(codBanca.compareTo(new BigDecimal(-1))==0)){
				productoBean = new Producto();
				productoBean.setCodBanca(codBanca);
				productoBean.setEstado(Constant.ESTADO_ACTIVO);
				lstProducto = catalogoService.getLstProductoByCriteria(productoBean);
				return lstProducto;
			}
		
		} catch (Exception e) {
			logger.error("Exception ProductoAction.consultarAjax: " + e.getMessage());
		} 
		return new ArrayList<Producto>();
	}
	
	public List<Campania> cargarCampaniasAjax(){
		try {
			Campania campaniaBean = new Campania();
			campaniaBean.setEstado(Constant.ESTADO_ACTIVO);
			return catalogoService.getlstCampaniaByCriteria(campaniaBean);
		} catch (Exception e) {
			logger.error("Exception ProductoAction.cargarCampaniasAjax: " + e.getMessage());
		} 
		return new ArrayList<Campania>();
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
	
	public ActionForward guardarProductoAjax(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SolicitudDetalle solicitudDetalleBean = new SolicitudDetalle();
		ProductoForm productoForm =(ProductoForm)form;
		/*solicitudDetalleBean.setCodProducto(new BigDecimal(productoForm.getCodProducto()));
		solicitudDetalleBean.setCodProdBase(productoForm.getDesProducto());
		solicitudDetalleBean.setContratoVinculado(productoForm.getContratoVinculado());
		solicitudDetalleBean.setCodPrevaluador(productoForm.getCodPreEvaluador());
		solicitudDetalleBean.setCodMultCampania(productoForm.getCampania());
		solicitudDetalleBean.setTipo(productoForm.getTipo());
		solicitudDetalleBean.setMonto(new BigDecimal(productoForm.getMonto()));
		solicitudDetalleBean.setCodMultPlazo(productoForm.getPlazo());
		solicitudDetalleBean.setCodGarantia(new BigDecimal(productoForm.getGarantia()));*/
		
		lstSolicitudDetalle.add(solicitudDetalleBean);
		request.getSession().setAttribute("lstDetalleProdSession", lstSolicitudDetalle);
		//ReadProperties readProperties = new ReadProperties();
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
		//	response.getWriter().write(readProperties.getProperty(mapResult.get("msgError")));
		}else if(mapResult.get("idGenerado") != null) {
			response.setStatus(200);
			response.getWriter().write(mapResult.get("idGenerado"));
		}
		
		return null;
	}
	
	public List<Contrato> cargarContratosVincPorProducto(String codProducto){
		Contrato  contratoBean = new Contrato();
		List<Contrato> lstContrato = new ArrayList<Contrato>();
		
		contratoBean.setIndxContrato(-1);
		contratoBean.setCodContrato("-- Selecione Contrato --");
		lstContrato.add(contratoBean);
		
		//frk: Pasar la logica de negocio al service
		if(codProducto.equalsIgnoreCase("10001")){
			for (int i = 1; i <= 3; i++) {
				 contratoBean = new Contrato();
				 contratoBean.setIndxContrato(i);
				 contratoBean.setCodContrato("101001428767890643"+i);
				 lstContrato.add(contratoBean);
			}
		}else if(codProducto.equalsIgnoreCase("10002")){
			for (int i = 4; i <= 8; i++) {
				contratoBean = new Contrato();
				contratoBean.setIndxContrato(i);
				contratoBean.setCodContrato("101001428767890643"+i);
				lstContrato.add(contratoBean);
			}
		}else if(codProducto.equalsIgnoreCase("10003")){
			for (int i = 9; i <= 13; i++) {
				contratoBean = new Contrato();
				contratoBean.setIndxContrato(i);
				contratoBean.setCodContrato("101001428767890643"+i);
				lstContrato.add(contratoBean);
			}
		}else if(codProducto.equalsIgnoreCase("10004")){
			for (int i = 14; i <= 18; i++) {
				contratoBean = new Contrato();
				contratoBean.setIndxContrato(i);
				contratoBean.setCodContrato("101001428767890643"+i);
				lstContrato.add(contratoBean);
			}
		}else if(codProducto.equalsIgnoreCase("10005")){
			for (int i = 19; i <= 10; i++) {
				contratoBean = new Contrato();
				contratoBean.setIndxContrato(i);
				contratoBean.setCodContrato("101001428767890643"+i);
				lstContrato.add(contratoBean);
			}
		}else{
			lstContrato = new ArrayList<Contrato>();
			contratoBean.setIndxContrato(-1);
			contratoBean.setCodContrato("-- No se Encontro Contratos --");
			lstContrato.add(contratoBean);
		}
		
		return lstContrato;
	}
	
	public String getScoring(String contrato){
		
		String strScoring;
		if(contrato.equals("C0001")){
			strScoring = "RECHAZADO";
		}else if(contrato.equals("C0002")){
			strScoring = "APROBADO";
		}else{
			strScoring = "DUDA";
		}
		
		return strScoring;
	}
	
	public List<MultitablaDetalle> cargarTipos(){
		try {
				MultitablaDetalle multitablaDetalleBean = new MultitablaDetalle();
				multitablaDetalleBean.setEstado(Constant.ESTADO_ACTIVO);
				return catalogoService.getLstMultitablaDetalle(Constant.TABLA_TIPOS);
		} catch (Exception e) {
			logger.error("Exception ProductoAction.consultarAjax: " + e.getMessage());
		} 
		return new ArrayList<MultitablaDetalle>();
	}

	public BigDecimal getProductoBaseAjax(BigDecimal codProducto){
		Producto productoBean = new Producto();
		try {
			productoBean = catalogoService.selectProductoByPrimaryKey(codProducto);
		} catch (Exception e) {
			logger.error(e);
		}
		return productoBean.getCodProductoBase();
	}

}
