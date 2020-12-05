package ProdConsLimitado;

/**
 *
 * @author Alejandro Younes
 */
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

    private final int n;
    private final int[] elementos;
    private int puntero;
    private final ReentrantLock cerrojoMutex = new ReentrantLock();
    private final Semaphore semVacios;
    private final Semaphore semLlenos;

    public Buffer(int elementos) {
        this.n = elementos;
        this.elementos = new int[n];
        this.puntero = -1;
        this.semVacios = new Semaphore(elementos);
        this.semLlenos = new Semaphore(0);
    }

    public void producirElemento(int elemento) {
        try {
            semVacios.acquire(); // Quiere ocupar un espacio vacio
            cerrojoMutex.lock(); // Se asegura que nadie mas manipule el buffer
            puntero = puntero + 1; // Se va al espacio siguiente del buffer
            System.out.println(nombre() + ": inserta " + elemento + " en espacio " + puntero);
            Thread.sleep(1000); // Demora 1 segundo en producir
            elementos[puntero] = elemento; // Llena el espacio
            semLlenos.release(); // Espacio llenado, avisa
        } catch (InterruptedException ex) {
            System.err.println("Error al adquirir semVacios");
        } finally {
            cerrojoMutex.unlock(); // Permite a otros manipular el buffer
        }
    }

    public void consumirElemento() {
        try {
            semLlenos.acquire(); // Quiere vaciar un espacio lleno
            cerrojoMutex.lock(); // Se asegura que nadie mas manipule el buffer
            System.out.println(nombre() + ": consume elemento en espacio " + puntero);
            Thread.sleep(1000); // Demora 1 segundo en consumir
            elementos[puntero] = -1; // Vacia el espacio
            puntero = puntero - 1; // Se va al espacio anterior del buffer
            semVacios.release(); // Espacio vaciado, avisa
        } catch (InterruptedException ex) {
            System.err.println("Error al adquirir semLlenos");
        } finally {
            cerrojoMutex.unlock(); // Permite a otros manipular el buffer
        }
    }
    
    private String nombre(){
        return Thread.currentThread().getName();
    }
}
