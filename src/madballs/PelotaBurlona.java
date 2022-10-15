package madballs;

import playground.JuegoMadball;

public class PelotaBurlona extends Madball {

    //Regla de la carta: Al final del turno si hay tres o más Madballs en juego pon un contador de locura en una de ellas.
    public PelotaBurlona(){
        super("Pelota Burlona", 3,0);
    }

    @Override
    public void finalTurno(JuegoMadball juego) {
        if(juego.madballsEnJuego() > 2){
            juego.getMadballsEstrategia().ponerContadorLocura(1,juego);
        }
    }
}
