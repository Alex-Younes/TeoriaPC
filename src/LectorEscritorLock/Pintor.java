package LectorEscritorLock;

/**
 *
 * @author Alejandro Younes
 */
public class Pintor extends Contemplador implements Runnable {

    public Pintor(Sala sala) {
        super(sala);
    }

    private void pintar() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void run() {
        while (!sala.llena()) {
            sala.empezarPintar();
            if (!sala.llena()) {
                pintar();
                sala.terminarPintar();
                super.ver();
                sala.terminarVer();
            }
        }
    }
}
