package antimadball;

import exceptions.CantDoThatException;
import playground.JuegoMadball;

/**
 * Regla de la carta: Hasta tu próximo turno no pueden entrar Madballs en juego.
 */
public class CerrarPuertas extends Antimadball {

    /**
     * La carta se pone en espera por tener un efecto que perdura un tiempo
     */
    public void entraEnJuego(JuegoMadball juego) throws Exception {
        juego.ponerEnEspera(this);
    }

    /**
     * Cuando se chequea si una madball puede entrar en juego se invoca a este método.
     * @throws Exception
     */
    public void madballEntraEnJuego() throws Exception {
        throw new CantDoThatException("Hasta el próximo turno del jugador las Madballs no pueden entrar en juego", this);
    }

    /**
     * Indica cuando la carta pasa al descarte
     */
    @Override
    public void inicioTurno(JuegoMadball juego) {
        juego.removerCartaEspera(this);
        juego.ponerEnDescarte(this);
    }
}
