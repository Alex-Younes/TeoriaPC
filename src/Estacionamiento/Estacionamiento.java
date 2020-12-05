package Estacionamiento;

/**
 *
 * @author Alejandro Younes
 */
public class Estacionamiento {

    private final int MAXMOTOS = 10;
    private final int MAXAUTOS = 10;
    private int motos = 0;
    private int autos = 0;

    public int getMotos() {
        return motos;
    }

    public int getAutos() {
        return autos;
    }

    public boolean hayMotos() {
        boolean res;
        if (motos > 0) {
            res = true;
        } else {
            res = false;
        }
        return res;
    }

    public boolean hayAutos() {
        boolean res;
        if (autos > 0) {
            res = true;
        } else {
            res = false;
        }
        return res;
    }

    public synchronized boolean ingresarAuto() {
        boolean res;
        if (autos < MAXAUTOS) {
            autos = autos + 1;
            res = true;
        } else {
            res = false;
        }
        return res;
    }

    public synchronized void sacarAuto() {
        autos = autos - 1;
    }

    public synchronized boolean ingresarMoto() {
        boolean res;
        if (motos < MAXMOTOS) {
            motos = motos + 1;
            res = true;
        } else {
            res = false;
        }
        return res;
    }

    public synchronized void sacarMoto() {
        motos = motos - 1;
    }
}
