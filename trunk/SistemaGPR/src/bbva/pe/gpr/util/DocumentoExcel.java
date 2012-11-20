package bbva.pe.gpr.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;

public class DocumentoExcel {

	private HSSFSheet sheet = null;
	private HSSFWorkbook workbook = null;
	private HSSFCellStyle styleLabel = null;
	private HSSFCellStyle styleContent = null;
	private HSSFCellStyle styleBarraTitulo = null;
	private HSSFCellStyle styleFooter = null;
	private HSSFCellStyle styleFooterRight = null;	

	public DocumentoExcel(String filePath) throws InvalidFormatException, FileNotFoundException, IOException {
		File file = new File(filePath);
		this.setWorkbook((HSSFWorkbook) WorkbookFactory.create(new FileInputStream(file)));
		this.setSheet((HSSFSheet) this.getWorkbook().getSheetAt(0));
		this.crearContentStaticStyle();
		this.crearLabelStaticStyle();
		this.crearStyleBarraTitulo();
		this.crearStyleFooter();
		this.crearStyleFooterRight();
	}

	public DocumentoExcel(File file) throws InvalidFormatException, FileNotFoundException, IOException {
		this.setWorkbook((HSSFWorkbook) WorkbookFactory.create(new FileInputStream(file)));
		this.setSheet((HSSFSheet) this.getWorkbook().getSheetAt(0));
		this.crearContentStaticStyle();
		this.crearLabelStaticStyle();
		this.crearStyleBarraTitulo();
		this.crearStyleFooter();
		this.crearStyleFooterRight();
	}
	
	public void setLabelValue(int indexRow, int indexCell, String valor) {
		if (this.getSheet().getRow(indexRow) == null)
			this.getSheet().createRow(indexRow);
		if (this.getSheet().getRow(indexRow).getCell(indexCell) == null)
			this.getSheet().getRow(indexRow).createCell(indexCell);

		this.getSheet().getRow(indexRow).getCell(indexCell).setCellStyle(
				this.getStyleLabel());
		this.getSheet().getRow(indexRow).getCell(indexCell).setCellValue(valor);
	}
	
	public void setContentValue(int indexRow, int indexCell, BigDecimal valor) {
		if (this.getSheet().getRow(indexRow) == null)
			this.getSheet().createRow(indexRow);
		if (this.getSheet().getRow(indexRow).getCell(indexCell) == null)
			this.getSheet().getRow(indexRow).createCell(indexCell);

		this.getSheet().getRow(indexRow).getCell(indexCell).setCellStyle(this.getStyleContent());
		if(valor != null) {
			this.getSheet().getRow(indexRow).getCell(indexCell).setCellValue(valor.doubleValue());
		} else {
			this.getSheet().getRow(indexRow).getCell(indexCell).setCellValue("");
		}
	}

	public void setContentValue(int indexRow, int indexCell, String valor) {
		if (this.getSheet().getRow(indexRow) == null)
			this.getSheet().createRow(indexRow);
		if (this.getSheet().getRow(indexRow).getCell(indexCell) == null)
			this.getSheet().getRow(indexRow).createCell(indexCell);

		this.getSheet().getRow(indexRow).getCell(indexCell).setCellStyle(this.getStyleContent());
		this.getSheet().getRow(indexRow).getCell(indexCell).setCellValue(valor);
	}

	public void setContentValue(int indexRow, int indexCell, String valor, HSSFCellStyle style) {

		if (this.getSheet().getRow(indexRow) == null)
			this.getSheet().createRow(indexRow);
		if (this.getSheet().getRow(indexRow).getCell(indexCell) == null)
			this.getSheet().getRow(indexRow).createCell(indexCell);

		this.getSheet().getRow(indexRow).getCell(indexCell).setCellStyle(style);
		this.getSheet().getRow(indexRow).getCell(indexCell).setCellValue(valor);

	}
	
	public void setContentValue(int indexRow, int indexCellFrom,
			int indexCellTo, String titulo) {
		if (this.getSheet().getRow(indexRow) == null)
			this.getSheet().createRow(indexRow);

		for (int index = indexCellFrom; index <= indexCellTo; index++) {
			if (this.getSheet().getRow(indexRow).getCell(index) == null)
				this.getSheet().getRow(indexRow).createCell(index);
		}

		this.getSheet().addMergedRegion(
				new CellRangeAddress(indexRow, indexRow, indexCellFrom,
						indexCellTo));

		this.getSheet().getRow(indexRow).getCell(indexCellFrom).setCellStyle(
				this.getStyleContent());
		this.getSheet().getRow(indexRow).getCell(indexCellFrom).setCellValue(
				titulo);
	}

	public void setContentValue(int indexRow, int indexCellFrom,
			int indexCellTo, String titulo, HSSFCellStyle style) {
		if (this.getSheet().getRow(indexRow) == null)
			this.getSheet().createRow(indexRow);

		for (int index = indexCellFrom; index <= indexCellTo; index++) {
			if (this.getSheet().getRow(indexRow).getCell(index) == null)
				this.getSheet().getRow(indexRow).createCell(index);
		}

		this.getSheet().addMergedRegion(
				new CellRangeAddress(indexRow, indexRow, indexCellFrom, indexCellTo));

		this.getSheet().getRow(indexRow).getCell(indexCellFrom).setCellStyle(style);
		this.getSheet().getRow(indexRow).getCell(indexCellFrom).setCellValue(titulo);
	}

	public void setFooter(int indexRow, int indexCell, String valor) {

		if (this.getSheet().getRow(indexRow) == null)
			this.getSheet().createRow(indexRow);
		if (this.getSheet().getRow(indexRow).getCell(indexCell) == null)
			this.getSheet().getRow(indexRow).createCell(indexCell);

		this.getSheet().getRow(indexRow).getCell(indexCell).setCellStyle(
				this.getStyleFooter());
		this.getSheet().getRow(indexRow).getCell(indexCell).setCellValue(valor);

	}

	public void setBarraTitulo(int indexRow, int indexCell, String valor) {

		if (this.getSheet().getRow(indexRow) == null)
			this.getSheet().createRow(indexRow);
		if (this.getSheet().getRow(indexRow).getCell(indexCell) == null)
			this.getSheet().getRow(indexRow).createCell(indexCell);

		this.getSheet().getRow(indexRow).getCell(indexCell).setCellStyle(this.getStyleBarraTitulo());
		this.getSheet().getRow(indexRow).getCell(indexCell).setCellValue(valor);

	}

	public void setFooter(int indexRow, int indexCellFrom, int indexCellTo,
			String titulo) {
		if (this.getSheet().getRow(indexRow) == null)
			this.getSheet().createRow(indexRow);

		for (int index = indexCellFrom; index <= indexCellTo; index++) {
			if (this.getSheet().getRow(indexRow).getCell(index) == null)
				this.getSheet().getRow(indexRow).createCell(index);
		}

		this.getSheet().addMergedRegion(
				new CellRangeAddress(indexRow, indexRow, indexCellFrom,
						indexCellTo));

		this.getSheet().getRow(indexRow).getCell(indexCellFrom).setCellStyle(
				this.getStyleFooter());
		this.getSheet().getRow(indexRow).getCell(indexCellFrom).setCellValue(
				titulo);
	}

	public void setFooterRight(int indexRow, int indexCellFrom,
			int indexCellTo, String titulo) {
		if (this.getSheet().getRow(indexRow) == null)
			this.getSheet().createRow(indexRow);

		for (int index = indexCellFrom; index <= indexCellTo; index++) {
			if (this.getSheet().getRow(indexRow).getCell(index) == null)
				this.getSheet().getRow(indexRow).createCell(index);
		}

		this.getSheet().addMergedRegion(
				new CellRangeAddress(indexRow, indexRow, indexCellFrom,
						indexCellTo));

		this.getSheet().getRow(indexRow).getCell(indexCellFrom).setCellStyle(this.getStyleFooterRight());
		this.getSheet().getRow(indexRow).getCell(indexCellFrom).setCellValue(titulo);
	}

	public void setBarraTitulo(int indexRow, int indexCellFrom, int indexCellTo, String titulo) {
		if (this.getSheet().getRow(indexRow) == null)
			this.getSheet().createRow(indexRow);

		for (int index = indexCellFrom; index <= indexCellTo; index++) {
			if (this.getSheet().getRow(indexRow).getCell(index) == null) {
				this.getSheet().getRow(indexRow).createCell(index);
			}
			this.getSheet().getRow(indexRow).getCell(index).setCellStyle(this.getStyleBarraTitulo());
		}
		
		this.getSheet().addMergedRegion(new CellRangeAddress(indexRow, indexRow, indexCellFrom, indexCellTo));
		this.getSheet().getRow(indexRow).getCell(indexCellFrom).setCellValue(titulo);
	}

	public void setImagen(int indexRow, int indexCell, byte[] imageToByteArray) throws IOException {
		HSSFClientAnchor anchor = new HSSFClientAnchor();
		anchor.setCol1(indexCell);
		anchor.setRow1(indexRow);
		int index = this.getWorkbook().addPicture(imageToByteArray, HSSFWorkbook.PICTURE_TYPE_JPEG);

		HSSFPatriarch patriarch = null;
		if (this.getSheet().getDrawingPatriarch() == null)
			patriarch = this.getSheet().createDrawingPatriarch();
		else
			patriarch = this.getSheet().getDrawingPatriarch();

		HSSFPicture picture = patriarch.createPicture(anchor, index);
		picture.setAnchor(picture.getPreferredSize());
	}
	
	public void setImagen(int indexRow, int indexCell, String pathImg) throws IOException {
		FileInputStream fis = new FileInputStream(pathImg);
		@SuppressWarnings("resource")
		ByteArrayOutputStream img_bytes = new ByteArrayOutputStream();
		
		int b;
		while ((b = fis.read()) != -1)
			img_bytes.write(b);

		fis.close();
		HSSFClientAnchor anchor = new HSSFClientAnchor();
		anchor.setCol1(indexCell);
		anchor.setRow1(indexRow);
		int index = this.getWorkbook().addPicture(img_bytes.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG);

		HSSFPatriarch patriarch = null;

		if (this.getSheet().getDrawingPatriarch() == null)
			patriarch = this.getSheet().createDrawingPatriarch();
		else
			patriarch = this.getSheet().getDrawingPatriarch();

		HSSFPicture picture = patriarch.createPicture(anchor, index);
		picture.setAnchor(picture.getPreferredSize());
	}

	public InputStream getExcelStream() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		this.getWorkbook().write(baos);
		return new ByteArrayInputStream(baos.toByteArray());
	}

	public byte[] getExcelToByteArray() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		this.getWorkbook().write(baos);
		return baos.toByteArray();
	}
	
	// ---- PRIVATE METHOD'S ----

	protected HSSFCell obtenerCelda(int indexRow, int indexCell) {
		HSSFRow filaExcel = sheet.getRow(indexRow);
		HSSFCell cellExcel = null;

		if (filaExcel == null)
			filaExcel = sheet.createRow(indexRow);

		cellExcel = filaExcel.getCell(indexCell);

		if (cellExcel == null)
			cellExcel = filaExcel.createCell(indexCell);

		return cellExcel;
	}

	private void crearLabelStaticStyle() {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);

		HSSFFont font = workbook.createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 10);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);

		this.setStyleLabel(style);

	}

	private void crearContentStaticStyle() {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);

		style.setBorderBottom(CellStyle.BORDER_THIN);
	    style.setBorderLeft(CellStyle.BORDER_THIN);
	    style.setBorderRight(CellStyle.BORDER_THIN);
	    style.setBorderTop(CellStyle.BORDER_THIN);
		
		HSSFFont font = workbook.createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 10);
		style.setFont(font);

		this.setStyleContent(style);
	}

	private void crearStyleBarraTitulo() {
		this.crearColor(HSSFColor.BLUE.index, 83, 142, 213);
		
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setFillForegroundColor(HSSFColor.BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		style.setBorderBottom(CellStyle.BORDER_THIN);
	    style.setBorderLeft(CellStyle.BORDER_THIN);
	    style.setBorderRight(CellStyle.BORDER_THIN);
	    style.setBorderTop(CellStyle.BORDER_THIN);
		
		HSSFFont font = workbook.createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 10);
		font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);

		this.setStyleBarraTitulo(style);
	}

	private void crearStyleFooter() {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		HSSFFont font = workbook.createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 10);
		font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);

		this.setStyleFooter(style);
	}

	private void crearStyleFooterRight() {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		style.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		HSSFFont font = workbook.createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 10);
		font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);

		this.setStyleFooterRight(style);
	}

	private HSSFColor crearColor(short colorIndex, int red, int green, int blue) {
		HSSFPalette palette = workbook.getCustomPalette();
		palette.setColorAtIndex(colorIndex, (byte) red, (byte) green, (byte) blue);
		return palette.getColor(colorIndex);
	}

	public HSSFCellStyle crearStyleDynamic(short align, short color, short colorFont, short fill, short fontSize) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(align);
		style.setFillForegroundColor(color);
		style.setFillPattern(fill);

		HSSFFont font = workbook.createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) fontSize);
		font.setColor(colorFont);
		style.setFont(font);

		return style;
	}
	
	public HSSFCellStyle crearStyleDynamic(short align, short colorIndex, int red, int green, int blue) {
		crearColor(colorIndex, red, green, blue);
		return crearStyleDynamic(align, colorIndex, HSSFColor.WHITE.index, HSSFCellStyle.SOLID_FOREGROUND, (short) 10);
	}

	public HSSFCellStyle crearStyleDynamic(short colorIndex, int red, int green, int blue) {
		crearColor(colorIndex, red, green, blue);
		return crearStyleDynamic(HSSFCellStyle.ALIGN_CENTER, colorIndex, HSSFColor.BLACK.index, HSSFCellStyle.SOLID_FOREGROUND, (short) 10);
	}
	
	// ---- GETTER'S AND SETTER'S ----

	public HSSFSheet getSheet() {
		return sheet;
	}

	public void setSheet(HSSFSheet sheet) {
		this.sheet = sheet;
	}

	public HSSFWorkbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(HSSFWorkbook workbook) {
		this.workbook = workbook;
	}

	public HSSFCellStyle getStyleLabel() {
		return styleLabel;
	}

	public void setStyleLabel(HSSFCellStyle styleLabel) {
		this.styleLabel = styleLabel;
	}

	public HSSFCellStyle getStyleContent() {
		return styleContent;
	}

	public void setStyleContent(HSSFCellStyle styleContent) {
		this.styleContent = styleContent;
	}

	public HSSFCellStyle getStyleBarraTitulo() {
		return styleBarraTitulo;
	}

	public void setStyleBarraTitulo(HSSFCellStyle styleBarraTitulo) {
		this.styleBarraTitulo = styleBarraTitulo;
	}

	public HSSFCellStyle getStyleFooter() {
		return styleFooter;
	}

	public void setStyleFooter(HSSFCellStyle styleFooter) {
		this.styleFooter = styleFooter;
	}

	public HSSFCellStyle getStyleFooterRight() {
		return styleFooterRight;
	}

	public void setStyleFooterRight(HSSFCellStyle styleFooterRight) {
		this.styleFooterRight = styleFooterRight;
	}
}