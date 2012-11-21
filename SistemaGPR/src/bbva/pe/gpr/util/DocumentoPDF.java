package bbva.pe.gpr.util;

import java.io.ByteArrayOutputStream;

import org.apache.log4j.Logger;

import bbva.pe.gpr.action.EstadisticaAction;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class DocumentoPDF {

	private static Logger logger = Logger.getLogger(EstadisticaAction.class);
    private Document document;
    private ByteArrayOutputStream baos;
    private Font fontDefault;
    private Font fontTitle;
    private Font fontHeaderTable;
    
    public DocumentoPDF() {
    	baos = new ByteArrayOutputStream();
    	try {
    		document = new Document(PageSize.A4.rotate());
			PdfWriter.getInstance(document, baos);
		} catch (DocumentException e) {
			logger.error("", e);
		}
    	document.open();
    	
    	createFontDefault();
    	createFontTitle();
    	createFontHeaderTable();
    }
    
    private void createFontDefault() {
    	fontDefault = new Font(FontFamily.HELVETICA);
    	fontDefault.setSize(8);
    }
    
    private void createFontTitle() {
    	fontTitle = new Font(FontFamily.HELVETICA);
    	fontTitle.setSize(10);
    	fontTitle.setColor(31,73,125);
    }
    
    private void createFontHeaderTable() {
    	fontHeaderTable = new Font(FontFamily.HELVETICA);
    	fontHeaderTable.setStyle(Font.BOLD);
    	fontHeaderTable.setSize(8);
    	fontHeaderTable.setColor(255, 255, 255);
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
    
    public void addParagraph(String text) {
    	addParagraph(new Paragraph(text, fontDefault));
    }
 
    public PdfPTable getTable(int cols) {
    	PdfPTable table = new PdfPTable(cols);
    	table.setWidthPercentage(100f);
    	table.getDefaultCell().setUseAscender(true);
    	table.getDefaultCell().setUseDescender(true);
    
    	return table;
    }
    
    public byte[] toByteArray() {
    	document.close();
    	return baos.toByteArray();
    }
    
    public void setTitle(String text) {
    	addParagraph(new Paragraph(text, fontTitle));
    	add(Chunk.NEWLINE);
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Font getFontDefault() {
		return fontDefault;
	}

	public void setFontDefault(Font fontDefault) {
		this.fontDefault = fontDefault;
	}

	public Font getFontTitle() {
		return fontTitle;
	}

	public void setFontTitle(Font fontTitle) {
		this.fontTitle = fontTitle;
	}

	public Font getFontHeaderTable() {
		return fontHeaderTable;
	}

	public void setFontHeaderTable(Font fontHeaderTable) {
		this.fontHeaderTable = fontHeaderTable;
	}

	public boolean add(Element element)  {
		try {
			return document.add(element);
		} catch (DocumentException e) {
			logger.error("", e);
			return false;
		}
	}
	
	
}
