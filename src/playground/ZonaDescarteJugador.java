package playground;

import antimadball.Antimadball;
import madballs.Carta;
import madballs.MadballEnJuego;

import java.util.HashSet;
import java.util.Set;

public class ZonaDescarteJugador {

    private static ZonaDescarteJugador zonaDescarte;
    protected Set<Antimadball> antimadballs = new HashSet<>();

    void ponerCarta(Antimadball antimadball, JuegoMadball juego) throws Exception {
        System.out.println("Poniendo en Juego " + antimadball.getNombre());
        antimadballs.add(antimadball);
        antimadball.entraEnJuego(juego);
    }

    private ZonaDescarteJugador(){ }

    public static ZonaDescarteJugador getInstance(){
        if(zonaDescarte == null){
            zonaDescarte = new ZonaDescarteJugador();
        }
        return zonaDescarte;
    }

    public void vaciarZona(){
        antimadballs.clear();
    }

    public Set<Antimadball> getCartas(){
        return new HashSet<Antimadball>(antimadballs);
    }
}
