package BarberoConMonitores;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Faustino
 * @Correciones para la profe: Alejandro
 */
public class Barberia {

    private boolean hayCliente;
    private int sillas;
    private int ocupadas = 0;
    private boolean barberoOcupado;
    private boolean corteTerminado;

    public Barberia(int sillas) {
        this.sillas = sillas;
        this.hayCliente = false;
    }

    public synchronized void afeitar(String color) throws InterruptedException {
        while (!hayCliente) { // Mientras no haya cliente sentado
            System.out.println(color + Thread.currentThread().getName() + "------- ZZZ duerme...");
            this.wait(); // Espera
            System.out.println(color + Thread.currentThread().getName() + "!!!-------------- se despierta.");
        }
        System.out.println("Empieza a cortar el pelo");
        Thread.sleep(2500);
        System.out.println(color + Thread.currentThread().getName() + " TERMINA DE CORTAR PELO");
        this.hayCliente = false; // Se indica que no hay cliente sentado
        this.corteTerminado = true; // Indica que termino de cortar
        this.notifyAll(); // Avisa al cliente y al que siga
    }

    public synchronized boolean serAtendido(String color) throws InterruptedException {
        boolean res = sillas > ocupadas;
        if (res) {
            System.out.println(color + Thread.currentThread().getName() + " se sienta en silla de espera");
            ocupadas++;
            while (barberoOcupado) { // Mientras el barbero este atendiendo
                this.wait(); // Espera
            }
            ocupadas--;
            this.barberoOcupado = true;
        }
        return res;
    }

    public synchronized void terminarCorte(String color) {
        this.barberoOcupado = false; // Indica que el barbero esta desocupado
        this.notifyAll(); // Despierta un cliente
    }

    public synchronized void recibirCorte(String color) throws InterruptedException {
        hayCliente = true; // Indica al barbero que esta sentado
        corteTerminado = false; // Indica que le tiene que cortar
        this.notifyAll(); // Despierta al barbero
        System.out.println(color + Thread.currentThread().getName() + " ------recibe corte----");
        while (!corteTerminado) { // Mientras no le hayan terminado de cortar
            this.wait(); // Espera
        }
        System.out.println(color + Thread.currentThread().getName() + " ++++++++++++++++se va con corte nuevo");
    }
}
