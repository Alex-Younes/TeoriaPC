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
        cerrojoLecEsc.readLock().lock();
        System.out.println(nombre() + "comienza a ver pintura");
    }

    public void terminarVer() {
        System.out.println(nombre() + "termina de ver pintura");
        cerrojoLecEsc.readLock().unlock();
    }

    public void empezarPintar() {
        cerrojoLecEsc.writeLock().lock();
        if (!llena()) {
            System.out.println(nombre() + "comienza a pintar");
        } else {
            cerrojoLecEsc.writeLock().unlock();
        }
    }

    public void terminarPintar() {
            pinturasHechas++;
            System.out.println(nombre() + "termina de hacer la pintura " + pinturasHechas);
        cerrojoLecEsc.readLock().lock();
        System.out.println(nombre() + "comienza a ver pintura");
        cerrojoLecEsc.writeLock().unlock();
        cerrojoCon.lock();
        hayPintado.signalAll();
        cerrojoCon.unlock();
    }

    public boolean llena() {
        cerrojoLecEsc.writeLock().lock();
        boolean res = (pinturasHechas == espaciosTotales);
        cerrojoLecEsc.writeLock().unlock();
        return res;
    }

    public int ObtenerEspaciosTotales() {
        return espaciosTotales;
    }

    public int hayPintado() {
        cerrojoLecEsc.readLock().lock();
        int res = pinturasHechas;
        cerrojoLecEsc.readLock().unlock();
        return res;
    }

    public void esperar() {
        cerrojoCon.lock();
        try {
            hayPintado.await();
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        cerrojoCon.unlock();
    }

    private String nombre() {
        return Thread.currentThread().getName();
    }
}
