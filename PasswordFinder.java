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

    // WARNING: DO NOT MODIFY
    static private final int MAX_NUMBERS_IN_PARTITION = 2;
    static private final int PARTITIONS_PER_LEVEL = 3;

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

    // Crea las particiones del total de letras y numeros
    // Toma 1/3 del total y crea la particion 1 y asi con el restante de elementos
    // Hace un total de 3 niveles de particiones, (crea 1/3, luego de ese tercio toma 1/3 y vuelve a tomar 1/3)
    private static char[][] createPartitions(String string, int cantPartitions) {
        int charsPerPartition = string.length() / cantPartitions;

        char[][] outputPartitions = new char[cantPartitions][];

        for (int partitionIndex = 0; partitionIndex < cantPartitions; partitionIndex++) {
            int lastIndex = partitionIndex == cantPartitions - 1 ? string.length()
                    : (partitionIndex + 1) * charsPerPartition;
            outputPartitions[partitionIndex] = string.substring(partitionIndex * charsPerPartition, lastIndex)
                    .toCharArray();
        }

        return outputPartitions;
    }
    // Toma una particion y la evalua con el conjunto de combinaciones dentro de ella
    // Sino consigue un resultado se devuelve, hace scramble y crea una particion nueva y vuelve a intentarlo
    // Hasta que en la particion x queden juntas la letra y numero correcto
    private static char[][] recursivePartitionMaker(char[] letters, char[] numbers) {
        if (numbers.length <= MAX_NUMBERS_IN_PARTITION)
            if (hasSolution(letters, numbers))
                return new char[][] { letters, numbers };
            else
                return null;

        char[][] output = null;

        String scrambledLetters = new String(Util.scrambleCharArray(letters, letters.length));
        String scrambledNumbers = new String(Util.scrambleCharArray(numbers, numbers.length));

        char[][] lettersPartitions = createPartitions(scrambledLetters, PARTITIONS_PER_LEVEL);
        char[][] numbersPartitions = createPartitions(scrambledNumbers, PARTITIONS_PER_LEVEL);

        for (int partitionIndex = 0; partitionIndex < PARTITIONS_PER_LEVEL && output == null; partitionIndex++)
            output = recursivePartitionMaker(lettersPartitions[partitionIndex], numbersPartitions[partitionIndex]);

        return output;
    }

    // Funcion general para ejecutar todo el algoritmo en secuencia
    public static void run() {
        char[][] bestPartition = null;

        while (bestPartition == null)
            bestPartition = recursivePartitionMaker(ALPHABET, NUMBERS);
        
        System.out.println("Es muy probable que la respuesta esté en una combinación con los siguientes caracteres: ");
        System.out.print("Letras -> ");
        Util.printCharArray(bestPartition[0]);
        System.out.print("Números -> ");
        Util.printCharArray(bestPartition[1]);
    }

}