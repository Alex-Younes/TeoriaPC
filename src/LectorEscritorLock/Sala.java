package LectorEscritorLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author Alejandro Younes
 */
public class Sala {

    private final int espaciosTotales;
    private int pinturasHechas = 0;
    private final ReentrantReadWriteLock cerrojoLecEsc = new ReentrantReadWriteLock(true);
    private final ReentrantLock cerrojoCon = new ReentrantLock();
    private final Condition hayPintado = cerrojoCon.newCondition();

    public Sala(int espaciosTotales) {
        this.espaciosTotales = espaciosTotales;
    }

    public void empezarVer() {
        cerrojoLecEsc.readLock().lock(); // Evita que pintores entren
        System.out.println(nombre() + "comienza a ver pintura");
    }

    public void terminarVer() {
        System.out.println(nombre() + "termina de ver pintura");
        cerrojoLecEsc.readLock().unlock(); // Permite que pintores entren
    }

    public void empezarPintar() {
        cerrojoLecEsc.writeLock().lock(); // Pintor se asegura ser el unico dentro de secciones criticas
        if (!llena()) { // Si hay espacio para otra pintura
            System.out.println(nombre() + "comienza a pintar");
        } else {
            cerrojoLecEsc.writeLock().unlock(); // Pintores permite a contempladores o a un pintor entrar
        }
    }

    public void terminarPintar() {
        pinturasHechas++; // Agrega otra pintura a la sala
        System.out.println(nombre() + "termina de hacer la pintura " + pinturasHechas);
        cerrojoLecEsc.readLock().lock(); // Se convierte en contemplador
        System.out.println(nombre() + "comienza a ver pintura");
        cerrojoLecEsc.writeLock().unlock(); // Permite a otros contempladores entrar
        cerrojoCon.lock();
        try {
            hayPintado.signalAll(); // Avisa a los contempladores esperando
        } finally {
            cerrojoCon.unlock();
        }
    }

    public boolean llena() {
        cerrojoLecEsc.writeLock().lock(); // Pintor evita que otro modifique el valor
        try {
            boolean res = pinturasHechas == espaciosTotales; // Pintor consulta si todavía faltan pinturas
            return res;
        } finally {
            cerrojoLecEsc.writeLock().unlock(); // Pintor permite que otro modifique el valor
        }
    }

    public int ObtenerEspaciosTotales() {
        return espaciosTotales; // Contemplador consulta cuantas pinturas habrá
    }

    public int hayPintado() {
        cerrojoLecEsc.readLock().lock(); // Contempladores aseguran que ningun pintor pinte
        try {
            int res = pinturasHechas; // Contempladores obtienen la cantidad de pinturas actuales
            return res;
        } finally {
            cerrojoLecEsc.readLock().unlock(); // Contempladores permiten que algun pintor pinte
        }
    }

    public void esperar() {
        cerrojoCon.lock();
        try {
            hayPintado.await(); // Contemplador espera a que haya mas pinturas
        } catch (InterruptedException ex) {
            System.out.println(ex);
        } finally {
            cerrojoCon.unlock();
        }
    }

    private String nombre() {
        return Thread.currentThread().getName();
    }
}
