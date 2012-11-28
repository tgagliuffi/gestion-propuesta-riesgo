package bbva.pe.gpr.serviceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bbva.pe.gpr.bean.Delegacion;
import bbva.pe.gpr.bean.ProductoDelegacion;
import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.dao.CartasRiesgosDAO;
import bbva.pe.gpr.dao.GerenteOficinaDAO;
import bbva.pe.gpr.dao.MultitablaDetalleDAO;
import bbva.pe.gpr.dao.SolicitudDetalleDAO;
import bbva.pe.gpr.dao.UsuarioDAO;
import bbva.pe.gpr.service.ControlService;
import bbva.pe.gpr.util.Constant;

public class ControlServiceImpl implements ControlService {

	private MultitablaDetalleDAO multitablaDetalleDAO;
	private SolicitudDetalleDAO solicitudDetalleDAO;
 	private CartasRiesgosDAO    cartasRiesgosDAO;
    private UsuarioDAO usuarioDAO;
    private GerenteOficinaDAO gerenteOficinaDAO;

	public ControlServiceImpl(MultitablaDetalleDAO multitablaDetalleDAO,
							  SolicitudDetalleDAO solicitudDetalleDAO,
							  CartasRiesgosDAO    cartasRiesgosDAO,
							  UsuarioDAO usuarioDAO,
							  GerenteOficinaDAO gerenteOficinaDAO
							  ) {
		super();
		this.multitablaDetalleDAO=multitablaDetalleDAO;
		this.solicitudDetalleDAO=solicitudDetalleDAO;
		this.cartasRiesgosDAO=cartasRiesgosDAO;
		this.usuarioDAO=usuarioDAO;
		this.gerenteOficinaDAO=gerenteOficinaDAO;
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
	public CartasRiesgosDAO getCartasRiesgosDAO() {
		return cartasRiesgosDAO;
	}

	public void setCartasRiesgosDAO(CartasRiesgosDAO cartasRiesgosDAO) {
		this.cartasRiesgosDAO = cartasRiesgosDAO;
	}
	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	public GerenteOficinaDAO getGerenteOficinaDAO() {
		return gerenteOficinaDAO;
	}

	public void setGerenteOficinaDAO(GerenteOficinaDAO gerenteOficinaDAO) {
		this.gerenteOficinaDAO = gerenteOficinaDAO;
	}

	//TODO si retorna 1 paso validacion si retorna 0 no paso validacion
	public int condicionCliente(Solicitud solicitud) throws Exception {
		String getBureau = "";
		String getBbva = "";
		String getSiFinan = "";
		String getRelPub = "";
		String getInele = "";
		if(solicitud.getCondicionCliente() != null) {
			getBureau = solicitud.getCondicionCliente().getBureau();
			getBbva = solicitud.getCondicionCliente().getBbvaa();
			getSiFinan = solicitud.getCondicionCliente().getSistFinan();
			getRelPub = solicitud.getCondicionCliente().getRelevPubli();
			getInele = solicitud.getCondicionCliente().getInelegible();
		}
		
		if (	metodoGenericoCondCliente(Constant.TABLA_BUREAU, getBureau).equals("1")&& 
				metodoGenericoCondCliente(Constant.TABLA_BBVA, getBbva).equals("1") && 
				metodoGenericoCondCliente(Constant.TABLA_SISTEMA_FINANCIERO, getSiFinan).equals("1") && 
				metodoGenericoCondCliente(Constant.TABLA_RELEVANCIA_PUBLICA, getRelPub).equals("1") && 
				metodoGenericoCondCliente(Constant.TABLA_INELEGIBLES, getInele).equals("1")) {
				return 1;
			} else
				return 0;
	}
	
	public String mensajeCondicionCliente(Solicitud solicitud ) throws Exception{
		String mensaje="",bureau=Constant.NO_APTO,bBVA=Constant.NO_APTO,sistemaFin=Constant.NO_APTO,relevaPub="",inelegibles="";
		String getBureau = "",getBbva = "",getSiFinan = "",getRelPub = "",getInele = "";

		    getBureau = (solicitud.getCondicionCliente().getBureau()!=null?solicitud.getCondicionCliente().getBureau():"");
			getBbva = (solicitud.getCondicionCliente().getBbvaa()!=null?solicitud.getCondicionCliente().getBbvaa():"");
			getSiFinan =(solicitud.getCondicionCliente().getSistFinan()!=null?solicitud.getCondicionCliente().getSistFinan():"");
			getRelPub = (solicitud.getCondicionCliente().getRelevPubli()!=null?solicitud.getCondicionCliente().getRelevPubli():"");
			getInele = (solicitud.getCondicionCliente().getInelegible()!=null?solicitud.getCondicionCliente().getInelegible():"");
	
	    String valorBureau=metodoGenericoCondCliente(Constant.TABLA_BUREAU, getBureau);
		String valorBBVA=metodoGenericoCondCliente(Constant.TABLA_BBVA, getBbva);
		String valorSistemaFin=metodoGenericoCondCliente(Constant.TABLA_SISTEMA_FINANCIERO, getSiFinan);
		String valorRelevaPubl=metodoGenericoCondCliente(Constant.TABLA_RELEVANCIA_PUBLICA, getRelPub);
		String valorInelegibles=metodoGenericoCondCliente(Constant.TABLA_INELEGIBLES, getInele);
      	if(!getBureau.equals("")){
    		if(!valorBureau.equals("1")){
    	      	 bureau=multitablaDetalleDAO.getDescripcion(getBureau);
    		}else bureau=Constant.APTO;
      	}
    	if(!getBbva.equals("")){
    		if(!valorBBVA.equals("1")){
    			bBVA=multitablaDetalleDAO.getDescripcion(getBbva);
    		}else bBVA=Constant.APTO;
		}
    	if(!getSiFinan.equals("")){
    		if(!valorSistemaFin.equals("1")){
    			sistemaFin=multitablaDetalleDAO.getDescripcion(getSiFinan);
    		}else sistemaFin=Constant.APTO;
    	}
    	if(!getRelPub.equals("")){
    		if(valorRelevaPubl.equals("1")){
    			relevaPub=Constant.NO_APTO;
    		}else relevaPub=Constant.APTO;
    	}
    	if(!valorInelegibles.equals("")){
    		if(valorInelegibles.equals("1")){
    			inelegibles=Constant.NO_APTO;
    		}else inelegibles=Constant.APTO;
    	}
        mensaje ="<table> " +
       	         "<tr>" + "<td align=left>"+ "BUREAU"+"</td>"+
      	         "<td align=right>"+ bureau+ "</td>"+
        		 "</tr> "+
        		 "<tr>" + "<td align=left>"+ "CLASIFICACION BBVA"+"</td>"+
	        	 "<td align=right>"+ bBVA+ "</td>"+
	             "</tr> "+
	             "<tr>" + "<td align=left>"+ "CLASIFICACION Siste. Financiero"+"</td>"+
	             "<td align=right>"+ sistemaFin+ "</td>"+
	             "</tr> "+
	             "<tr>" + "<td align=left>"+ "RELEVANCIA"+"</td>"+
	             "<td align=right>"+ relevaPub+ "</td>"+	
	             "</tr> "+
	             "<tr>" + "<td align=left>"+ "INELEGIBLES"+"</td>"+
	             "<td align=right>"+ inelegibles+ "</td>"+
	             "</tr> "+
        		 "</table>";
	     return mensaje;
    }
	
	//TODO si retorna 1 paso validacion si retorna 0 no paso validacion
	public String metodoGenericoCondCliente(String codMultiTabla, String codValor) throws Exception {
		String valorRetorno="0";
		HashMap<String, String> hashDatosGenericos = new HashMap<String, String>();
		hashDatosGenericos.put("codMultiTabla", codMultiTabla);
		hashDatosGenericos.put("codValor", codValor);
		if (!codValor.equals("")) {
			if (multitablaDetalleDAO.lstMultitablaDetalle(hashDatosGenericos).equals("1")) {
				return valorRetorno="1";
			}
		}
		return valorRetorno;
	}
	
	//TODO si retorna 1 paso validacion si retorna 0 no paso validacion
	public int validacionMontosPlazos(Solicitud solicitud,String codGestor) throws Exception {

	 String tipoPersona=usuarioDAO.getTipoPersona(codGestor);
	 BigDecimal getMonto=BigDecimal.ZERO;
	 String codCargo=solicitud.getGestorCod();
	 String codOficina=solicitud.getCodOficina();
     if(tipoPersona.equals("O")){
    	 String montoDelegacion=cartasRiesgosDAO.montoDelegacionUsuario(codGestor,solicitud.getGrupoPersona());
     		if(montoDelegacion.equals("0")){
     				String codJefeOficina=usuarioDAO.getJefeOficina(codCargo,codOficina);
     				String montoJefeOficina=cartasRiesgosDAO.montoDelegacionUsuario(codJefeOficina,solicitud.getGrupoPersona());
     				if(!montoJefeOficina.equals("")){
                       getMonto= new BigDecimal(montoJefeOficina);     					
     				}
     				if (getMonto.compareTo(solicitud.getRiesgoTotal())==0 || getMonto.compareTo(solicitud.getRiesgoTotal())==1){
                	  return 1;
     				}
     		 }
     	}else if (tipoPersona.equals("R")){
     		String monto="0";
     		 String montoDelegacion=cartasRiesgosDAO.montoDelegacionUsuario(codGestor,solicitud.getGrupoPersona());
     		  if(montoDelegacion.equals("0")){
     			 String jefeInmediato= jefeInmediato(codGestor, solicitud);
     			 if(!jefeInmediato.equals("")){
         			  monto=cartasRiesgosDAO.montoDelegacionUsuario(jefeInmediato,solicitud.getGrupoPersona());     				 
     			 }
     			  BigDecimal getMontos= new BigDecimal(monto);
     			  if(getMontos.compareTo(solicitud.getRiesgoTotal())==1 || getMontos.compareTo(solicitud.getRiesgoTotal())==0 ){
     				  return 1;
     			  }
     		  }else
     		  {
     			  BigDecimal getMontos= new BigDecimal(montoDelegacion);
     			  if(getMontos.compareTo(solicitud.getRiesgoTotal())==1 || getMontos.compareTo(solicitud.getRiesgoTotal())==0 ){
     				  return 1;
     			   }
     		  }
     	}
     	return 0;
	}

	public Delegacion getDelegacion(String idUsuario) {
	
	ProductoDelegacion productoDelegacion01 = new ProductoDelegacion();
	productoDelegacion01.setCodProducto(new BigDecimal("10003"));
	productoDelegacion01.setMontoProducto(new BigDecimal("900"));
	productoDelegacion01.setPlazoProducto(new BigDecimal("120"));

	ProductoDelegacion productoDelegacion02= new ProductoDelegacion();
	productoDelegacion02.setCodProducto(new BigDecimal("10002"));
	productoDelegacion02.setMontoProducto(new BigDecimal("15000"));
	productoDelegacion02.setPlazoProducto(new BigDecimal("120"));

	ProductoDelegacion productoDelegacion03= new ProductoDelegacion();
	productoDelegacion03.setCodProducto(new BigDecimal("10001"));
	productoDelegacion03.setMontoProducto(new BigDecimal("500"));
	productoDelegacion03.setPlazoProducto(new BigDecimal("60"));
	
	ProductoDelegacion productoDelegacion06= new ProductoDelegacion();
	productoDelegacion06.setCodProducto(new BigDecimal("10006"));
	productoDelegacion06.setMontoProducto(new BigDecimal("20"));
	productoDelegacion06.setPlazoProducto(new BigDecimal("12"));

	ProductoDelegacion productoDelegacion10= new ProductoDelegacion();
	productoDelegacion10.setCodProducto(new BigDecimal("10010"));
	productoDelegacion10.setMontoProducto(new BigDecimal("70"));
	productoDelegacion10.setPlazoProducto(new BigDecimal("20"));
	
	ProductoDelegacion productoDelegacion12= new ProductoDelegacion();
	productoDelegacion12.setCodProducto(new BigDecimal("10012"));
	productoDelegacion12.setMontoProducto(new BigDecimal("200"));
	productoDelegacion12.setPlazoProducto(new BigDecimal("15"));

	ProductoDelegacion productoDelegacion14= new ProductoDelegacion();
	productoDelegacion14.setCodProducto(new BigDecimal("10014"));
	productoDelegacion14.setMontoProducto(new BigDecimal("100"));
	productoDelegacion14.setPlazoProducto(new BigDecimal("9"));
    
	ProductoDelegacion productoDelegacion17= new ProductoDelegacion();
	productoDelegacion17.setCodProducto(new BigDecimal("10017"));
	productoDelegacion17.setMontoProducto(new BigDecimal("120"));
	productoDelegacion17.setPlazoProducto(new BigDecimal("12"));
	
	ProductoDelegacion productoDelegacion18= new ProductoDelegacion();
	productoDelegacion18.setCodProducto(new BigDecimal("10018"));
	productoDelegacion18.setMontoProducto(new BigDecimal("600"));
	productoDelegacion18.setPlazoProducto(new BigDecimal("24"));
	
	ProductoDelegacion productoDelegacion20= new ProductoDelegacion();
	productoDelegacion20.setCodProducto(new BigDecimal("10020"));
	productoDelegacion20.setMontoProducto(new BigDecimal("120"));
	productoDelegacion20.setPlazoProducto(new BigDecimal("12"));
	
	List<ProductoDelegacion> getLstProductoDelegacion=new ArrayList<ProductoDelegacion>();
	getLstProductoDelegacion.add(productoDelegacion01);
	getLstProductoDelegacion.add(productoDelegacion02);
	getLstProductoDelegacion.add(productoDelegacion03);
	getLstProductoDelegacion.add(productoDelegacion06);
	getLstProductoDelegacion.add(productoDelegacion10);
	getLstProductoDelegacion.add(productoDelegacion12);
	getLstProductoDelegacion.add(productoDelegacion14);
	getLstProductoDelegacion.add(productoDelegacion17);
	getLstProductoDelegacion.add(productoDelegacion18);
	getLstProductoDelegacion.add(productoDelegacion20);
	
	Delegacion delegacion= new Delegacion();
	delegacion.setCodUsuario("P013556");
	delegacion.setMontoMaximo("15000");
	delegacion.setGetLstProductoDelegacion(getLstProductoDelegacion);
	return delegacion;
	}


	public String jefeInmediato(String codUsuario, Solicitud solicitud)throws Exception {
		String montoMaximo="";
		for(int i=0;i<10;i++){
			montoMaximo=cartasRiesgosDAO.montoDelegacionUsuario(codUsuario, solicitud.getGrupoPersona());
			BigDecimal getMontoRiesgos=new BigDecimal(montoMaximo);
			if(getMontoRiesgos.compareTo(solicitud.getRiesgoTotal())==1 || getMontoRiesgos.compareTo(solicitud.getRiesgoTotal())==0) {
				break;
			}
			codUsuario=usuarioDAO.codJefeInmediatoRiesgos(codUsuario);
		}
		return codUsuario;
	}
} 