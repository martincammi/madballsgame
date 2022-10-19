package estados;

import antimadball.*;
import estrategia.JugadorEstrategia;
import madballs.Carta;
import madballs.Madball;
import playground.JuegoMadball;
import soporte.Mazo;
import soporte.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TurnoJugador extends Turno {

    public Mazo mazoJugador = new Mazo();
    private Set mano = new HashSet();

    public TurnoJugador(){
        armarMazo();
    }

    private void armarMazo() {
        mazoJugador.push(new GranRedCapturadora());
        mazoJugador.push(new CerrarPuertas());
        mazoJugador.push(new FrioParalizador());
        mazoJugador.push(new GranRedCapturadora());
        mazoJugador.push(new EntrarEnRazon());
        mazoJugador.push(new GranRedCapturadora());
        mazoJugador.push(new CerrarPuertas());
        mazoJugador.push(new FrioParalizador());
        mazoJugador.push(new GranRedCapturadora());
        mazoJugador.push(new EntrarEnRazon());
    }

    @Override
    public Integer getId() {
        return 1;
    }

    @Override
    public void iniciar(JuegoMadball juego) throws Exception {
        System.out.println(" - Juega el Jugador");

        for (Antimadball antimadball: juego.cartasEnEsperaJugador()){
            antimadball.inicioTurno(juego);
        }

        //1) Roba carta
        Carta antimadball = juego.robarJugador();
        if(antimadball != null){
            mano.add(antimadball);
        }
        StringUtils.time();

        //2) Juega una o más cartas de su mano realizando las acciones que indiquen
        JugadorEstrategia estrategia = juego.getJugadorEstrategia();
        estrategia.jugarCarta(juego);

        //3) Pasa el turno a las Madballs

        estadoDelJuego(juego);
    }

    public Antimadball robar(){
        return (Antimadball) mazoJugador.robar();
    }

    @Override
    public boolean mazoVacio() {
        return mazoJugador.isEmpty();
    }

    public void vaciarMazo(){ mazoJugador.empty();}

    public void ponerCartas(Set<Antimadball> antiMadballs) {
        mazoJugador.addAll(antiMadballs);
    }

    public void ponerCartaAlTope(Carta carta) {
        mazoJugador.push(carta);
    }

    @Override
    public void mezclar() {
        mazoJugador.mezclar();
    }

    public Set getCartas(){
        return new HashSet(mano);
    }

    public void removerCarta(Antimadball antimadball){
        mano.remove(antimadball);
    }

    @Override
    public void reiniciar(List<? extends Carta> antimadballs) {
        vaciarMazo();
        if(antimadballs.isEmpty()){
            armarMazo();
        }else{
            mazoJugador.addAll(0, antimadballs);
        }
        mano.clear();
    }

    @Override
    public void reiniciar() {
        vaciarMazo();
        armarMazo();
        mano.clear();
    }
}
