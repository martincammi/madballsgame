package soporte;

public class Contador {

    private Integer contador;

    public Contador(Integer contador){
        this.contador = contador;
    }

    public static Contador getInstance(Integer contador){
        return new Contador(contador);
    }

    public void increase(){
        contador++;
    }

    public void decrease(){
        if(contador > 0){
            contador--;
        }
    }

    public void sumar(Integer cantidad){
        contador += cantidad;
    }

    public void restar(Integer cantidad){
        if(cantidad >= contador){
            contador = 0;
        }else{
            contador -= cantidad;
        }
    }

    public Integer getValue(){
        return contador;
    }

    public void vaciar(){
        contador = 0;
    }
}
