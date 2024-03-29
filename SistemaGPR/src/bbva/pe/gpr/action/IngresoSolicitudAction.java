
package bbva.pe.gpr.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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

import bbva.pe.gpr.bean.Banca;
import bbva.pe.gpr.bean.BancaSub;
import bbva.pe.gpr.bean.Campania;
import bbva.pe.gpr.bean.Contrato;
import bbva.pe.gpr.bean.Garantia;
import bbva.pe.gpr.bean.MultitablaDetalle;
import bbva.pe.gpr.bean.Producto;
import bbva.pe.gpr.bean.ProductoBase;
import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.bean.SolicitudDetalle;
import bbva.pe.gpr.context.Context;
import bbva.pe.gpr.form.SolicitudForm;
import bbva.pe.gpr.service.AplicativoPersonasService;
import bbva.pe.gpr.service.AplicativoRCCService;
import bbva.pe.gpr.service.AplicativoRCDService;
import bbva.pe.gpr.service.AsignacionService;
import bbva.pe.gpr.service.CatalogoService;
import bbva.pe.gpr.service.ControlService;
import bbva.pe.gpr.service.SolicitudService;
import bbva.pe.gpr.service.ValidacionService;
import bbva.pe.gpr.serviceImpl.AplicativoPersonasServiceImpl;
import bbva.pe.gpr.serviceImpl.AplicativoRCCServiceImpl;
import bbva.pe.gpr.serviceImpl.AplicativoRCDServiceImpl;
import bbva.pe.gpr.util.Constant;
import bbva.pe.gpr.util.ReadProperties;
import bbva.pe.gpr.util.UtilDate;
import bbva.pe.gpr.util.UtilGpr;

import com.grupobbva.bc.per.tele.ldap.serializable.IILDPeUsuario;


public class IngresoSolicitudAction extends DispatchAction {

	private static Logger logger = Logger.getLogger(IngresoSolicitudAction.class);
	
	SolicitudService solicitudService;
	CatalogoService catalogoService;
	AplicativoPersonasService appPersonasService;
	ControlService controlService;
	AplicativoRCDService appRCDService;
	AplicativoRCCService appRCCService;
	AsignacionService asignacionService;
	ValidacionService validacionService;
	
	public IngresoSolicitudAction() {
		asignacionService = (AsignacionService)Context.getInstance().getBean("asignacionService");
		solicitudService = (SolicitudService)Context.getInstance().getBean("solicitudService");
		catalogoService = (CatalogoService)Context.getInstance().getBean("catalogoService");
		controlService = (ControlService)Context.getInstance().getBean("controlService");
		appPersonasService = new AplicativoPersonasServiceImpl();
		appRCDService = new AplicativoRCDServiceImpl();
		appRCCService = new AplicativoRCCServiceImpl();
		validacionService = (ValidacionService)Context.getInstance().getBean("validacionService");
	}
	
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("ENTRA INIT");
		request.getSession().removeAttribute("lstDetalleProdSession");
		String target = "success";	
		SolicitudForm solicitudForm = (SolicitudForm)form;	
		solicitudForm.reset(mapping, request);
		if(request.getParameter("param")!=null){
			request.setAttribute("param",request.getParameter("param"));
		}
		
		request.removeAttribute("indMensaje");
		request.getSession().removeAttribute("lstBancas");
		request.getSession().removeAttribute("lstMonedas");
		return mapping.findForward(target);
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward insertSolicitud(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String target = "success";
		SolicitudForm solicitudForm = (SolicitudForm)form;
		Solicitud solicitudBean = new Solicitud();
		String indMensaje = null;
		String strMensaje = null;
		IILDPeUsuario usuarioSesion = (IILDPeUsuario)request.getSession(true).getAttribute("USUARIO_SESION");
		
		
		
		List<SolicitudDetalle> lstSolicitudDetalle = (List<SolicitudDetalle>)request.getSession().getAttribute("lstDetalleProdSession");
		
		try {
			if(lstSolicitudDetalle==null){
				indMensaje = Constant.MSJ_ALERT;
				strMensaje = "Debe cargar datos del cliente y guardar por lo menos un producto.";	
		
			}else{
				solicitudBean.setCodUsuarioSession(usuarioSesion.getUID());
				solicitudBean.setNomUsuarioSession(usuarioSesion.getNombre()+ Constant.ESPACIO +  
												   usuarioSesion.getApellido1() + Constant.ESPACIO +
												   usuarioSesion.getApellido2());
				solicitudBean.setCodCentral(solicitudForm.getHdnCodCentral());
				solicitudBean.setNumeroDocumento(solicitudForm.getNumeroDocumento());
				solicitudBean.setDesSolicitante(solicitudForm.getDesSolicitante());								
				solicitudBean.setCodOficina(solicitudForm.getCodOficina());
				solicitudBean.setDesOficina(solicitudForm.getDesOficina());								
				solicitudBean.setGestorCod(solicitudForm.getGestorCod());
				solicitudBean.setGestorNom(solicitudForm.getGestorNom());								
				solicitudBean.setEmpleadorCod(solicitudForm.getEmpleadorCod());
				solicitudBean.setEmpleadorNom(solicitudForm.getEmpleadorNom());				
				solicitudBean.setGerenciaTerritorialCod(solicitudForm.getGerenciaTerritorialCod());
				solicitudBean.setGerenciaTerritorialNom(solicitudForm.getGerenciaTerritorialNom());
				solicitudBean.setDeudaDirecta(solicitudForm.getDeudaDirecta()!=null?new BigDecimal(solicitudForm.getDeudaDirecta().replace(",", "")):null);
				solicitudBean.setDeudaIndirecta(solicitudForm.getDeudaIndirecta()!=null?new BigDecimal(solicitudForm.getDeudaIndirecta().replace(",", "")):null);
				solicitudBean.setCastigo(solicitudForm.getCastigo()!=null?new BigDecimal(solicitudForm.getCastigo().replace(",", "")):null);
				solicitudBean.setDeudaSistemaFinanciero(solicitudForm.getDeudaSistemaFinanciero()!=null?new BigDecimal(solicitudForm.getDeudaSistemaFinanciero().replace(",", "")):null);
				solicitudBean.setScorating(solicitudForm.getScorating());
				solicitudBean.setRating(solicitudForm.getRating());
				solicitudBean.setReelevancia(solicitudForm.getReelevancia());
				solicitudBean.setClasificacion(solicitudForm.getClasificacion());	
				solicitudBean.setOficinaAltaCod(solicitudForm.getOficinaAltaCod());
				solicitudBean.setOficinaAltaNom(solicitudForm.getOficinaAltaNom());
				solicitudBean.setEjecutivoCtaCod(solicitudForm.getEjecutivoCtaCod());				
				solicitudBean.setEjecutivoCtaNom(solicitudForm.getEjecutivoCtaNom());
				solicitudBean.setCodMultTipoPersona(solicitudForm.getCodMultTipoPersona());
				
				solicitudBean.setRiesgoActual(solicitudForm.getRiesgoActual()!=null?new BigDecimal(solicitudForm.getRiesgoActual().replace(",", "")):null);
				solicitudBean.setRiesgoGrupal(solicitudForm.getRiesgoGrupal()!=null?new BigDecimal(solicitudForm.getRiesgoGrupal().replace(",", "")):null);
				solicitudBean.setFechaIngreso(new Date());	
				solicitudBean.setCodSubanca(solicitudForm.getSubBanca());
				solicitudBean.setCodBanca(new BigDecimal(solicitudForm.getCodBanca()));
				
				solicitudBean.setCodMultMoneda(solicitudForm.getCodMultMoneda());
				solicitudForm.setFlagPopUP(Constant.STR_VACIO);
				MultitablaDetalle multDetalleMoneda = catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_MONEDA, solicitudForm.getCodMultMoneda());
				solicitudBean.setDesMultMoneda((multDetalleMoneda!=null?multDetalleMoneda.getStrValor():Constant.VALOR_NO_ENCONTRADO));				
				solicitudBean.setCodMultMoneda(multDetalleMoneda.getCodMultitabla()!=null?multDetalleMoneda.getCodMultitabla()+Constant.CHAR_GUION+multDetalleMoneda.getCodElemento():null);
				
				IILDPeUsuario usuarioSession;
				usuarioSession = (IILDPeUsuario)request.getSession(true).getAttribute("USUARIO_SESION");
				Long nroSolicitud= new Long(0);
				
				solicitudBean.setPrefijoIngreso(catalogoService.getUsuarioTipo(usuarioSession.getUID())=="1"?"OFICINA":"RIESGOS");
				solicitudBean.setGrupoPersona(solicitudService.validaGrupoPersona(solicitudBean.getCodMultTipoPersona(), solicitudBean.getNumeroDocumento(), solicitudBean.getRating()));
				solicitudBean.setMtoSolicitud(solicitudForm.getMtoTotal()!=null?new BigDecimal(solicitudForm.getMtoTotal().replace(",", "")):new BigDecimal(0));
				solicitudBean.setRiesgoTotal((solicitudBean.getRiesgoActual()!=null?solicitudBean.getRiesgoActual():new BigDecimal(0)).add(solicitudBean.getMtoSolicitud()!=null?solicitudBean.getMtoSolicitud():new BigDecimal(0)));
				solicitudBean.setStrMensaje(solicitudForm.getHdnStrMensaje());
				
				MultitablaDetalle multitablaDetalleBean = null;
				multitablaDetalleBean = catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_ESTADOS_SOLCITUD, Constant.ESTADO_SOLICITUD_PENDIENTE);
				
				if(solicitudForm.getCondicion().equals("ER")){
					if(multitablaDetalleBean!=null){
						solicitudBean.setEstadoSolicitud(Constant.TABLA_ESTADOS_SOLCITUD+
														 Constant.CHAR_GUION+
														 Constant.ESTADO_SOLICITUD_PENDIENTE);
					}
				
				solicitudBean.setEstado(Constant.ESTADO_ACTIVO);
				nroSolicitud = solicitudService.registraSolicitud(solicitudBean, lstSolicitudDetalle);
				
				if(nroSolicitud != new Long(0)){
					
						if(solicitudService.asignacionAutomatica(solicitudBean) == 1){
							solicitudForm = (SolicitudForm)form;	
							solicitudForm.reset(mapping, request);
							request.getSession().removeAttribute("lstDetalleProdSession");
							indMensaje = Constant.MSJ_ALERT;
							strMensaje = "Se ha ingresado la solicitud " + nroSolicitud + ". La c�al fue enviada a riesgos.";	
						}else{
							//-1: no s� encontro usuarios para asignacion, 0:no se encontro banca, 
							solicitudForm = (SolicitudForm)form;	
							solicitudForm.reset(mapping, request);
							request.getSession().removeAttribute("lstDetalleProdSession");
							indMensaje = Constant.MSJ_ALERT;
							strMensaje = "Se ha ingresado la solicitud " + nroSolicitud + ". No se completo la asignaci�n.";	
						}
					
				}else{
					solicitudForm = (SolicitudForm)form;	
					solicitudForm.reset(mapping, request);
					request.getSession().removeAttribute("lstDetalleProdSession");
					indMensaje = Constant.MSJ_ERROR;
					strMensaje = "Sucedio un error en el ingreso de Solicitud.";				
				}
			}else if(solicitudForm.getCondicion().equals("R")){
				solicitudBean.setEstadoSolicitud(Constant.TABLA_ESTADOS_SOLCITUD+Constant.CHAR_GUION+Constant.ESTADO_SOLICITUD_RECHAZADO);
				solicitudBean.setEstado(Constant.ESTADO_INACTIVO);
				nroSolicitud = solicitudService.registraSolicitud(solicitudBean, lstSolicitudDetalle);
				catalogoService.insertSolicitudRechazada(solicitudBean);
				solicitudForm = (SolicitudForm)form;	
				solicitudForm.reset(mapping, request);
				request.getSession().removeAttribute("lstDetalleProdSession");
				indMensaje = Constant.MSJ_OK;
				strMensaje = "Se registro como rechazada la solicitud n�mero : " + nroSolicitud;
			
				
			}else{
				solicitudBean.setEstadoSolicitud(Constant.TABLA_ESTADOS_SOLCITUD + Constant.CHAR_GUION + Constant.ESTADO_SOLICITUD_PENDIENTE);
				
				if(validacionService.metodoEncapsuladoIngresoSolicitud(solicitudBean, lstSolicitudDetalle,usuarioSession.getUID())==1){
							if(controlService.validacionMontosPlazos(solicitudBean, usuarioSession.getUID())==1){
								solicitudBean.setEstado(Constant.ESTADO_ACTIVO);
								nroSolicitud = solicitudService.registraSolicitud(solicitudBean, lstSolicitudDetalle);
								
								if(solicitudService.updateDictaminaEnOficina(solicitudBean)>0){
										indMensaje = Constant.MSJ_ALERT;
										strMensaje = "Se ha ingresado la solicitud " + nroSolicitud + ". Requiere asignaci�n en oficina";
										solicitudForm = (SolicitudForm)form;	
										solicitudForm.reset(mapping, request);
										request.getSession().removeAttribute("lstDetalleProdSession");
								}else{
										
										indMensaje = Constant.MSJ_ERROR;
										strMensaje = "Sucedio un error en el momento de asignar la solicitud " + nroSolicitud + ". a oficina";	
								}
							}else{
								solicitudForm.setFlagPopUP("envioRiesgos");
								if(request.getSession().getAttribute("lstDetalleProdSession")!=null){
									List<SolicitudDetalle> lstDetalleProdSession = (List<SolicitudDetalle>)request.getSession().getAttribute("lstDetalleProdSession");
									request.getSession().setAttribute("lstDetalleProdSession", lstDetalleProdSession);
								}
							}
				}else{
							solicitudForm.setFlagPopUP("envioRiesgos");
								if(request.getSession().getAttribute("lstDetalleProdSession")!=null){
									List<SolicitudDetalle> lstDetalleProdSession = (List<SolicitudDetalle>)request.getSession().getAttribute("lstDetalleProdSession");
									request.getSession().setAttribute("lstDetalleProdSession", lstDetalleProdSession);
								}
				}
			}
			
		 }	
		
		request.setAttribute("indMensaje", indMensaje);
		request.setAttribute("strMensaje", strMensaje);
		
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception IngresoSolicitudAction.insertSolicitud: " + e.getMessage());
			indMensaje = Constant.MSJ_ERROR;
			strMensaje = "Sucedio un error. Comuniquese con el Administrador";
			request.setAttribute("indMensaje", indMensaje);
			request.setAttribute("strMensaje", strMensaje);
			return mapping.findForward(target);
			
		}
		
		return mapping.findForward(target);
	}
	
	public ActionForward showClient(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String indMensaje=null;
		String strMensaje=null;
		String indicadorPopUP=null;
		String target="show";
		
	
		try {
			SolicitudForm solicitudForm = (SolicitudForm)form;
			Solicitud solicitudBean = appPersonasService.invokeClient(solicitudForm.getCodCentral());
			IILDPeUsuario usuarioSesion = null;
			
			if(solicitudBean!=null){
				MultitablaDetalle multDetallePersona = catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_NATURALEZA, solicitudBean.getCodMultTipoPersona());
								
				usuarioSesion = (IILDPeUsuario)request.getSession(true).getAttribute("USUARIO_SESION");
				solicitudBean = appRCCService.invokeDeudaSisFinanciero(solicitudBean);
				solicitudBean = appRCDService.invokeDedudasRCD(solicitudBean);
				
				solicitudForm.setCodCentral(solicitudBean.getCodCentral());
				solicitudForm.setHdnCodCentral(solicitudBean.getCodCentral());
				solicitudForm.setDesMultTipoPersona((multDetallePersona!=null?multDetallePersona.getStrValor():Constant.VALOR_NO_ENCONTRADO));				
				solicitudForm.setCodMultTipoPersona(multDetallePersona.getCodMultitabla()!=null?multDetallePersona.getCodMultitabla()+Constant.CHAR_GUION+multDetallePersona.getCodElemento():null);
				solicitudForm.setNumeroDocumento(solicitudBean.getNumeroDocumento());
				solicitudForm.setDesSolicitante(solicitudBean.getDesSolicitante());								
				solicitudForm.setCodOficina(solicitudBean.getCodOficina());
				solicitudForm.setDesOficina(solicitudBean.getDesOficina());								
				solicitudForm.setGestorCod(solicitudBean.getGestorCod());
				solicitudForm.setGestorNom(solicitudBean.getGestorNom());								
				solicitudForm.setEmpleadorCod(solicitudBean.getEmpleadorCod());
				solicitudForm.setEmpleadorNom(solicitudBean.getEmpleadorNom());				
				solicitudForm.setGerenciaTerritorialCod(solicitudBean.getGerenciaTerritorialCod());
				solicitudForm.setGerenciaTerritorialNom(solicitudBean.getGerenciaTerritorialNom());
				solicitudForm.setDeudaDirecta(UtilGpr.roundUp(solicitudBean.getDeudaDirecta()!=null?solicitudBean.getDeudaDirecta().toString():Constant.RESET_MONTO, 2));
				solicitudForm.setDeudaIndirecta(UtilGpr.roundUp(solicitudBean.getDeudaIndirecta()!=null?solicitudBean.getDeudaIndirecta().toString():Constant.RESET_MONTO, 2));
				solicitudForm.setCastigo(UtilGpr.roundUp(solicitudBean.getCastigo()!=null?solicitudBean.getCastigo().toString():Constant.RESET_MONTO, 2));
				solicitudForm.setDeudaSistemaFinanciero(UtilGpr.roundUp(solicitudBean.getDeudaSistemaFinanciero()!=null?solicitudBean.getDeudaSistemaFinanciero().toString():Constant.RESET_MONTO, 2));
				solicitudForm.setScorating(solicitudBean.getScorating());
				solicitudForm.setRating(solicitudBean.getRating());
				if(solicitudBean.getReelevancia()!=null){
					solicitudForm.setReelevancia(solicitudBean.getReelevancia());
					solicitudForm.setRelevPublica1(solicitudBean.getReelevancia().length()>=3?(catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_RELEVANCIA_PUBLICA, solicitudBean.getReelevancia().substring(0, 3))!=null?catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_RELEVANCIA_PUBLICA, solicitudBean.getReelevancia().substring(0, 3)).getStrValor():Constant.STR_VACIO):Constant.STR_VACIO);
					solicitudForm.setRelevPublica2(solicitudBean.getReelevancia().length()>=6?(catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_RELEVANCIA_PUBLICA, solicitudBean.getReelevancia().substring(3, 6))!=null?catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_RELEVANCIA_PUBLICA, solicitudBean.getReelevancia().substring(03, 6)).getStrValor():Constant.STR_VACIO):Constant.STR_VACIO);
					solicitudForm.setRelevPublica3(solicitudBean.getReelevancia().length()>=9?(catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_RELEVANCIA_PUBLICA, solicitudBean.getReelevancia().substring(6, 9))!=null?catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_RELEVANCIA_PUBLICA, solicitudBean.getReelevancia().substring(6, 9)).getStrValor():Constant.STR_VACIO):Constant.STR_VACIO);
					solicitudForm.setRelevPublica4(solicitudBean.getReelevancia().length()>=12?(catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_RELEVANCIA_PUBLICA, solicitudBean.getReelevancia().substring(9, 12))!=null?catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_RELEVANCIA_PUBLICA, solicitudBean.getReelevancia().substring(9, 12)).getStrValor():Constant.STR_VACIO):Constant.STR_VACIO);
				}
				solicitudForm.setClasificacion(solicitudBean.getClasificacion());	
				solicitudForm.setOficinaAltaCod(usuarioSesion.getBancoOficina().getCodigo());
				solicitudForm.setOficinaAltaNom(usuarioSesion.getBancoOficina().getDescripcion());
				solicitudForm.setEjecutivoCtaCod(usuarioSesion.getUID());
				solicitudForm.setEjecutivoCtaNom(usuarioSesion.getApellido1() + Constant.ESPACIO + usuarioSesion.getApellido2() + Constant.ESPACIO + usuarioSesion.getNombre());
				solicitudForm.setStrFechaIngreso(UtilDate.fechaActual());		
				solicitudForm.setCodBanca(solicitudBean.getCodSubanca()!=null?solicitudBean.getCodSubanca():Constant.RESET_COMBO);
				String riesgoActual = UtilGpr.roundUp(solicitudBean.getDeudaDirecta().add(solicitudBean.getDeudaIndirecta()).add(solicitudBean.getDeudaSistemaFinanciero()).add(solicitudBean.getCastigo()).toString(),2);
				solicitudForm.setRiesgoActual(riesgoActual);
				solicitudForm.setRiesgoTotal(riesgoActual!=null?riesgoActual:Constant.RESET_MONTO);
									
				Banca bancaBean = new Banca();
				bancaBean.setEstado(new BigDecimal(1));
			
				request.getSession().setAttribute("lstBancas", catalogoService.getLstBancaByCriteria(bancaBean));
				request.getSession().setAttribute("lstMonedas", catalogoService.getLstMultitablaDetalle(Constant.TABLA_MONEDA));
			

					strMensaje = controlService.mensajeCondicionCliente(solicitudBean);
					indicadorPopUP = "mostrarContinuar";
					solicitudForm.setStrMensajePopUP(strMensaje);
					solicitudForm.setFlagPopUP(indicadorPopUP);
					request.setAttribute("strMensaje", strMensaje);
				
				}else{
					solicitudForm.setFlagPopUP(Constant.STR_VACIO);
				}
				
			
		} catch (Exception e) {
			logger.error("Exception IngresoSolicitudAction.showClient: " + e.getMessage());
			indMensaje = Constant.MSJ_ERROR;
			strMensaje = "Sucedio un error. Comuniquese con el Administrador";
			request.setAttribute("sindMensaje", indMensaje);
			request.setAttribute("strMensaje", strMensaje);
			return mapping.findForward(target);	
		}
		return mapping.findForward(target);
	}
	
	public List<BancaSub> getLstSubBanca(String codBanca){
		BancaSub bancaSubBean = new BancaSub();
		bancaSubBean.setCodBanca(new BigDecimal(codBanca));
		List<BancaSub> lstSubBanca =  new ArrayList<BancaSub>();
		try {
			BancaSub subanca = new BancaSub();
			subanca.setCodBanca(new BigDecimal(-1));
			subanca.setDescripcion(Constant.SELECCIONE);
			lstSubBanca.add(subanca);
			for (BancaSub bancaSub : catalogoService.getLstSubBancaPorBanca(bancaSubBean)) {
				lstSubBanca.add(bancaSub);
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lstSubBanca;
	}
	
	public List<Producto> consultarAjax(BigDecimal codBanca){
		List<Producto> lstProducto = new ArrayList<Producto>();
		try {
			Producto productoBean = new Producto();
			productoBean.setCodProducto(new BigDecimal(-1));
			productoBean.setDescripcion(Constant.SELECCIONE);
			lstProducto.add(productoBean);
			
			if(codBanca!=null && !(codBanca.compareTo(new BigDecimal(-1))==0)){
				productoBean = new Producto();
				productoBean.setCodBanca(codBanca);
				productoBean.setEstado(Constant.ESTADO_ACTIVO);
				for (Producto producto : catalogoService.getLstProductoByCriteria(productoBean)) {
					lstProducto.add(producto);
				}
			}
		
		} catch (Exception e) {
			logger.error("Exception ProductoAction.consultarAjax: " + e.getMessage());
		} 
		return lstProducto;
	}
	
	public List<Campania> cargarCampaniasAjax(){
		List<Campania> lstCampanias = new ArrayList<Campania>();
		try {
			Campania campaniaBean = new Campania();
			campaniaBean.setCodCampania(new Long(-1));
			campaniaBean.setNombre(Constant.SELECCIONE);
			lstCampanias.add(campaniaBean);
			campaniaBean = new Campania();
			campaniaBean.setEstado(Constant.ESTADO_ACTIVO);
			for (Campania campania : catalogoService.getlstCampaniaByCriteria(campaniaBean)) {
				lstCampanias.add(campania);
			}
		} catch (Exception e) {
			logger.error("Exception ProductoAction.cargarCampaniasAjax: " + e.getMessage());
		} 
		return lstCampanias;
	}
	
	@SuppressWarnings("unchecked")
	public String eliminarProductoAjax(String selecciones){
		HttpServletRequest request = WebContextFactory.get().getHttpServletRequest();	
		ReadProperties readProperties = new ReadProperties();
		List<SolicitudDetalle> lstSolicitudDetalle;
		int sizeIni = 0,sizeFin = 0;
		try {
			if(request.getSession().getAttribute("lstDetalleProdSession")!=null){
				lstSolicitudDetalle = (List<SolicitudDetalle>)request.getSession().getAttribute("lstDetalleProdSession");
				sizeIni =lstSolicitudDetalle.size();
				lstSolicitudDetalle = solicitudService.eliminarProducto(selecciones, lstSolicitudDetalle);
				sizeFin = lstSolicitudDetalle.size();
			}

			if(sizeIni != sizeFin){
				return readProperties.getProperty("etiqueta.success.delete");
			}
			
			return "";
			
		} catch (Exception e) {
			logger.error("Exception ProductoAction.eliminarProductoAjax: " + e.getMessage());
			return readProperties.getProperty("etiqueta.error.delete");
		}
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward guardarProductoAjax(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SolicitudDetalle> lstSolicitudDetalle = new ArrayList<SolicitudDetalle>();
		
		SolicitudDetalle solicitudDetalleBean = new SolicitudDetalle();
		SolicitudForm solicitudForm =(SolicitudForm)form;
		solicitudDetalleBean.setCodProducto(new BigDecimal(solicitudForm.getDesProducto().split(Constant.CHAR_CONCAT)[0]));
		solicitudDetalleBean.setDesProducto(solicitudForm.getDesProducto().split(Constant.CHAR_CONCAT)[1]);
		solicitudDetalleBean.setReferenciaProdBase(solicitudForm.getCodProdBase().split(Constant.CHAR_CONCAT)[0]);
		solicitudDetalleBean.setContratoVinculado(solicitudForm.getContratoVinculado()!=null && !solicitudForm.getContratoVinculado().equals("|")?solicitudForm.getContratoVinculado().split(Constant.CHAR_CONCAT)[1]:null);
		solicitudDetalleBean.setCodPrevaluador(solicitudForm.getCodPreEvaluador());
		if(solicitudForm.getDesCampania().split(Constant.CHAR_CONCAT)[0]!=null){
		solicitudDetalleBean.setCodCampania(new BigDecimal(solicitudForm.getDesCampania().split(Constant.CHAR_CONCAT)[0]).compareTo(new BigDecimal(-1))==0?null:new BigDecimal(solicitudForm.getDesCampania().split(Constant.CHAR_CONCAT)[0]));	
		}
		solicitudDetalleBean.setDesCampania(solicitudForm.getDesCampania().split(Constant.CHAR_CONCAT)[1]!=null || solicitudForm.getDesCampania().split(Constant.CHAR_CONCAT)[0].equals("-1")?"":solicitudForm.getDesCampania().split(Constant.CHAR_CONCAT)[1]);
		
		solicitudDetalleBean.setTipo(solicitudForm.getDesTipo().split(Constant.CHAR_CONCAT)[0].equals(Constant.RESET_COMBO)?null:Constant.TABLA_TIPOS+Constant.CHAR_GUION+solicitudForm.getDesTipo().split(Constant.CHAR_CONCAT)[0]);
		solicitudDetalleBean.setDesTipo(solicitudDetalleBean.getTipo()!=null?solicitudForm.getDesTipo().split(Constant.CHAR_CONCAT)[1]:null);
		
		solicitudDetalleBean.setMtoProducto(new BigDecimal(solicitudForm.getMtoProducto().replace(",", "")));
		solicitudDetalleBean.setPlazo(Integer.parseInt(solicitudForm.getPlazo()));
		solicitudDetalleBean.setMtoGarantia(new BigDecimal(solicitudForm.getMtoGarantia().split(Constant.CHAR_CONCAT)[0]));
		solicitudDetalleBean.setScoring(solicitudForm.getScoring());
		solicitudDetalleBean.setCodPrevaluador(solicitudForm.getCodPreEvaluador());
		solicitudDetalleBean.setMtoTotalRow(solicitudForm.getMtoTotalRow()!=null?new BigDecimal(solicitudForm.getMtoTotalRow().replace(",", "")):null);
		solicitudDetalleBean.setIndice(Integer.parseInt(solicitudForm.getIndice()));
		
		if(request.getSession().getAttribute("lstDetalleProdSession")!=null){
			lstSolicitudDetalle = (List<SolicitudDetalle>)request.getSession().getAttribute("lstDetalleProdSession");
			for (int i=0; i<lstSolicitudDetalle.size(); i++) {
				SolicitudDetalle bean = new SolicitudDetalle();
				bean = lstSolicitudDetalle.get(i);
				if(bean.getIndice()==Integer.parseInt(solicitudForm.getIndice())){
					lstSolicitudDetalle.remove(i);
				}
			}
		    lstSolicitudDetalle.add(solicitudDetalleBean);
		}else{
			lstSolicitudDetalle.add(solicitudDetalleBean);
		}
		request.getSession().setAttribute("lstDetalleProdSession", lstSolicitudDetalle);
		
		
		ReadProperties readProperties = new ReadProperties();
		Map<String, String> mapResult = new HashMap<String, String>();
		mapResult.put("idGenerado", solicitudForm.getIndice());
				
		solicitudForm.setIndice("");
		solicitudForm.setGarantia("");
		solicitudForm.setPlazo("");
		solicitudForm.setMtoProducto(Constant.RESET_MONTO);
		solicitudForm.setGarantia(Constant.RESET_MONTO);
		solicitudForm.setTipo("");
		solicitudForm.setScoring("");
		solicitudForm.setCampania("");
		solicitudForm.setCodPreEvaluador("");
		solicitudForm.setContratoVinculado("");
		solicitudForm.setDesProducto("");
		solicitudForm.setCodProducto("");

		
		if(mapResult.get("msgError") != null) {
			response.setStatus(500);
			response.getWriter().write(readProperties.getProperty(mapResult.get("msgError")));
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
		contratoBean.setCodContrato(Constant.SELECCIONE);
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
		List<MultitablaDetalle> lstTipo = new ArrayList<MultitablaDetalle>();
		try {
				MultitablaDetalle multitablaDetalleBean = new MultitablaDetalle();
				multitablaDetalleBean.setCodElemento("-1");
				multitablaDetalleBean.setStrValor(Constant.SELECCIONE);
				lstTipo.add(multitablaDetalleBean);
				multitablaDetalleBean.setEstado(Constant.ESTADO_ACTIVO);
				for (MultitablaDetalle mtBean : catalogoService.getLstMultitablaDetalle(Constant.TABLA_TIPOS)) {
					lstTipo.add(mtBean);
				}

		} catch (Exception e) {
			logger.error("Exception ProductoAction.consultarAjax: " + e.getMessage());
		} 
		return lstTipo;
	}

	public String getProductoBaseAjax(BigDecimal codProducto){
		Producto productoBean = new Producto();
		try {
			productoBean = catalogoService.selectProductoByPrimaryKey(codProducto);
			if(productoBean!=null){
				if(productoBean.getCodProductoBase()!=null){
					ProductoBase productoBaseBean = catalogoService.selectProductoBasePrimaryKey(productoBean.getCodProductoBase());
				//En lugar del producto Base se obtendra el campo referencia
				return productoBaseBean.getReferencia();}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return "";
	}
	
	public String changeMtoTotalRowAjax(String value, String pMtoGarantia){
		  HttpServletRequest oRequest = WebContextFactory.get().getHttpServletRequest();
		  try {
			return solicitudService.changeMtoTotalRowAjax(value, pMtoGarantia, oRequest);
		} catch (Exception e) {
			logger.info("Exception IngresoSolicitudAction.changeMtoTotalRowAjax: " + e.getMessage());
		}return null;
		
	}
	public String changeMtoTotalAjax(String value){
		  HttpServletRequest oRequest = WebContextFactory.get().getHttpServletRequest();
		  try {
			return solicitudService.changeMtoTotalAjax(value, oRequest);
		} catch (Exception e) {
			logger.info("Exception IngresoSolicitudAction.changeMtoTotalRowAjax: " + e.getMessage());
		}
		  return null;
	}
	public String changeRiesgoTotalAjax(String pdeudaDirecta, 
										String pdeudaIndirecta, 
										String pdeudaCastigo, 
										String pdeudaSisFinanciero, 
										String pOtroRiesgo, 
										String pRiesgoGrupal, 
										String pMtoTotalProd){
		  HttpServletRequest oRequest = WebContextFactory.get().getHttpServletRequest();
		  try {
			return solicitudService.changeRiesgoTotalAjax(pdeudaDirecta, pdeudaIndirecta, pdeudaCastigo, pdeudaSisFinanciero, pOtroRiesgo, pRiesgoGrupal, pMtoTotalProd, oRequest);
		} catch (Exception e) {
			logger.info("Exception IngresoSolicitudAction.changeRiesgoTotalAjax: " + e.getMessage());
		}
		  return null;
	}		

	public String changeOtroRiesgoAjax(String value){
		  HttpServletRequest oRequest = WebContextFactory.get().getHttpServletRequest();
		  try {
			return solicitudService.changeOtroRiesgoAjax(value, oRequest);
		} catch (Exception e) {
			logger.info("Exception IngresoSolicitudAction.changeMtoTotalRowAjax: " + e.getMessage());
		}return null;
	}

	public String  changeRiesgoActualAjax(String pdeudaDirecta, 
										  String pdeudaIndirecta, 
										  String pdeudaCastigo, 
										  String pdeudaSisFinanciero, 
										  String pOtroRiesgo, 
										  String value){
		  HttpServletRequest oRequest = WebContextFactory.get().getHttpServletRequest();
		  try {
			return solicitudService.changeRiesgoActualAjax(pdeudaDirecta, pdeudaIndirecta, pdeudaCastigo, pdeudaSisFinanciero, pOtroRiesgo, value, oRequest);
		} catch (Exception e) {
			logger.info("Exception IngresoSolicitudAction.changeRiesgoActualAjax: " + e.getMessage());
		}return null;
	}	
	
	@SuppressWarnings("unchecked")
	public List<SolicitudDetalle> consultarProductoBancaAjax(){
		 HttpServletRequest oRequest = WebContextFactory.get().getHttpServletRequest();
		 if(oRequest.getSession().getAttribute("lstDetalleProdSession")!=null){
		 return ((List<SolicitudDetalle>)oRequest.getSession().getAttribute("lstDetalleProdSession"));
		 }else
		 {
		 return new ArrayList<SolicitudDetalle>();
		 }
	}
	
	public List<SolicitudDetalle> removeListProduct(){
		 HttpServletRequest oRequest = WebContextFactory.get().getHttpServletRequest();
		 if(oRequest.getSession().getAttribute("lstDetalleProdSession")!=null){
			 oRequest.getSession().removeAttribute("lstDetalleProdSession");
		 }
		 return new ArrayList<SolicitudDetalle>();
	}

	@SuppressWarnings("unchecked")
	public String setIndice(){
	List<SolicitudDetalle> lstSolicitudDetalle;
		HttpServletRequest oRequest = WebContextFactory.get().getHttpServletRequest();
		int indice=0;
		String rpta;
		if(oRequest.getSession().getAttribute("lstDetalleProdSession")!=null){
			lstSolicitudDetalle = (List<SolicitudDetalle>)oRequest.getSession().getAttribute("lstDetalleProdSession");
		
			for (int i = 0; i < lstSolicitudDetalle.size(); i++) {
				indice++;
			}
			indice++;
		}else{
			indice++;
		}
		rpta = String.valueOf(indice);
		return rpta;
	}
	
	public List<SolicitudDetalle> removerListaAjax(){
		 HttpServletRequest oRequest = WebContextFactory.get().getHttpServletRequest();
		 if(oRequest.getSession().getAttribute("lstDetalleProdSession")!=null){
			 oRequest.removeAttribute("lstDetalleProdSession");
			 oRequest.getSession().removeAttribute("lstBancas");
			 oRequest.getSession().removeAttribute("lstMonedas");
		 }
		 return new ArrayList<SolicitudDetalle>();
		 
	}
	
	public int validaCodPreEvaluador(String value){
		return 0;
	}
	
	public String  getGarantiaByProductoAjax(String codProducto){
		Garantia garantiaBean = null;
		try {
			garantiaBean = validacionService.getProductoGarantia(codProducto);
		} catch (Exception e) {
			logger.info("Exception IngresoSolicitudAction.getGarantiaByProductoAjax: " + e.getMessage());
		}
		if(garantiaBean!=null){
			return garantiaBean.getNombre();
		}
		return "";
	}
}