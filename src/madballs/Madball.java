package madballs;

import playground.JuegoMadball;

import java.util.ArrayList;
import java.util.List;

public abstract class Madball implements Carta {

    private String nombre;
    protected Integer locura;
    protected Integer costo;

    //Sólo para cumplir con MadballEnJuego
    public Madball(){}

    public Madball(String nombre, Integer locura, Integer costo){
        this.nombre = nombre;
        this.locura = locura;
        this.costo = costo;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getLocura() {
        return locura;
    }

    public Integer getCosto() {
        return costo;
    }

    @Override
    public void inicioTurno(JuegoMadball juego) throws Exception {

    }

    public void entraEnJuego(JuegoMadball juego){
    }

    @Override
    public void entraEnEspera(JuegoMadball juego) {

    }

    @Override
    public void finalTurno(JuegoMadball juego) {

    }
}
