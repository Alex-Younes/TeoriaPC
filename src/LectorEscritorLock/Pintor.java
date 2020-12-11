package LectorEscritorLock;

/**
 *
 * @author Alejandro Younes
 */
public class Pintor extends Contemplador implements Runnable {

    private final int tiempoPintar;

    public Pintor(Sala sala, int tiempoVer, int tiempoPintar) {
        super(sala, tiempoVer);
        this.tiempoPintar = tiempoPintar;
    }

    private void pintar() {
        try {
            Thread.sleep(tiempoPintar); // Simula que esta pintando
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void run() {
        while (!sala.llena()) { // Mientras haya espacio para otra pintura
            sala.empezarPintar(); // Intenta ocupar el pincel
            if (!sala.llena()) { // Si la sala no fue llenada despues de obtener el pincel
                pintar(); // Empieza a pintar
                sala.terminarPintar(); // Deja su pintura en la sala y la empieza a ver
                super.ver(); // Contempla su propia pintura exhibida
                sala.terminarVer(); // Deja de ver su pintura
            }
        }
    }
}
