package bbva.pe.gpr.serviceImpl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import bbva.pe.gpr.bean.CartasDelegaciones;
import bbva.pe.gpr.bean.Garantia;
import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.bean.SolicitudDetalle;
import bbva.pe.gpr.dao.GarantiaDetalleDAO;
import bbva.pe.gpr.dao.SolicitudDetalleDAO;
import bbva.pe.gpr.service.ValidacionService;


public class ValidacionServiceImpl implements ValidacionService {
	private SolicitudDetalleDAO solicitudDetalleDAO;
	private GarantiaDetalleDAO garantiaDetalleDAO;
	
	public ValidacionServiceImpl(SolicitudDetalleDAO solicitudDetalleDAO, GarantiaDetalleDAO garantiaDetalleDAO) {
		super();
		this.solicitudDetalleDAO=solicitudDetalleDAO;
		this.garantiaDetalleDAO=garantiaDetalleDAO;
	}
	
	
	public SolicitudDetalleDAO getSolicitudDetalleDAO(){return solicitudDetalleDAO;}
	public void setSolicitudDetalleDAO(SolicitudDetalleDAO solicitudDetalleDAO){this.solicitudDetalleDAO = solicitudDetalleDAO;}
	public GarantiaDetalleDAO getGarantiaDetalleDAO(){return garantiaDetalleDAO;}
	public void setGarantiaDetalleDAO(GarantiaDetalleDAO garantiaDetalleDAO){this.garantiaDetalleDAO = garantiaDetalleDAO;}

	public int oficinaConRating(Solicitud solicitud) {
		CartasDelegaciones getlistCartaDelegaciones= new CartasDelegaciones();
		int valorRetorno = 0;
		int procentaje = getlistCartaDelegaciones.getPorcentaje();
		List<SolicitudDetalle> getLstSolicitudDetalles = solicitudDetalleDAO.getListSolicitudDetalleForId(solicitud);
		for (SolicitudDetalle getDetalles : getLstSolicitudDetalles) {
			HashMap<String, BigDecimal> getLstProdGaranDetalle = garantiaDetalleDAO.getlstProdGaranDeta(getDetalles);
			BigDecimal montoDetalle = getLstProdGaranDetalle.get("MONTO");
			BigDecimal plazoDetalle = getLstProdGaranDetalle.get("PLAZOS");
			//TODO:CRISTIAN VALIDA PLEASE
			BigDecimal montoSolicitud = getDetalles.getMtoProducto();
			int plazoSolicitud = getDetalles.getPlazo();
			BigDecimal montoAEvaluar = montoDetalle.multiply(new BigDecimal(procentaje/100)) ;
			//BigDecimal montoAEvaluar = montoDetalle.intValue() * procentaje/100;
		
			//if (montoAEvaluar >= montoSolicitud && plazoSolicitud <= plazoDetalle.intValue()) {
				//valorRetorno++;}
			if((montoAEvaluar.compareTo(montoSolicitud)==0 || montoAEvaluar.compareTo(montoSolicitud)==1) && plazoSolicitud <= plazoDetalle.intValue()){
				valorRetorno++;}
		}
		if (getLstSolicitudDetalles.size() == valorRetorno) {
			return 1;
		} else
			return 0;
	}


	public int oficinaSinRating(Solicitud solicitud) {
		int valorRetorno = 0;
		BigDecimal montoDetalle = new BigDecimal(0);
		List<SolicitudDetalle> getLstSolicitudDetalles = solicitudDetalleDAO.getListSolicitudDetalleForId(solicitud);
		for (SolicitudDetalle getSolicitud : getLstSolicitudDetalles) {
			BigDecimal montoSolicitud = getSolicitud.getMtoProducto();
			int plazosSolicitud = getSolicitud.getPlazo();
			HashMap<String, BigDecimal> getLstProdGaranDetalle = garantiaDetalleDAO.getlstProdGaranDeta(getSolicitud);
			BigDecimal plazoDetalle = getLstProdGaranDetalle.get("PLAZOS");
			if (getSolicitud.getPlazoGarantia().equals("1")) {
				montoDetalle = getLstProdGaranDetalle.get("MONTO_CON_GARANTIA");
			} else
				montoDetalle = getLstProdGaranDetalle.get("MONTO");
			//TODO: VALIDA CRISTIAN
			/*
			 * if (montoSolicitud <=  montoDetalle&& plazosSolicitud <= plazoDetalle.intValue()) {
				valorRetorno++;}
			 * */
			if((montoSolicitud.compareTo(montoDetalle)==-1 || montoSolicitud.compareTo(montoDetalle)==0) && plazosSolicitud <= plazoDetalle.intValue()){
				valorRetorno++;
			}
		}
		if (getLstSolicitudDetalles.size() == valorRetorno) {
			return 1;
		}
		return 0;
	}

	public int oficinaPersonaNatural(Solicitud solicitud) {
		int retornoValidacion = 0;
		List<SolicitudDetalle> getLstSolicitudDetalles = solicitudDetalleDAO.getListSolicitudDetalleForId(solicitud);
		for (SolicitudDetalle getSolicitud : getLstSolicitudDetalles) {
			BigDecimal montoSolicitud = getSolicitud.getMtoProducto();
			int plazosSolicitud = getSolicitud.getPlazo();
			HashMap<String, BigDecimal> getLstProdGaranDetalle = garantiaDetalleDAO.getlstProdGaranDeta(getSolicitud);
			BigDecimal montoDetalle = getLstProdGaranDetalle.get("MONTO");
			BigDecimal plazoDetalle = getLstProdGaranDetalle.get("PLAZOS");
			//TODO: VALIDA CRISTIAN
			/*if (montoSolicitud <= montoDetalle.intValue() && plazosSolicitud <= plazoDetalle.intValue()) {
				retornoValidacion++;}
			 * 
			 * */
			if ((montoSolicitud.compareTo(montoDetalle)==-1 || montoSolicitud.compareTo(montoDetalle)==0) && plazosSolicitud <= plazoDetalle.intValue()) {
				retornoValidacion++;}
		}
		if (getLstSolicitudDetalles.size() == retornoValidacion) {
			return 1;
		}
		return 0;
	}


	public int riesgoPorRating(Solicitud solicitud) {
		int valorRetorno = 0;
		CartasDelegaciones getlistCartaDelegaciones= new CartasDelegaciones();
		BigDecimal montoPivotCartas = getlistCartaDelegaciones.getMontoPivot();
		int porcentaje = getlistCartaDelegaciones.getPorcentaje();
		//TODO: VALIDA CRISTIAN
		BigDecimal montoDetalle = montoPivotCartas.multiply(new BigDecimal(porcentaje/100));
		int plazoDetalle = getlistCartaDelegaciones.getPlazos();
		List<SolicitudDetalle> getLstSolicitudDetalles = solicitudDetalleDAO.getListSolicitudDetalleForId(solicitud);
		for (SolicitudDetalle getSolicitud : getLstSolicitudDetalles) {
			BigDecimal montoSolicitud = getSolicitud.getMtoProducto();
			int plazosSolicitud = getSolicitud.getPlazo();
			//TODO: VALIDA CRISTIAN
			/*if (montoSolicitud <= montoDetalle && plazoSolicitud <= plazoDetalle) {
				valorRetorno++;}*/
			if ((montoSolicitud.compareTo(montoDetalle)==-1 || montoSolicitud.compareTo(montoDetalle)==0)  && plazosSolicitud <= plazoDetalle) {
				valorRetorno++;}
		}
		if (getLstSolicitudDetalles.size() == valorRetorno) {
			return 1;
		}
		return 0;
	}

	public int riesgoSinRating(Solicitud solicitud) {
		int valorRetorno = 0;
		CartasDelegaciones getlistCartaDelegaciones= new CartasDelegaciones();
		BigDecimal montoDetalle = getlistCartaDelegaciones.getMontos();
		int plazoDetalle = getlistCartaDelegaciones.getPlazos();
		List<SolicitudDetalle> getLstSolicitudDetalles = solicitudDetalleDAO.getListSolicitudDetalleForId(solicitud);
		for (SolicitudDetalle getSolicitud : getLstSolicitudDetalles) {
			BigDecimal montoSolicitud = getSolicitud.getMtoProducto();
			int plazosSolicitud = getSolicitud.getPlazo();
			//TODO: VALIDA CRISITIAN
			/*
			 * if (montoSolicitud < montoDetalle && plazoSolicitud < plazoDetalle) {
				valorRetorno++;}
			 * */
			if (montoSolicitud.compareTo(montoDetalle)==-1 && plazosSolicitud < plazoDetalle) {
				valorRetorno++;}
		}
		if (getLstSolicitudDetalles.size() == valorRetorno) {
			return 1;
		}
		return 0;
	}


	public int riesgoPersonaNatural(Solicitud solicitud) {
		int valorRetorno = 0;
		CartasDelegaciones getlistCartaDelegaciones= new CartasDelegaciones();
		BigDecimal montoDetalle = getlistCartaDelegaciones.getMontos();
		int plazoDetalle = getlistCartaDelegaciones.getPlazos();
		List<SolicitudDetalle> getLstSolicitudDetalles = solicitudDetalleDAO.getListSolicitudDetalleForId(solicitud);
		for (SolicitudDetalle getSolicitud : getLstSolicitudDetalles) {
			BigDecimal montoSolicitud = getSolicitud.getMtoProducto();
			int plazosSolicitud = getSolicitud.getPlazo();
			//TODO: VALIDA XTIAN
			/*if (montoSolicitud <= montoDetalle && plazoSolicitud <= plazoDetalle) {
				valorRetorno++;}
			 * */
			if ((montoSolicitud.compareTo(montoDetalle)==-1 || montoSolicitud.compareTo(montoDetalle)==0) && plazosSolicitud <= plazoDetalle) {
				valorRetorno++;}
		}
		if (getLstSolicitudDetalles.size() == valorRetorno) {
			return 1;
		}
		return 0;
	}


	public Garantia getProductoGarantia(String idProducto) {
        
		return null;
	}

	
}