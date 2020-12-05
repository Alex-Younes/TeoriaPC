package PersonalJefePOO;

/**
 *
 * @author Alejandro Younes
 */
public class TestSaludo {

    public static void main(String argv[]) {
        String[] nombresEmpleados = {"Pablo", "Luis", "Andrea", "Pedro", "Paula"};
        int numEmp = nombresEmpleados.length;
        Saludo hola = new Saludo(numEmp);
        Thread[] elPersonal = new Thread[numEmp + 1];
        elPersonal[0] = new Thread(new Jefe("JEFE",hola));
        for (int i = 1; i <= numEmp; i++) {
            elPersonal[i] = new Thread(new Empleado(nombresEmpleados[i - 1],hola));
        }
        for (int i = 0; i <= numEmp; i++) {
            elPersonal[i].start();
        }
        for (int i = 0; i <= numEmp; i++) {
            try {
                elPersonal[i].join();
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
        System.out.println("LISTO, ahora que todos han saludado - a trabajar");
    }
}
