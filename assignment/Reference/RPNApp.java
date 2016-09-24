package week09;

/**
 *  program to create a Reverse Polish Notation calculator.
 *  @author Ben Dutton.
 */

import java.util.Scanner;
import java.util.Stack;
import java.lang.NumberFormatException;
import java.util.EmptyStackException;

public class RPNApp{

    /** instantiates a stack of strings. */
    public static Stack<String> s = new Stack<String>();

    /** instantiates a flag to check for errors. */
    public static boolean errorFlag = false;

    /**
     *  runs a scanner for as long as the program is running.
     *  outputs result of input calculation.
     *  @param args array of type string to be used in the main method.
     */
    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()){
            input(scan.nextLine());
            if (!errorFlag){
                System.out.println(s);
            }
            clear();
            errorFlag = false;
        }
        clear();
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
            if (lineOfInputs[i].equals("+") || lineOfInputs[i].equals("-") ||
                lineOfInputs[i].equals("*") || lineOfInputs[i].equals("/") ||
                lineOfInputs[i].equals("%")){
                if (s.isEmpty()){
                    error("Error: too few operands");
                    return;
                }
                String endValue = s.pop();
                if (s.isEmpty()){
                    error("Error: too few operands");
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
                        if (endVal == 0){
                            error("Error: division by 0");
                            return;
                        }
                        result = endVal2 / endVal;
                    }else if (lineOfInputs[i].equals("%")){
                        if (endVal == 0){
                            error("Error: division by 0");
                            return;
                        }
                        result = endVal2 % endVal;
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
                        try {
                            sum = value/sum;
                        } catch (ArithmeticException e){
                            error("Error: division by 0");
                            return;
                        }
                    }else if (lineOfInputs[i].equals("%!")){
                        long value = new Long(item);
                        try {
                        sum = value%sum;
                        } catch (ArithmeticException e){
                            error("Error: division by 0");
                            return;
                        }
                    }
                }
                String result = Long.toString(sum);
                s.push(result);
            }else if (lineOfInputs[i].equals("d")){
                try {
                     s.push(s.peek());
                } catch (EmptyStackException e){
                    System.out.println("Error: too few operands");
                    errorFlag = true;
                    clear();
                    return;
                }
            }else if (lineOfInputs[i].equals("o")){
                try {
                    System.out.print(s.peek()+ " ");
                } catch (EmptyStackException e){
                    error("Error: too few operands");
                    return;
                }
            }else if (lineOfInputs[i].equals("c")){
                String top = "";
                try {
                    top = s.pop();
                } catch (EmptyStackException e){
                    error("Error: too few operands");
                    return;
                }
                long copies = new Long(top);
                if (copies < 0){
                    error("Error: negative copy");
                    return;
                }
                if (s.isEmpty()){
                    error("Error: too few operands");
                    return;
                }else {
                    String copied = s.pop();
                    while (copies > 0){
                        s.push(copied);
                        copies --;
                    }
                }
            }else if (lineOfInputs[i].equals("r")){
                int rollCount = 0;
                try {
                    rollCount = new Integer(s.pop());
                }catch (EmptyStackException e){
                    error("Error: too few operands");
                    return;
                }
                if (rollCount < 0){
                    error("Error: negative roll");
                    return;
                }else if (rollCount >= i){
                    error("Error: too few operands");
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
                String pMinus = "";
                int repeats;
                try {
                    repeats = new Integer(s.pop());
                }catch(EmptyStackException e){
                    error("Error: too few operands");
                    return;
                }
                int counter = 0;
                i ++;
                if (i == inString.length()){
                    error("Error: unmatched parentheses");
                    return;
                }
                try {
                    while (!lineOfInputs[i].equals(")")) {
                        if (lineOfInputs[i].equals("(")){
                            String multiP = "";
                            String multiAdd = "";
                            int multiR;
                            try {
                                multiR = new Integer(lineOfInputs[i-1]);
                            }catch(NumberFormatException ex){
                                error("Error: too few operands");
                                return;
                            }
                            i ++;
                            while (!lineOfInputs[i].equals(")")){
                                multiAdd = multiAdd + lineOfInputs[i] + " ";
                                i ++;
                            }for (int j = 0;j<multiR;j++){
                                multiP = multiP + multiAdd;
                            }
                            parentheses = pMinus + multiP;
                        }
                        pMinus = parentheses;
                        if (!lineOfInputs[i].equals(")")){
                            parentheses = parentheses + lineOfInputs[i] + " ";
                        }
                        i ++;
                    }
                } catch (ArrayIndexOutOfBoundsException e){
                    error("Error: unmatched parentheses");
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
            }else if (lineOfInputs[i].equals(")")){
                error("Error: unmatched parentheses");
                return;
            }else {
                try {
                    long number = new Long(lineOfInputs[i]);
                } catch (NumberFormatException e){
                    error("Error: bad token '" + lineOfInputs[i] + "'");
                    return;
                }
                s.push(lineOfInputs[i]);
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

    /**
     * Manages errors in the input.
     * @param message the error message to be printed out.
     */
    public static void error(String message){
        System.out.println(message);
        errorFlag = true;
        clear();
    } 
}
