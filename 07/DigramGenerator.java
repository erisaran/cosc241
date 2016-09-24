package week07;

import java.util.*;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 *  Random word generator based on letter continuity.
 *
 *  @author Ben Dutton
 */
public class DigramGenerator implements WordGenerator {

    /** Random source to generate words. */
    private Random random;
    /** File containing starting letter frequencies. */
    private String startFile = ("first-letters.txt");
    /** File containing continuations. */
    private String continuationFile = ("continuations.txt");
    /** Specifys alphabet size. */
    private final int alphabetSize = 26;

    /** Constructs a continuity random word generator using the random source.
     *  @param r source of randomness used to generate words.
     */
    public DigramGenerator(Random r) {
        random = r;
    }

    /** Return a random word.
     *  @param n length of the word.
     *  @return a random word.
     */
    public String nextWord(int n) {
        StringBuilder result = new StringBuilder();
        String st = starting();
        char previous = ' ';
        for (int i = 0; i<n; i++){
            if (i == 0){
                int x = random.nextInt(st.length());
                char y = st.charAt(x);
                result.append(y);
                previous = y;
            }else if (i > 0){
                String cont = continuation(previous);
                int x = random.nextInt(cont.length());
                char y = cont.charAt(x);
                result.append(y);
                previous = y;
            }
        }   
        return result.toString();
    }

    /**
     *  Method to read a file of letter frequencies.
     *  @return string of starting letters.
     */
    public String starting(){
        String letters = "";
        try{
            FileReader f = new FileReader(startFile);
            BufferedReader b = new BufferedReader(f);
            letters = b.readLine();
        }catch (IOException e){
            System.out.println( e.getMessage() );
        }
        return letters;
    }

    /**
     *  Method to read a file of letter continuations.
     *  @param c previous character.
     *  @return list of common letters that occur after the character.
     */
    public String continuation(char c){
        int x = c - 'a';
        String cont = "";
        try{
            FileReader f = new FileReader(continuationFile);
            BufferedReader b = new BufferedReader(f);
            for (int i = 0;i< alphabetSize;i++){
                if (i == x){
                    cont = b.readLine();
                }else if(i != x){
                    b.readLine();
                }
            }
        }catch (IOException e){
            System.out.println( e.getMessage());
        }
        return cont;
    }
}
