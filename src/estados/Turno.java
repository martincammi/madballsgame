package estados;

import antimadball.Antimadball;
import madballs.Carta;
import madballs.Madball;
import madballs.MadballEnJuego;
import playground.JuegoMadball;
import playground.ZonaJugadorEspera;
import playground.ZonaMadballEspera;

import java.util.List;
import java.util.Set;

public abstract class Turno {

    public abstract Integer getId();
    public abstract void iniciar(JuegoMadball juego) throws Exception;
    public abstract Carta robar();
    public abstract void ponerCartaAlTope(Carta carta);
    public abstract boolean mazoVacio();
    public abstract void mezclar();
    public abstract Set getCartas();

    public void estadoDelJuego(JuegoMadball juego){

        System.out.println("Estado del Juego");
        System.out.print("ZonaMadball Juego: ");
        for(MadballEnJuego madballEnJuego : juego.cartasEnJuego()){
            System.out.print(madballEnJuego.getNombre() + " (" + madballEnJuego.getContadorLocura() + "," + madballEnJuego.getCosto() + ") ");
        }

        System.out.print("ZonaMadball Espera: ");
        for(MadballEnJuego madballEnJuego : juego.cartasEnEspera()){
            System.out.print(madballEnJuego.getNombre() + " (" + madballEnJuego.getContadorEspera() + "," + madballEnJuego.getCosto() + ") ");
        }

        System.out.print("ZonaMadball Descarte: ");
        for(MadballEnJuego madballEnJuego : juego.cartasEnDescarte()){
            System.out.print(madballEnJuego.getNombre() + ", ");
        }

        System.out.print("ZonaJugador Espera: ");
        for(Antimadball antimadball : juego.cartasEnEsperaJugador()){
            System.out.print(antimadball.getNombre());
        }
        System.out.println();
    }

    public abstract void reiniciar(List<? extends Carta> cartas);

    public abstract void reiniciar();

}
