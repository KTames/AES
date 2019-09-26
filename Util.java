import java.util.Arrays;
import java.util.Random;

public class Util {
    
    // Verifica si el rsultado del dencrypt es imprimible o no, puede salir basura
    // (caracteres no imprimibles o un error que retorna null)
    public static boolean isPrintable(char character) {
        return (character >= 'a' && character <= 'z') || (character >= 'A' && character <= 'Z');
    }

    // Imprime el array final donde posiblemente se encuentre el resultado, de esta
    // manera -> []
    public static void printCharArray(char[] chars) {
        System.out.print("[ " + (chars.length != 0 ? chars[0] : ""));

        for (int charIndex = 1; charIndex < chars.length; charIndex++)
            System.out.print(", " + chars[charIndex]);

        System.out.println(" ]");
    }
}