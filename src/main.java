import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        String key ;
        String message;
        Scanner input = new Scanner(System.in);

        System.out.print("Enter 1 for Encryption and 2 for Decryption : ");
        int c = input.nextInt();

        if( c == 1)
        {
            System.out.println("\n.........ENCRYPTION..........\n");
            System.out.print("Enter  key    : ");
            Scanner i2 = new Scanner(System.in);
            key = i2.nextLine();

            for (int i=1; i< key.length() ; i=i+1)
            {
                if(key.charAt(i) == key.charAt(i-1))
                {
                    System.out.println("Key could not have consecutive characters");
                    return;
                }
                else if(key.charAt(i) == 'j')
                {
                    System.out.println("Key should not have j character");
                    return;
                }
            }
            System.out.print("Enter  message: ");
            Scanner i3 = new Scanner(System.in);
            message = i3.nextLine();

            for(int i=0 ;i< message.length();i++)
            {
                if(message.charAt(i) == 'j')
                {
                    System.out.println("Message should not have j character");
                    return;
                }
            }

            Encryption e = new Encryption(message,key);
            Encryption.Display();
        }
       else
        {
            System.out.println("\n.........DECRYPTION..........\n");
            System.out.print("Enter  key    : ");
            Scanner i4 = new Scanner(System.in);
            key = i4.nextLine();

            for (int i=1; i< key.length() ; i=i+1)
            {
                if(key.charAt(i) == key.charAt(i-1))
                {
                    System.out.println("Key could not have consecutive characters");
                    return;
                }
                else if(key.charAt(i) == 'j')
                {
                    System.out.println("Key should not have j character");
                    return;
                }
            }

            System.out.print("Enter  Encrypted message: ");
            Scanner in5 =new Scanner(System.in);
            message = in5.nextLine();

            for(int i=0 ;i< message.length();i++)
            {
                if(message.charAt(i) == 'j')
                {
                    System.out.println("Message should not have j character");
                    return;
                }
            }
            Decryption d = new Decryption(message,key);
            System.out.println("\n");
            Decryption.Display();
        }




    }
}