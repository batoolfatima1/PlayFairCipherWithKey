import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Locale;

class Decryption {

    static char[][] matrix2 = new char[5][5];
    char[] msg ;
    char[] key;
    String[] pairs;

    Decryption( String m, String k)
    {
        k = k.toLowerCase();
        m = m.toLowerCase();

        key = Encryption.cleanKey(k).toCharArray();
        msg = m.toCharArray();

        Encryption.generateTable(k,matrix2);
        pairs = Encryption.pairs(msg);

        String out = decryptCalculations(pairs);


        System.out.println("\nencrypted message : " + m);
        System.out.println("decrypted message : " + out);
       // System.out.print(  "decrypted message : " + finalOutput ) ;

    }
    public static String decryptCalculations(String[] pairs)
    {
        StringBuilder output = new StringBuilder();
        for (String pair : pairs) {
            char char1 = pair.charAt(0);
            char char2 = pair.charAt(1);


            int[] char1_pos = Encryption.getCharPos(char1,matrix2);
            int[] char2_pos = Encryption.getCharPos(char2,matrix2);


            if (char1_pos[0] == char2_pos[0]) {
                // same row (add column)
                output.append(matrix2[char1_pos[0]][(char1_pos[1] + 4) % 5]);
                output.append(matrix2[char2_pos[0]][(char2_pos[1] + 4) % 5]);
            } else if (char1_pos[1] == char2_pos[1]) {
                // same column (add row)
                output.append(matrix2[(char1_pos[0] + 4) % 5][char1_pos[1]]);
                output.append(matrix2[(char2_pos[0] + 4) % 5][char2_pos[1]]);

            } else {
                // different row column (opposite corners of block)
                output.append(matrix2[char1_pos[0]][char2_pos[1]]);
                output.append(matrix2[char2_pos[0]][char1_pos[1]]);

            }
        }
        return output.toString();

    }
    public static void Display()
    {
        for(int i=0; i<5;i++)
        {
            for(int j=0; j<5;j++)
            {
                System.out.print(matrix2[i][j] + "\t\t");
            }
            System.out.println("\n");
        }
    }


}
