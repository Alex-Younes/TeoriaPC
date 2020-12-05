package Filosofos;

/**
 *
 * @author Alejandro Younes
 */
public class Filosofo implements Runnable {

    private final Mesa mesa;
    private final int tenIzq;
    private final int tenDer;

    public Filosofo(Mesa mesa, int tenIzq, int tenDer) {
        this.mesa = mesa;
        this.tenIzq = tenIzq;
        this.tenDer = tenDer;
    }

    private void comer() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void run() {
        System.out.println(nombre() + "tiene hambre");
        mesa.tomarTenedores(tenIzq, tenDer);
        comer();
        mesa.dejarTenedores(tenIzq, tenDer);
    }

    private String nombre() {
        return Thread.currentThread().getName();
    }
}
