package estados;

import antimadball.Antimadball;
import exceptions.CantDoThatException;
import madballs.*;
import playground.JuegoMadball;
import soporte.Mazo;
import soporte.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TurnoMadball extends Turno {

    public Mazo mazoMadball = new Mazo();

    public TurnoMadball(){
        armarMazo();
    }

    private void armarMazo(){
        mazoMadball.push(new Medusa());
        mazoMadball.push(new Medusa());
        mazoMadball.push(new PelotaBurlona());
        mazoMadball.push(new Calavera());
        mazoMadball.push(new HeadHorn());
        mazoMadball.push(new Oculus());
        mazoMadball.push(new Medusa());
    }

    @Override
    public Integer getId() {
        return 0;
    }


    @Override
    public void reiniciar(List<? extends Carta> madballs) {
        vaciarMazo();
        if(madballs.isEmpty()){
            armarMazo();
        }else{
            mazoMadball.addAll(0, madballs);
        }
    }

    @Override
    public void reiniciar() {
        vaciarMazo();
        armarMazo();
    }

    @Override
    public void iniciar(JuegoMadball juego) throws Exception {

        System.out.println(" - Juegan las Madball");

        //1) Remover contadores de locura de las cartas en la zona de juego.
        Integer contadoresLocuraRemovidos = 0;
        List<MadballEnJuego> cartasConContadoresLocura = (juego.cartasEnJuego().stream().filter(m -> m.tieneContadorLocura()).collect(Collectors.toList()));
        for(MadballEnJuego madballEnJuego: cartasConContadoresLocura){
            try{
                juego.removerContadorLocura(madballEnJuego);
                contadoresLocuraRemovidos++;
            }catch(CantDoThatException e){
                System.out.println(madballEnJuego.getNombre() + " " + e.getMessage() + " porque " + e.getNombreCarta() + " no lo permite");
            }
        }
        System.out.println("Removiendo " + contadoresLocuraRemovidos + StringUtils.plural(contadoresLocuraRemovidos, " contador", "es") + " de locura" );
        StringUtils.time();

        //2) Madballs ganan puntos de locura.
        //System.out.println("- Calculando puntos de locura");
        juego.sumarPuntosLocura(contadoresLocuraRemovidos);
        StringUtils.time();

        //3) Remover contadores de espera de las cartas en la zona de espera.
        //System.out.println("- Removiendo contadores de espera");
        Integer contadoresEsperaRemovidos = new Long(juego.cartasEnEspera().stream().filter(m -> m.tieneContadorEspera())
                .peek(m -> m.restarContadorEspera()).count()).intValue();
        System.out.println("Removiendo " + contadoresEsperaRemovidos + StringUtils.plural(contadoresEsperaRemovidos, " contador", "es") + " de espera" );

        StringUtils.time();

        //4) y 5) Poner cartas con cero contadores de espera de la zona de espera en la zona de juego.
        //System.out.println("- Poniendo cartas de espera en juego");
        for(MadballEnJuego madballEnJuego: juego.cartasEnEspera().stream().filter(m -> m.getContadorEspera().equals(0)).collect(Collectors.toList())) {
            juego.moverEsperaJuego(madballEnJuego);
        }
        StringUtils.time();

        //6) Habilidades de cartas por inicio de turno
        //System.out.println("- Habilidades por inicio de turno");
        for(MadballEnJuego madballEnJuego: juego.cartasEnJuego()){
            madballEnJuego.inicioTurno(juego);
        }
        StringUtils.time();

        //7) Robar madball del mazo
        Madball madball = juego.robarMadball();
        if(madball != null){
            juego.jugar(madball);
        }
        StringUtils.time();

        //8) Habilidades de cartas por fin de turno
        juego.cartasEnJuego().stream().forEach(m -> m.finalTurno(juego));
        StringUtils.time();

        //9) Pasa el turno al Jugador.
        //No se hace nada, sólo el método termina.

        estadoDelJuego(juego);
    }

    public Madball robar(){
        return (Madball) mazoMadball.robar();
    }

    public void ponerCartaAlTope(Carta carta) {
        System.out.println(carta.getNombre() + " va al tope del mazo");
        mazoMadball.push(carta);
    }

    public boolean mazoVacio(){
        return mazoMadball.isEmpty();
    }

    public void vaciarMazo(){ mazoMadball.empty();}

    public void mezclar(){
        mazoMadball.mezclar();
    }

    public void ponerCartas(Set<MadballEnJuego> madballs) {
        mazoMadball.addAll(madballs.stream().map(madball -> madball.getMadball()).collect(Collectors.toList()));
    }

    @Override
    public Set getCartas() {
        return null;
    }
}
