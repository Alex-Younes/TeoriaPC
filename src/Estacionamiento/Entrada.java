package Estacionamiento;

/**
 *
 * @author Alejandro Younes
 */
public abstract class Entrada extends Puerta {

    int veces;

    public Entrada(Estacionamiento estacionamiento, String nombre, int veces) {
        super(estacionamiento, nombre);
        this.veces = veces;
    }

    public abstract void ingresar();

    @Override
    public void run() {
        this.ingresar();
    }

}
