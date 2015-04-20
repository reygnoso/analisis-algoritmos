package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import metodos.Semilla;
import net.miginfocom.swing.MigLayout;

public class SimpleUI {
	private static final Logger logger = new Logger(SimpleUI.class);
	private JFrame frame;
	private static JTextArea log;

	public void mostrar() {
		frame.setVisible(true);
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
		frame = new JFrame();
		frame.setBounds(100, 100, 975, 551);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow][][grow][][][grow][][][]", "[][][grow]"));

		JLabel label1 = new JLabel("M\u00E9todos");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(label1, "cell 0 0");

		JLabel label2 = new JLabel("Salida");
		frame.getContentPane().add(label2, "cell 2 0");

		JList<String> lista = new JList<String>();
		lista.setModel(modelo);
		frame.getContentPane().add(lista, "cell 0 2,grow");

		JButton ejecutar = new JButton("ejecutar rutina");
		ejecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {

				String clase = lista.getSelectedValue();
				if (clase == null) {
					logger.warn("No hay una clase seleccionada");
					return;
				}

				logger.info("Ejecutando clase seleccionada: " + lista.getSelectedValue());

				try {
					new Thread() {
						public void run() {
							Class<?> cls;
							try {
								cls = Class.forName(lista.getSelectedValue());
								@SuppressWarnings("unchecked")
								List<Semilla> semillas = (List<Semilla>) cls.getMethod("obtenSemillas").invoke(
										cls.newInstance());

								for (Semilla semilla : semillas) {
									Date ahora = new Date();
									logger.info(">> Inicio en el [ " + ahora.toString() + " ]  con semilla tam [" + semilla.tam + "] ");

									// void -> cuerpo del problema
									cls.getMethod("Cuerpo", Semilla.class).invoke(cls.newInstance(), semilla);

									long tiempoFin = new Date().getTime();
									long duracion = tiempoFin - ahora.getTime();
									logger.info(">> Duraci�n:  " + duracion(duracion) + ">> [ " + duracion + " ] milis");

								}

							} catch (Exception e) {
								logger.error("Error en la ejecucion: " + e.getLocalizedMessage());
								e.printStackTrace();
							}
						}

						private String duracion(long millis) {
							{
								if (millis < 0) {
									throw new IllegalArgumentException("Duracion invalida");
								}

								long days = TimeUnit.MILLISECONDS.toDays(millis);
								millis -= TimeUnit.DAYS.toMillis(days);
								long hours = TimeUnit.MILLISECONDS.toHours(millis);
								millis -= TimeUnit.HOURS.toMillis(hours);
								long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
								millis -= TimeUnit.MINUTES.toMillis(minutes);
								long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

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
					logger.error("Error en   ejecucion: " + e1.getLocalizedMessage());
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(ejecutar, "cell 0 1");

		log = new JTextArea();
		frame.getContentPane().add(log, "cell 2 2 7 1,grow");
	}

}
