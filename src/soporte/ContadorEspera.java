package soporte;

public class ContadorEspera extends Contador {

    public ContadorEspera(Integer contador) {
        super(contador);
    }

    public static Contador getInstance(){
        return new ContadorEspera(0);
    }

    public static Contador getInstance(Integer contador){
        return new ContadorEspera(contador);
    }
}
