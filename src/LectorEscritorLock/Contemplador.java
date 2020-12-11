package LectorEscritorLock;

/**
 *
 * @author Alejandro Younes
 */
public class Contemplador implements Runnable {

    protected final Sala sala;
    protected final int tiempo;
    private int vistas = 0;
    private int totales;

    public Contemplador(Sala sala, int tiempo) {
        this.sala = sala;
        this.tiempo = tiempo;
    }

    protected void ver() {
        try {
            Thread.sleep(tiempo); // Simula que esta viendo
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        vistas++;
    }

    @Override
    public void run() {
        totales = sala.ObtenerEspaciosTotales(); // Obtiene la cantidad de pinturas a exhibirse
        while (vistas < totales) { // Mientras no vio todas las pinturas
            if (vistas < sala.hayPintado()) { // Si vio menos pinturas de las pintadas
                sala.empezarVer();
                ver(); // Ve una pintura
                sala.terminarVer();
            } else { // Sino
                sala.esperar(); // Espera que un pintor le avise
            }
        }
    }
}
