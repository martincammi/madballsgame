package playground;

import madballs.MadballEnJuego;
import soporte.StringUtils;

public class ZonaMadballJuego extends ZonaMadball {

    private static ZonaMadballJuego zonaJuego;

    public void ponerCarta(MadballEnJuego madball, JuegoMadball juego){
        System.out.println("Poniendo en Juego " + madball.getNombre() + " [" + madball.getContadorLocura() + "]");
        StringUtils.time();
        madballs.add(madball);
        madball.entraEnJuego(juego);
    }

    public ZonaMadballJuego(){
    }


}
