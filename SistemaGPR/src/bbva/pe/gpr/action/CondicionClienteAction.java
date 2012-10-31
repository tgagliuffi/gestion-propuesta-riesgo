package bbva.pe.gpr.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	private CatalogoService catalogoService;
	
	public CondicionClienteAction() {
		catalogoService=(CatalogoService)Context.getInstance().getBean("catalogoService");
	}

	public ActionForward listarCondicion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

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

			String[] idElementoSelect = idChecked.split(",");
			String[] idElementoNoSelect = idNochecked.split(",");

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
		}
		out.print(jsonObjectRoot.toString());
		out.flush();
		out.close();
		return null;
	}
}