package playground;

import antimadball.Antimadball;

import java.util.HashSet;
import java.util.Set;

public class ZonaJugadorEspera {

    private static ZonaJugadorEspera zonaEspera;
    protected Set<Antimadball> antimadballs = new HashSet<>();

    void ponerCarta(Antimadball antimadball, JuegoMadball juego) throws Exception {
        System.out.println("Poniendo en espera " + antimadball.getNombre());
        antimadballs.add(antimadball);
        antimadball.entraEnEspera(juego);
    }

    private ZonaJugadorEspera(){ }

    public static ZonaJugadorEspera getInstance(){
        if(zonaEspera == null){
            zonaEspera = new ZonaJugadorEspera();
        }
        return zonaEspera;
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
