package bbva.pe.gpr.util;

import java.awt.Color;
import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleInsets;
import org.jfree.util.Rotation;

public class Grafico {

	private static Logger logger = Logger.getLogger(Grafico.class);
	private boolean legend = false;
	private boolean tooltips = false;
	private boolean urls = false;
	private String title;
	private String subtitle;
	private String explodePercent;
	private int width;
	private int heigth;
	private DefaultPieDataset datasetPie;

	public Grafico() {
		legend = false;
		tooltips = false;
		urls = false;
		datasetPie = null;
		width = 400;
		heigth = 350;
	}

	public boolean isLegend() {
		return legend;
	}

	public void setLegend(boolean legend) {
		this.legend = legend;
	}

	public boolean isTooltips() {
		return tooltips;
	}

	public void setTooltips(boolean tooltips) {
		this.tooltips = tooltips;
	}

	public boolean isUrls() {
		return urls;
	}

	public void setUrls(boolean urls) {
		this.urls = urls;
	}

	public DefaultPieDataset getDatasetPie() {
		if(datasetPie ==null) {
			datasetPie = new DefaultPieDataset();
		}
		return datasetPie;
	}

	public void setDatasetPie(DefaultPieDataset datasetPie) {
		this.datasetPie = datasetPie;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getExplodePercent() {
		return explodePercent;
	}

	public void setExplodePercent(String explodePercent) {
		this.explodePercent = explodePercent;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeigth() {
		return heigth;
	}

	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}

	public byte[] generaPieChart() {
		byte[] toByte = null; 
 		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JFreeChart chart = ChartFactory.createPieChart(title, datasetPie, legend, tooltips, urls);
		
		chart.getTitle().setFont(new Font("Tahoma", Font.BOLD, 14));
		chart.getTitle().setPaint(new Color(32, 32, 127));
		chart.getTitle().setMargin(5, 0, 2, 0);
		
		TextTitle textSubtitle = new TextTitle(subtitle, new Font("Tahoma", Font.PLAIN, 12));
		textSubtitle.setPaint(Color.red);
		chart.addSubtitle(textSubtitle);
		
		chart.setBackgroundPaint(Color.white);
		chart.setPadding(new RectangleInsets(0, 0, 0, 0));
		chart.setBorderPaint(Color.white);
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setOutlinePaint(Color.white);
        plot.setCircular(true);
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setBackgroundPaint(Color.white);
        plot.setBaseSectionOutlinePaint(Color.white);
        
        plot.setNoDataMessage("No hay resultados");
        plot.setExplodePercent(explodePercent, 0.1D);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({2})"));
        plot.setLegendLabelToolTipGenerator(new StandardPieSectionLabelGenerator("{0}"));
        plot.setSimpleLabels(true);
        plot.setInteriorGap(0.0D);
        plot.setBaseSectionOutlinePaint(Color.white);
        plot.setLabelBackgroundPaint(new Color(250, 250, 250));
        plot.setShadowPaint(new Color(240, 240, 240));
        
		try {
			ChartUtilities. writeChartAsPNG(baos, chart, width, heigth);
			toByte = baos.toByteArray();
		} catch (IOException e) {
			logger.error("generaPieChart", e);
		}
		
		return toByte; 
	}
	
	public void clear() {
		datasetPie = null;
	}
}
