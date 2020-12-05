package ProdConsMonitor;

/**
 *
 * @author Alejandro Younes
 */
public class Consumidor implements Runnable {

    private final Buffer buffer;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            buffer.consumirElemento();
            try {
                Thread.sleep(1000); // Demora 1 segundo en consumir
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
    }

}
