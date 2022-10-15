package estrategia;

import antimadball.Antimadball;
import madballs.Carta;
import playground.JuegoMadball;

import java.util.Set;

public class JugadorEstrategia {

    public void jugarCarta(JuegoMadball juego) throws Exception {
        Carta carta;
        Set<Carta> cartas = juego.cartasJugador();
        if(!cartas.isEmpty()){
            carta = cartas.stream().findFirst().get();
            System.out.println("Jugando " + carta.getNombre());
            juego.ponerEnJuego((Antimadball)carta);
        }
    }

}
