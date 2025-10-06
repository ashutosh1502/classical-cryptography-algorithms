import java.util.ArrayList;
import java.util.Scanner;

class TranspositionCipher {

    private static String plainText = "";
    private static String cipherText = "";
    private static String key = "";
    private static ArrayList<ArrayList<Character>> shuffledTranspositionMatrix;
    private static ArrayList<ArrayList<Character>> transpositionMatrix;

    public TranspositionCipher() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter the plain text: ");
            plainText = sc.nextLine();
            System.out.print("Enter the key (shuffle sequence): ");
            key = sc.nextLine();
            encrypt();
            System.out.print("\n\nEnter the cipher text: ");
            cipherText = sc.nextLine();
            System.out.print("Enter the key (shuffle sequence): ");
            key = sc.nextLine();
            decrypt();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void encrypt() {
        transpositionMatrix = new ArrayList<>();

        int charIndex = 0;
        while (charIndex < plainText.length()) {
            ArrayList<Character> row = new ArrayList<>();
            while (row.size() < key.length() && charIndex < plainText.length()) {
                row.add(plainText.charAt(charIndex));
                charIndex += 1;
            }

            while (row.size() < key.length()) {
                row.add('x');
            }
            transpositionMatrix.add(row);
        }

        System.out.println("\nTransposition Matrix (for encryption): ");
        printMatrix(transpositionMatrix);

        shuffleTranspositionMatrix(transpositionMatrix);

        System.out.print("\nEncrypted text: ");
        for (int col = 0; col < shuffledTranspositionMatrix.get(0).size(); col++) {
            for (int row = 0; row < shuffledTranspositionMatrix.size(); row++) {
                System.out.print(shuffledTranspositionMatrix.get(row).get(col));
            }
        }
    }

    private static void shuffleTranspositionMatrix(ArrayList<ArrayList<Character>> transpositionMatrix) {
        shuffledTranspositionMatrix = new ArrayList<>();

        for (ArrayList<Character> row : transpositionMatrix) {
            ArrayList<Character> shuffledRow = new ArrayList<>();
            for (int keyIndex = 0; keyIndex < key.length(); keyIndex++) {
                int columnIndex = key.charAt(keyIndex) - '1';
                shuffledRow.add(row.get(columnIndex));
            }
            shuffledTranspositionMatrix.add(shuffledRow);
        }

        System.out.println("\nShuffled Transposition Matrix: ");
        printMatrix(shuffledTranspositionMatrix);
    }

    public static void decrypt() {
        ArrayList<ArrayList<Character>> decryptionMatrix = new ArrayList<>();
        int rows = cipherText.length() / key.length();
        int charIndex = 0;

        // Fill the matrix column by column based on key
        for (int i = 0; i < key.length(); i++) {
            ArrayList<Character> column = new ArrayList<>();
            for (int j = 0; j < rows; j++) {
                column.add(cipherText.charAt(charIndex));
                charIndex++;
            }
            decryptionMatrix.add(column);
        }

        System.out.println("\nTransposition Matrix (for decryption): ");
        printMatrix(decryptionMatrix);

        // Read the matrix row by row and use the key to reorder columns
        System.out.print("\nDecrypted text: ");
        for (int row = 0; row < rows; row++) {
            for (int keyIndex = 0; keyIndex < key.length(); keyIndex++) {
                int colIndex = key.indexOf((char) ('1' + keyIndex));
                if (decryptionMatrix.get(colIndex).get(row) != 'x') {
                    System.out.print(decryptionMatrix.get(colIndex).get(row));
                }
            }
        }
    }

    private static void printMatrix(ArrayList<ArrayList<Character>> matrix) {
        for (ArrayList<Character> row : matrix) {
            System.out.print("[ ");
            for (Character c : row) {
                System.out.print(c + " ");
            }
            System.out.print("]");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new TranspositionCipher();
    }
}
