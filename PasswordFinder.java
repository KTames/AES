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

    // Porcentaje del total de combinaciones que se van a probar 4/6
    static private final double PORCENTAJE_COMBINACIONES_A_PROBAR = 0.7;

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
    // Toma un porcentaje 4/6 del total de combinaciones a partir de las particiones que se crearon
    // Y retorna el set de combinaciones que se van a probar
    private static String[] selectCombinations(char[] letters, char[] numbers) {
        String[] combinaciones = getFullCombinations(letters, numbers);
        String[] combinacionesElegidas = Util.scrambleStringArray(combinaciones,
                (int) (combinaciones.length * PORCENTAJE_COMBINACIONES_A_PROBAR));

        return combinacionesElegidas;
    }

    // Toma una combinacion x y la prueba en el secret key original para saber si esa es la combinacion correcta
    private static boolean isSolution(char letter, char number) {
        String key = INCOMPLETE_KEY[0] + letter + INCOMPLETE_KEY[1] + number + INCOMPLETE_KEY[2];
        String decryptedString = AES.decrypt(ENCRYPTED_STRING, key);

        return !decryptedString.equals("") && Util.isPrintable(decryptedString.charAt(0));
    }
    // Del conjunto de combinaciones de una particion retorna si tiene o no la posible solucion en sus elementos
    public static boolean hasSolution(char[] letters, char[] numbers) {
        String[] combinaciones = selectCombinations(letters, numbers);

        for (String combinacion : combinaciones)
            if (isSolution(combinacion.charAt(0), combinacion.charAt(2)))
                return true;

        return false;
    }

    public static void run() {}

}