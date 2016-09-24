package week03;

/** part one.
 *  10/3/2015.
 *  Determines number of digits and sum of each individual digit.
 *  @author Ben Dutton.
 */
public class RecursiveApp{

    /** records if the number is negative. */
    private static int isNegative = 1;

    /** Prints out the number of digits in an input number.
     *  @param args argument for the main method.
     */
    public static void main(String[]args){
        //System.out.println(digits(2574324));
        //System.out.println(sumOfDigits(257));

    }//end main

    /** Recursion to see how many digits n an input.
     *  @param n input number.
     *  @return the number of digits
     */
    public static long digits(long n){
        if (0 < n && n < 10){
            return 1;
        }else if ( n < 0){            
            n *= -1;
        }        
        return 1 + digits(n/10);
    }//end method
               

    /** Adds each digit in a given number together.
     *  @param n input number.
     *  @return sum of the digits.
     */
    public static long sumOfDigits(long n){
        if (n<0){
            isNegative *= -1;
            n*= -1;
        }else if(n<10){
            return (n%10)*isNegative;
        }
        return (n%10)*isNegative + sumOfDigits(n/10);

    }//end method


}//end class
