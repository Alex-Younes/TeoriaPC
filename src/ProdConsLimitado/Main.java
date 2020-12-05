package ProdConsLimitado;

/**
 *
 * @author Alejandro Younes
 */
public class Main {

    public static void main(String[] args) {
        int n = 6; // Cantidad total de hilos
        int productores = 2; // Cantidad de hilos productores
        Buffer buffer = new Buffer(10);
        Productor productor = new Productor(buffer);
        Consumidor consumidor = new Consumidor(buffer);
        Thread[] hilos = new Thread[n];
        for (int i = 0; i < productores; i++) { // Crea los hilos productores
            hilos[i] = new Thread(productor, "Productor " + i);
        }
        for (int i = productores; i < n; i++) { // Crea los hilos consumidores
            hilos[i] = new Thread(consumidor, "Consumidor " + (i - productores));
        }
        for (int i = 0; i < n; i++) { // Arranca todos los hilos
            hilos[i].start();
        }
    }
}
