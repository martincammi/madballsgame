package playground;

import Exceptions.CantDoThatException;
import antimadball.Antimadball;
import estados.EstadoTurno;
import estados.Turno;
import estados.TurnoJugador;
import estados.TurnoMadball;
import estrategia.JugadorEstrategia;
import estrategia.MadballEstrategia;
import madballs.Madball;
import madballs.MadballEnJuego;
import soporte.StringUtils;

import java.util.Set;

/**
 * Se encarga de todo lo relacionado con el estado del juego: contadores, condiciones de ganar, validaciones
 */
public class JuegoMadball {

    private EstadoTurno turno;
    private TurnoMadball turnoMadball;
    private TurnoJugador turnoJugador;
    private ZonaMadball zonaMadballJuego;
    private ZonaMadball zonaMadballEspera;
    private ZonaMadball zonaMadballDescarte;
    private ZonaJugadorDescarte zonaJugadorDescarte;
    private ZonaJugadorEspera zonaJugadorEspera;
    private MadballEstrategia madballEstrategia = new MadballEstrategia();
    private JugadorEstrategia jugadorEstrategia = new JugadorEstrategia();
    private Integer PUNTOS_LOCURA_PARA_GANAR = 10;
    private Integer PUNTOS_CAPTURA_PARA_GANAR = 10;
    private Integer puntosLocura = 0;
    private Integer puntosCaptura = 0;

    public JuegoMadball(){
        turno = EstadoTurno.getInstance(this);
        turnoMadball = TurnoMadball.getInstance();
        turnoJugador = TurnoJugador.getInstance();
        zonaMadballJuego =  ZonaMadballJuego.getInstance();
        zonaMadballEspera = ZonaMadballEspera.getInstance();
        zonaMadballDescarte = ZonaDescarteMadballs.getInstance();
        zonaJugadorDescarte = ZonaJugadorDescarte.getInstance();
        zonaJugadorEspera = ZonaJugadorEspera.getInstance();
    }

    public void iniciarJuego() {
        try{
            turno.iniciar(20);
            System.out.println("--- EMPATE, nadie ganó el juego ---");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Madball robarMadball(){
        if(!turnoMadball.mazoVacio()){
            return turnoMadball.robar();
        }else{
            if(!cartasEnDescarte().isEmpty()){
                System.out.println("Mezclando pila descarte en mazo");
                //El problema está que en la zona de Descarte debería haber madballs no madballsEnJuego
                turnoMadball.ponerCartas(cartasEnDescarte());
                turnoMadball.mezclar();
                ZonaDescarteMadballs.getInstance().vaciarZona();
                return turnoMadball.robar();
            }else{
                System.out.println("No hay más cartas para robar");
                //Hacer nada si el mazo está vacio y la pila de descarte también
            }
        }
        return null;
    }

    public Antimadball robarJugador(){
        if(!turnoJugador.mazoVacio()){
            return turnoJugador.robar();
        }else{
            if(!cartasEnDescarteJugador().isEmpty()){
                System.out.println("Mezclando pila descarte en mazo");
                turnoJugador.ponerCartas(cartasEnDescarteJugador());
                turnoJugador.mezclar();
                ZonaJugadorDescarte.getInstance().vaciarZona();
                return turnoJugador.robar();
            }else{
                System.out.println("No hay más cartas para robar");
                //Hacer nada si el mazo está vacio y la pila de descarte también
            }
        }
        return null;
    }

    public Turno getTurnoActual(){
        return turno.turnoActual();
    }

    public void jugar(MadballEnJuego madballEnJuego) throws Exception {
        jugar(madballEnJuego.getMadball());
    }

    public void ponerEnDescarte(Antimadball antimadball) {
        zonaJugadorDescarte.ponerCarta(antimadball, this);
    }

    public void ponerEnEspera(Antimadball antimadball) throws Exception {
        zonaJugadorEspera.ponerCarta(antimadball, this);
    }

    /**
     * La carta se coloca en juego sin chequear el costo (probablemente porque entró por efecto de una habilidad)
     * @param madball
     * @throws Exception
     */
    public void ponerEnJuego(Madball madball) throws Exception {
        avisarEntraMadballEnJuego();
        MadballEnJuego madballEnJuego = MadballEnJuego.getInstance(madball);
        madballEnJuego.sumarContadorLocura(madball.getLocura());
        zonaMadballJuego.ponerCarta( madballEnJuego, this);
    }

    /**
     * La carta se intenta jugar validando los costos y así determinar a que zona debe ir
     * @param antimadball
     * @throws Exception
     */
    public void jugar(Antimadball antimadball) throws Exception {
        antimadball.entraEnJuego(this);
    }

    public void jugar(Madball madball) throws Exception {

        try{
            avisarEntraMadballEnJuego();

            Integer costo = madball.getCosto();
            MadballEnJuego madballEnJuego = MadballEnJuego.getInstance(madball);

            if(madballsEnJuego() >= costo){
                madballEnJuego.sumarContadorLocura(madball.getLocura());
                zonaMadballJuego.ponerCarta( madballEnJuego, this);
            } else{
                madballEnJuego.sumarContadorEspera(costo);
                zonaMadballEspera.ponerCarta( madballEnJuego, this);
            }
        }catch (CantDoThatException e){
            System.out.println(madball.getNombre() + " no se puede jugar porque " + e.getNombreCarta() + " no lo permite");
            getTurnoActual().ponerCartaAlTope(madball);
        }
    }

    public void avisarEntraMadballEnJuego() throws Exception {
        for (Antimadball antimadball: cartasEnEsperaJugador()){
            antimadball.madballEntraEnJuego();
        }
    }

    /**
     * Cuenta cantidad de Madballs en juego
     * @return un número que indica la cantidad de Madballs en juego.
     */
    public Integer madballsEnJuego() {
        return zonaMadballJuego.contarMadballs();
    }

    private Integer madballsEnDescarte() {
        return zonaMadballDescarte.contarMadballs();
    }

    @Deprecated
    public Set<MadballEnJuego> cartasEnZona(ZonaMadball zonaMadball) {
        return zonaMadball.getCartas();
    }

    public Set<MadballEnJuego> cartasEnJuego(){
        return (Set) ZonaMadballJuego.getInstance().getCartas();
    }

    public Set<MadballEnJuego> cartasEnEspera(){
        return (Set) ZonaMadballEspera.getInstance().getCartas();
    }

    public Set<MadballEnJuego> cartasEnDescarte(){
        return (Set) ZonaDescarteMadballs.getInstance().getCartas();
    }

    public Set<Antimadball> cartasEnDescarteJugador(){
        return (Set) ZonaJugadorDescarte.getInstance().getCartas();
    }

    public Set<Antimadball> cartasEnEsperaJugador(){
        return (Set) ZonaJugadorEspera.getInstance().getCartas();
    }

    public MadballEstrategia getMadballsEstrategia(){
        return madballEstrategia;
    }

    public JugadorEstrategia getJugadorEstrategia(){
        return jugadorEstrategia;
    }

    public void sumarPuntosLocura(Integer puntosLocura) throws Exception {
        agregarPuntosLocura(puntosLocura);
        System.out.println("Las Madballs tienen " + getPuntosLocura() + " punto" + (puntosLocura != 1? "s": "") + " de locura");
        if(getPuntosLocura() >= PUNTOS_LOCURA_PARA_GANAR ){
            throw new Exception("--- MADBALLS GANAN ---");
        }
    }

    public void agregarPuntosLocura(Integer puntosLocura){
        this.puntosLocura = this.puntosLocura + puntosLocura;
    }

    public Integer getPuntosLocura(){
        return puntosLocura;
    }

    public void sumarPuntosCaptura(Integer puntosCaptura) throws Exception {
        agregarPuntosCaptura(puntosCaptura);
        System.out.println("El Jugador tiene " + getPuntosCaptura() + " punto" + (puntosCaptura != 1? "s": "") + " de captura");
        if(getPuntosCaptura() >= PUNTOS_CAPTURA_PARA_GANAR){
            throw new Exception("--- EL JUGADOR GANA ---");
        }
    }

    public void agregarPuntosCaptura(Integer puntosCaptura){
        this.puntosCaptura = this.puntosCaptura + puntosCaptura;
    }

    public Integer getPuntosCaptura(){
        return puntosCaptura;
    }

    public void removerMadballZona(MadballEnJuego madballEnJuego, ZonaMadball zonaMadball){
        zonaMadball.remover(madballEnJuego);
    }

    public void destruirMadballs(){
        Set<MadballEnJuego> madballsEnJuego = cartasEnJuego();
        System.out.println(madballsEnJuego.size() + " " + StringUtils.plural(madballsEnJuego.size(),"Madball","s") + StringUtils.plural(madballsEnJuego.size()," capturada","s"));
        madballsEnJuego.stream().forEach(m -> m.removerContadoresLocura());

        zonaMadballDescarte.ponerMadballs((Set)madballsEnJuego);
        zonaMadballJuego.vaciarZona();
    }

    public Set cartasJugador() {
        return getTurnoActual().getCartas();
    }

    public void removerCartaMano(Antimadball antimadball){
        turnoJugador.removerCarta(antimadball);
    }

    public void removerCartaDescarte(Antimadball antimadball){
        zonaJugadorDescarte.remover(antimadball);
    }

    public void removerCartaEspera(Antimadball antimadball){
        zonaJugadorEspera.remover(antimadball);
    }

    public void removerCartaEspera(MadballEnJuego madballEnJuego){
        zonaMadballEspera.remover(madballEnJuego);
    }

    public void moverEsperaJuego(MadballEnJuego madballEnJuego) throws Exception {
        try {
            avisarEntraMadballEnJuego();
            removerCartaEspera(madballEnJuego);
            ponerEnJuego(madballEnJuego.getMadball());
        }catch(CantDoThatException e){
            System.out.println(madballEnJuego.getNombre() + " no se puede jugar porque " + e.getNombreCarta() + " no lo permite");
        }
    }
}
