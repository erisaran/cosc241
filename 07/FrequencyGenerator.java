package week07;

import java.util.*;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 *  Random word generator based on letter frequencies.
 *
 *  @author Ben Dutton
 */
public class FrequencyGenerator implements WordGenerator{

    /** Random source to generate words. */
    private Random random;
    /** File for letter frequencies. */
    private String freqFile = ("letter-frequencies.txt");

    /** Constructs a frequency random word generator using the random source.
     *  @param r source of randomness used to generate words.
     */
    public FrequencyGenerator(Random r) {
        random = r;
    }

    /** Return a random word.
     *  @param n length of the word.
     *  @return a random word.
     */
    public String nextWord(int n){
        double [] frequen = freqArray();
        StringBuilder result = new StringBuilder();
        for(int i = 0;i < n;i++){
            double x = this.random.nextDouble();
            for (int q = 0;q < frequen.length;q++){
                x -= frequen[q];
                if (x < 0){
                    char c = (char) ('a'+ q);
                    result.append(c);
                    q = frequen.length;
                }                               
            }
        }
        return result.toString();
    }

    /** uses a file to create an array representing word frequencies.
     *  @return frequency array.
     */
    public double [] freqArray(){
        final int alphabetSize = 26;
        double [] freq = new double [alphabetSize];
        try {
            FileReader f = new FileReader(freqFile);
            BufferedReader b = new BufferedReader(f);
            for (int i = 0;i < alphabetSize;i++){
                String x = b.readLine();
                freq[i] = Double.parseDouble(x);
            }
        }catch (IOException e) {
            System.out.println( e.getMessage() );
        }                
        return freq;
    }
}
