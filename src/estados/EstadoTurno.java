package estados;

import playground.JuegoMadball;

import java.util.ArrayList;
import java.util.List;

public class EstadoTurno {

    private static EstadoTurno estadoTurno;
    private Turno turnoActual;
    private List<Turno> turnos = new ArrayList<>();
    private JuegoMadball juego;

    public static EstadoTurno getInstance(JuegoMadball juegoMadball){
        if(estadoTurno == null){
            estadoTurno = new EstadoTurno();
            estadoTurno.turnos.add(TurnoMadball.getInstance());
            estadoTurno.turnos.add(TurnoJugador.getInstance());
            estadoTurno.turnoActual = estadoTurno.turnos.get(0);
            estadoTurno.juego = juegoMadball;
        }
        return estadoTurno;
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
}
