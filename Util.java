import java.util.Arrays;
import java.util.Random;

public class Util {
    // Declaracion de variables fijas 
    static private final Random random = new Random();
    
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
    // Hace Scramble a un array de chars
    public static char[] scrambleCharArray(char[] characters, int length) {
        char[] charactersCopy = Arrays.copyOf(characters, characters.length);
        char[] output = new char[length];
        int originalLength = length;
        //empieza a modificar los campos de los elementos, para hacerlos random
        for (int charPosition = 0; charPosition < originalLength; charPosition++) {
            int randomIndex = random.nextInt(length--);
            output[charPosition] = charactersCopy[randomIndex];
            charactersCopy[randomIndex] = charactersCopy[length];
        }

        return output;
    }
    // Hace Scramble a un array de strings
    public static String[] scrambleStringArray(String[] characters, int length) {
        String[] charactersCopy = Arrays.copyOf(characters, characters.length);

        String[] output = new String[length];
        int originalLength = length;
        //empieza a modificar los campos de los elementos, para hacerlos random
        for (int charPosition = 0; charPosition < originalLength; charPosition++) {
            int randomIndex = random.nextInt(length--);
            output[charPosition] = charactersCopy[randomIndex];
            charactersCopy[randomIndex] = charactersCopy[length];
        }
    }
}