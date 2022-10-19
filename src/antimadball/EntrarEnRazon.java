package antimadball;

import madballs.MadballEnJuego;
import playground.JuegoMadball;

import java.util.List;
import java.util.Set;

/**
 * Regla de la carta: Remueve un contador de una Madball y roba una carta.
 */
public class EntrarEnRazon extends Antimadball {

    public void entraEnJuego(JuegoMadball juego) throws Exception {
        Set<MadballEnJuego> madballs = juego.cartasEnJuego();
        MadballEnJuego madballElegida;
        if(!madballs.isEmpty()){
            madballElegida = madballs.stream().findFirst().get();
            juego.removerContadorLocura(madballElegida);
        }
        juego.robarJugador();
    }
}
