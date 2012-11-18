package bbva.pe.gpr.serviceImpl;

import bbva.pe.gpr.bean.CondicionCliente;
import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.service.AplicativoPersonasService;
import bbva.pe.gpr.util.Constant;

public class AplicativoPersonasServiceImpl implements AplicativoPersonasService{
	

	public Solicitud  invokeClient(String codCentral){
		Solicitud solicitudBean = new Solicitud();
		
		if(codCentral.substring(0, 1).equalsIgnoreCase("4")){
			solicitudBean.setCodCentral(codCentral);
			solicitudBean.setDesSolicitante("PERSONA " + codCentral);
			solicitudBean.setCodMultTipoPersona("NATURAL");
			solicitudBean.setNumeroDocumento("42809680");
			
			solicitudBean.setCodOficina("0120");
			solicitudBean.setDesOficina("PLAZA SAN MIGUEL");
			
			solicitudBean.setGestorCod("B23");
			solicitudBean.setGestorNom("SANDRA NIRSA ARENAS ALVAREZ");
			
			solicitudBean.setCondicionCliente(new CondicionCliente());
			solicitudBean.getCondicionCliente().setBbvaa("40 CPP");
			solicitudBean.getCondicionCliente().setBureau("G1");
			solicitudBean.getCondicionCliente().setInelegible("INELEGIBLES");
			solicitudBean.getCondicionCliente().setRelevPubli("SIN RELEVANCIA");
			solicitudBean.getCondicionCliente().setSistFinan("20 DUDOSO");
			
			
		}else if(codCentral.substring(0, 1).equalsIgnoreCase("2")){
			solicitudBean.setCodCentral(codCentral);
			solicitudBean.setDesSolicitante("EMPRESA" + codCentral);
			solicitudBean.setCodMultTipoPersona("JURIDICA");
			solicitudBean.setNumeroDocumento("44444444");
			
			solicitudBean.setCodOficina("0120");
			solicitudBean.setDesOficina("PLAZA SAN MIGUEL");
			
			solicitudBean.setGestorCod("B23");
			solicitudBean.setGestorNom("SANDRA NIRSA ARENAS	ALVAREZ");
			
			solicitudBean.getCondicionCliente().setBbvaa("40 CPP");
			solicitudBean.getCondicionCliente().setBureau("G1");
			solicitudBean.getCondicionCliente().setInelegible("INELEGIBLES");
			solicitudBean.getCondicionCliente().setRelevPubli("SIN RELEVANCIA");
			solicitudBean.getCondicionCliente().setSistFinan("20 DUDOSO");
						
			
		}else{
			solicitudBean = null;
		}
		
		if(solicitudBean!=null){
			solicitudBean = invokePE7CRUCE(solicitudBean);
			solicitudBean = invokeGerenciaTerritorial(solicitudBean);
			solicitudBean = invokeRelevancia(solicitudBean);
			solicitudBean = invokeRelevancia(solicitudBean);
			solicitudBean = invokeClasificacionCliente(solicitudBean);
			if(solicitudBean.getCodMultTipoPersona().equals(Constant.PERSONA_NATURAL)){
				solicitudBean=invokeScorating(solicitudBean);
			}else{
				solicitudBean=invokeRating(solicitudBean);
			}
		}
		
		return solicitudBean;
	}
	
	public Solicitud invokePE7CRUCE(Solicitud solicitudBean){
		String rucEmpleador = (solicitudBean.getCodCentral().substring(0, 1).equalsIgnoreCase("4")? "10447691839": null);
		if(rucEmpleador!=null){
			solicitudBean.setEmpleadorCod(rucEmpleador);
			solicitudBean.setEmpleadorNom(invokepPE2C5200(rucEmpleador));
		}
		return solicitudBean;
	}
	
	public String invokepPE2C5200(String rucEmpleador){
		return "EMPRESA CLIENTE S.A";
	}
	 	
	public Solicitud invokeGerenciaTerritorial(Solicitud solicitudBean){
		//TODO: obtener datos del ejecutivo
		solicitudBean.setGerenciaTerritorialCod("XXXXXX");
		solicitudBean.setGerenciaTerritorialNom("XXXXXXXXXXXXXX");
		return solicitudBean;
	}
	
	public Solicitud invokeRelevancia(Solicitud solicitudBean){
		String relevancias = "404403404";	
		solicitudBean.setReelevancia(relevancias);
		return solicitudBean;
	}

	public Solicitud invokeClasificacionCliente(Solicitud solicitudBean){
		solicitudBean.setClasificacion("NORMAL");
		return solicitudBean;
	}

	
	public Solicitud invokeScorating(Solicitud solicitudBean){
		solicitudBean.setScorating("");
		return solicitudBean;
	}
	
	public Solicitud invokeRating(Solicitud solicitudBean){
		solicitudBean.setRating("C");
		return solicitudBean;
	}

}
