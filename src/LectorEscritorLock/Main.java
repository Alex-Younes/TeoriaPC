package LectorEscritorLock;

import java.util.Random;

/**
 *
 * @author Alejandro Younes
 */
public class Main {

    /*
     Enunciado:
     Se dispone de una sala de arte donde se exponen pinturas
     hechas en la misma exposición, hay muchos pintores
     pero como solo hay un pincel un pintor puede pintar a la vez.
     A la exposición asisten muchos contempladores de arte.
     Cuando un pintor termina de colocar su obra, es el primero
     en contemplarla, los asistentes también pueden verla junto a el.
     Además, como cada pintor necesita concentración para inspirarse 
     no pueden haber contempladores viendo las demás obras.
     */
    public static void main(String[] args) {
        Random generador = new Random();
        int tiempoVer, tiempoPintar;
        int numPin = 2; // Numero de pintores
        int numCon = 5; // Numero de contempladores
        int pinMax = 10; // Numero de pinturas máximas en la sala
        Sala sala = new Sala(pinMax); // Creación de sala
        Pintor[] pintor = new Pintor[numPin];
        Contemplador[] contemplador = new Contemplador[numCon];
        Thread[] pintorHilo = new Thread[numPin];
        Thread[] contempladorHilo = new Thread[numCon];
        for (int i = 0; i < numPin; i++) { // Creación de pintores
            tiempoVer = 500 + generador.nextInt(1500); // Tiempo aleatorio mirando de cada uno
            tiempoPintar = 1000 + generador.nextInt(2000); // Tiempo aleatorio pintando de cada uno
            pintor[i] = new Pintor(sala,tiempoVer,tiempoPintar);
            pintorHilo[i] = new Thread(pintor[i], "Pintor " + (i + 1) + ": ");
            pintorHilo[i].start();
        }
        for (int i = 0; i < numCon; i++) { // Creación de contempladores
            tiempoVer = 500 + generador.nextInt(1500); // Tiempo aleatorio mirando de cada uno
            contemplador[i] = new Contemplador(sala,tiempoVer);
            contempladorHilo[i] = new Thread(contemplador[i], "Contemplador " + (i + 1) + ": ");
            contempladorHilo[i].start();
        }
    }
}
