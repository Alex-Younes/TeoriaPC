package LectorEscritor;

/**
 *
 * @author Alejandro Younes
 */
public class Escritor implements Runnable {

    private final Libro libro;

    public Escritor(Libro libro) {
        this.libro = libro;
    }
    
    private void escribir(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void run() {
        while(!libro.finalizado()){
            libro.empezarEscribir();
            escribir();
            libro.terminarEscribir();
        }
    }
}
