package antimadball;

import playground.JuegoMadball;

/**
 * Regla de la carta:  Destruye todas Madballs gana puntos de locura igual las Madballs destruidas.
 */
public class GranRedCapturadora extends Antimadball {

    public void entraEnJuego(JuegoMadball juego) throws Exception {
        Integer madballsEnJuego = juego.madballsEnJuego();
        juego.destruirMadballs();
        juego.sumarPuntosCaptura(madballsEnJuego);
        juego.sumarPuntosLocura(madballsEnJuego);
        juego.ponerEnDescarte(this);
    }
}
