package PersonalJefePOO;

/**
 *
 * @author Alejandro Younes
 */
public class Jefe extends Personal {

    public Jefe(String nombre, Saludo saludo) {
        super(nombre, saludo);
    }

    @Override
    public void run() {
        super.run();
        try {
            saludo.jefeLlega();
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        saludo.jefeSaluda(nombre);
    }
}
