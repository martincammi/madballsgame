package antimadball;

import playground.JuegoMadball;

/**
 * Regla de la carta:  Destruye todas Madballs gana puntos de locura igual las Madballs destruidas.
 */
public class DestruccionTotal extends Antimadball {

    public String getNombre() {
        return "Destruccion Total";
    }

    public void entraEnJuego(JuegoMadball juego) throws Exception {
        Integer madballsEnJuego = juego.madballsEnJuego();
        juego.destruirMadballs();
        juego.sumarPuntosDestruccion(madballsEnJuego);
        juego.sumarPuntosLocura(madballsEnJuego);
    }
}
