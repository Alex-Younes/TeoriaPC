package Estacionamiento;

/**
 *
 * @author Alejandro Younes
 */
public class Main {

    public static void main(String[] args) {
        Estacionamiento estacionamiento = new Estacionamiento();
        Puerta[] puertas = new Puerta[5];
        Thread[] hilos = new Thread[5];
        puertas[0] = new EntradaAuto(estacionamiento, "EA 1", 10);
        puertas[1] = new EntradaAuto(estacionamiento, "EA 2", 10);
        puertas[2] = new EntradaMoto(estacionamiento, "EM", 10);
        puertas[3] = new SalidaAuto(estacionamiento, "SA", 5);
        puertas[4] = new SalidaMoto(estacionamiento, "SM", 5);
        for (int i = 0; i < 5; i++) {
            hilos[i] = new Thread(puertas[i]);
            hilos[i].start();
        }
    }
}
