package estrategia;

import antimadball.Antimadball;
import playground.JuegoMadball;

import java.util.Set;

public class JugadorEstrategia {

    public void jugarCarta(JuegoMadball juego) throws Exception {

        Integer CANTIDAD_CARTAS_A_JUGAR = 2;
        Antimadball antimadball;
        Set<Antimadball> cartas = juego.cartasJugador();
        for(int i = 0; i < CANTIDAD_CARTAS_A_JUGAR; i++){
            if(!cartas.isEmpty()){
                antimadball = cartas.stream().findFirst().get();
                juego.removerCartaMano(antimadball);
                System.out.println("Jugando " + antimadball.getNombre());
                juego.jugar(antimadball);
            }
        }
    }

}
