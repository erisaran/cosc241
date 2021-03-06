package assn;

/**
 *  program to create a Reverse Polish Notation calculator.
 *  @author Ben Dutton.
 */

import java.util.Scanner;
import java.util.Stack;
import java.lang.NumberFormatException;
import java.util.EmptyStackException;

public class RPNApp{

    /** initialises a stack of strings. */
    public static Stack<String> s = new Stack<String>();

    /**
     *  runs a scanner for as long as the program is running.
     *  outputs result of input calculation.
     *  @param args array of type string to be used in the main method.
     */
    public static void main(String [] args){
        while (true){
            Scanner scan = new Scanner(System.in);
            input(scan.nextLine());
            if (!s.isEmpty()){
                System.out.print(s);
            }
            System.out.println();
            clear();
        }       
    }

    /**
     *  manages each line of input adding numbers to the stack and.
     *  calculating results when operators are encountered.
     *  gives error messages when the input is invalid.
     *  @param inString a single line of numbers and operators.
     */
    public static void input(String inString){
        String [] lineOfInputs = inString.split(" ");
        if (inString.equals("")){
            return;
        }
        for (int i = 0;i< lineOfInputs.length;i++){
            char charItem = lineOfInputs[i].charAt(0);
            if (charItem >= '0' && charItem <= '9' || charItem == '-'){
                try {
                    long number = new Long(lineOfInputs[i]);
                } catch (NumberFormatException e){
                    System.out.println("Error: bad token '" + lineOfInputs[i] +
                                       "'");
                    return;
                }
                s.push(lineOfInputs[i]);
            }else if (s.isEmpty()){
                return;
            }else if (lineOfInputs[i].equals("+") ||
                      lineOfInputs[i].equals("-") ||
                      lineOfInputs[i].equals("*") ||
                      lineOfInputs[i].equals("/") ||
                      lineOfInputs[i].equals("%")){
                String endValue = s.pop();
                if (s.isEmpty()){
                    System.out.println("Error: too few operands");
                    clear();
                    return;
                }else{
                    String secToLast = s.pop();
                    long endVal = new Long(endValue);
                    long endVal2 = new Long(secToLast);
                    long result = 0;
                    if (lineOfInputs[i].equals("+")){
                        result = endVal + endVal2;
                    }else if (lineOfInputs[i].equals("-")){
                        result = endVal2 - endVal;
                    }else if (lineOfInputs[i].equals("*")){
                        result = endVal * endVal2;
                    }else if (lineOfInputs[i].equals("/")){
                        result = endVal2 / endVal;
                        if (endVal == 0){
                            System.out.println("Error: division by 0");
                            return;
                        }
                    }else if (lineOfInputs[i].equals("%")){
                        result = endVal2 % endVal;
                        if (endVal == 0){
                            System.out.println("Error: division by 0");
                            return;
                        }
                    }
                    String resul = Long.toString(result);
                    s.push(resul);
                }
            }else if (lineOfInputs[i].equals("+!") ||
                      lineOfInputs[i].equals("-!") ||
                      lineOfInputs[i].equals("*!") ||
                      lineOfInputs[i].equals("/!") ||
                      lineOfInputs[i].equals("%!")){
                long sum = new Long(s.pop());
                while (!s.isEmpty()){
                    String item = s.pop();
                    if (lineOfInputs[i].equals("+!")){
                        long value = new Long(item);
                        sum += value;
                    }else if (lineOfInputs[i].equals("-!")){
                        long value = new Long(item);
                        sum = value - sum;
                    }else if (lineOfInputs[i].equals("*!")){
                        long value = new Long(item);
                        sum *= value;
                    }else if (lineOfInputs[i].equals("/!")){
                        long value = new Long(item);
                        sum = value/sum;
                    }else if (lineOfInputs[i].equals("%!")){
                        long value = new Long(item);
                        sum = value%sum;
                    }
                }
                String result = Long.toString(sum);
                s.push(result);
            }else if (lineOfInputs[i].equals("d")){
                if (!s.isEmpty()){
                    s.push(toCopy);
                }else{
                    System.out.println("Error: too few operands");
                    errorFlag = true;
                    clear();
                    return;
                }
            }else if (lineOfInputs[i].equals("o")){
                System.out.print(s.peek()+ " ");
            }else if (lineOfInputs[i].equals("c")){
                String top = s.pop();
                long copies = new Long(top);
                if (s.isEmpty()){
                    System.out.println("Error: too few operands");
                    errorFlag = true;
                    clear();
                    return;
                }else {
                    String copied = s.pop();
                    while (copies > 0){
                        s.push(copied);
                        copies --;
                    }
                }
            }else if (lineOfInputs[i].equals("r")){
                int rollCount = new Integer(s.pop());
                if (rollCount < 0){
                    System.out.println("Error: negative roll");
                    clear();
                    return;
                }else if (rollCount >= i){
                    System.out.println("Error: too few operands");
                    clear();
                    return;
                }else if (rollCount > 0 ){
                    String [] toRollOver = new String[rollCount - 1];
                    String toBeRolled = s.pop();
                    for (int q = 0;q < rollCount - 1; q++){
                        toRollOver[q] = s.pop();
                    }
                    s.push(toBeRolled);
                    for (int q = toRollOver.length - 1; q >= 0; q --){
                        s.push(toRollOver[q]);
                    }
                }
            }else if (lineOfInputs[i].equals("(")){
                String workedOut = "";
                String parentheses = "";
                
                int repeats = new Integer(s.pop());
                int counter = 0;
                i ++;
                if (i == inString.length()){
                    System.out.println("Error: unmatched parentheses");
                    return;
                }
                try {
                    while (!lineOfInputs[i].equals(")")){
                        parentheses = parentheses + lineOfInputs[i] + " ";
                        i ++;
                    }
                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Error: unmatched parentheses");
                    clear();
                    return;
                }
                while (counter < repeats){
                    for (String trueItem : s){
                        workedOut = workedOut + trueItem + " ";
                    }
                    clear();
                    workedOut = workedOut + parentheses;
                    input(workedOut);
                    workedOut = "";
                    counter ++;
                }
                input(workedOut);
            }else if (lineOfInputs[i].equals(null)){
            }else if (lineOfInputs[i].equals(")")){
            }
            else{
                System.out.println("Error: bad token '"+lineOfInputs[i] +"'");
                lineOfInputs = null;
                return;
            }
        }
    }       

    /**
     *  Clears the stack when called.
     */
    public static void clear(){
        while (!s.isEmpty()){
            s.pop();
        }
    }
}
