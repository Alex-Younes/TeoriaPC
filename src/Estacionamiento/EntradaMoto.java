package Estacionamiento;

/**
 *
 * @author Alejandro Younes
 */
public class EntradaMoto extends Entrada {

    public EntradaMoto(Estacionamiento estacionamiento, String nombre, int veces) {
        super(estacionamiento, nombre, veces);
    }

    @Override
    public void ingresar() {
        for (int i = 0; i < veces; i++) {
            if (estacionamiento.ingresarMoto()) {
                System.out.println(nombre + ": Moto " + i + " pudo ingresar");
            } else {
                System.out.println(nombre + ": Moto " + i + " NO pudo ingresar");
            }
        }
    }
}
