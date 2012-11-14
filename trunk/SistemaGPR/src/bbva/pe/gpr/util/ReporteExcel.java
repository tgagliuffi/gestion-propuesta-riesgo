package bbva.pe.gpr.util;


import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import bbva.pe.gpr.bean.Solicitud;
import bbva.pe.gpr.context.Context;
import bbva.pe.gpr.service.CatalogoService;
import bbva.pe.gpr.service.SolicitudService;

/**
 * @author tgagliuffi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReporteExcel {
	
	SolicitudService solicitudService;
	CatalogoService catalogoService;

	public ReporteExcel() 
	{
		solicitudService=(SolicitudService)Context.getInstance().getBean("solicitudService");	
		catalogoService=(CatalogoService)Context.getInstance().getBean("catalogoService");
	}

	@SuppressWarnings("deprecation")
	public String generaExcel(List<Solicitud> lstSolicitud) throws Exception{
	
		HSSF1 example = new HSSF1();
		String rutaSalida =  catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_CONFIGURACIONES, "16002").getStrValor() + "RPT_LISTA_SOLICITUDES.XLS"; 
		String rutaPantilla =  catalogoService.selectMultitablaDTByPrimaryKey(Constant.TABLA_CONFIGURACIONES, "16002").getStrValor() + "PLANTILLA_RPT_LSTSOLICITUD.XLS"; 

     
    	HSSFWorkbook libro=example.getWorkbook(rutaPantilla);
    	if(libro!=null){
    	HSSFSheet hoja = libro.getSheetAt(0);
    		
    		HSSFRichTextString texto;
	        HSSFRow fila;
	        
		for(int i=1; i<lstSolicitud.size()+1; i++){
			
			Solicitud solicitudBean = new Solicitud();
			solicitudBean = lstSolicitud.get(i-1);
		
			//NRO_SOLICITUD
			fila = hoja.createRow(i);
		    HSSFCell celda1 = fila.createCell((short) 0);
	        texto = new HSSFRichTextString(solicitudBean.getNroSolicitud().toString());
	        celda1.setCellValue(texto);
	        
	        //COD_CENTRAL
	        HSSFCell celda2 = fila.createCell((short) 1);
	        texto = new HSSFRichTextString(solicitudBean.getCodCentral());
	        celda2.setCellValue(texto);
	        
	        //PERSONA
	        HSSFCell celda3 = fila.createCell((short) 2);
	        texto = new HSSFRichTextString(solicitudBean.getDesMultTipoPersona());
	        celda3.setCellValue(texto);
	        
	        //NUMERO_DOCUMENTO
	        HSSFCell celda4 = fila.createCell((short) 3);
	        texto = new HSSFRichTextString(solicitudBean.getNumeroDocumento());
	        celda4.setCellValue(texto);
	        
	        //DES_SLICITANTE	
	        HSSFCell celda5 = fila.createCell((short) 4);
	        texto = new HSSFRichTextString(solicitudBean.getDesSolicitante());// preguntar
	        celda5.setCellValue(texto);
	        
	        //COD_OFICINA
	        HSSFCell celda6 = fila.createCell((short) 5);
	        texto = new HSSFRichTextString(solicitudBean.getCodOficina());
	        celda6.setCellValue(texto);
	        
	        //DES_OFICINA
	        HSSFCell celda7 = fila.createCell((short) 6);
	        texto = new HSSFRichTextString(solicitudBean.getDesOficina());
	        celda7.setCellValue(texto);
	        
	        //GESTOR_COD
	        HSSFCell celda8 = fila.createCell((short) 7);
	        texto = new HSSFRichTextString(solicitudBean.getGestorCod());
	        celda8.setCellValue(texto);
	        
	        //GESTOR_NOM
	        HSSFCell celda9 = fila.createCell((short) 8);
	        texto = new HSSFRichTextString(solicitudBean.getGestorNom());
	        celda9.setCellValue(texto);
	        
	        //EMPLEADOR_COD
	        HSSFCell celda10 = fila.createCell((short) 9);
	        texto = new HSSFRichTextString(solicitudBean.getEmpleadorCod());
	        celda10.setCellValue(texto);
	        
	        //EMPLEADOR_NOM	
	        HSSFCell celda11 = fila.createCell((short) 10);
	        texto = new HSSFRichTextString(solicitudBean.getEmpleadorNom());
	        celda11.setCellValue(texto);
	        
	    	//EJECUTIVO_CTA_COD
	        HSSFCell celda12 = fila.createCell((short) 11);
	        texto = new HSSFRichTextString(solicitudBean.getEjecutivoCtaCod());
	        celda12.setCellValue(texto);
	        
	        //EJECUTIVO_CTA_NOM
	        HSSFCell celda13 = fila.createCell((short) 12);
	        texto = new HSSFRichTextString(solicitudBean.getEjecutivoCtaNom());
	        celda13.setCellValue(texto);
	        
	        //OFICINA_ALTA_COD
	        HSSFCell celda14 = fila.createCell((short) 13);
	        texto = new HSSFRichTextString(solicitudBean.getOficinaAltaCod());
	        celda14.setCellValue(texto);
	        
	        //OFICINA_ALTA_NOM	
	        HSSFCell celda15 = fila.createCell((short) 14);
	        texto = new HSSFRichTextString(solicitudBean.getOficinaAltaNom());
	        celda15.setCellValue(texto);
	        
	        //GERENCIA_TERRITORIAL_COD
	        HSSFCell celda16 = fila.createCell((short) 15);
	        texto = new HSSFRichTextString(solicitudBean.getGerenciaTerritorialCod());
	        celda16.setCellValue(texto);
	        
	        //GERENCIA_TERRITORIAL_NOM
	        HSSFCell celda17 = fila.createCell((short) 16);
	        texto = new HSSFRichTextString(solicitudBean.getGerenciaTerritorialNom());
	        celda17.setCellValue(texto);
	        
	        //FECHA_INGRESO
	        HSSFCell celda18 = fila.createCell((short) 17);
	        celda18.setCellType(HSSFCell.CELL_TYPE_STRING);
	        texto = new HSSFRichTextString(solicitudBean.getStrFechaIngreso());
	        celda18.setCellValue(texto);
	        
	        //COD_BANCA
	        HSSFCell celda19 = fila.createCell((short) 18);
	        celda18.setCellType(HSSFCell.CELL_TYPE_STRING);
	        texto = new HSSFRichTextString(solicitudBean.getDesBanca());
	        celda19.setCellValue(texto);
	        
	        //MONEDA
	        HSSFCell celda20 = fila.createCell((short) 19);
	        texto = new HSSFRichTextString(solicitudBean.getDesMultMoneda());
	        celda20.setCellValue(texto);
	        
	        //RATING
	        HSSFCell celda21 = fila.createCell((short) 20);
	        texto = new HSSFRichTextString(solicitudBean.getRating());
	        celda21.setCellValue(texto);
	        
	        //SCORING
	        HSSFCell celda22 = fila.createCell((short) 21);
	        texto = new HSSFRichTextString(solicitudBean.getScoring());
	        celda22.setCellValue(texto);
	        
	        //SCORATING
	        HSSFCell celda23 = fila.createCell((short) 22);
	        texto = new HSSFRichTextString(solicitudBean.getScorating());
	        celda23.setCellValue(texto);
	        
	        //CLASIFICACION
	        HSSFCell celda24 = fila.createCell((short) 23);
	        texto = new HSSFRichTextString(solicitudBean.getScorating());
	        celda24.setCellValue(texto);
	        
	        //REELEVANCIA
	        HSSFCell celda25 = fila.createCell((short) 24);
	        texto = new HSSFRichTextString(solicitudBean.getReelevancia());
	        celda25.setCellValue(texto);
	        
	        //DUD DIRECTA       
	        HSSFCell celda26 = fila.createCell((short) 25);
	        texto = new HSSFRichTextString(solicitudBean.getDeudaDirecta().toString());
	        celda26.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	        celda26.setCellValue(texto);
	        
	        //DED. INDIRECTA
	        HSSFCell celda27 = fila.createCell((short) 26);
	        texto = new HSSFRichTextString(solicitudBean.getDeudaIndirecta()!=null?solicitudBean.getDeudaIndirecta().toString():null);
	        celda27.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	        celda27.setCellValue(texto);
	        
	        //DED.CASTIGO
	        HSSFCell celda28 = fila.createCell((short) 27);
	        texto = new HSSFRichTextString(solicitudBean.getCastigo()!=null?solicitudBean.getCastigo().toString():null);
	        celda28.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	        celda28.setCellValue(texto);
	        
	        //DED. SIST FIN	
	        HSSFCell celda29 = fila.createCell((short) 28);
	        texto = new HSSFRichTextString(solicitudBean.getDeudaSistemaFinanciero()!=null?solicitudBean.getDeudaSistemaFinanciero().toString():null);
	        celda29.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	        celda29.setCellValue(texto);
	        
	        //RIESGO_ACTUAL
	        HSSFCell celda30 = fila.createCell((short) 29);
	        texto = new HSSFRichTextString(solicitudBean.getRiesgoActual()!=null?solicitudBean.getRiesgoActual().toString():null);
	        celda30.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	        celda30.setCellValue(texto);
	        
	        //OTROS_RIESGOS
	        HSSFCell celda31 = fila.createCell((short) 30);
	        texto = new HSSFRichTextString(solicitudBean.getOtroRiesgo()!=null?solicitudBean.getOtroRiesgo().toString():null);
	        celda31.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	        celda31.setCellValue(texto);
	        
	        //RIESGO_GRUPAL
	        HSSFCell celda32 = fila.createCell((short) 31);
	        texto = new HSSFRichTextString(solicitudBean.getRiesgoGrupal()!=null?solicitudBean.getRiesgoGrupal().toString():null);
	        celda32.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	        celda32.setCellValue(texto);
	        
	        //RIESGO_TOTAL
	        HSSFCell celda33 = fila.createCell((short) 32);
	        texto = new HSSFRichTextString(solicitudBean.getRiesgoTotal()!=null?solicitudBean.getRiesgoTotal().toString():null);
	        celda33.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	        celda33.setCellValue(texto);
	        
	        //PRE_INGRESO
	        HSSFCell celda34 = fila.createCell((short) 33);
	        texto = new HSSFRichTextString(solicitudBean.getDeudaSistemaFinanciero()!=null?solicitudBean.getDeudaSistemaFinanciero().toString():null);
	        celda34.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	        celda34.setCellValue(texto);
	        
	        //LUGAR_DICTAMINA
	        HSSFCell celda35 = fila.createCell((short) 34);
	        texto = new HSSFRichTextString(solicitudBean.getDeudaSistemaFinanciero()!=null?solicitudBean.getDeudaSistemaFinanciero().toString():null);
	        celda35.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	        celda35.setCellValue(texto);
	        //LST_DOCUMENTOS
	        
	        //CHK_REV_SUBGERENTE
	        HSSFCell celda36 = fila.createCell((short) 35);
	        texto = new HSSFRichTextString(solicitudBean.getDeudaSistemaFinanciero()!=null?solicitudBean.getDeudaSistemaFinanciero().toString():null);
	        celda36.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	        celda36.setCellValue(texto);
	        
	        //ESTADO_SOLICITUD
	        HSSFCell celda37 = fila.createCell((short) 36);
	        texto = new HSSFRichTextString(solicitudBean.getDeudaSistemaFinanciero()!=null?solicitudBean.getDeudaSistemaFinanciero().toString():null);
	        celda37.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	        celda37.setCellValue(texto);
	        
	        //ESTADO
	        HSSFCell celda38 = fila.createCell((short) 37);
	        texto = new HSSFRichTextString(solicitudBean.getDeudaSistemaFinanciero()!=null?solicitudBean.getDeudaSistemaFinanciero().toString():null);
	        celda38.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	        celda38.setCellValue(texto);
	    	
			}
	
    	}
	     try {
            
            if(libro!=null){
            	FileOutputStream elFichero = new FileOutputStream(rutaSalida);
                libro.write(elFichero);
                elFichero.close();
		            return rutaSalida;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    
	
        return null;
	}
	
		

}
