package Filosofos;

/**
 *
 * @author Alejandro Younes
 */
public class Mesa {

    private final boolean[] tenOcu = new boolean[5];

    public synchronized void tomarTenedores(int posIzq, int posDer) {
        boolean tenIzq = tenOcu[posIzq];
        boolean tenDer = tenOcu[posDer];
        while (tenIzq || tenDer) {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
            tenIzq = tenOcu[posIzq];
            tenDer = tenOcu[posDer];
        }
        tenOcu[posIzq] = true;
        tenOcu[posDer] = true;
        System.out.println(nombre() + "empieza a comer");
    }

    public synchronized void dejarTenedores(int posIzq, int posDer) {
        tenOcu[posIzq] = false;
        tenOcu[posDer] = false;
        System.out.println(nombre() + "termina a comer");
        notifyAll();
    }

    private String nombre() {
        return Thread.currentThread().getName();
    }
}
