import java.util.Scanner;

/**
 * Demonstrates the use of nested while loops.
 * 
 * @author Lewis/Loftus/Cocking
 */
public class PalindromeTester
{
    /**
     * Tests strings to see if they are palindromes.
     *
     */
    public static void main (String[] args)
    {
        String str, another = "y";
        int left, right;
        Scanner s = new Scanner(System.in);

        do
        {
            System.out.println ("Enter a potential palindrome:");
            str = s.nextLine();

            left = 0;
            right = str.length() - 1;

            while (str.charAt(left) == str.charAt(right) && left < right)
            {
                left++;
                right--;
            }

            System.out.println();

            if (left < right)
                System.out.println ("That string is NOT a palindrome.");
            else
                System.out.println ("That string IS a palindrome.");

            System.out.println();
            System.out.print ("Test another palindrome (y/n)? ");
            another = s.nextLine();
        }
        while (another.equalsIgnoreCase("y")); // allows y or Y
    }

    public static boolean isPalindrome(String str)
    {
        //must have a terminating case
        if(str.length() <= 1 )
        {

            return true;

        }

        //solve a small part of the problem

        String firstChar = str.substring(0,1);
        String lastChar = str.substring(str.length()-1);
        if(firstChar.equals(lastChar))
        {
            // recurse with a simpler version of the problem 

            return isPalindrome(str.substring(1,str.length()-1));
        }
        else
        {

            return false;
        }
    
    
    }
}
