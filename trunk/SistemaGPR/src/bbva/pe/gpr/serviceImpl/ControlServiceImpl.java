package bbva.pe.gpr.serviceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bbva.pe.gpr.bean.CondicionCliente;
import bbva.pe.gpr.bean.DelegacionGerenteOficina;
import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.bean.SolicitudDetalle;
import bbva.pe.gpr.dao.MultitablaDetalleDAO;
import bbva.pe.gpr.dao.SolicitudDetalleDAO;
import bbva.pe.gpr.service.ControlService;
import bbva.pe.gpr.util.Constant;

public class ControlServiceImpl implements ControlService {

	private MultitablaDetalleDAO multitablaDetalleDAO;
	private SolicitudDetalleDAO solicitudDetalleDAO;
	
	public ControlServiceImpl(MultitablaDetalleDAO multitablaDetalleDAO,
							  SolicitudDetalleDAO solicitudDetalleDAO) {
		super();
		this.multitablaDetalleDAO=multitablaDetalleDAO;
		this.solicitudDetalleDAO=solicitudDetalleDAO;
	}
	
	public MultitablaDetalleDAO getMultitablaDetalleDAO() {
		return multitablaDetalleDAO;
	}
	public void setMultitablaDetalleDAO(MultitablaDetalleDAO multitablaDetalleDAO) {
		this.multitablaDetalleDAO = multitablaDetalleDAO;
	}
	public SolicitudDetalleDAO getSolicitudDetalleDAO() {
		return solicitudDetalleDAO;
	}
	public void setSolicitudDetalleDAO(SolicitudDetalleDAO solicitudDetalleDAO) {
		this.solicitudDetalleDAO = solicitudDetalleDAO;
	}
	//TODO si retorna 1 paso validacion si retorna 0 no paso validacion
	public int condicionCliente(CondicionCliente condicionCliente) throws Exception{
		String getBureau = condicionCliente.getBureau();
		String getBbva = condicionCliente.getBbvaa();
		String getSiFinan = condicionCliente.getSistFinan();
		String getRelPub = condicionCliente.getRelevPubli();
		String getInele = condicionCliente.getInelegible();
		if (metodoGenericoCondCliente(Constant.TABLA_BUREAU, getBureau) == 1 && 
			metodoGenericoCondCliente(Constant.TABLA_BBVA, getBbva)   == 1 && 
			metodoGenericoCondCliente(Constant.TABLA_SISTEMA_FINANCIERO, getSiFinan)== 1 && 
			metodoGenericoCondCliente(Constant.TABLA_RELEVANCIA_PUBLICA, getRelPub) == 1 && 
			metodoGenericoCondCliente(Constant.TABLA_INELEGIBLES, getInele)  == 1) {
			return 1;
		} else
			return 0;
	}

	public int metodoGenericoCondCliente(String codMultiTabla, String codValor) throws Exception {
		int valorRetorno=0;
		HashMap<String, String> hashDatosGenericos = new HashMap<String, String>();
		hashDatosGenericos.put("codMultiTabla", codMultiTabla);
		hashDatosGenericos.put("codValor", codValor);
		if (!codValor.equals("")) {
			if (multitablaDetalleDAO.lstMultitablaDetalle(hashDatosGenericos).equals("1")) {
				return valorRetorno=1;
			}
		}
		return valorRetorno;
	}

	//TODO si retorna 1 paso validacion si retorna 0 no paso validacion
	public int validacionMontosPlazos(Solicitud solicitud) {
		//capturar el metodo que me trae de host
		List<DelegacionGerenteOficina> delegacionGerenteOficina = getlstdelegaciones();
		int valorRetorno = 0;
		List<SolicitudDetalle> solicitudDetalle = solicitudDetalleDAO.getListSolicitudDetalleForId(solicitud);
		for (int i = 0; i < solicitudDetalle.size(); i++) {
			for (int y = 0; y < delegacionGerenteOficina.size(); y++) {
				if (solicitudDetalle.get(i).getCodProducto() == delegacionGerenteOficina.get(y).getCodProducto() && 
					Integer.parseInt(solicitudDetalle.get(i).getCodMultPlazo()) <= delegacionGerenteOficina.get(y).getPlazoProducto().intValue()&&
					solicitudDetalle.get(i).getMonto().intValue() <= delegacionGerenteOficina.get(y).getMontoProducto().intValue()
					){
				    valorRetorno++;
				}
			}
		}
		if(solicitudDetalle.size()==valorRetorno){
			return 1;
		}
		    return 0;
	}
	//TODO data de Prueba
	public List<DelegacionGerenteOficina> getlstdelegaciones(){
		DelegacionGerenteOficina delegaciones= new DelegacionGerenteOficina();
		DelegacionGerenteOficina delegaciones02= new DelegacionGerenteOficina();
		DelegacionGerenteOficina delegaciones03= new DelegacionGerenteOficina();
		DelegacionGerenteOficina delegaciones04= new DelegacionGerenteOficina();
			
		delegaciones.setCodProducto(new BigDecimal(1));
		delegaciones.setMontoProducto(new BigDecimal(1478));
		delegaciones.setPlazoProducto(new BigDecimal(1236));
		delegaciones02.setCodProducto(new BigDecimal(2));
		delegaciones02.setMontoProducto(new BigDecimal(1500));
		delegaciones02.setPlazoProducto(new BigDecimal(1300));
		delegaciones03.setCodProducto(new BigDecimal(3));
		delegaciones03.setMontoProducto(new BigDecimal(1500));
		delegaciones03.setPlazoProducto(new BigDecimal(1300));
		delegaciones04.setCodProducto(new BigDecimal(4));
		delegaciones04.setMontoProducto(new BigDecimal(1500));
		delegaciones04.setPlazoProducto(new BigDecimal(1300));
		List<DelegacionGerenteOficina> getLst=new ArrayList<DelegacionGerenteOficina>();
		getLst.add(delegaciones);
		getLst.add(delegaciones02);
		getLst.add(delegaciones03);
		getLst.add(delegaciones04);
		return getLst;	
	}
	
}