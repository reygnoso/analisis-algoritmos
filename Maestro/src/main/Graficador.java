package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;
import de.erichseifert.gral.util.Insets2D;
import dominio.Semilla;

public class Graficador extends JFrame {
	private static final long serialVersionUID = 1L;

	public Graficador(List<Semilla> semillas, String titulo) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// Create data
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
		// Style the plot
		double insetsTop = 20.0, insetsLeft = 60.0, insetsBottom = 60.0, insetsRight = 40.0;
		plot.setInsets(new Insets2D.Double(insetsTop, insetsLeft, insetsBottom, insetsRight));
		plot.getTitle().setText(titulo);

		// Style axes
		plot.getAxisRenderer(XYPlot.AXIS_X).setLabel("n");
		plot.getAxisRenderer(XYPlot.AXIS_Y).setLabel("tiempo");
		plot.getAxisRenderer(XYPlot.AXIS_X).setTickSpacing(1.0);
		plot.getAxisRenderer(XYPlot.AXIS_Y).setTickSpacing(2.0);
		plot.getAxisRenderer(XYPlot.AXIS_X).setIntersection(-Double.MAX_VALUE);
		plot.getAxisRenderer(XYPlot.AXIS_Y).setIntersection(-Double.MAX_VALUE);

		// Display on screen
		getContentPane().add(new InteractivePanel(plot), BorderLayout.CENTER);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setMinimumSize(getContentPane().getMinimumSize());
		setSize(1000, 500);
	}

}