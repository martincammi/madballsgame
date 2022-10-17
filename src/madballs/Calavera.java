package madballs;

import estrategia.MadballEstrategia;
import playground.JuegoMadball;

public class Calavera extends Madball {

    //Regla de la carta: Regresa una Madball de la pila de descarta al juego.
    public Calavera(){
        super("Calavera", 1,0);
    }

    public void entraEnJuego(JuegoMadball juego){
        MadballEstrategia estrategia = juego.getMadballsEstrategia();
        try{
            MadballEnJuego madballEnJuego = estrategia.cartaDelDescarte(juego);
            juego.jugar(madballEnJuego);
        }catch (Exception e){
            System.out.println("No hay cartas en el descarte, la regla queda sin uso.");
        }
    }
}
