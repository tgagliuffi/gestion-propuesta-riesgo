package bbva.pe.gpr.serviceImpl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bbva.pe.gpr.bean.CartasDelegaciones;
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

	// TODO OFICINAS retorna 1 paso validacion retorna 0 no paso validacion
	public int oficinaConRating(Solicitud solicitud, CartasDelegaciones getlstCartaDelegaciones) {
		int valorRetorno = 0;
		int procentaje = getlstCartaDelegaciones.getPorcentaje();
		List<SolicitudDetalle> getLstSolicitudDetalles = solicitudDetalleDAO.getListSolicitudDetalleForId(solicitud);
		for (SolicitudDetalle getDetalles : getLstSolicitudDetalles) {
			HashMap<String, BigDecimal> getLstProdGaranDetalle = garantiaDetalleDAO.getlstProdGaranDeta(getDetalles);
			BigDecimal montoDetalle = getLstProdGaranDetalle.get("MONTO");
			BigDecimal plazoDetalle = getLstProdGaranDetalle.get("PLAZOS");
			int montoSolicitud = Integer.parseInt(getDetalles.getMonto().toString());
			int plazoSolicitud = Integer.parseInt(getDetalles.getCodMultPlazo());
			double montoAEvaluar = montoDetalle.intValue() * procentaje/100;
			if (montoAEvaluar >= montoSolicitud && plazoSolicitud <= plazoDetalle.intValue()) {
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
			int montoSolicitud = Integer.parseInt(getSolicitud.getMonto().toString());
			int plazosSolicitud = Integer.parseInt(getSolicitud.getCodMultPlazo());
			HashMap<String, BigDecimal> getLstProdGaranDetalle = garantiaDetalleDAO.getlstProdGaranDeta(getSolicitud);
			BigDecimal plazoDetalle = getLstProdGaranDetalle.get("PLAZOS");
			if (getSolicitud.getPlazoGarantia().equals("1")) {
				montoDetalle = getLstProdGaranDetalle.get("MONTO_CON_GARANTIA");
			} else
				montoDetalle = getLstProdGaranDetalle.get("MONTO");
			if (montoSolicitud <= montoDetalle.intValue() && plazosSolicitud <= plazoDetalle.intValue()) {
				valorRetorno++;}
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
			int montoSolicitud = Integer.parseInt(getSolicitud.getMonto().toString());
			int plazosSolicitud = Integer.parseInt(getSolicitud.getCodMultPlazo());
			HashMap<String, BigDecimal> getLstProdGaranDetalle = garantiaDetalleDAO.getlstProdGaranDeta(getSolicitud);
			BigDecimal montoDetalle = getLstProdGaranDetalle.get("MONTO");
			BigDecimal plazoDetalle = getLstProdGaranDetalle.get("PLAZOS");
			if (montoSolicitud <= montoDetalle.intValue() && plazosSolicitud <= plazoDetalle.intValue()) {
				retornoValidacion++;}
		}
		if (getLstSolicitudDetalles.size() == retornoValidacion) {
			return 1;
		}
		return 0;
	}

	// TODO RIESGOS
	public int riesgoPorRating(Solicitud solicitud, CartasDelegaciones getlistCartaDelegaciones) {
		int valorRetorno = 0;
		int montoPivotCartas = getlistCartaDelegaciones.getMontoPivot();
		int porcentaje = getlistCartaDelegaciones.getPorcentaje();
		int montoDetalle = (montoPivotCartas * porcentaje/100);
		int plazoDetalle = getlistCartaDelegaciones.getPlazos();
		List<SolicitudDetalle> getLstSolicitudDetalles = solicitudDetalleDAO.getListSolicitudDetalleForId(solicitud);
		for (SolicitudDetalle getSolicitud : getLstSolicitudDetalles) {
			int montoSolicitud = Integer.parseInt(getSolicitud.getMonto().toString());
			int plazoSolicitud = Integer.parseInt(getSolicitud.getCodMultPlazo());
			if (montoSolicitud <= montoDetalle && plazoSolicitud <= plazoDetalle) {
				valorRetorno++;}
		}
		if (getLstSolicitudDetalles.size() == valorRetorno) {
			return 1;
		}
		return 0;
	}

	public int riesgoSinRating(Solicitud solicitud, CartasDelegaciones getlistCartaDelegaciones) {
		int valorRetorno = 0;
		int montoDetalle = getlistCartaDelegaciones.getMontos();
		int plazoDetalle = getlistCartaDelegaciones.getPlazos();
		List<SolicitudDetalle> getLstSolicitudDetalles = solicitudDetalleDAO.getListSolicitudDetalleForId(solicitud);
		for (SolicitudDetalle getSolicitud : getLstSolicitudDetalles) {
			int montoSolicitud = Integer.parseInt(getSolicitud.getMonto().toString());
			int plazoSolicitud = Integer.parseInt(getSolicitud.getCodMultPlazo());
			if (montoSolicitud < montoDetalle && plazoSolicitud < plazoDetalle) {
				valorRetorno++;}
		}
		if (getLstSolicitudDetalles.size() == valorRetorno) {
			return 1;
		}
		return 0;
	}

	public int riesgoPersonaNatural(Solicitud solicitud, CartasDelegaciones getlistCartaDelegaciones) {
		int valorRetorno = 0;
		int montoDetalle = getlistCartaDelegaciones.getMontos();
		int plazoDetalle = getlistCartaDelegaciones.getPlazos();
		List<SolicitudDetalle> getLstSolicitudDetalles = solicitudDetalleDAO.getListSolicitudDetalleForId(solicitud);
		for (SolicitudDetalle getSolicitud : getLstSolicitudDetalles) {
			int montoSolicitud = Integer.parseInt(getSolicitud.getMonto().toString());
			int plazoSolicitud = Integer.parseInt(getSolicitud.getCodMultPlazo());
			if (montoSolicitud <= montoDetalle && plazoSolicitud <= plazoDetalle) {
				valorRetorno++;}
		}
		if (getLstSolicitudDetalles.size() == valorRetorno) {
			return 1;
		}
		return 0;
	}

	@Override
	public int oficinaConRating(Solicitud solicitud) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int riesgoPorRating(Solicitud solicitud) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int riesgoSinRating(Solicitud solicitud) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int riesgoPersonaNatural(Solicitud solicitud) {
		// TODO Auto-generated method stub
		return 0;
	}
}