package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultCaret;

import net.miginfocom.swing.MigLayout;
import dominio.Semilla;

public class SimpleUI {
	private static final Logger logger = new Logger(SimpleUI.class);
	private JFrame frmAnlisisDeAlgoritmos;
	static JTextArea log;

	public void mostrar() {
		frmAnlisisDeAlgoritmos.setVisible(true);
	}

	public static void writeLog(String msg) {
		try {
			log.append("\n" + msg);

		} catch (Exception ex) {
		}
	}

	/**
	 * @wbp.parser.constructor
	 */
	public SimpleUI() {
		initialize(null);
	}

	public SimpleUI(DefaultListModel<String> modelo) {
		initialize(modelo);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param modelo
	 */
	private void initialize(DefaultListModel<String> modelo) {
		frmAnlisisDeAlgoritmos = new JFrame();
		frmAnlisisDeAlgoritmos.setTitle("An\u00E1lisis de Algoritmos");
		frmAnlisisDeAlgoritmos.setBounds(100, 100, 1275, 551);
		frmAnlisisDeAlgoritmos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAnlisisDeAlgoritmos.getContentPane().setLayout(
				new MigLayout("", "[grow][][grow][][][grow][][][]",
						"[][][grow]"));

		JLabel label1 = new JLabel("M\u00E9todos");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		frmAnlisisDeAlgoritmos.getContentPane().add(label1, "cell 0 0");

		JLabel label2 = new JLabel("Salida");
		frmAnlisisDeAlgoritmos.getContentPane().add(label2, "cell 2 0");

		final JList<String> lista = new JList<String>();
		lista.setModel(modelo);
		frmAnlisisDeAlgoritmos.getContentPane().add(lista, "cell 0 2,grow");

		JButton ejecutar = new JButton("ejecutar rutina");
		ejecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {

				String clase = lista.getSelectedValue();
				if (clase == null) {
					logger.warn("No hay una clase seleccionada");
					return;
				}

				logger.info("Ejecutando clase seleccionada: "
						+ lista.getSelectedValue());

				try {
					new Thread() {
						public void run() {
							Class<?> cls;
							try {
								cls = Class.forName(lista.getSelectedValue());
								@SuppressWarnings("unchecked")
								List<Semilla> semillas = (List<Semilla>) cls
										.getMethod("obtenSemillas").invoke(
												cls.newInstance());

								float porcentaje = 0;
								float total = semillas.size();
								int i = 0;
								for (Semilla semilla : semillas) {
									i++;
									porcentaje = (i * 100) / total;
									logger.info(">> [  % " + porcentaje
											+ " ]  con semilla tam ["
											+ semilla.tam + "] ");
									// void -> cuerpo del problema
									long inicio = System.nanoTime();
									cls.getMethod("Cuerpo", Semilla.class)
											.invoke(cls.newInstance(), semilla);
									long tiempoFin = System.nanoTime();
									long duracion = tiempoFin - inicio;
									logger.info(">> Duración:  "
											+ duracion(duracion) + ">> [ "
											+ duracion + " ] nanos");
									semilla.duracion = duracion;
								}
								logger.info("Graficando . . .");
								new SimplePlot(semillas).setVisible(true);
								// new Graficador(semillas,
								// cls.getName()).setVisible(true);
							} catch (Exception e) {
								logger.error("Error en la ejecucion: "
										+ e.getLocalizedMessage());
								e.printStackTrace();
								System.exit(0);
							}
						}

						private String duracion(long millis) {
							{
								if (millis < 0) {
									throw new IllegalArgumentException(
											"Duracion invalida");
								}

								long days = TimeUnit.NANOSECONDS.toDays(millis);
								millis -= TimeUnit.DAYS.toMillis(days);

								long hours = TimeUnit.NANOSECONDS
										.toHours(millis);
								millis -= TimeUnit.HOURS.toMillis(hours);

								long minutes = TimeUnit.NANOSECONDS
										.toMinutes(millis);
								millis -= TimeUnit.MINUTES.toMillis(minutes);

								long seconds = TimeUnit.NANOSECONDS
										.toSeconds(millis);

								StringBuilder sb = new StringBuilder(64);
								sb.append(days);
								sb.append(" Dias ");
								sb.append(hours);
								sb.append(" Horas ");
								sb.append(minutes);
								sb.append(" Minutos ");
								sb.append(seconds);
								sb.append(" Segundos");

								return (sb.toString());
							}
						}
					}.start();

				} catch (Exception e1) {
					logger.error("Error en  ejecucion: "
							+ e1.getLocalizedMessage());
					e1.printStackTrace();
					System.exit(0);

				}
			}
		});
		frmAnlisisDeAlgoritmos.getContentPane().add(ejecutar, "cell 0 1");

		JScrollPane scrollPane = new JScrollPane();
		frmAnlisisDeAlgoritmos.getContentPane().add(scrollPane,
				"cell 2 2 7 1,grow");

		log = new JTextArea();

		DefaultCaret caret = (DefaultCaret) log.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		scrollPane.setViewportView(log);
	}

}
