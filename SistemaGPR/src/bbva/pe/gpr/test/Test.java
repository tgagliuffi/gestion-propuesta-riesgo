package bbva.pe.gpr.test;

import java.math.BigDecimal;
import java.util.Date;

import bbva.pe.gpr.bean.Producto;
import bbva.pe.gpr.bean.Solicitud;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String relevancias = "Inelegibles,Terrorismo,Lista negra,Alto riesgo,Lavado de dinero";
		String[] arrayRelevancia = relevancias.split(",");
		System.out.println("ver : " + arrayRelevancia[1]);

	}

	
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
