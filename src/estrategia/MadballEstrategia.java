package estrategia;

import madballs.MadballEnJuego;
import playground.JuegoMadball;
import playground.ZonaDescarteMadballs;
import playground.ZonaMadballEspera;
import playground.ZonaMadballJuego;

import java.util.Set;

public class MadballEstrategia {

    /**
     * Estrategia, coloca el contador en una Madball que no tenga ya contadores, si no hay ninguna,
     * lo coloca en la primer madball que encuentre
     * @param contadorlocura
     * @param juego
     */
    public void ponerContadorLocura(Integer contadorlocura, JuegoMadball juego){
        Set<MadballEnJuego> madballsEnJuego = juego.cartasEnJuego();
        if(!madballsEnJuego.isEmpty()){
            MadballEnJuego madballEnJuego = madballsEnJuego.stream()
                                                .filter(m ->  m.getContadorLocura().equals(0))
                                                .findFirst().orElse(madballsEnJuego.stream().findFirst().get());
            madballEnJuego.sumarContadorLocura(contadorlocura);
            System.out.println("Poniendo contador de locura en " + madballEnJuego.getNombre() + " ["+ madballEnJuego.getContadorLocura() +"]");
        }
    }

    /**
     * Retorna una carta del descarte
     * @param juego
     */
    public MadballEnJuego cartaDelDescarte(JuegoMadball juego) {
        Set<MadballEnJuego> madballsEnJuego = juego.cartasEnDescarte();
        return madballsEnJuego.stream().findFirst().get();
    }

    public MadballEnJuego cartaEnEspera(JuegoMadball juego) {
        Set<MadballEnJuego> madballsEnJuego = juego.cartasEnEspera();
        MadballEnJuego madballEnJuego = madballsEnJuego.stream().findFirst().get();
        juego.removerCartaEspera(madballEnJuego);
        return madballEnJuego;
    }
}
