package playground;

import antimadball.Antimadball;
import madballs.MadballEnJuego;

import java.util.HashSet;
import java.util.Set;

public class ZonaJugadorDescarte {

    private static ZonaJugadorDescarte zonaDescarte;
    protected Set<Antimadball> antimadballs = new HashSet<>();

    void ponerCarta(Antimadball antimadball, JuegoMadball juego) {
        System.out.println("Descartando " + antimadball.getNombre());
        antimadballs.add(antimadball);
    }

    private ZonaJugadorDescarte(){ }

    public static ZonaJugadorDescarte getInstance(){
        if(zonaDescarte == null){
            zonaDescarte = new ZonaJugadorDescarte();
        }
        return zonaDescarte;
    }

    public void vaciarZona(){
        antimadballs.clear();
    }

    public Set<Antimadball> getCartas(){
        return new HashSet<Antimadball>(antimadballs);
    }

    public void remover(Antimadball antimadball){
        antimadballs.remove(antimadball);
    }
}
