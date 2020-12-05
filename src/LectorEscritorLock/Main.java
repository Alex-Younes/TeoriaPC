package LectorEscritorLock;

/**
 *
 * @author Alejandro Younes
 */
public class Main {

    public static void main(String[] args) {
        int numPin = 2;
        int numCon = 5;
        int pinMax = 10;
        Sala sala = new Sala(pinMax);
        Pintor[] pintor = new Pintor[numPin];
        Contemplador[] contemplador = new Contemplador[numCon];
        Thread[] pintorHilo = new Thread[numPin];
        Thread[] contempladorHilo = new Thread[numCon];
        for (int i = 0; i < numPin; i++) {
            pintor[i] = new Pintor(sala);
            pintorHilo[i] = new Thread(pintor[i], "Pintor " + (i + 1) + ": ");
            pintorHilo[i].start();
        }
        for (int i = 0; i < numCon; i++) {
            contemplador[i] = new Contemplador(sala);
            contempladorHilo[i] = new Thread(contemplador[i], "Contemplador " + (i + 1) + ": ");
            contempladorHilo[i].start();
        }
    }
}
