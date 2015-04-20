package main;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.UIManager;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

public class Main {
	private static final Logger logger = new Logger(Main.class);

	public static void main(String[] args) throws InterruptedException {

		try {
			logger.info("Iniciando aplicacion . . .");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

			List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
			classLoadersList.add(ClasspathHelper.contextClassLoader());
			classLoadersList.add(ClasspathHelper.staticClassLoader());

			Reflections reflections = new Reflections(new ConfigurationBuilder()
					.setScanners(new SubTypesScanner(false), new ResourcesScanner())
					.setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
					.filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("metodos"))));
			Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);

			DefaultListModel<String> modelo = new DefaultListModel<String>();
			for (Class<? extends Object> e : classes) {
				modelo.addElement(e.getName());
			}

			new SimpleUI(modelo).mostrar();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
