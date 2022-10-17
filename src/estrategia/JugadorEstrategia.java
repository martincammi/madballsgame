package estrategia;

import antimadball.Antimadball;
import playground.JuegoMadball;

import java.util.Set;

public class JugadorEstrategia {

    public void jugarCarta(JuegoMadball juego) throws Exception {
        Antimadball antimadball;
        Set<Antimadball> cartas = juego.cartasJugador();
        if(!cartas.isEmpty()){
            antimadball = cartas.stream().findFirst().get();
            juego.removerCartaMano(antimadball);
            System.out.println("Jugando " + antimadball.getNombre());
            juego.jugar(antimadball);
        }
    }

}
