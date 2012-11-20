package bbva.pe.gpr.util;

import java.io.ByteArrayOutputStream;

import org.apache.log4j.Logger;

import bbva.pe.gpr.action.EstadisticaAction;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class DocumentoPDF {

	private static Logger logger = Logger.getLogger(EstadisticaAction.class);
    private Document document;
    private ByteArrayOutputStream baos;
    
    public DocumentoPDF() {
    	baos = new ByteArrayOutputStream();
    	try {
			PdfWriter.getInstance(document, baos);
		} catch (DocumentException e) {
			logger.error("", e);
		}
    	document.open();
    }
    
    public byte[] toArray() {
    	document.close();
    	return baos.toByteArray();
    }
    
    public void addParagraph(Paragraph paragraph) {
    	try {
			document.add(paragraph);
		} catch (DocumentException e) {
			logger.error("", e);
		}
    }
    
    public void addText(String text) {
    	addParagraph(new Paragraph(text));
    }
 
}
