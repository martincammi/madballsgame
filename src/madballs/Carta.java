package madballs;

import playground.JuegoMadball;

public interface Carta {

    String getNombre();
    void inicioTurno(JuegoMadball juego) throws Exception;
    void entraEnJuego(JuegoMadball juego) throws Exception;
    void entraEnEspera(JuegoMadball juego);
    void finalTurno(JuegoMadball juego);
}
