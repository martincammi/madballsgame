package playground;

import madballs.MadballEnJuego;

public class ZonaDescarteMadballs extends ZonaMadball {

    private static ZonaDescarteMadballs zonaDescarte;

    public void ponerCarta(MadballEnJuego madball, JuegoMadball juego){
        System.out.println("Poniendo en Juego " + madball.getNombre() + " [" + madball.getContadorLocura() + "]");
        madballs.add(madball);
        madball.entraEnJuego(juego);
    }

    public ZonaDescarteMadballs(){ }
}
