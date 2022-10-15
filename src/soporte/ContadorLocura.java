package soporte;

public class ContadorLocura extends Contador {

    public ContadorLocura(Integer contador) {
        super(contador);
    }

    public static Contador getInstance(){
        return new ContadorLocura(0);
    }

    public static Contador getInstance(Integer contador){
        return new ContadorLocura(contador);
    }
}
