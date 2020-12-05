package LectorEscritor;

/**
 *
 * @author Alejandro Younes
 */
public class Libro {

    private final int paginasTotales;
    private int paginasEscritas = 0;
    private int lectoresActuales = 0;
    private boolean hayEscritor = false;

    public Libro(int paginasTotales) {
        this.paginasTotales = paginasTotales;
    }

    public synchronized void empezarLeer() {
        while (hayEscritor) {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
        lectoresActuales++;
        System.out.println(nombre() + "comienza a leer");
    }

    public synchronized void terminarLeer() {
        lectoresActuales--;
        System.out.println(nombre() + "termina de leer");
        notifyAll();
    }

    public synchronized void empezarEscribir() {
        while (hayEscritor || lectoresActuales > 0) {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
        hayEscritor = true;
        System.out.println(nombre() + "comienza a escribir");
    }

    public synchronized void terminarEscribir() {
        paginasEscritas++;
        hayEscritor = false;
        System.out.println(nombre() + "termina a escribir");
        notifyAll();
    }

    public boolean finalizado() { // Escritor usa para saber si libro finalizado
        return paginasEscritas == paginasTotales;
    }

    public boolean hayEscrito(int leidas) { // Lector usa para saber si hay algo escrito
        return (leidas < paginasEscritas);
    }

    public synchronized void esperar(int leidas) {
        while (leidas >= paginasEscritas) {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
    }

    public int obtenerPaginasTotales() {
        return paginasTotales;
    }

    private String nombre() {
        return Thread.currentThread().getName();
    }
}
