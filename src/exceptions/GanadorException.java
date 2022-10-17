package exceptions;


import madballs.Carta;

public class GanadorException extends Exception {

    private String ganador;

    public GanadorException(String message, String ganador) {
        super(message);
        this.ganador = ganador;
    }

    public String getGanador(){
        return ganador;
    }
}
