package main;

import java.awt.Color;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;
import dominio.Semilla;

public class SimplePlot extends JFrame {

	private static final long serialVersionUID = 1L;

	public SimplePlot(List<Semilla> semillas) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(1200, 800);
		

		@SuppressWarnings("unchecked")
		DataTable data = new DataTable(Integer.class, Long.class);

		for (Iterator<Semilla> iterator = semillas.iterator(); iterator.hasNext();) {
			Semilla semilla = (Semilla) iterator.next();
			data.add(semilla.tam, semilla.duracion);
		}

		XYPlot plot = new XYPlot(data);
		getContentPane().add(new InteractivePanel(plot));
		LineRenderer lines = new DefaultLineRenderer2D();
		plot.setLineRenderer(data, lines);
		Color color = new Color(0.0f, 0.3f, 1.0f);
		plot.getPointRenderer(data).setColor(color);
		plot.getLineRenderer(data).setColor(color);
	}
}