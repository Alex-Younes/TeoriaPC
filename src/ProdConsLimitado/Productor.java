package ProdConsLimitado;

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
        while(true){
            buffer.producirElemento((int)((Math.random())*10));
        }
    }
}
