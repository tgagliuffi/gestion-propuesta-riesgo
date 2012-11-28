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

	public int oficinaConRating(Solicitud solicitud,String codUsuario) {
		int valorRetorno=0,plazoOficina=0;
		BigDecimal codDetalle=BigDecimal.ZERO;
		BigDecimal montoOficina=BigDecimal.ZERO;
		 List<SolicitudDetalle> getLstSolicitudDetalles = solicitudDetalleDAO.getListSolicitudDetalleForId(solicitud);
		 if(getLstSolicitudDetalles.size()<=0){
			 return 0;
		 }
		 	for(SolicitudDetalle solicitudes:getLstSolicitudDetalles){
		 		Garantia garantia=garantiaDAO.getProductoGarantia(solicitudes.getCodProducto().toString());
		 		if(garantia!=null){
		 			GarantiaDetalle garantiaDetalle=garantiaDetalleDAO.garantiaDetalle(garantia.getCodGarantia().toString());
			 		codDetalle=garantiaDetalle.getCodConfiguracion();
			 		CartasDelegaciones cartasDelegaciones=getCartasOficinaRating(solicitud.getRating(),codUsuario,codDetalle.toString());    
			 		montoOficina=(cartasDelegaciones.getMontos()!=null?cartasDelegaciones.getMontos():BigDecimal.ZERO);
			 		plazoOficina=(cartasDelegaciones.getPlazos());
		 		}
		 //cartas delegacion
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

	public int oficinaSinRating(Solicitud solicitud,String codUsuario) {
			
		int valorRetorno=0,plazoOficina=0;
		BigDecimal codDetalle=BigDecimal.ZERO;
		BigDecimal montoOficina=BigDecimal.ZERO;
		 List<SolicitudDetalle> getLstSolicitudDetalles = solicitudDetalleDAO.getListSolicitudDetalleForId(solicitud);		
		 	for(SolicitudDetalle solicitudes:getLstSolicitudDetalles){
		 		Garantia garantia=garantiaDAO.getProductoGarantia(solicitudes.getCodProducto().toString());
		 		if(garantia!=null){
		 			GarantiaDetalle garantiaDetalle=garantiaDetalleDAO.garantiaDetalle(garantia.getCodGarantia().toString());
			 		codDetalle=garantiaDetalle.getCodConfiguracion();
			 		CartasDelegaciones cartasDelegaciones=getCartasOficinaRating(solicitud.getRating(),codUsuario,codDetalle.toString());    
			 		montoOficina=(cartasDelegaciones.getMontos()!=null?cartasDelegaciones.getMontos():BigDecimal.ZERO);
			 		plazoOficina=(cartasDelegaciones.getPlazos());
		 		}
		 //cartas delegacion
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

	public int oficinaPersonaNatural(Solicitud solicitud,String codUsuario) {
		int valorRetorno=0,plazoOficina=0;
		BigDecimal codDetalle=BigDecimal.ZERO;
		BigDecimal montoOficina=BigDecimal.ZERO;
		List<SolicitudDetalle> getLstSolicitudDetalles = solicitudDetalleDAO.getListSolicitudDetalleForId(solicitud);		
			for(SolicitudDetalle solicitudes:getLstSolicitudDetalles){
				Garantia garantia=garantiaDAO.getProductoGarantia(solicitudes.getCodProducto().toString());
				if(garantia!=null){
		 			GarantiaDetalle garantiaDetalle=garantiaDetalleDAO.garantiaDetalle(garantia.getCodGarantia().toString());
			 		codDetalle=garantiaDetalle.getCodConfiguracion();
			 		CartasDelegaciones cartasDelegaciones=getCartasOficinaRating(solicitud.getRating(),codUsuario,codDetalle.toString());    
			 		montoOficina=(cartasDelegaciones.getMontos()!=null?cartasDelegaciones.getMontos():BigDecimal.ZERO);
			 		plazoOficina=(cartasDelegaciones.getPlazos());
		 		}
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

	public int riesgoPorRating(Solicitud solicitud,String codUsuario) {
		int valorRetorno=0;
		List<SolicitudDetalle> getLstSolicitudDetalles = solicitudDetalleDAO.getListSolicitudDetalleForId(solicitud);
		if (getLstSolicitudDetalles.size()<=0){
			return 0;
		}
			for(SolicitudDetalle solicitudes:getLstSolicitudDetalles){
				//cartas delegacion
				BigDecimal montoRiesgos=cartasRiesgosDAO.montDelegacionRating(codUsuario,solicitud.getRating());
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

	public int riesgoSinRating(Solicitud solicitud,String codUsuario) {
		int valorRetorno=0;
		List<SolicitudDetalle> getLstSolicitudDetalles = solicitudDetalleDAO.getListSolicitudDetalleForId(solicitud);
		if (getLstSolicitudDetalles.size()<=0){
			return 0;
		}
			for(SolicitudDetalle solicitudes:getLstSolicitudDetalles){
				//cartas delegacion
				BigDecimal montoRiesgos=cartasRiesgosDAO.montDelegacionSinRating(codUsuario,solicitud.getGrupoPersona());			
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

	public int riesgoPersonaNatural(Solicitud solicitud,String codUsuario) {
		int valorRetorno=0;
		List<SolicitudDetalle> getLstSolicitudDetalles = solicitudDetalleDAO.getListSolicitudDetalleForId(solicitud);
		if (getLstSolicitudDetalles.size()<=0){
			return 0;
		}
		List<ProductoDelegacion> getLstProducto=cartasRiesgosDAO.getDelegacionPersonaNatural(codUsuario);
		if (getLstProducto.size()<=0){
			return 0;
		}
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

	public int riesgoPorRatingIngresoSolicitud(Solicitud solicitud,List<SolicitudDetalle> solicitudDetalle, String codUsuario) {	
		int valorRetorno=0;
		//cartas delegacion
		BigDecimal montoRiesgos=cartasRiesgosDAO.montDelegacionRating(codUsuario,solicitud.getRating());
		for(SolicitudDetalle solicitudes:solicitudDetalle){
		BigDecimal montoSolicitud=solicitudes.getMtoProducto();
		if((montoRiesgos.compareTo(montoSolicitud)==0 || montoRiesgos.compareTo(montoSolicitud)==1)){
							valorRetorno++;
				}
			}
			    if(solicitudDetalle.size() == valorRetorno) {
			    	return 1;
			    } else
			    	return 0;
	}

	public int riesgoSinRatingIngresoSolicitud(Solicitud solicitud,List<SolicitudDetalle> solicitudDetalle, String codUsuario) {
		int valorRetorno=0;
		//cartas delegacion
		BigDecimal montoRiesgos=cartasRiesgosDAO.montDelegacionSinRating(codUsuario,Constant.GRUPO_SIN_RATING);			
		for(SolicitudDetalle solicitudes:solicitudDetalle){
				BigDecimal montoSolicitud=solicitudes.getMtoProducto();
				if((montoRiesgos.compareTo(montoSolicitud)==0 || montoRiesgos.compareTo(montoSolicitud)==1)){
							valorRetorno++;
					}
				}
				if (solicitudDetalle.size() == valorRetorno) {
					return 1;
				} else
					return 0;	
		}

	public int riesgoPersonaNaturalIngresoSolicitud(Solicitud solicitud,List<SolicitudDetalle> solicitudDetalle, String codUsuario) {
		int valorRetorno=0;
		List<ProductoDelegacion> getLstProducto=cartasRiesgosDAO.getDelegacionPersonaNatural(codUsuario);
		if(getLstProducto.size()<=0){
			return 0;
		}
				for (int i = 0; i < solicitudDetalle.size(); i++) {
					for (int y = 0; y < getLstProducto.size(); y++) {
						if (solicitudDetalle.get(i).getCodProducto().equals((getLstProducto.get(y).getCodProducto())) && 
						   (solicitudDetalle.get(i).getMtoProducto().compareTo(getLstProducto.get(y).getMontoProducto())==0 ||
						    solicitudDetalle.get(i).getMtoProducto().compareTo(getLstProducto.get(y).getMontoProducto())==1)){
						    valorRetorno++;
						}
				   }
		     }
		if(solicitudDetalle.size() == valorRetorno) {
			    return 1;
			} else
			    return 0;	
		}


	public int oficinaPorRatingIngresoSolicitud(Solicitud solicitud,List<SolicitudDetalle> solicitudDetalle, String codUsuario) {
		int valorRetorno=0;
		BigDecimal codDetalle=BigDecimal.ZERO;
		BigDecimal montoOficina=BigDecimal.ZERO;
		int plazoOficina=0;
		 List<SolicitudDetalle> getLstSolicitudDetalles = solicitudDetalle;		
		 	for(SolicitudDetalle solicitudes:getLstSolicitudDetalles){
		 		Garantia garantia=garantiaDAO.getProductoGarantia(solicitudes.getCodProducto().toString());
		 		if(garantia!=null){
			 		GarantiaDetalle garantiaDetalle=garantiaDetalleDAO.garantiaDetalle(garantia.getCodGarantia().toString());
			 		codDetalle=garantiaDetalle.getCodConfiguracion();
			 		CartasDelegaciones cartasDelegaciones=getCartasOficinaRating(solicitud.getRating(),codUsuario,codDetalle.toString());    
			 		montoOficina=cartasDelegaciones.getMontos();
			 		plazoOficina=cartasDelegaciones.getPlazos();
		 		}else return 0;
		 //cartas delegacion
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


	public int oficinaSinRatingIngresoSolicitud(Solicitud solicitud,List<SolicitudDetalle> solicitudDetalle, String codUsuario) {
		int valorRetorno=0;
		BigDecimal codDetalle=BigDecimal.ZERO;
		BigDecimal montoOficina=BigDecimal.ZERO;
		int plazoOficina=0;
		 List<SolicitudDetalle> getLstSolicitudDetalles = solicitudDetalle;		
		 	for(SolicitudDetalle solicitudes:getLstSolicitudDetalles){
		 		Garantia garantia=garantiaDAO.getProductoGarantia(solicitudes.getCodProducto().toString());
		 		if(garantia!=null){
		 		GarantiaDetalle garantiaDetalle=garantiaDetalleDAO.garantiaDetalle(garantia.getCodGarantia().toString());
		 		codDetalle=garantiaDetalle.getCodConfiguracion();
		 		CartasDelegaciones cartasDelegaciones=getCartasOficinaSinRating(codUsuario,codDetalle.toString());    
		 		 montoOficina=cartasDelegaciones.getMontos();
		 	     plazoOficina=cartasDelegaciones.getPlazos();
		 		}else return 0;
		 //cartas delegacion
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

	public int oficinaPersonaNaturalIngresoSolicitud(Solicitud solicitud,List<SolicitudDetalle> solicitudDetalle, String codUsuario) {
		int valorRetorno=0;
		List<ProductoDelegacion> getLstProducto=cartasRiesgosDAO.getDelegacionPersonaNatural(codUsuario);
		if(getLstProducto.size()<=0){
			return 0;
		}
				for (int i = 0; i < solicitudDetalle.size(); i++) {
					for (int y = 0; y < getLstProducto.size(); y++) {
						if (solicitudDetalle.get(i).getCodProducto().equals((getLstProducto.get(y).getCodProducto())) && 
						   (solicitudDetalle.get(i).getMtoProducto().compareTo(getLstProducto.get(y).getMontoProducto())==0 ||
						    solicitudDetalle.get(i).getMtoProducto().compareTo(getLstProducto.get(y).getMontoProducto())==1)){
						    valorRetorno++;
						}
				   }
		     }
		if(solicitudDetalle.size() == valorRetorno) {
			    return 1;
			} else
			    return 0;	
	}
	
	public int metodoEncapsulado(Solicitud solicitud,String codUsuario) {
		int valorRetorno=0;
		String tipopersona=gerenteOficinaDAO.getUsuarioTipo(codUsuario);
		String grupo=solicitud.getGrupoPersona();
		if (tipopersona==null || grupo==null){
			return valorRetorno;
		}
			if(tipopersona.equals("R")){
				//RIESGO
				if(grupo.equals(Constant.GRUPO_CON_RATING)){
				valorRetorno=riesgoPorRating(solicitud,codUsuario);	
				}else if(grupo.equals(Constant.GRUPO_SIN_RATING)){
				valorRetorno=riesgoSinRating(solicitud,codUsuario);					
				}else if(grupo.equals(Constant.GRUPO_PER_NATUAL)){
				valorRetorno=riesgoPersonaNatural(solicitud,codUsuario);	
				}
			}else if (tipopersona.equals("O")){
				//OFICINA
				if(grupo.equals(Constant.GRUPO_CON_RATING)){
				valorRetorno=oficinaConRating(solicitud,codUsuario);	
				}else if(grupo.equals(Constant.GRUPO_SIN_RATING)){
				valorRetorno=oficinaSinRating(solicitud,codUsuario);					
				}else if(grupo.equals(Constant.GRUPO_PER_NATUAL)){
				valorRetorno=riesgoPersonaNatural(solicitud, codUsuario);	
				}
			}
				return valorRetorno;
		}
	
	public int metodoEncapsuladoIngresoSolicitud(Solicitud solicitud,List<SolicitudDetalle> solicitudDetalle, String codUsuario) {
		int valorRetorno=0;
		String tipopersona=gerenteOficinaDAO.getUsuarioTipo(codUsuario);
		String grupo=solicitud.getGrupoPersona();
		if (tipopersona==null && grupo==null){
			return valorRetorno;
		}
			if(tipopersona.equals("R")){
				//RIESGO
				if(grupo.equals(Constant.GRUPO_CON_RATING)){
				valorRetorno=riesgoPorRatingIngresoSolicitud(solicitud ,solicitudDetalle,codUsuario);
				}else if(grupo.equals(Constant.GRUPO_SIN_RATING)){
				valorRetorno=riesgoSinRatingIngresoSolicitud(solicitud ,solicitudDetalle,codUsuario);					
				}else if(grupo.equals(Constant.GRUPO_PER_NATUAL)){
				valorRetorno=riesgoPersonaNaturalIngresoSolicitud(solicitud,solicitudDetalle,codUsuario);	
				}
			}else if (tipopersona.equals("O")){
				//OFICINA
				if(grupo.equals(Constant.GRUPO_CON_RATING)){
				valorRetorno=oficinaPorRatingIngresoSolicitud(solicitud, solicitudDetalle, codUsuario);	
				}else if(grupo.equals(Constant.GRUPO_SIN_RATING)){
				valorRetorno=oficinaSinRatingIngresoSolicitud(solicitud, solicitudDetalle, codUsuario);					
				}else if(grupo.equals(Constant.GRUPO_PER_NATUAL)){
				valorRetorno=oficinaPersonaNaturalIngresoSolicitud(solicitud, solicitudDetalle, codUsuario);	
				}
			}
				return valorRetorno;
	}
		
	public CartasDelegaciones getCartasOficinaRating(String rating,String codUsuario,String idDetalle){
		CartasDelegaciones cartasDelegaciones= new CartasDelegaciones();
		cartasDelegaciones.setMontos(new BigDecimal("1455"));
		cartasDelegaciones.setPlazos(147850);
		return cartasDelegaciones;
	}
	public CartasDelegaciones getCartasOficinaSinRating(String codUsuario,String idDetalle){
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
}