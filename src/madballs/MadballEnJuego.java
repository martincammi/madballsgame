package madballs;

import playground.JuegoMadball;
import soporte.Contador;
import soporte.ContadorEspera;
import soporte.ContadorLocura;

import java.util.ArrayList;
import java.util.List;

/**
 * Hace un Proxy de la clase Madball para agregarle características propias del juego pero no de la carta, como ser contadores.
 * Tiene que recibir una Madball como parámetro porque la idea es que haga un wrapping sobre el objeto existente.
 * Quizás no: Tiene que extender de Madball porque requiere ser pasado como parámetro a otros métodos como si fuera una madball.
 * Tenemos que tener también la instancia de una Madball internamente porque tenemos que poder redirigir a la implementación de los métodos abstractos
 */
public class MadballEnJuego implements Carta {

    private Madball madball;
    private Contador contadorLocura;
    private Contador contadorEspera;
    @Deprecated
    private List<Contador> contadores = new ArrayList<>();

    private MadballEnJuego(Madball madball){
        this.madball = madball;
        this.contadorLocura = ContadorLocura.getInstance(0);
        this.contadorEspera = ContadorEspera.getInstance(0);
    }

    public Madball getMadball(){
        return madball;
    }

    public static MadballEnJuego getInstance(Madball madball){
        return new MadballEnJuego(madball);
    }

    public String getNombre() {
        return madball.getNombre();
    }

    public void inicioTurno(JuegoMadball juego) throws Exception { madball.inicioTurno(juego); }

    public void entraEnJuego(JuegoMadball juego){
        madball.entraEnJuego(juego);
    }

    public void entraEnEspera(JuegoMadball juego){ madball.entraEnJuego(juego); }

    public void finalTurno(JuegoMadball juego){ madball.finalTurno(juego); }


    public void sumarContadorLocura(){
        contadorLocura.increase();
    }

    public void sumarContadorEspera(){
        contadorEspera.increase();
    }

    public void sumarContadorLocura(Integer cantidad){
        contadorLocura.sumar(cantidad);
    }

    public void sumarContadorEspera(Integer cantidad){
        contadorEspera.sumar(cantidad);
    }

    public void restarContadorLocura(){
        contadorLocura.decrease();
    }

    public void restarContadorEspera(){
        contadorEspera.decrease();
    }

    public void removerContadoresLocura(){
        contadorLocura.vaciar();
    }

    public void removerContadoresEspera(){
        contadorEspera.vaciar();
    }

    public void restarContadorLocura(Integer cantidad){
        contadorLocura.restar(cantidad);
    }

    public void restarContadorEspera(Integer cantidad){
        contadorEspera.restar(cantidad);
    }

    public Integer getContadorLocura(){
        return contadorLocura.getValue();
    }

    public Integer getContadorEspera(){
        return contadorEspera.getValue();
    }

    public Integer getCosto(){
        return madball.getCosto();
    }

    public Boolean tieneContadorLocura(){
        return contadorLocura.getValue() > 0;
    }

    public Boolean tieneContadorEspera(){
        return contadorEspera.getValue() > 0;
    }
}
