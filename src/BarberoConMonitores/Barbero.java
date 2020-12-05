package BarberoConMonitores;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Faustino
 */
public class Barbero implements Runnable {

    String color;
    Barberia sillon;
    int cant;

    public Barbero(String color, Barberia sillon, int cant) {
        this.color = color;
        this.sillon = sillon;
        this.cant = cant;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Barberia getSillon() {
        return sillon;
    }

    public void setSillon(Barberia sillon) {
        this.sillon = sillon;
    }

    @Override
    public void run() {
        double time = System.currentTimeMillis();
        while (System.currentTimeMillis() <= time + 1000000) {
            try {
                sillon.afeitar(color);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
        System.out.println(color + Thread.currentThread().getName() + " terminó la jornada laboral.");
    }
}
