
/**
 * Write a description of class StringReverse here.
 *
 * @author ArjSan1
 * @version 21apr2021
 */
public class StringReverse
{
   public static String reverse(String str)
   {
    // must have terminating case
    
    if(str.equals(""))
    {
    return str;
    }
    //must make problem simpler
    
    String firstChar = str.substring(0,1);
    String restOfString = str.substring(1);
    
    
    
    //recurse - call this method with the simpler problem
    
    String restOfStringReversed = reverse(restOfString);
    
    String strReversed = restOfStringReversed + firstChar;
    
    return strReversed;
    }
    
    
   public static String reverseIter(String str)
   {
    
    String strReversed = "";
    
    
    for (int i = 0; i< str.length(); i++)
    {
    strReversed = str.substring(i, i+1) +  strReversed;
    
    
    
    }
    
    return strReversed;
    
    
    
    }
}
