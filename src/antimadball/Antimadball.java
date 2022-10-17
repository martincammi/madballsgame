package antimadball;

import madballs.Carta;
import playground.JuegoMadball;

public abstract class Antimadball implements Carta {

    private String nombre;

    public String getNombre(){
        return this.getClass().getSimpleName();
    }

    //Sólo para cumplir con MadballEnJuego
    public Antimadball(){}

    public Antimadball(String nombre){
        this.nombre = nombre;
    }

    @Override
    public void inicioTurno(JuegoMadball juego) {
    }

    public void entraEnJuego(JuegoMadball juego) throws Exception {
    }

    @Override
    public void entraEnEspera(JuegoMadball juego) {

    }

    @Override
    public void finalTurno(JuegoMadball juego) {

    }

    public void madballEntraEnJuego() throws Exception {

    }
}
