import playground.JuegoMadball;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("\n¡Iniciando el Juego de Madball!");
        JuegoMadball juegoMadball = new JuegoMadball();
        Integer CANTIDAD_CORRIDAS = 10;
        Map<String, Integer> ganadores = new HashMap<String, Integer>();
        String ganador;

        for(int i = 0; i < CANTIDAD_CORRIDAS; i++){
            ganador = juegoMadball.iniciarJuego();
            if(!ganadores.containsKey(ganador)){
                ganadores.put(ganador,0);
            }
            ganadores.put(ganador, ganadores.get(ganador)+1);
        }

        System.out.println();
        for(String cadaGanador: ganadores.keySet()){
            System.out.println(cadaGanador + " ganó " + ganadores.get(cadaGanador) + " veces (" + ganadores.get(cadaGanador)*100/CANTIDAD_CORRIDAS + "%)");
        }

    }
}
