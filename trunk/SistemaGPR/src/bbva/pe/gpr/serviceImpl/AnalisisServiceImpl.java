package bbva.pe.gpr.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import bbva.pe.gpr.bean.Analisis;
import bbva.pe.gpr.bean.MultitablaDetalle;
import bbva.pe.gpr.bean.SolicitudMensaje;
import bbva.pe.gpr.bean.SolicitudOperacion;
import bbva.pe.gpr.dao.AnalisisDAO;
import bbva.pe.gpr.dao.MultitablaDetalleDAO;
import bbva.pe.gpr.dao.SolicitudMensajeDAO;
import bbva.pe.gpr.dao.SolicitudOperacionDAO;
import bbva.pe.gpr.service.AnalisiService;
import bbva.pe.gpr.util.Constant;

public class AnalisisServiceImpl implements AnalisiService {
	private AnalisisDAO analisisDAO;
	private SolicitudOperacionDAO solicitudOperacionDAO;
	private SolicitudMensajeDAO solicitudMensajeDAO;
	private MultitablaDetalleDAO multitablaDetalleDAO;

	public AnalisisServiceImpl(AnalisisDAO analisisDAO,
			SolicitudOperacionDAO solicitudOperacionDAO,
			SolicitudMensajeDAO solicitudMensajeDAO,
			MultitablaDetalleDAO multitablaDetalleDAO) {
		super();
		this.analisisDAO = analisisDAO;
		this.solicitudOperacionDAO = solicitudOperacionDAO;
		this.solicitudMensajeDAO = solicitudMensajeDAO;
		this.multitablaDetalleDAO = multitablaDetalleDAO;
	}

	public AnalisisDAO getAnalisiDao() {
		return analisisDAO;
	}

	public void setAnalisiDao(AnalisisDAO analisiDao) {
		this.analisisDAO = analisiDao;
	}

	public AnalisisDAO getAnalisisDAO() {
		return analisisDAO;
	}

	public void setAnalisisDAO(AnalisisDAO analisisDAO) {
		this.analisisDAO = analisisDAO;
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

	public Long insertarAnalisis(Analisis analisisBean) throws Exception {
		Long rpst = null;
		List<Analisis> list = analisisDAO.selectByAnalisis(analisisBean);
		if(list != null && list.size() == 0) {
			if (analisisDAO.insert(analisisBean) != null) {
				ingresaSolicitudOperacion(analisisBean, Constant.TABLA_PROCESO, Constant.MULT_PROCESO_ANALIZAR);
				if (analisisBean.getStrMensaje() != null) {
					solicitudMensajeDAO.insert(seteaMensajeBean(analisisBean));
				}
				rpst = new Long(1);
			}
		} else {
			rpst = new Long(-1);
		}
		return rpst;
	}

	private void ingresaSolicitudOperacion(Analisis analisisBean, String codTabla, String codProceso) throws Exception {
		MultitablaDetalle multDetalleBean = multitablaDetalleDAO.selectByPrimaryKey(codTabla, codProceso);

		SolicitudOperacion solicitudOperacionBean = new SolicitudOperacion();
		solicitudOperacionBean.setCodCentral(analisisBean.getCodCentral());
		if (multDetalleBean != null) {
			solicitudOperacionBean.setCodMultOperacion(multDetalleBean.getStrValor());
			solicitudOperacionBean.setDesOperacion(multDetalleBean.getStrValor2());
		}

		solicitudOperacionBean.setNroSolicitud(analisisBean.getNroSolicitud());
		solicitudOperacionBean.setEstado(new BigDecimal(1));
		solicitudOperacionDAO.insert(solicitudOperacionBean);
	}

	private SolicitudMensaje seteaMensajeBean(Analisis analisisBean) {
		SolicitudMensaje solicitudMensajeBean = new SolicitudMensaje();
		solicitudMensajeBean.setCodCentral(analisisBean.getCodCentral());
		solicitudMensajeBean.setCodMensaje(new BigDecimal(1));
		solicitudMensajeBean.setDesMensaje(analisisBean.getStrMensaje());
		solicitudMensajeBean.setEstado(new BigDecimal(1));
		solicitudMensajeBean.setNroSolicitud(analisisBean.getNroSolicitud());
		return solicitudMensajeBean;
	}

	public Long eliminarAnalisis(Analisis record) throws Exception {
		Long rpst = null;
		if (analisisDAO.deleteByPrimaryKey(record) > 0) {
			ingresaSolicitudOperacion(record, Constant.TABLA_PROCESO, Constant.MULT_PROCESO_ANALIZAR);
			if (record.getStrMensaje() != null) {
				solicitudMensajeDAO.insert(seteaMensajeBean(record));
			}
			rpst = new Long(1);
		}
		return rpst;
	}

	public Long actualizarAnalisis(Analisis record) throws Exception {
		return null;
	}

	public List<Analisis> buscarAnalisis(Analisis record) throws Exception {
		return analisisDAO.selectByNroSolicitud(record);
	}
}