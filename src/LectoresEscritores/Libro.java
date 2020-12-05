package LectoresEscritores;

public class Libro {

    private int cantPaginasEscritas;
    private int cantLectoresActual;
    private final int maxPaginas;
    private boolean escribirLibro;//hay un escritor que quiere escribir
    private boolean escribiendo;//hay un escritor escribiendo
    private boolean prioridadLector;
    private int escritoresSuc;
    private int maxEscritoresSuc;

    public Libro(int maxPaginas, int maxEscritoresSuc) {
        this.cantPaginasEscritas = 0;
        this.cantLectoresActual = 0;
        this.maxPaginas = maxPaginas;
        this.escribirLibro = false;
        this.escribiendo = false;
        this.maxEscritoresSuc = maxEscritoresSuc;
        this.escritoresSuc = 0;
        this.prioridadLector = false;
    }

    public boolean finalizado() {
        return cantPaginasEscritas == maxPaginas; //retorna true si estan todas las paginas escritas
    }

    public synchronized void empezarLeer(int id) throws InterruptedException {
        while (escribirLibro || escribiendo) {
            System.out.println("** Lector " + id + " no puede leer aun! **");
            this.wait();
        }
        if (prioridadLector) {
            prioridadLector = false;
        }
        System.out.println("** Lector " + id + " empieza a leer! **");
        cantLectoresActual++;
    }

    public synchronized boolean empezarEscribir(int id) throws InterruptedException {
        while (cantLectoresActual != 0 || escribiendo || prioridadLector) {
            if (!prioridadLector) {
                escribirLibro = true;
            }
            System.out.println("** Escritor " + id + " no puede escribir aun! **");
            this.wait();
        }
        escribirLibro = false;
        if (!finalizado()) {
            escribiendo = true;
            System.out.println("** Escritor " + id + " empieza a escribir! **");
            escritoresSuc++;
            if (escritoresSuc == maxEscritoresSuc) {
                prioridadLector = true;
                escritoresSuc = 0;
            }
        }
        return escribiendo;
    }

    public synchronized void terminarLeer(int id) {
        cantLectoresActual--;
        System.out.println("** Lector " + id + " Termina de leer! **");
        notifyAll();
    }

    public synchronized void terminarEscribir(int id) {
        cantPaginasEscritas++;
        System.out.println("** Escritor " + id + " Termina de escribir! **");
        escribiendo = false;
        notifyAll();
    }

    public boolean hayEscrito(int leidas) { // Lector usa para saber si hay algo escrito
        return (leidas < cantPaginasEscritas);
    }

    public synchronized void esperar(int leidas) {
        while (leidas >= cantPaginasEscritas) {
            try {
                this.wait();
            } catch (InterruptedException ex) {
            }
        }
    }

    public int obtenerPaginasTotales() {
        return maxPaginas;
    }
}
