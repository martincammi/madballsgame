package madballs;

import estrategia.MadballEstrategia;
import playground.JuegoMadball;

public class Medusa extends Madball {

    //Regla de la carta: Pone un contador de Locura en otra Madball
    public Medusa(){
        super("Medusa", 1,0);
    }

    public void entraEnJuego(JuegoMadball juego){
        MadballEstrategia estrategia = juego.getMadballsEstrategia();
        estrategia.ponerContadorLocura(1,juego);
    }
}
