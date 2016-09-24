package practice1;

import java.util.Random;

public class Coins{

    public static final boolean HEADS = true;
    public static final boolean TAILS = false;
	
    private boolean[] coins;

    public static void main(String[]args){
        boolean [] coin = {HEADS,TAILS,TAILS,HEADS};
        Coins c = new Coins(coin);
        Coins x = new Coins("HHHTH");
        Coins y = new Coins(6);
        System.out.println(y.countHeads());
        System.out.println(y.toString());
        System.out.println(y.countRuns());
        
    }
	
    public Coins(boolean[] coins) {
        this.coins = coins;
    }

    public Coins(String c){
        boolean [] a = new boolean[c.length()];
        for (int i=0;i<c.length();i++){
            if ((c.substring(i,i+1)).equals("H")){
                a[i] = HEADS;
            }else if ((c.substring(i,i+1)).equals("T")){
                a[i] = TAILS;
            }
        }
        coins = a;
    }

    public Coins(int length){
        Random x = new Random();
        boolean[] set = new boolean[length]; 
        for (int i = 0;i<length;i++){
            int num = x.nextInt(2);
            if (num == 0){
                set[i] = HEADS;
            }else if (num == 1){
                set[i] = TAILS;
            }
        }
        coins = set;
    }

    public int countHeads(){
        int count = 0;
        for (boolean x : coins){
            if (x){
                count ++;
            }
        }
        return count;
    }

    public String toString(){
        String s = "";
        for (boolean x : coins){
            if (x){
                s += "H";
            }else if (!x){
                s += "T";
            }
        }
        return s;
    }

    public int countRuns(){
        boolean wasHeads = false;
        int count = 0;
        for (int i = 0;i<coins.length;i++){
            if (coins[i] && !wasHeads){
                count ++;
                wasHeads = true;
            }else if (!coins[i] && i==0){
                count ++;
            }else if (!coins[i] && wasHeads){
                count ++;
                wasHeads = false;
            }
        }
        return count;
    }
		
}
