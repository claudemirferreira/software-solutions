package br.com.ss.core.web.ireport;

import java.awt.Color;
import java.awt.GradientPaint;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Set;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import br.com.ss.academico.dominio.DetalheBoletim;
import br.com.ss.academico.dominio.MediaTurma;
import br.com.ss.academico.enumerated.Bimestre;

@SuppressWarnings("unused")
public class Grafico implements Serializable {

	private static final long serialVersionUID = 6201495152288650807L;

	private DefaultCategoryDataset dataset;
	private JFreeChart chart;

	public JFreeChart criarGraficoMedia(List<MediaTurma> medias,
			Set<DetalheBoletim> boletins, Bimestre bimestre) {
		createDataset(medias, boletins, bimestre);
		criarGraficoBoletim(medias, boletins, bimestre, "Gráfico do "
				+ bimestre.getDescricao());

		return chart;
	}

	public JFreeChart criarGraficoMediaAcumulada(List<MediaTurma> medias,
			Set<DetalheBoletim> boletins, Bimestre bimestre) {
		createDatasetAcumulado(medias, boletins, bimestre);
		criarGraficoBoletim(medias, boletins, bimestre,
				"Gráfico de Média Acumulada até o  " + bimestre.getDescricao());

		return chart;

	}

	public JFreeChart criarGraficoBoletim(List<MediaTurma> medias,
			Set<DetalheBoletim> boletins, Bimestre bimestre, String titulo) {

		chart = ChartFactory.createBarChart3D(titulo, // chart
				// title
				"CATEGORIA", // domain axis label
				"NOTAS", // range axis label
				this.dataset, // data
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, // tooltips?
				false // URLs?
				);

		chart.getCategoryPlot().getRangeAxis().setRange(0, 10);

		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

		// set the background color for the chart...
		chart.setBackgroundPaint(Color.white);

		// get a reference to the plot for further customisation...
		final CategoryPlot plot = chart.getCategoryPlot();

		CategoryItemRenderer itemRerender = plot.getRenderer();
		// Caso vc queira mudar a cor das barras
		itemRerender.setSeriesPaint(0, Color.blue);
		itemRerender.setSeriesPaint(1, Color.RED);
		// Aki fica o codigo para colocar o valor na barra
		itemRerender
				.setItemLabelGenerator(new StandardCategoryItemLabelGenerator(
						"{2}", new DecimalFormat("0.00")));
		itemRerender.setItemLabelsVisible(true);

		plot.getRangeAxis().setRange(0, 10);
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);

		// set the range axis to display integers only...
		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		// disable bar outlines...
		final BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setDrawBarOutline(false);

		// set up gradient paints for series...
		final GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.blue,
				0.0f, 0.0f, Color.lightGray);
		final GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.green,
				0.0f, 0.0f, Color.lightGray);
		final GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.red,
				0.0f, 0.0f, Color.lightGray);
		renderer.setSeriesPaint(0, gp0);
		renderer.setSeriesPaint(1, gp1);
		renderer.setSeriesPaint(2, gp2);

		final CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions
				.createUpRotationLabelPositions(Math.PI / 6.0));

		return chart;

	}

	private void createDataset(List<MediaTurma> medias,
			Set<DetalheBoletim> boletins, Bimestre bimestre) {
		this.dataset = new DefaultCategoryDataset();

		for (DetalheBoletim detalheBoletim : boletins)
			this.dataset.addValue(detalheBoletim.pegarMedia(bimestre), "ALUNO",
					detalheBoletim.getDisciplina().getNome());

		for (MediaTurma mediaTurma : medias)
			this.dataset.addValue(mediaTurma.pegarMedia(bimestre), "SALA",
					mediaTurma.getDisciplina());

		for (MediaTurma mediaTurma : medias)
			this.dataset.addValue(6, "ESCOLA", mediaTurma.getDisciplina());

	}

	private void createDatasetAcumulado(List<MediaTurma> medias,
			Set<DetalheBoletim> boletins, Bimestre bimestre) {
		this.dataset = new DefaultCategoryDataset();

		for (DetalheBoletim detalheBoletim : boletins)
			this.dataset.addValue(detalheBoletim.pegarMedia(bimestre), "ALUNO",
					detalheBoletim.getDisciplina().getNome());

		for (MediaTurma mediaTurma : medias)
			this.dataset.addValue(mediaTurma.pegarMedia(bimestre), "SALA",
					mediaTurma.getDisciplina());
		for (MediaTurma mediaTurma : medias)
			this.dataset.addValue(6, "ESCOLA", mediaTurma.getDisciplina());

	}

	public DefaultCategoryDataset getDataset() {
		return dataset;
	}

	public void setDataset(DefaultCategoryDataset dataset) {
		this.dataset = dataset;
	}

	public JFreeChart getChart() {
		return chart;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

}