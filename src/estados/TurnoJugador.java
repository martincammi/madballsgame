package estados;

import antimadball.Antimadball;
import antimadball.CerrarPuertas;
import antimadball.GranRedCapturadora;
import estrategia.JugadorEstrategia;
import madballs.Carta;
import playground.JuegoMadball;
import soporte.Mazo;
import soporte.StringUtils;

import java.util.HashSet;
import java.util.Set;

public class TurnoJugador extends Turno {

    private static TurnoJugador turnoJugador;
    private Mazo mazoJugador = new Mazo();
    private Set mano = new HashSet();

    private TurnoJugador(){
        armarMazo();
    }

    private void armarMazo() {
        mazoJugador.push(new GranRedCapturadora());
        mazoJugador.push(new CerrarPuertas());
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

    public static TurnoJugador getInstance(){
        if(turnoJugador == null){
            turnoJugador = new TurnoJugador();
        }
        return turnoJugador;
    }

    public Antimadball robar(){
        return (Antimadball) mazoJugador.robar();
    }

    @Override
    public boolean mazoVacio() {
        return mazoJugador.isEmpty();
    }

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

}
