package ProdConsMonitor;

/**
 *
 * @author Alejandro Younes
 */
public class Productor implements Runnable {

    private final Buffer buffer;

    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            buffer.producirElemento((int) ((Math.random()) * 10));
            try {
                Thread.sleep(1000); // Demora 1 segundo en producir
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
    }
}
