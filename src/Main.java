import playground.JuegoMadball;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("\n¡Iniciando el Juego de Madball!");
        JuegoMadball juegoMadball = new JuegoMadball();
        Integer CANTIDAD_CORRIDAS = 10000;
        Map<String, Integer> ganadores = new HashMap<String, Integer>();
        String ganador;

        for(int i = 0; i < CANTIDAD_CORRIDAS; i++){
            ganador = juegoMadball.iniciarJuego();
            if(!ganadores.containsKey(ganador)){
                ganadores.put(ganador,0);
            }
            ganadores.put(ganador, ganadores.get(ganador)+1);
            juegoMadball = new JuegoMadball();
        }

        System.out.println();
        System.out.println("Cantidad de partidas jugadas: " + CANTIDAD_CORRIDAS);
        for(String cadaGanador: ganadores.keySet()){
            System.out.println(cadaGanador + " ganó " + ganadores.get(cadaGanador) + " veces (" + ganadores.get(cadaGanador)*100/CANTIDAD_CORRIDAS + "%)");
        }

        /**
         * Jugador: 12%, 19%, 18%, 18%
         * Empate: 59% 57%, 55%, 56%
         * Madballs: 29%, 24%, 26%, 24%
         */

    }
}
