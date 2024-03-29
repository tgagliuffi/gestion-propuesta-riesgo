package bbva.pe.gpr.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import bbva.pe.gpr.bean.MultitablaDetalle;
import bbva.pe.gpr.context.Context;
import bbva.pe.gpr.service.CatalogoService;
import bbva.pe.gpr.util.Constant;
import bbva.pe.gpr.util.JSONObject;

public class CondicionClienteAction extends DispatchAction {
	private static Logger logger = Logger.getLogger(CondicionClienteAction.class);
	private CatalogoService catalogoService;
	
	public CondicionClienteAction() {
		catalogoService=(CatalogoService)Context.getInstance().getBean("catalogoService");
	}

	public ActionForward listarCondicion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
		List<MultitablaDetalle> multitablaDetalleBBVA = catalogoService.getLstMultitablaDetalle(Constant.TABLA_BBVA);
		request.setAttribute("multitablaDetalleBBVA", multitablaDetalleBBVA);

		List<MultitablaDetalle> multitablaDetalleBUREAU = catalogoService.getLstMultitablaDetalle(Constant.TABLA_BUREAU);
		request.setAttribute("multitablaDetalleBUREAU", multitablaDetalleBUREAU);

		List<MultitablaDetalle> multitablaDetalleSFinan = catalogoService.getLstMultitablaDetalle(Constant.TABLA_SISTEMA_FINANCIERO);
		request.setAttribute("multitablaDetalleSFinan", multitablaDetalleSFinan);

		List<MultitablaDetalle> multitablaDetalleRelePubl = catalogoService.getLstMultitablaDetalle(Constant.TABLA_RELEVANCIA_PUBLICA);
		request.setAttribute("multitablaDetalleRelePubl", multitablaDetalleRelePubl);

		List<MultitablaDetalle> multitablaDetalleInele = catalogoService.getLstMultitablaDetalle(Constant.TABLA_INELEGIBLES);
		request.setAttribute("multitablaDetalleInele", multitablaDetalleInele);
 		}catch (Exception e) {
 			logger.error("CondicionClienteAction.listarCondicion", e);
 		}
		return mapping.findForward("condicionCliente");
	}

	public ActionForward actualizarCondicion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject jsonObjectRoot = new JSONObject();
		try {
			String idChecked = request.getParameter("idChecked");
			String idNochecked = request.getParameter("idNochecked");
			String idCheckedBbva = request.getParameter("idCheckedBbva");
			String idNocheckedBbva = request.getParameter("idNocheckedBbva");
			String idCheckedSisFi = request.getParameter("idCheckedSisFi");
			String idNoCheckedSisFi = request.getParameter("idNoCheckedSisFi");

			String[] idElementoSelect = idChecked.split(",");
			String[] idElementoNoSelect = idNochecked.split(",");
			String[] idElementoSelectBbva = idCheckedBbva.split(",");
			String[] idElementoNoSelectBbva = idNocheckedBbva.split(",");
			String[] idElementoSelectSisFi = idCheckedSisFi.split(",");
			String[] idElementoNoSelectSisFi = idNoCheckedSisFi.split(",");
			for (String idValor : idElementoSelectBbva) {
				MultitablaDetalle multitablaDetalle = new MultitablaDetalle();
				multitablaDetalle.setCodElemento(idValor);
				multitablaDetalle.setNumberValor(new BigDecimal(1));
				multitablaDetalle.setCodMultitabla(Constant.TABLA_BBVA);
				catalogoService.update(multitablaDetalle);
			}
			for (String idValorN : idElementoNoSelectBbva) {
				MultitablaDetalle multitablaDetalle = new MultitablaDetalle();
				multitablaDetalle.setCodElemento(idValorN);
				multitablaDetalle.setNumberValor(new BigDecimal(0));
				multitablaDetalle.setCodMultitabla(Constant.TABLA_BBVA);				
				catalogoService.update(multitablaDetalle);
			}
			for (String idValor : idElementoSelectSisFi) {
				MultitablaDetalle multitablaDetalle = new MultitablaDetalle();
				multitablaDetalle.setCodElemento(idValor);
				multitablaDetalle.setNumberValor(new BigDecimal(1));
				multitablaDetalle.setCodMultitabla(Constant.TABLA_SISTEMA_FINANCIERO);
				catalogoService.update(multitablaDetalle);
			}
			for (String idValorN : idElementoNoSelectSisFi) {
				MultitablaDetalle multitablaDetalle = new MultitablaDetalle();
				multitablaDetalle.setCodElemento(idValorN);
				multitablaDetalle.setNumberValor(new BigDecimal(0));
				multitablaDetalle.setCodMultitabla(Constant.TABLA_SISTEMA_FINANCIERO);				
				catalogoService.update(multitablaDetalle);
			}
			for (String idValor : idElementoSelect) {
				MultitablaDetalle multitablaDetalle = new MultitablaDetalle();
				multitablaDetalle.setCodElemento(idValor);
				multitablaDetalle.setNumberValor(new BigDecimal(1));
				catalogoService.updateCondicionCliente(multitablaDetalle);
			}
			for (String idValorN : idElementoNoSelect) {
				MultitablaDetalle multitablaDetalle = new MultitablaDetalle();
				multitablaDetalle.setCodElemento(idValorN);
				multitablaDetalle.setNumberValor(new BigDecimal(0));
				catalogoService.updateCondicionCliente(multitablaDetalle);
			}
			jsonObjectRoot.put("success", true);
			jsonObjectRoot.put("data","Registro Actualizado");
		} catch (Exception e) {
			jsonObjectRoot.put("success", false);
			jsonObjectRoot.put("message", e.getCause());
			logger.error("CondicionClienteAction.actualizarCondicion", e);
		}
		out.print(jsonObjectRoot.toString());
		out.flush();
		out.close();
		return null;
	}
}