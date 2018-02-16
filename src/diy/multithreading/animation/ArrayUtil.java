package diy.multithreading.animation;
import java.util.Random;
//
/**
 * This class contains utility methods for array
 manipulation.
 * @author <a href="mailto:bremner@unb.ca">David Bremner</a>
 * @version 1.0
 */
public class ArrayUtil
{  /**
      Creates an array filled with random values.
      @param length the length of the array
      @param n the number of possible random values
      @return an array filled with length numbers between
      0 and n-1
   */
   public static int[] randomIntArray(int length, int n)
   {  int[] a = new int[length];
      Random generator = new Random();

      for (int i = 0; i < a.length; i++)
         a[i] = generator.nextInt(n);

      return a;
   }

    /**
     * Swaps two elements in an array.
     * @param a the array with the elements to swap
     * @param i the index of one of the elements
     * @param j the index of the other element
     */
    public static void swap(int[] a, int i, int j)
   {  int temp = a[i];
      a[i] = a[j];
      a[j] = temp;
   }

   /**
      Prints some elements in an array.
      @param a the array to print
   */
   public static void print(int[] a)
   {
       print(a,0,a.length-1);
   }

    /**
     * Prints all elements in an array.
     *
     * @param a array
     * @param from first index to print
     * @param to last index to print
     */
    public static void print(int[] a, int from, int to)
   {
       print(a,from,to,false);
   }

    /**
     * Describe <code>print</code> method here.
     *
     * @param a array
     * @param from first index to print
     * @param to last index to print
     * @param pad flag indicating whether non printed values should
     *          be replaced by a space.
     */
    public static void print(int[] a, int from, int to,boolean pad){
        if (pad)
            for (int i = 0; i <from; i++)
                System.out.print("  ");

        for (int i = from; i <= to; i++)
            System.out.print(a[i] + " ");

        if (pad)
            for (int i = to+1; i <a.length; i++)
                System.out.print("  ");

        System.out.println();
    }
}