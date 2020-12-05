package Estacionamiento;

/**
 *
 * @author Alejandro Younes
 */
public class EntradaAuto extends Entrada {

    public EntradaAuto(Estacionamiento estacionamiento, String nombre, int veces) {
        super(estacionamiento, nombre, veces);
    }

    @Override
    public void ingresar() {
        for (int i = 0; i < veces; i++) {
            if (estacionamiento.ingresarAuto()) {
                System.out.println(nombre + ": Auto " + i + " pudo ingresar");
            } else {
                System.out.println(nombre + ": Auto " + i + " NO pudo ingresar");
            }
        }
    }
}
