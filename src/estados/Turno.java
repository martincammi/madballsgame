package estados;

import madballs.Carta;
import madballs.MadballEnJuego;
import playground.JuegoMadball;
import playground.ZonaMadballEspera;

import java.util.Set;

public abstract class Turno {

    public abstract Integer getId();
    public abstract void iniciar(JuegoMadball juego) throws Exception;
    public abstract Carta robar();
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
        for(MadballEnJuego madballEnJuego : juego.cartasEnZona(ZonaMadballEspera.getInstance())){
            System.out.print(madballEnJuego.getNombre() + " (" + madballEnJuego.getContadorEspera() + "," + madballEnJuego.getCosto() + ") ");
        }
        System.out.println();
    }

}
