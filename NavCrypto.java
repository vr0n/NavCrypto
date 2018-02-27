package navcrypto;

import java.util.Scanner;
/**
 * Write a description of class Driver here.
 *
 * @author (your name)
 * @version 123117
 */
public class NavCrypto
{
    public static void main(String[] args)
    {
        String text;
        char userDecision;
        int menuInput = 999;
        
        Scanner input;
        
        input = new Scanner(System.in);
        
        Crypto code;
        
        while(menuInput != 0)
        {
            System.out.println("Please select an option from below: ");
            System.out.println("\t1. Encode/Decode Using Custom Cipher "
                    + "\n\t2. Analyze Text "
                    + "\n\t3. Reverse String "
                    + "\n\t4. Caesar Cipher"
                    + "\n\t5. Vignere Cipher "
                    + "\n\t0. Quit!");
            //1 Encode/Decode
            //2 Count characters et cetera
            //3 Reverse
            //4 Caesar
            //5 Vignere
            //0 Quit
            menuInput = input.nextInt();
            input.nextLine();
            switch(menuInput)
            {
                case 1:
                    System.out.print("Enter a code to either encode or decode: ");
                    text = input.nextLine();
                    
                    System.out.print("\nEnter either 'E' or 'D' to either Encode or Decode respectively: ");
                    userDecision = input.nextLine().charAt(0);
                    
                    code = new Crypto(text, userDecision);
                    
                    System.out.println("\n" + code.getFinishedCode());
                    break;
                case 2:
                    System.out.print("Enter text to analyze (NOTE: under construction): ");
                    text = input.nextLine();
                    
                    code = new Crypto();
                    
                    System.out.println(code.analyze(text));                    
                    break;
                case 3:
                    System.out.print("Enter text to reverse: ");
                    text = input.nextLine();
                    code = new Crypto(text);
                    System.out.println(code.reverse());
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    menuInput = 0;
            }
        }
        
        System.out.println("Good Bye!");
    }
}
