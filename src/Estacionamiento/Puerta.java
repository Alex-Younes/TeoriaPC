package Estacionamiento;

/**
 *
 * @author Alejandro Younes
 */
public abstract class Puerta implements Runnable {

    Estacionamiento estacionamiento;
    String nombre;

    public Puerta(Estacionamiento estacionamiento, String nombre) {
        this.estacionamiento = estacionamiento;
        this.nombre = nombre;
    }

    @Override
    public abstract void run();
}
