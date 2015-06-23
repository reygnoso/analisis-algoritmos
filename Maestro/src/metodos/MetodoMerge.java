package metodos;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
 
import dominio.MetodoPrueba;
import dominio.Semilla;
import main.Logger;
 
public class MetodoMerge implements MetodoPrueba {
 
    private static final Logger logger = new Logger(MetodoMerge.class);
 
    public void Cuerpo(Semilla s) {
 
        // ordenamiento por merge sort
        logger.info("ordenando . . .");
        int[] arr = (int[]) s.dato;
        sort(arr);
        // fin del metodo
    }
 
    private int[] copia;
    private int[] helper; 
 
    public void sort(int[] a) {
        this.copia = a; 
        this.helper = new int[ a.length];
        mergesort(0,  a.length - 1);
    }
 
    private void mergesort(int p, int r) {
        if (p < r) {
            int q = p + (r - p) / 2;
            mergesort(p, q);
            mergesort(q + 1, r);
            merge(p, q, r);
        }
    }
 
    private void merge(int p, int q, int r) {
 
        for (int i = p; i <= r; i++) {
            helper[i] = copia[i];
        }
 
        int i = p;
        int j = q + 1;
        int k = p;
        while (i <= q && j <= r) {
            if (helper[i] <= helper[j]) {
                copia[k] = helper[i];
                i++;
            } else {
                copia[k] = helper[j];
                j++;
            }
            k++;
        }
        while (i <= q) {
            copia[k] = helper[i];
            k++;
            i++;
        }
 
    }
 
    @Override
    public List<Semilla> obtenSemillas() {
        List<Semilla> s = new ArrayList<Semilla>();
        // generacion de 30 semillas simples
        int total = 2500;
        for (int i = 1; i < total; i++) {
            //tamaño de la semilla
            s.add(generaSemilla(i * 50));
        }
        return s;
    }
 
    @Override
    public Semilla generaSemilla(int n) {
        // n sera el numero de elementos de un arreglo
        // de elementos aleatoriamente distribuidos de 1- 100
        int[] e = new int[n];
 
        Random rand = new Random();
        int max = 100;
        int min = 1;
 
        for (int i = 0; i < e.length; i++) {
            e[i] = rand.nextInt((max - min) + 1) + min;
        }
        return new Semilla(n, e);
    }
 
}