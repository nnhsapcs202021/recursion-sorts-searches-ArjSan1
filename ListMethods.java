import java.util.*;

/**
 * Write a description of class Recursion here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */


public class ListMethods
{
   public static ArrayList<Integer> makeList(int n)
   {
                ArrayList<Integer> tempList = new ArrayList<Integer>();

      if (n <= 0)  // The smallest list we can make
      {
          
          return tempList;
          
      }
      else        // All other size lists are created here
      {
      
          return makeList(n-1);


      }
      
   }
}

