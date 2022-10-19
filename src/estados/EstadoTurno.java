package estados;

import antimadball.Antimadball;
import madballs.Madball;
import playground.JuegoMadball;

import java.util.ArrayList;
import java.util.List;

public class EstadoTurno {

    private Turno turnoActual;
    private List<Turno> turnos = new ArrayList<>();
    private JuegoMadball juego;

    public EstadoTurno (JuegoMadball juegoMadball){
        turnos.add(new TurnoMadball());
        turnos.add(new TurnoJugador());
        turnoActual = turnos.get(0);
        juego = juegoMadball;
    }

    public void iniciar(Integer turnos) throws Exception {
        Integer cantidadTurnos = 1;

        while(cantidadTurnos <= turnos){
            System.out.println("--------");
            System.out.print("Turno " + cantidadTurnos);
            turnoActual.iniciar(juego);
            siguienteTurno();
            cantidadTurnos++;
        }
    }

    public void siguienteTurno(){
        turnoActual = turnos.get((turnoActual.getId() + 1) % 2);
    }

    public Turno turnoActual(){
        return turnoActual;
    }

    public void reiniciar(List<Madball> madballs, List<Antimadball> antimadballs){
        turnoActual = turnos.get(0);
        turnos.get(0).reiniciar(madballs);
        turnos.get(1).reiniciar(antimadballs);
    }

    public void reiniciar(){
        turnoActual = turnos.get(0);
        turnos.forEach(t -> t.reiniciar());
    }
}
