package Estacionamiento;

/**
 *
 * @author Alejandro Younes
 */
public abstract class Salida extends Puerta {

    int veces;

    public Salida(Estacionamiento estacionamiento, String nombre, int veces) {
        super(estacionamiento, nombre);
        this.veces = veces;
    }

    public abstract void sacar();

    @Override
    public void run() {
        this.sacar();
    }
}
