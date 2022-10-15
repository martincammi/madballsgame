package playground;

import madballs.MadballEnJuego;

public class ZonaMadballEspera extends ZonaMadball {

    private static ZonaMadballEspera zonaEspera;

    public void ponerCarta(MadballEnJuego madball, JuegoMadball juego){
        System.out.println("Poniendo en Espera " + madball.getNombre() + " con contador de espera: " + madball.getContadorEspera());
        madballs.add(madball);
        madball.entraEnEspera(juego);

    }

    private ZonaMadballEspera(){ }

    public static ZonaMadball getInstance(){
        if(zonaEspera == null){
            zonaEspera = new ZonaMadballEspera();
        }
        return zonaEspera;
    }

}
