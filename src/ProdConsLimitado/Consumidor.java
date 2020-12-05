package ProdConsLimitado;

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
        }
    }

}
