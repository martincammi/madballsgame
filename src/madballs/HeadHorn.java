package madballs;

import estrategia.MadballEstrategia;
import playground.JuegoMadball;

public class HeadHorn extends Madball {

    //Regla de la carta: Pon en juego una Madball en espera
    public HeadHorn(){
        super("Head Horn", 2,1);
    }

    public void entraEnJuego(JuegoMadball juego){
        MadballEstrategia estrategia = juego.getMadballsEstrategia();
        try{
            MadballEnJuego madballEnJuego = estrategia.cartaEnEspera(juego);
            juego.jugar(madballEnJuego);
        }catch (Exception e){
            System.out.println("No hay cartas en espera, la regla queda sin uso.");
        }
    }
}
