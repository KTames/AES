public class PasswordFinder {
    // Declaracion de variables fijas

    // Mesaje encriptado
    static private final String ENCRYPTED_STRING = "xZwM7BWIpSjYyGFr9rhpEa+cYVtACW7yQKmyN6OYSCv0ZEg9jWbc6lKzzCxRSSIvOvlimQZBMZOYnOwiA9yy3YU8zk4abFSItoW6Wj0ufQ0=";
    // Alfabeto completo 26 letras
    static private final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    // El secret key incompleto
    static private final String[] INCOMPLETE_KEY = { "29dh120", "dk1", "3" };
    // Los numeros del 0 al 9
    static private final char[] NUMBERS = "0123456789".toCharArray();

    // Crea todas las combinaciones de las 2 listas de elementos (letras y numeros)
    private static String[] getFullCombinations(char[] letters, char[] numbers) {
        int combinacionesTotales = letters.length * numbers.length;

        String[] combinaciones = new String[combinacionesTotales];

        int indiceCombinaciones = 0;
        // Combina las letras con los numeros, para dar una posible combinacion correcta ((1/26)+(1/10))/260
        for (int letterIndex = 0; letterIndex < letters.length; letterIndex++)
            for (int numberIndex = 0; numberIndex < numbers.length; numberIndex++)
                combinaciones[indiceCombinaciones++] = letters[letterIndex] + "-" + numbers[numberIndex];
        return combinaciones;
    }

    public static void run() {}

}