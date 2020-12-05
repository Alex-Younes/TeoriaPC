package PersonalJefePOO;

/**
 *
 * @author Alejandro Younes
 */
public abstract class Personal implements Runnable​ {

    final String nombre;
    final Saludo saludo;

    public Personal(String nombre, Saludo saludo) {
        this.nombre = nombre;
        this.saludo = saludo;
    }
 
    @Override
    public void run() {
        System.out.println("(" + nombre + " llega)");
    }
}
