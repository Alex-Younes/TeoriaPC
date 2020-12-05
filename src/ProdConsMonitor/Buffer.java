package ProdConsMonitor;

/**
 *
 * @author Alejandro Younes
 */
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

    private final int n;
    private final int[] elementos;
    private int punProductor;
    private int punConsumidor;
    private int ocupados;
    private final ReentrantLock mutexOcupados;
    private final Object monConsumidor;
    private final Object monProductor;
    private boolean conConsumidor;
    private boolean conProductor;

    public Buffer(int elementos) {
        this.n = elementos;
        this.elementos = new int[n];
        this.punProductor = -1;
        this.punConsumidor = -1;
        this.ocupados = 0;
        this.mutexOcupados = new ReentrantLock();
        this.monConsumidor = new Object();
        this.monProductor = new Object();
        this.conConsumidor = false;
        this.conProductor = false;
    }

    public void producirElemento(int elemento) {
        synchronized (monProductor) {
            conProductor = false;
            while (!conProductor) {
                mutexOcupados.lock();
                if (ocupados == n) {
                    mutexOcupados.unlock();
                    try {
                        monProductor.wait(); // Quiere llenar un espacio vacio
                    } catch (InterruptedException ex) {
                        System.err.println("Error en el wait de monProductor");
                    }
                } else {
                    conProductor = true;
                    mutexOcupados.unlock();
                }
            }
        }
        punProductor = (punProductor + 1) % n; // Avanza de forma circular
        System.out.println(nombre() + ": produce " + elemento + " en " + punProductor);
        elementos[punProductor] = elemento; // marca el espacio
        mutexOcupados.lock();
        ocupados++;
        mutexOcupados.unlock();
        synchronized (monConsumidor) {
            monConsumidor.notifyAll(); // Espacio llenado, avisa a consumidores
        }
    }

    public void consumirElemento() {
        synchronized (monConsumidor) {
            conConsumidor = false;
            while (!conConsumidor) {
                mutexOcupados.lock();
                if (ocupados == 0) {
                    mutexOcupados.unlock();
                    try {
                        monConsumidor.wait(); // Quiere vaciar un espacio lleno
                    } catch (InterruptedException ex) {
                        System.err.println("Error en el wait de monConsumidor");
                    }
                } else {
                    conConsumidor = true;
                    mutexOcupados.unlock();
                }
            }
        }
        punConsumidor = (punConsumidor + 1) % n; // Avanza de forma circular
        System.out.println(nombre() + ": consume elemento en " + punConsumidor);
        elementos[punConsumidor] = -1; // Vacia el espacio
        mutexOcupados.lock();
        ocupados--;
        mutexOcupados.unlock();
        synchronized (monProductor) {
            monProductor.notifyAll(); // Espacio vaciado, avisa
        }
    }

    private String nombre() {
        return Thread.currentThread().getName();
    }
}
