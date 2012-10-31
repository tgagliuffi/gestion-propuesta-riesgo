package bbva.pe.gpr.action;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.OutputStream;
import java.text.NumberFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.RectangleEdge;
import org.jfree.util.TableOrder;

public class PieChartServlet extends HttpServlet {
	
	private static final long serialVersionUID = -1131814089513522695L;

	public PieChartServlet() {
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        OutputStream out = response.getOutputStream();
        
        try {
        	
        	final CategoryDataset dataset = createDataset();
            final JFreeChart chart = createChart(dataset);
            
            response.setContentType("image/png");
            ChartUtilities.writeChartAsPNG(out, chart, 900, 450);            
        }
        catch (Exception e) {
            System.err.println(e.toString());
        }
        finally {
            out.close();
        }
    }

	private JFreeChart createChart(CategoryDataset dataset) {
		final JFreeChart chart = ChartFactory.createMultiplePieChart(
				"",  				   // chart title
	            dataset,               // dataset
	            TableOrder.BY_COLUMN,
	            true,                  // include legend
	            true,
	            false
	    );
		
		final LegendTitle legendTitle = chart.getLegend();
		legendTitle.setPosition(RectangleEdge.TOP);
		legendTitle.setItemFont(new Font("SansSerif", Font.PLAIN, 12));
				
        final MultiplePiePlot plot = (MultiplePiePlot) chart.getPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setOutlineStroke(null);
        
        final JFreeChart subchart = plot.getPieChart();
        final PiePlot p = (PiePlot) subchart.getPlot();
        p.setBackgroundPaint(null);
        p.setOutlineStroke(null);
        //p.setSimpleLabels(true);
        p.setCircular(true);
        
        p.setNoDataMessage("No data available");
        p.setLabelGenerator(new StandardPieSectionLabelGenerator(
            "{0} ({2})", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance()
        ));
        
        p.setMaximumLabelWidth(0.35);
        p.setLabelFont(new Font("SansSerif", Font.PLAIN, 10));
        p.setInteriorGap(0.01);
        return chart;
	}

	private CategoryDataset createDataset() {
		final double[][] data = new double[][] {
                {1200.0, 6700.0, 2700.0}, //Asignadas
                {300.0, 3300.0, 150.0},   //Sin asignar
                {0.0, 0.0, 150.0}   	  //Anuladas
                //BE //BP //BPY
        };
        
        String[] estados = {"Asignadas", "Sin asignar", "Anuladas"};
        String[] bancas = {
        		"Banca Empresa 1500 solicitudes", 
        		"Banca Personas 10000 solicitudes", 
        		"Banca Pymes 3000 solicitudes"};
        
        final CategoryDataset dataset = DatasetUtilities.createCategoryDataset(
        		estados,
        		bancas,
                data
        );
        return dataset;
	}
}