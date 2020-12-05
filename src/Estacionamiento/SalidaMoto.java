package Estacionamiento;

/**
 *
 * @author Alejandro Younes
 */
public class SalidaMoto extends Salida {

    public SalidaMoto(Estacionamiento estacionamiento, String nombre, int veces) {
        super(estacionamiento, nombre, veces);
    }

    @Override
    public void sacar() {
        int numero = 0;
        for (int i = 0; i < veces; i++) {
            while (estacionamiento.hayMotos()) {
                estacionamiento.sacarMoto();
                System.out.println("Moto " + numero + " salio");
                numero = numero + 1;
            }
            System.out.println(nombre + ": no hay motos, durmiendo");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                System.out.println(nombre + ": Excepcion en el sleep");
            }
        }
    }
}
