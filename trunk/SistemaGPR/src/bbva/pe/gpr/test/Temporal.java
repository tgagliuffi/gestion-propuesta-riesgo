package bbva.pe.gpr.test;

import java.math.BigDecimal;
import java.util.Date;

import bbva.pe.gpr.bean.Producto;
import bbva.pe.gpr.bean.Solicitud;

public class Temporal {
	
	 /*
	  * Persona jurídica, Persona natural, Persona natural con negocio.
	  * .(se debe crear una transacción que invoque primero a la rutina 
	  * PE7CRUCE donde se accederá por número de cliente y se obtendrá el 
	  * RUC del empleador y luego se procederá con el RUC OBTENIDO se invocará al programa PE2C5200 para obtener el NOMBRE DEL EMPLEADOR)
	  */
	
	
	
	public Solicitud obtenerDatosOficina(Solicitud solicitudBean){
		
		solicitudBean.setEjecutivoCtaCod("123");
		solicitudBean.setEjecutivoCtaNom("PRUEBA");
		solicitudBean.setOficinaAltaCod("456");
		solicitudBean.setOficinaAltaNom("PRUEBA");
		solicitudBean.setGerenciaTerritorialCod("678");
		solicitudBean.setGerenciaTerritorialNom("PRUEBA PRUEBA");
		
		return solicitudBean;
	}
	
	public Producto addNewProducto(){
		Producto productoBean = new Producto();		
		productoBean.setCodProducto(new BigDecimal(1));
		productoBean.setCodProductoBase(new BigDecimal(100));
		productoBean.setCodBanca(new BigDecimal(1));
		productoBean.setDescripcion("PRESTAMO PESONAL");
		productoBean.setVigencia(new Date());
		return productoBean;
	}
	
	

}
