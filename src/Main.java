import antimadball.Antimadball;
import madballs.Carta;
import madballs.Madball;
import playground.JuegoMadball;
import soporte.Mazo;

import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static String JUGADOR = "JUGADOR";
    public static String MADBALLS = "MADBALLS";
    public static String EMPATE = "EMPATE";

    public static void main(String[] args) throws Exception {

//        System.out.println("\n¡Iniciando el Juego de Madball!");
//        JuegoMadball juegoMadball = new JuegoMadball();
//        Integer CANTIDAD_CORRIDAS = 10;
//
//        Map<String, Integer> ganadores = testearPartidas(juegoMadball, CANTIDAD_CORRIDAS);
//
//        imprimirGanadores(CANTIDAD_CORRIDAS, ganadores);

        buscarCombinacionMazosOptimo();
    }

    private static void imprimirGanadores(Integer CANTIDAD_CORRIDAS, Map<String, Integer> ganadores) {
        System.out.println();
        System.out.println("Cantidad de partidas jugadas: " + CANTIDAD_CORRIDAS);
        for(String cadaGanador: ganadores.keySet()){
            System.out.println(cadaGanador + " ganó " + ganadores.get(cadaGanador) +
                                             " veces (" + ganadores.get(cadaGanador)*100/CANTIDAD_CORRIDAS + "%)");
        }

        /**
         * JUGADOR: 12%, 19%, 18%, 18%
         * EMPATE: 59% 57%, 55%, 56%
         * MADBALLS: 29%, 24%, 26%, 24%
         */
    }

    private static Map<String, Integer> testearPartidas(JuegoMadball juegoMadball, Integer cantidadDeCorridas) throws Exception {

        Map<String, Integer> ganadores = new HashMap<String, Integer>();

        String ganador;
        for(int i = 0; i < cantidadDeCorridas; i++){
            ganador = juegoMadball.iniciarJuego();
            if(!ganadores.containsKey(ganador)){
                ganadores.put(ganador,0);
            }
            ganadores.put(ganador, ganadores.get(ganador) + 1);
            juegoMadball.reiniciar();
        }

        for(String cadaGanador: ganadores.keySet()){
            ganadores.put(cadaGanador, ganadores.get(cadaGanador)*100/cantidadDeCorridas);
        }

        return ganadores;
    }

    public static void buscarCombinacionMazosOptimo() throws Exception {

        Integer CANTIDAD_CORRIDAS_PARTIDAS = 10;

        //Obtener cartas
        List<Madball> madballs = Madball.allMadballs();
        List<Antimadball> antimadballs = Antimadball.allAntiMadballs();

        //Obtener 1 carta
        Madball madballInicial = madballs.get(0);
        Antimadball antimadballInicial = antimadballs.get(0);

        //Inicializar variables
        Integer CARTAS_MAZO = 10;
        JuegoMadball juegoMadball;
        juegoMadball = new JuegoMadball();
        Mazo mazoMadball = juegoMadball.turnoMadball.mazoMadball;
        Mazo mazoJugador = juegoMadball.turnoJugador.mazoJugador;
        agregarMazo(mazoMadball, madballInicial, CARTAS_MAZO);
        agregarMazo(mazoJugador, antimadballInicial, CARTAS_MAZO);
        Map<String, Integer> mejorResultado = new HashMap<String, Integer>();
        mejorResultado.put(JUGADOR,0);
        mejorResultado.put(EMPATE,0);
        mejorResultado.put(MADBALLS,0);
        Map<String, Integer> resultadoPartidas = new HashMap<String, Integer>();
        Mazo mejorMazoMadball = (Mazo)juegoMadball.turnoMadball.mazoMadball.clone();
        Mazo mejorMazoJugador;
        List<Madball> combinacionMadballs;

        //Combinaciones
        List<int[]> combinaciones = generate(CARTAS_MAZO, CARTAS_MAZO, madballs.size());
        System.out.println("Iniciando combinaciones de " + combinaciones.size());
        for(int[] combinacion : combinaciones) {
            //Armar combinacion
            combinacionMadballs = armarCombinacionCartas(combinacion, madballs);
            System.out.println("Combinacion de " + combinacionMadballs.size());

            //Establecer cartas al juego
            juegoMadball.reiniciar(combinacionMadballs, Antimadball.allAntiMadballs());

            //Testear partidas
            resultadoPartidas = testearPartidas(juegoMadball, CANTIDAD_CORRIDAS_PARTIDAS);

            //Comparar con mejores resultados
            if(resultadoEsMejor(resultadoPartidas, mejorResultado)){
                //Salvar mejor resultado
                mejorResultado.clear();
                mejorResultado.putAll(resultadoPartidas);
                //Salvar mazo que lo hizo
                mejorMazoMadball = (Mazo)juegoMadball.turnoMadball.mazoMadball.clone();
            }
        }

        System.out.println();
        System.out.println("Cantidad de partidas jugadas: " + combinaciones.size() * CANTIDAD_CORRIDAS_PARTIDAS);
        System.out.println("Mejor Mazo: ");
        for(Carta carta : mejorMazoMadball){
            System.out.print(carta.getNombre() + ",");
        }
        System.out.println("Mejor Resultado: ");
        for(String cadaGanador: mejorResultado.keySet()){
            System.out.println(cadaGanador + " ganó " + mejorResultado.get(cadaGanador) +
                    " veces (" + mejorResultado.get(cadaGanador) + "%)");
        }


    }

    //[5,1,2,0,2]
    private static List<Madball> armarCombinacionCartas(int[] combinaciones, List<Madball> madballs){

        List<Madball> resultado = new ArrayList<>();

        for(int i = 0; i < combinaciones.length; i++){
            Integer combinacion = combinaciones[i];
            for(int j = 0; j < combinacion; j++){
                resultado.add(madballs.get(i));
            }
        }

        return resultado;
    }

    public static boolean resultadoEsMejor(Map<String, Integer> resultado, Map<String, Integer> mejorResultado){

        if(!resultado.containsKey(JUGADOR)){ resultado.put(JUGADOR,0);}
        if(!resultado.containsKey(EMPATE)){ resultado.put(EMPATE,0);}
        if(!resultado.containsKey(MADBALLS)){ resultado.put(MADBALLS,0);}

        return (resultado.get(JUGADOR) > resultado.get(EMPATE) && resultado.get(MADBALLS) > resultado.get(EMPATE)) &&
                (resultado.get(JUGADOR) - resultado.get(EMPATE)) <= 10 &&
                (resultado.get(MADBALLS) - resultado.get(EMPATE)) <= 10;
    }

    private static void agregarMazo(Mazo mazo, Madball madball, Integer cantidad){
        for(int i = 0; i < cantidad; i++){
            mazo.push(madball);
        }
    }

    private static void agregarMazo(Mazo mazo, Antimadball antimadball, Integer cantidad){
        for(int i = 0; i < cantidad; i++){
            mazo.push(antimadball);
        }
    }

    public static void main2(String[] args) {
        List<int[]> solutions = generate(3, 1, 4);
        for(int[] c: solutions) {
            System.out.println(Arrays.toString(c));
        }
    }

    /**
     * target = 3 (Value to sum for each combination) (cards in Deck)
     * max = 1 (No number can be greater than max) (cards in deck)
     * n = 4 (Slots in Deck) (cards available to add into the deck, meaning existing designed cards)
     * [0, 1, 1, 1]
     * [1, 0, 1, 1]
     * [1, 1, 0, 1]
     * [1, 1, 1, 0]
     *
     * target = 10
     * max = 10
     * n = 5
     * @param target
     * @param max
     * @param n
     * @return
     */
    public static List<int[]> generate(int target, int max, int n) {
        return generate(new ArrayList(), new int[0], target, max, n);
    }

    private static List<int[]> generate(List<int[]> solutions, int[] current, int target, int max, int n) {
        int sum = Arrays.stream(current).sum();
        if (current.length == n) {
            if (sum == target) {
                solutions.add(current);
            }
            return solutions;
        }
        if (sum > target) {
            return solutions;
        }
        for(int i=0; i <= max; i++) {
            int[] next = Arrays.copyOf(current, current.length + 1);
            next[current.length] = i;
            generate(solutions, next, target, max, n);
        }
        return solutions;
    }
}
