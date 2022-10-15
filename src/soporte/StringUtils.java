package soporte;

public class StringUtils {

    public static String plural(Integer integer, String palabra, String plural){
        return integer.equals(1) ? palabra : palabra + plural;
    }

    public static void time(){
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
