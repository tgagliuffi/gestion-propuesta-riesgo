package bbva.pe.gpr.serviceImpl;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import bbva.pe.gpr.bean.Dictamen;
import bbva.pe.gpr.bean.MultitablaDetalle;
import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.bean.SolicitudMensaje;
import bbva.pe.gpr.bean.SolicitudOperacion;
import bbva.pe.gpr.bean.Usuario;
import bbva.pe.gpr.dao.DictamenDAO;
import bbva.pe.gpr.dao.MultitablaDetalleDAO;
import bbva.pe.gpr.dao.SolicitudMensajeDAO;
import bbva.pe.gpr.dao.SolicitudOperacionDAO;
import bbva.pe.gpr.service.DictaminarService;
import bbva.pe.gpr.util.Constant;

public class DictaminarServiceImpl implements DictaminarService {
	
	private static Logger logger = Logger.getLogger(DictaminarServiceImpl.class);
	private DictamenDAO dictamenDAO;
	private SolicitudOperacionDAO solicitudOperacionDAO;
	private SolicitudMensajeDAO solicitudMensajeDAO;
	private MultitablaDetalleDAO multitablaDetalleDAO;

	public DictaminarServiceImpl(DictamenDAO dictamenDAO,
			SolicitudOperacionDAO solicitudOperacionDAO,
			SolicitudMensajeDAO solicitudMensajeDAO,
			MultitablaDetalleDAO multitablaDetalleDAO) {
		super();
		this.dictamenDAO = dictamenDAO;
		this.solicitudOperacionDAO = solicitudOperacionDAO;
		this.solicitudMensajeDAO = solicitudMensajeDAO;
		this.multitablaDetalleDAO = multitablaDetalleDAO;
	}

	public DictamenDAO getDictamenDAO() {
		return dictamenDAO;
	}

	public void setDictamenDAO(DictamenDAO dictamenDAO) {
		this.dictamenDAO = dictamenDAO;
	}

	public SolicitudOperacionDAO getSolicitudOperacionDAO() {
		return solicitudOperacionDAO;
	}

	public void setSolicitudOperacionDAO(SolicitudOperacionDAO solicitudOperacionDAO) {
		this.solicitudOperacionDAO = solicitudOperacionDAO;
	}

	public SolicitudMensajeDAO getSolicitudMensajeDAO() {
		return solicitudMensajeDAO;
	}

	public void setSolicitudMensajeDAO(SolicitudMensajeDAO solicitudMensajeDAO) {
		this.solicitudMensajeDAO = solicitudMensajeDAO;
	}

	public MultitablaDetalleDAO getMultitablaDetalleDAO() {
		return multitablaDetalleDAO;
	}

	public void setMultitablaDetalleDAO(MultitablaDetalleDAO multitablaDetalleDAO) {
		this.multitablaDetalleDAO = multitablaDetalleDAO;
	}

	public Long dictaminarSolicitud(Dictamen dictamenBean) throws Exception {
		Long rspt = null;

		if (dictamenDAO.insert(dictamenBean) != null) {
			ingresaSolicitudOperacion(dictamenBean, Constant.TABLA_PROCESO, Constant.MULT_PROCESO_DICTAMINAR);
			if (dictamenBean.getStrMensaje() != null) {
				solicitudMensajeDAO.insert(seteaMensajeBean(dictamenBean));
			}
			rspt = new Long(1);
		}
		return rspt;
	}

	private void ingresaSolicitudOperacion(Dictamen dictamenBean, String codTabla, String codProceso) throws Exception {
		MultitablaDetalle multDetalleBean = multitablaDetalleDAO.selectByPrimaryKey(codTabla, codProceso);

		SolicitudOperacion solicitudOperacionBean = new SolicitudOperacion();
		solicitudOperacionBean.setCodCentral(dictamenBean.getCodCentral());
		if (multDetalleBean != null) {
			solicitudOperacionBean.setCodMultOperacion(multDetalleBean.getStrValor());
			solicitudOperacionBean.setDesOperacion(multDetalleBean.getStrValor2());
		}

		solicitudOperacionBean.setNroSolicitud(dictamenBean.getNroSolicitud());
		solicitudOperacionBean.setEstado(new BigDecimal(1));
		solicitudOperacionBean.setCodUsuario(dictamenBean.getCodUsuarioSession());
		solicitudOperacionBean.setNomUsuario(dictamenBean.getNomUsuarioSession());
		solicitudOperacionDAO.insert(solicitudOperacionBean);
	}

	public SolicitudMensaje seteaMensajeBean(Dictamen dictamenBean) {
		SolicitudMensaje solicitudMensajeBean = new SolicitudMensaje();
		solicitudMensajeBean.setCodCentral(dictamenBean.getCodCentral());
		solicitudMensajeBean.setCodMensaje(new BigDecimal(1));
		solicitudMensajeBean.setDesMensaje(dictamenBean.getStrMensaje());
		solicitudMensajeBean.setEstado(new BigDecimal(1));
		solicitudMensajeBean.setNroSolicitud(dictamenBean.getNroSolicitud());
		solicitudMensajeBean.setCodUsuario(dictamenBean.getCodUsuarioSession());
		solicitudMensajeBean.setNomUsuario(dictamenBean.getNomUsuarioSession());
		return solicitudMensajeBean;
	}

	public Dictamen findForNroSolictud(Dictamen d) throws Exception {
		return dictamenDAO.findForNumeroSolicitud(d);
	}

	public BigDecimal montoMaxDelegacion(Solicitud s) {
		BigDecimal monto = BigDecimal.ZERO;
		
		try {
			Usuario usuario = dictamenDAO.montoMaxDelegacion(s);
			monto = usuario.getMtoMaxDelegacion();
		} catch(Exception e) {
			logger.error("", e);
		}
		
		return monto;
	}

	public Long delete(Dictamen dictamen) throws Exception {
		Long rspt = null;

		if (dictamenDAO.deleteByPrimaryKey(dictamen) > 0) {
			ingresaSolicitudOperacion(dictamen, Constant.TABLA_PROCESO, Constant.MULT_PROCESO_DICTAMINAR);
			if (dictamen.getStrMensaje() != null) {
				solicitudMensajeDAO.insert(seteaMensajeBean(dictamen));
			}
			rspt = new Long(1);
		}
		
		return rspt;
	}
}
