import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Locale;

class Encryption {

    private static char[][] matrix = new char[5][5];
    char[] msg ;
    char[] key;
    String[] pairs;

    Encryption(String m, String k)
    {
        m = m.toLowerCase();
        k = k.toLowerCase();

        msg = formatText(m).toCharArray();
        key = cleanKey(k).toCharArray();

        generateTable(k,matrix);
        pairs = pairs(msg);

        System.out.println("\n\nkey send          : "+ k);
        System.out.println("orignal message   : "+ m);
        System.out.println("intermediate text : "+ formatText(m));
        System.out.print(  "encrypt message   : ") ;
        encryptCalculations(pairs);
    }

    public static void encryptCalculations(String[] pairs)
    {
        for(int i=0 ; i< pairs.length; i++)
        {
            char char1  = pairs[i].charAt(0);
            char char2 = pairs[i].charAt(1);

            int[] char1_pos = getCharPos(char1,matrix);
            int[] char2_pos = getCharPos(char2,matrix);

            if(char1_pos[0] == char2_pos[0])
            {
                // same row (add column)
                System.out.print(matrix[char1_pos[0]][(char1_pos[1]+1)%5]);
                System.out.print(matrix[char2_pos[0]][(char2_pos[1]+1)%5]);
            }

            else if (char1_pos[1] == char2_pos[1])
            {
                // same column (add row)
                System.out.print(matrix[(char1_pos[0]+1)%5][char1_pos[1]]);
                System.out.print(matrix[(char2_pos[0]+1)%5][char2_pos[1]]);
            }
            else
            {
                // different row column (opposite corners of block)
                System.out.print(matrix[char1_pos[0]][char2_pos[1]]);
                System.out.print(matrix[char2_pos[0]][char1_pos[1]]);

            }
        }
        System.out.println("\n");
    }

    public static void generateTable(String k , char[][] matrix)
    {
        //generateTable();
        String alphabets = "abcdefghiklmnopqrstuvwxyz";
        String temp = cleanKey(k)+alphabets;
        String combo = cleanKey(temp);

        int counter =0;

        for(int x=0; x< 5;x++)
        {
            for(int y=0; y<5; y++)
            {
                matrix[x][y] = combo.charAt(counter);
                counter ++;
            }
        }
    }

    // pairs of msg
    public static String[] pairs(char[] msg )
    {
        String[] pairs = new String[msg.length/2];

        for(int i=0, j=0; i< msg.length/2 ; i=i+1, j=j+2)
        {
            String temp =String.valueOf(msg[j]);
            temp += String.valueOf(msg[j+1]);
            pairs[i] = temp;

        }
        return pairs;
    }

    // removing all the duplicated from the key
    public static String cleanKey(String duplicatedKey)
    {
        String temp = new String();
        duplicatedKey = duplicatedKey.replaceAll("\\s", "");
        for (int i = 0; i < duplicatedKey.length(); i++)
        {
            // if plaintext contains the character 'j',
            // replace it with 'i'
            if (duplicatedKey.charAt(i) == 'j')
                temp += 'i';
            else
                temp += duplicatedKey.charAt(i);
        }


        LinkedHashSet<Character> set = new LinkedHashSet<Character>();
        for (int i = 0; i < temp.length(); i++)
        {
            set.add(temp.charAt(i));
        }


        String cleanKey = "";
        Iterator<Character> it = set.iterator();
        while (it.hasNext())
            cleanKey += (Character)it.next();


        return cleanKey;
    }



    // formatting of plaintext
    public String formatText(String plainText)
    {
        String message = "";
        plainText = plainText.replaceAll("\\s", "");

        for (int i = 0; i < plainText.length(); i++)
        {
            // if plaintext contains the character 'j',
            // replace it with 'i'
            if (plainText.charAt(i) == 'j')
                message += 'i';
            else
                message += plainText.charAt(i);
        }

        // for consecutive characters
        for (int i = 0; i < message.length()-1; i += 2)
        {
            if (message.charAt(i) == message.charAt(i + 1))
            {
                if(message.charAt(i) == 'x')
                {
                    message = message.substring(0, i + 1) + 'z' + message.substring(i + 1);
                }
                else
                    message = message.substring(0, i + 1) + 'x' + message.substring(i + 1);
            }
        }

        // making text length even
        if (message.length() % 2 == 1)
        {
            if(message.charAt(message.length()-1) == 'x')
                message += 'z';
            else
                message += 'x';
        }

        return message;

    }

    public static void Display()
    {
        for(int i=0; i<5;i++)
        {
            for(int j=0; j<5;j++)
            {
                System.out.print(matrix[i][j] + "\t\t");
            }
            System.out.println("\n");
        }
    }

    // get single character position
    public static int[] getCharPos(char ch, char[][] m) {
        int[] keyPos = new int[2];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                if (m[i][j] == ch) {
                    keyPos[0] = i;
                    keyPos[1] = j;
                      break;
                }
            }
        }
        return keyPos;
    }




}

