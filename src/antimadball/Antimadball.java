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
    public void inicioTurno(JuegoMadball juego) throws Exception {
    }

    /**
     * La carta se pone en espera por tener un efecto que perdura un tiempo
     */
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

    public void madballQuitarContador() throws Exception {

    }
}
