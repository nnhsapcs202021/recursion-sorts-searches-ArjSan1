import java.util.*;

/**
 * Write a description of class Recursion here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class ListMethods
{
    public static ArrayList<Integer> makeList( int n ){
        ArrayList<Integer> tempList = null;
        if( n <= 0 )                                  // The smallest list we can make
        {
            tempList = new ArrayList<Integer>();
        }
        else                                          // All other lists are created here
        {
            tempList = ListMethods.makeList( n - 1 );  // Pass on the smaller problem to solve
            tempList.add( n );             // Use it to solve the larger problem
        }

        return tempList;
    }

    public static ArrayList<Integer> deepClone(ArrayList<Integer> tList)
    {
        ArrayList<Integer> list = new ArrayList<Integer>(); 
        for (Integer i : tList)
        {
            list.add(new Integer(i));
        }
        return list;
    }

    public static ArrayList<Integer> reverseList( ArrayList<Integer> tList )
    {ArrayList<Integer> list = ListMethods.deepClone( tList );
        if(( list.size() == 1 ) || ( list.size() == 0 ))
        {
            return list;
        }
        else{
            Integer tempInt = list.remove( 0 );
            list = ListMethods.reverseList( list );list.add( tempInt );
        }
        return list;
    }
}
