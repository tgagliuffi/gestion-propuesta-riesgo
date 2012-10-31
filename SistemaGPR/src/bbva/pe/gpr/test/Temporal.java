package bbva.pe.gpr.test;

import java.math.BigDecimal;
import java.util.Date;

import bbva.pe.gpr.bean.Producto;
import bbva.pe.gpr.bean.Solicitud;

public class Temporal {
	
	 /*
	  * Persona jur�dica, Persona natural, Persona natural con negocio.
	  * .(se debe crear una transacci�n que invoque primero a la rutina 
	  * PE7CRUCE donde se acceder� por n�mero de cliente y se obtendr� el 
	  * RUC del empleador y luego se proceder� con el RUC OBTENIDO se invocar� al programa PE2C5200 para obtener el NOMBRE DEL EMPLEADOR)
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
