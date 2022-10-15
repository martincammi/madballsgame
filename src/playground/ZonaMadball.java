package playground;

import madballs.Carta;
import madballs.MadballEnJuego;

import java.util.HashSet;
import java.util.Set;

/**
 * Knows which method to invoke from the Madballs/Jugador
 */
public abstract class ZonaMadball {

    protected Set<MadballEnJuego> madballs = new HashSet<>();

    abstract void ponerCarta(MadballEnJuego madballEnJuego, JuegoMadball juego) throws Exception;

    public Integer contarMadballs() {
        return new Long(madballs.stream().filter(m -> m instanceof MadballEnJuego).count()).intValue();
    }

    public Set<MadballEnJuego> getCartas(){
        return new HashSet<MadballEnJuego>(madballs);
    }

    public void ponerMadballs(Set<MadballEnJuego> madballEnJuego) {
        this.madballs.addAll(madballEnJuego);
    }

    public void vaciarZona(){
        madballs.clear();
    }

    public boolean estaVacia(){
        return madballs.isEmpty();
    }

    public void remover(MadballEnJuego madballEnJuego){
        madballs.remove(madballEnJuego);
    }
}
