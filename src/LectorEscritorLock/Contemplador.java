package LectorEscritorLock;

/**
 *
 * @author Alejandro Younes
 */
public class Contemplador implements Runnable {

    protected final Sala sala;
    private int vistas = 0;
    private int totales;

    public Contemplador(Sala sala) {
        this.sala = sala;
    }

    protected void ver() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        vistas++;
    }

    @Override
    public void run() {
        totales = sala.ObtenerEspaciosTotales();
        while (vistas < totales) {
            if (vistas < sala.hayPintado()) {
                sala.empezarVer();
                ver();
                sala.terminarVer();
            } else {
                sala.esperar();
            }
        }
    }
}
