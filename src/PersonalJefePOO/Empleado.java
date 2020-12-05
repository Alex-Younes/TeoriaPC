package PersonalJefePOO;

/**
 *
 * @author Alejandro Younes
 */
public class Empleado extends Personal {

    public Empleado(String nombre, Saludo saludo) {
        super(nombre, saludo);
    }

    @Override
    public void run() {
        super.run();
        try {
            saludo.empleadoLlega();
            saludo.empleadoSaluda(nombre);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }
}
