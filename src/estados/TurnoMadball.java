package estados;

import madballs.*;
import playground.JuegoMadball;
import playground.ZonaMadballEspera;
import playground.ZonaMadballJuego;
import soporte.*;

import java.util.Set;
import java.util.stream.Collectors;

public class TurnoMadball extends Turno {

    private static TurnoMadball turnoMadball;
    private Mazo mazoMadball = new Mazo();

    private TurnoMadball(){
        armarMazo();
    }

    private void armarMazo(){
        mazoMadball.push(new Medusa());
        mazoMadball.push(new Medusa());
        mazoMadball.push(new PelotaBurlona());
        mazoMadball.push(new Calavera());
        mazoMadball.push(new HeadHorn());
        mazoMadball.push(new Oculus());
        mazoMadball.push(new Medusa());
    }

    @Override
    public Integer getId() {
        return 0;
    }

    @Override
    public void iniciar(JuegoMadball juego) throws Exception {

        System.out.println(" - Juegan las Madball");

        //1) Remover contadores de locura de las cartas en la zona de juego.
        Integer contadoresLocuraRemovidos = new Long(juego.cartasEnZona(ZonaMadballJuego.getInstance()).stream().filter(m -> m.tieneContadorLocura())
                            .peek(m -> m.restarContadorLocura()).count()).intValue();
        System.out.println("Removiendo " + contadoresLocuraRemovidos + StringUtils.plural(contadoresLocuraRemovidos, " contador", "es"));
        StringUtils.time();

        //2) Madballs ganan puntos de locura.
        juego.sumarPuntosLocura(contadoresLocuraRemovidos);
        StringUtils.time();

        //3) Remover contadores de espera de las cartas en la zona de espera.
        juego.cartasEnZona(ZonaMadballEspera.getInstance()).forEach(m -> m.restarContadorEspera());
        StringUtils.time();

        //4) y 5) Poner cartas con cero contadores de espera de la zona de espera en la zona de juego.
        for(MadballEnJuego madballEnJuego: juego.cartasEnJuego().stream().filter(m -> m.getContadorEspera().equals(0)).collect(Collectors.toList())) {
            juego.ponerEnJuego(madballEnJuego);
        }
        StringUtils.time();

        //6) Habilidades de cartas por inicio de turno
        for(MadballEnJuego madballEnJuego: juego.cartasEnJuego()){
            madballEnJuego.inicioTurno(juego);
        }
        StringUtils.time();

        //7) Robar madball del mazo
        Madball madball = juego.robarMadball();
        if(madball != null){
            juego.ponerEnJuego(madball);
        }
        StringUtils.time();

        //8) Habilidades de cartas por fin de turno
        juego.cartasEnZona(ZonaMadballJuego.getInstance()).stream().forEach(m -> m.finalTurno(juego));
        StringUtils.time();

        //9) Pasa el turno al Jugador.
        //No se hace nada, sólo el método termina.

        estadoDelJuego(juego);
    }

    public static TurnoMadball getInstance(){
        if(turnoMadball == null){
            turnoMadball = new TurnoMadball();
        }
        return turnoMadball;
    }

    public Madball robar(){
        return (Madball) mazoMadball.robar();
    }

    public boolean mazoVacio(){
        return mazoMadball.isEmpty();
    }

    public void mezclar(){
        mazoMadball.mezclar();
    }

    public void ponerCartas(Set<MadballEnJuego> madballs) {
        mazoMadball.addAll(madballs.stream().map(madball -> madball.getMadball()).collect(Collectors.toList()));
    }

    @Override
    public Set getCartas() {
        return null;
    }
}
