package LectorEscritor;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejandro Younes
 */
public class Lector implements Runnable {

    private final Libro libro;
    private int leidas = 0;
    private int totales;

    public Lector(Libro libro) {
        this.libro = libro;
    }

    private void leer() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        leidas++;
    }

    @Override
    public void run() {
        totales = libro.obtenerPaginasTotales();
        while (leidas < totales) {
            if (libro.hayEscrito(leidas)) {
                libro.empezarLeer();
                leer();
                libro.terminarLeer();
            } else {
                libro.esperar(leidas);
            }
        }
    }
}
