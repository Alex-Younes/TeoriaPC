package LectorEscritor;

/**
 *
 * @author Alejandro Younes
 */
public class Main {

    public static void main(String[] args) {
        int numEsc = 1;
        int numLec = 3;
        Libro libro = new Libro(5);
        Escritor[] escritor = new Escritor[numEsc];
        Lector[] lector = new Lector[numLec];
        Thread[] escritorHilo = new Thread[numEsc];
        Thread[] lectorHilo = new Thread[numLec];
        for (int i = 0; i < numEsc; i++) {
            escritor[i] = new Escritor(libro);
            escritorHilo[i] = new Thread(escritor[i], "Escritor " + i + ": ");
            escritorHilo[i].start();
        }
        for (int i = 0; i < numLec; i++) {
            lector[i] = new Lector(libro);
            lectorHilo[i] = new Thread(lector[i], "Lector " + i + ": ");
            lectorHilo[i].start();
        }
    }
}

/* Problemas posibles
1. Escritor no para de entrar a escribir (inaniccion de lectores)
2. Lectores nuevos no paran de entrar (inaniccion de escritor)
*/