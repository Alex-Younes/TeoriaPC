package PersonalJefePOO;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Alejandro Younes
 */
public class Saludo {

    private final Semaphore semMutex = new Semaphore(1);
    private final Semaphore semLlegaron = new Semaphore(0);
    private final int numEmp;
    private boolean jefeSaludo = false;
    private int llegaron = 0;

    public Saludo(int numEmp) {
        this.numEmp = numEmp;
    }

    public void empleadoLlega() throws InterruptedException {
        semMutex.acquire();
        llegaron++; // Empleado indica que llego
        if (llegaron == numEmp) { // Si llegaron todos los empleados
            semLlegaron.release(); // Avisa al jefe
        }
        semMutex.release();
    }

    public synchronized void empleadoSaluda(String empleado) throws InterruptedException {
        while (!jefeSaludo) { // Si no saludo el jefe
            wait(); // Espera que salude el jefe
        }
        System.out.println(empleado + "> Buenos dias jefe!");
    }

    public void jefeLlega() throws InterruptedException {
        semMutex.acquire();
        if (llegaron < numEmp) { // Si no llegaron todos los empleados
            semMutex.release();
            System.out.println("(Esperando...)");
            semLlegaron.acquire(); // Espera a los empleados
            semMutex.acquire();
        }
        semMutex.release();
    }

    public synchronized void jefeSaluda(String jefe) {
        System.out.println(jefe + "> Buenos dias!");
        jefeSaludo = true; // Indica que saludo
        notifyAll(); // Avisa a los empleados
    }
}
