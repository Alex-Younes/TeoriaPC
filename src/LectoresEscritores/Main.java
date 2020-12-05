package LectoresEscritores;

public class Main {

    public static void main(String[] args) {
        int maxPaginas = 10;
        int cantEscritores = 4;
        int cantLectores = 10;
        int maxEscritoresSuc = 2;
        int maxLectoresSuc = 4;

        Libro libro = new Libro(maxPaginas, maxEscritoresSuc);

        Thread[] escritores = new Thread[cantEscritores];
        Thread[] lectores = new Thread[cantLectores];

        for (int i = 0; i < cantEscritores; i++) {
            escritores[i] = new Thread(new Escritor(i + 1, libro));
            escritores[i].start();
        }
        for (int i = 0; i < cantLectores; i++) {
            lectores[i] = new Thread(new Lector(i + 1, libro));
            lectores[i].start();
        }
    }
}
