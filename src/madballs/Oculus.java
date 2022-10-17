package madballs;

import playground.JuegoMadball;

public class Oculus extends Madball {

    //Regla de la carta: Al principio del turno revela una Madball más.
    public Oculus(){
        super("Oculus", 1,2);
    }

    @Override
    public void inicioTurno(JuegoMadball juego) throws Exception {
        Madball madball = juego.robarMadball();
        if(madball != null){
            System.out.println("Revela " + madball.getNombre());
            juego.jugar(madball);
        }
    }
}
