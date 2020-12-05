package LectoresEscritores;

public class Lector implements Runnable {

    private int id;
    private Libro libro;
    private int leidas;

    public Lector(int id, Libro libro) {
        this.id = id;
        this.libro = libro;
        this.leidas = 0;
    }

    @Override
    public void run() {
        try {
            int totales = libro.obtenerPaginasTotales();
            while (leidas < totales) {
                if (libro.hayEscrito(leidas)) {
                    libro.empezarLeer(id);
                    Thread.sleep(3000);
                    libro.terminarLeer(id);
                    leidas++;
                } else {
                    libro.esperar(leidas);
                }
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
