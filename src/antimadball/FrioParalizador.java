package antimadball;

import exceptions.CantDoThatException;
import playground.JuegoMadball;

/**
 * Regla de la carta: Hasta tu próximo turno no se pueden quitar contadores de las Madballs
 */
public class FrioParalizador extends Antimadball {

    public void entraEnJuego(JuegoMadball juego) throws Exception {
        juego.ponerEnEspera(this);
    }

    public void madballQuitarContador() throws Exception {
        throw new CantDoThatException("Hasta el próximo turno del jugador las Madballs no se le pueden quitar contadores", this);
    }

    /**
     * Indica cuando la carta pasa al descarte
     */
    @Override
    public void inicioTurno(JuegoMadball juego) throws Exception {
        juego.moverEsperaDescarte(this);
    }
}
