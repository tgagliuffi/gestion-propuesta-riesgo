package bbva.pe.gpr.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import bbva.pe.gpr.bean.CartasDelegaciones;
import bbva.pe.gpr.bean.Garantia;
import bbva.pe.gpr.bean.GarantiaDetalle;
import bbva.pe.gpr.bean.ProductoDelegacion;
import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.bean.SolicitudDetalle;
import bbva.pe.gpr.dao.CartasRiesgosDAO;
import bbva.pe.gpr.dao.GarantiaDAO;
import bbva.pe.gpr.dao.GarantiaDetalleDAO;
import bbva.pe.gpr.dao.GerenteOficinaDAO;
import bbva.pe.gpr.dao.SolicitudDetalleDAO;
import bbva.pe.gpr.service.ValidacionService;
import bbva.pe.gpr.util.Constant;


public class ValidacionServiceImpl implements ValidacionService {
	private SolicitudDetalleDAO solicitudDetalleDAO;
	private GarantiaDetalleDAO garantiaDetalleDAO;
	private GarantiaDAO  garantiaDAO; 
    private CartasRiesgosDAO cartasRiesgosDAO;
    private GerenteOficinaDAO gerenteOficinaDAO; 

	public ValidacionServiceImpl(SolicitudDetalleDAO solicitudDetalleDAO, GarantiaDetalleDAO garantiaDetalleDAO,GarantiaDAO  garantiaDAO,CartasRiesgosDAO cartasRiesgosDAO,GerenteOficinaDAO gerenteOficinaDAO) {
		super();
		this.solicitudDetalleDAO=solicitudDetalleDAO;
		this.garantiaDetalleDAO=garantiaDetalleDAO;
		this.garantiaDAO=garantiaDAO;
		this.cartasRiesgosDAO=cartasRiesgosDAO;
		this.gerenteOficinaDAO=gerenteOficinaDAO;
	}
	
	public SolicitudDetalleDAO getSolicitudDetalleDAO(){return solicitudDetalleDAO;}
	public void setSolicitudDetalleDAO(SolicitudDetalleDAO solicitudDetalleDAO){this.solicitudDetalleDAO = solicitudDetalleDAO;}
	public GarantiaDetalleDAO getGarantiaDetalleDAO(){return garantiaDetalleDAO;}
	public void setGarantiaDetalleDAO(GarantiaDetalleDAO garantiaDetalleDAO){this.garantiaDetalleDAO = garantiaDetalleDAO;}
	public GarantiaDAO getGarantiaDAO() {return garantiaDAO;}
	public void setGarantiaDAO(GarantiaDAO garantiaDAO) {this.garantiaDAO = garantiaDAO;}
	public CartasRiesgosDAO getCartasRiesgosDAO() {return cartasRiesgosDAO;}
	public void setCartasRiesgosDAO(CartasRiesgosDAO cartasRiesgosDAO) {this.cartasRiesgosDAO = cartasRiesgosDAO;}
	public GerenteOficinaDAO getGerenteOficinaDAO() {return gerenteOficinaDAO;}
	public void setGerenteOficinaDAO(GerenteOficinaDAO gerenteOficinaDAO) {this.gerenteOficinaDAO = gerenteOficinaDAO;}

	public int oficinaConRating(Solicitud solicitud) {
		int valorRetorno=0;
		 List<SolicitudDetalle> getLstSolicitudDetalles = solicitudDetalleDAO.getListSolicitudDetalleForId(solicitud);		
		 	for(SolicitudDetalle solicitudes:getLstSolicitudDetalles){
		 		Garantia garantia=garantiaDAO.getProductoGarantia(solicitudes.getCodProducto().toString());
		 		GarantiaDetalle garantiaDetalle=garantiaDetalleDAO.garantiaDetalle(garantia.getCodGarantia().toString());
		 		BigDecimal codDetalle=garantiaDetalle.getCodConfiguracion();
		 //cartas delegacion
		 		CartasDelegaciones cartasDelegaciones=getCartasOficinaRating(solicitud.getRating(),solicitud.getGestorCod(),codDetalle.toString());    
		 		BigDecimal montoOficina=cartasDelegaciones.getMontos();
		 		int plazoOficina=cartasDelegaciones.getPlazos();
		 		BigDecimal montoSolicitud=solicitudes.getMtoProducto();
		 		int plazoSolicitud=solicitudes.getPlazo();
		 				if((montoOficina.compareTo(montoSolicitud)==0 || montoOficina.compareTo(montoSolicitud)==1) && plazoSolicitud <= plazoOficina){
		 					valorRetorno++;
		 					}
		 				}
		 		if(getLstSolicitudDetalles.size() == valorRetorno) {
		 				return 1;
		 			} else
		 				return 0;			
	}

	public int oficinaSinRating(Solicitud solicitud) {
			
//		Delegacion delegacion ="";//getDelegacion("P013556");
//		List<ProductoDelegacion> getProductos=delegacion.getGetLstProductoDelegacion();
//		int valorRetorno = 0;
//		List<SolicitudDetalle> solicitudDetalle = solicitudDetalleDAO.getListSolicitudDetalleForId(solicitud);		
//		for (int i = 0; i < solicitudDetalle.size(); i++) {
//			for (int y = 0; y < getProductos.size(); y++) {
//				if (solicitudDetalle.get(i).getCodProducto().equals((getProductos.get(y).getCodProducto())) && 
//					solicitudDetalle.get(i).getPlazo()<= getProductos.get(y).getPlazoProducto().intValue()
//					){
//				    valorRetorno++;
//				}
//			}
//		}
//		if(solicitudDetalle.size()==valorRetorno){
//			return 1;
//		}
    	    return 0;
	}

	public int oficinaPersonaNatural(Solicitud solicitud) {
		int valorRetorno=0;
		List<SolicitudDetalle> getLstSolicitudDetalles = solicitudDetalleDAO.getListSolicitudDetalleForId(solicitud);		
			for(SolicitudDetalle solicitudes:getLstSolicitudDetalles){
				Garantia garantia=garantiaDAO.getProductoGarantia(solicitudes.getCodProducto().toString());
				GarantiaDetalle garantiaDetalle=garantiaDetalleDAO.garantiaDetalle(garantia.getCodGarantia().toString());
				BigDecimal codDetalle=garantiaDetalle.getCodConfiguracion();
				CartasDelegaciones cartasDelegaciones=getCartasOficinaPersonaNatural(solicitud.getGestorCod(),codDetalle.toString());    
				BigDecimal montoOficina=cartasDelegaciones.getMontos();
				int plazoOficina=cartasDelegaciones.getPlazos();
				BigDecimal montoSolicitud=solicitudes.getMtoProducto();
				int plazoSolicitud=solicitudes.getPlazo();
						if((montoOficina.compareTo(montoSolicitud)==0 || montoOficina.compareTo(montoSolicitud)==1) && plazoSolicitud <= plazoOficina){
							valorRetorno++;
		 	}
		}
				if(getLstSolicitudDetalles.size() == valorRetorno) {
					return 1;
				} else
					return 0;	
	}

	public int riesgoPorRating(Solicitud solicitud) {
		int valorRetorno=0;
		List<SolicitudDetalle> getLstSolicitudDetalles = solicitudDetalleDAO.getListSolicitudDetalleForId(solicitud);		
			for(SolicitudDetalle solicitudes:getLstSolicitudDetalles){
				//cartas delegacion
				BigDecimal montoRiesgos=cartasRiesgosDAO.montoDelegacionPorRating(solicitud.getGestorCod(), solicitud.getRating(),solicitud.getGrupoPersona());
				BigDecimal montoSolicitud=solicitudes.getMtoProducto();
				 if((montoRiesgos.compareTo(montoSolicitud)==0 || montoRiesgos.compareTo(montoSolicitud)==1)){
							valorRetorno++;
				}
			}
			    if(getLstSolicitudDetalles.size() == valorRetorno) {
			    	return 1;
			    } else
			    	return 0;
	}

	public int riesgoSinRating(Solicitud solicitud) {
		int valorRetorno=0;
		List<SolicitudDetalle> getLstSolicitudDetalles = solicitudDetalleDAO.getListSolicitudDetalleForId(solicitud);		
			for(SolicitudDetalle solicitudes:getLstSolicitudDetalles){
				//cartas delegacion
				BigDecimal montoRiesgos=solicitudes.getMtoProducto();				
				BigDecimal montoSolicitud=solicitudes.getMtoProducto();
				if((montoRiesgos.compareTo(montoSolicitud)==0 || montoRiesgos.compareTo(montoSolicitud)==1)){
							valorRetorno++;
				}
			}
				if (getLstSolicitudDetalles.size() == valorRetorno) {
					return 1;
				} else
					return 0;	
	}

	public int riesgoPersonaNatural(Solicitud solicitud) {
		int valorRetorno=0;
		List<SolicitudDetalle> getLstSolicitudDetalles = solicitudDetalleDAO.getListSolicitudDetalleForId(solicitud);		
		List<ProductoDelegacion> getLstProducto=cartasRiesgosDAO.getDelegacionPersonaNatural(solicitud.getGestorCod(), solicitud.getGrupoPersona());
				for (int i = 0; i < getLstSolicitudDetalles.size(); i++) {
					for (int y = 0; y < getLstProducto.size(); y++) {
						if (getLstSolicitudDetalles.get(i).getCodProducto().equals((getLstProducto.get(y).getCodProducto())) && 
							(getLstSolicitudDetalles.get(i).getMtoProducto().compareTo(getLstProducto.get(y).getMontoProducto())==0 ||
							getLstSolicitudDetalles.get(i).getMtoProducto().compareTo(getLstProducto.get(y).getMontoProducto())==1)){
						    valorRetorno++;
						}
				   }
		     }
		if(getLstSolicitudDetalles.size() == valorRetorno) {
			    return 1;
			} else
			    return 0;	
	}

	public CartasDelegaciones getCartasOficinaRating(String rating,String codUsuario,String idDetalle){
			CartasDelegaciones cartasDelegaciones= new CartasDelegaciones();
			cartasDelegaciones.setMontos(new BigDecimal("1455"));
			cartasDelegaciones.setPlazos(147850);
			return cartasDelegaciones;
	}
	
	public CartasDelegaciones getCartasOficinaPersonaNatural(String idDetalle,String codUsuario){
			CartasDelegaciones cartasDelegaciones= new CartasDelegaciones();
			cartasDelegaciones.setMontos(new BigDecimal("1455"));
			cartasDelegaciones.setPlazos(147850);
			return cartasDelegaciones;
	}

	public int metodoEncapsulado(Solicitud solicitud) {
	int valorRetorno=0;
		if(gerenteOficinaDAO.getValidarUsuario(solicitud.getGestorCod()).equals("1")){
			//oficina			
			if(solicitud.getGrupoPersona().equals(Constant.GRUPO_CON_RATING)){
			valorRetorno=riesgoPorRating(solicitud);	
			}else if(solicitud.getGrupoPersona().equals(Constant.GRUPO_SIN_RATING)){
			valorRetorno=riesgoSinRating(solicitud);					
			}else if(solicitud.getGrupoPersona().equals(Constant.GRUPO_PER_NATUAL)){
			valorRetorno=riesgoPersonaNatural(solicitud);	
			}
		}else{
			//riesgos
			if(solicitud.getGrupoPersona().equals(Constant.GRUPO_CON_RATING)){
			valorRetorno=riesgoPorRating(solicitud);	
			}else if(solicitud.getGrupoPersona().equals(Constant.GRUPO_SIN_RATING)){
			valorRetorno=riesgoSinRating(solicitud);					
			}else if(solicitud.getGrupoPersona().equals(Constant.GRUPO_PER_NATUAL)){
			valorRetorno=riesgoPersonaNatural(solicitud);	
			}
		}
			return valorRetorno;
	}
}