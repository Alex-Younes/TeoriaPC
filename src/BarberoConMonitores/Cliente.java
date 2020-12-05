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
public class Cliente implements Runnable {

    String color;
    Barberia sillon;

    public Cliente(String color, Barberia sillon) {
        this.color = color;
        this.sillon = sillon;
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
        boolean res = false;
        while (true && !res) {
            System.out.println(this.color + Thread.currentThread().getName() + " .------ Va a la peluqueria.");
            try {
                res = sillon.serAtendido(color);
                if (!res) {
                    System.out.println(color + Thread.currentThread().getName() + " se fue de la peluquerìa porque no habia sillas disponibles.");
                    this.pasear(color);
                } else {
                    sillon.recibirCorte(color);
                    sillon.terminarCorte(color);
                }
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
    }

    private void pasear(String color) {
        System.out.println(color + Thread.currentThread().getName() + " ~~~~~~ se va a P A S E A R ~~~~~~~");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }
}
