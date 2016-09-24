package week02;

/** Coin toss program.
 *  Feb 2015 week 2.
 */

import java.util.Random;

/** Class for making coin objects and counting number of heads.
 *  @author Ben Dutton.
 */
public class Coins{

    /** data field to determine heads. */
    public static final boolean HEADS = true;
    /** data field for tails. */
    public static final boolean TAILS = false;

    /** private data field for the sequence of coin tosses. */
    private boolean[] coins;

    /** Creates 3 Coin objects and calls countHeads,toString and countRuns
        methods on them.
     *  @param args arguments for the method.
     */
    public static void main(String[]args){
        boolean[] b = {HEADS,TAILS,HEADS,HEADS,TAILS};
        String s = "HHTHHH";
        Coins c = new Coins(b);
        Coins d = new Coins(s);
        //Coins e = new Coins(10);
        System.out.println(c.countHeads());
        System.out.println(c.toString());
        System.out.println(c.countRuns());
        System.out.println(d.countHeads());
        System.out.println(d.toString());
        System.out.println(d.countRuns());
        //System.out.println(e.countHeads());
        //System.out.println(e.toString());
        //System.out.println(e.countRuns());
    }//end main
    
    /** Constructor for coins with a array of booleans as input.
     * @param coins array of boolean values representing coins.
     */
    public Coins(boolean[] coins) {
        this.coins = coins;
    }//end constructor

    /** Constructor with a string of H and T as input.
     * @param c String of H and Ts representing heads and tails.
     */
    public Coins(String c) {
        boolean[] coins = new boolean [c.length()];
        for (int i = 0;i < c.length();i++){
            if ((c.substring(i,i + 1)).equals("H")){
                coins[i] = HEADS;
            }else if ((c.substring(i,i+1)).equals("T")){
                coins[i] = TAILS;
            }
        }
        this.coins = coins;
    }//end constructor

    /** Constructor for a random series of H and T given a length input.
     *  @param length int representing a certain number of tosses
     */
    public Coins(int length){
        Random num = new Random();
        boolean[] coins = new boolean [length];
        for (int i = 0;i < length;i++){
            int b = num.nextInt(2);
            if (b == 0){
                coins[i] = HEADS;
            }else if (b == 1){
                coins[i] = TAILS;
            }
        }
        this.coins = coins;
    }// end constructor

    /** method. counts # of heads in array and gives value.
     *  @return number of heads in the series.
     */
    public int countHeads(){
        int numHeads = 0;
        for (boolean x : coins){
            if (x){
                numHeads ++;
            }
        }
        return numHeads;
    }//end countHeads
    
    /** method to return the sequence of heads and tails and a string.
     *  @return string of tosses represented as H or Ts.
     */
    public String toString(){
        String tosses = "";
        for (boolean a : coins){
            if (a){
                tosses += "H";
            }else if (!a){
                tosses += "T";
            }
        }
        return tosses;
    }// end toString

    /** method to count the number of runs in the series.
     *  @return number of runs in the series.
      */
    public int countRuns(){
        int runs = 0;
        boolean wasHeads = false;
        String s = toString();
        for (int i = 0;i < s.length();i++){
            if ((s.substring(i,i + 1)).equals("H") && !wasHeads){
                wasHeads = true;
                runs ++;
            }else if ((s.substring(i,i+1)).equals("T") && wasHeads){
                wasHeads = false;
                runs ++;
            }else if (i ==0 && s.substring(i,i+1).equals("T")){
                runs ++;
            }
        }
        return runs;
    }// end countRuns


}
