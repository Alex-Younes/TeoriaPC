package Filosofos;

/**
 *
 * @author Alejandro Younes
 */
public class Main {

    public static void main(String[] args) {
        int numFil = 5;
        int tenIzq, tenDer;
        Mesa mesa = new Mesa();
        Filosofo[] filosofo = new Filosofo[numFil];
        Thread[] filosofoHilo = new Thread[numFil];
        for (int i = 0; i < numFil; i++) {
            tenIzq = i % 5;
            tenDer = (i + 1) % 5;
            filosofo[i] = new Filosofo(mesa, tenIzq, tenDer);
            filosofoHilo[i] = new Thread(filosofo[i], "Filosofo " + i + ": ");
            filosofoHilo[i].start();
        }
    }
}
