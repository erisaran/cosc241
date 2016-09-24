package week09;

/**
 *  program to create a Reverse Polish Notation calculator.
 *  @author Ben Dutton, Thomas Farr.
 */

import java.util.Scanner;
import java.util.Stack;
import java.util.EmptyStackException;

/**
 *  RPNApp class to create a Reverse Polish Notation calculator.
 *  Takes lines of RPN input and calculates the result.
 *  @author Ben Dutton, Thomas Farr.
 */
public class RPNApp{

    /** instantiates a stack of strings. */
    public static Stack<String> s = new Stack<String>();

    /** instantiates a flag to check for errors. */
    public static boolean errorFlag = false;
    
    /**
     *  takes line of Reverse Polish Notation input and outputs the result.
     *  @param args array of type string to be used in the main method.
     */
    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()){
            input(scan.nextLine());
            if (!errorFlag){
                System.out.println(s);
            }
            errorFlag = false;
            clear();
        }
    }

    /** manages each line of input adding numbers to the stack and.
     *  calculating results when operators are encountered.
     *  gives error messages when the input is invalid.
     *  @param inString a single line of numbers and operators.
     */
    public static void input(String inString){
        String [] line = inString.split(" ");
        if (inString.equals("")){
            return;
        }
        for (int i = 0;i< line.length;i++){
            if (line[i].equals("+") ||line[i].equals("-")||line[i].equals("*")
                || line[i].equals("/") || line[i].equals("%")){ 
                calculate(line[i]);
            }else if (line[i].equals("+!")||line[i].equals("-!")||
                      line[i].equals("*!")||line[i].equals("/!")||
                      line[i].equals("%!")){ //recurring operators.
                recursiveCalc(line[i]);
            }else if (line[i].equals("d")){ // double.
                duplicate();
            }else if (line[i].equals("o")){ // print top of the current stack.
                output();
            }else if (line[i].equals("c")){ // copy.
                copy();
            }else if (line[i].equals("r")){ // roll.
                roll(i);
            }else if (line[i].equals("(")){ //parentheses.
                String wkOut = "";String pa = "";String pMinus = "";
                int repeats; int counter = 0; i ++;
                try {
                    repeats = new Integer(s.pop());
                }catch(EmptyStackException e){
                    error("Error: too few operands"); return;
                }
                if (i == inString.length()){
                    error("Error: unmatched parentheses"); return;
                }
                try {
                    while (!line[i].equals(")")) { 
                        if (line[i].equals("(")){ // nested parentheses.
                            String multiP =""; String multiAdd = ""; int multiR;
                            try {
                                multiR = new Integer(line[i-1]);
                            }catch(NumberFormatException ex){
                                error("Error: too few operands"); return;
                            }
                            i ++;
                            while (!line[i].equals(")")){
                                multiAdd = multiAdd + line[i] + " "; i ++;
                            }
                            for (int j = 0;j<multiR;j++){
                                multiP = multiP + multiAdd;
                            }pa = pMinus + multiP;
                        }
                        pMinus = pa;
                        if (!line[i].equals(")")){
                            pa = pa + line[i] + " ";
                        }
                        i ++;
                    }
                } catch (ArrayIndexOutOfBoundsException e){
                    error("Error: unmatched parentheses"); return;
                }
                while (counter < repeats){
                    for (String trueItem : s){
                        wkOut = wkOut + trueItem + " ";
                    }clear();wkOut+=pa;input(wkOut);wkOut="";counter ++;
                }input(wkOut);
            }else if (line[i].equals(")")){
                error("Error: unmatched parentheses"); return;
            }else {                            // adds numbers to the stack.
                numbers(line[i]);
            }
        }
    }       

    /**
     *  Clears the stack when called.
     */
    public static void clear(){
        while (!s.isEmpty()) {
            s.pop();
        }
    }

    /**
     *  Adds numbers to the stack or outputs an error if they are not numbers.
     *  @param lineOfInputs the string value of the line input.
     */
    public static void numbers(String lineOfInputs){
        try {
            long number = new Long(lineOfInputs);
        } catch (NumberFormatException e){
            error("Error: bad token '" + lineOfInputs + "'");
            return;
        }
        s.push(lineOfInputs);
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

    /**
     * Calculator method for last and second to last operand.
     * @param lineOfInputs current operator.
     */
    public static void calculate(String lineOfInputs){
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
            if (lineOfInputs.equals("+")){
                result = endVal + endVal2;
            }else if (lineOfInputs.equals("-")){
                result = endVal2 - endVal;
            }else if (lineOfInputs.equals("*")){
                result = endVal * endVal2;
            }else if (lineOfInputs.equals("/")){
                if (endVal == 0){
                    error("Error: division by 0");
                    return;
                }
                result = endVal2 / endVal;
            }else if (lineOfInputs.equals("%")){
                if (endVal == 0){
                    error("Error: division by 0");
                    return;
                }
                result = endVal2 % endVal;
            }
            String resul = Long.toString(result);
            s.push(resul);
        }
    }

    /** recursive operator calculations.
     *  @param lineOfInputs current operator.
     */
    public static void recursiveCalc(String lineOfInputs){
        long sum = new Long(s.pop());
        while (!s.isEmpty()){
            String item = s.pop();
            if (lineOfInputs.equals("+!")){
                long value = new Long(item);
                sum += value;
            }else if (lineOfInputs.equals("-!")){
                long value = new Long(item);
                sum = value - sum;
            }else if (lineOfInputs.equals("*!")){
                long value = new Long(item);
                sum *= value;
            }else if (lineOfInputs.equals("/!")){
                long value = new Long(item);
                try {
                    sum = value/sum;
                } catch (ArithmeticException e){
                    error("Error: division by 0");
                    return;
                }
            }else if (lineOfInputs.equals("%!")){
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
    }

    /**
     *  copy method.
     */
    public static void copy(){
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
    }

    /**
     *  roll command manager.
     *  @param i the index of the line.
     */
    public static void roll( int i ){
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
    }

    /**
     *  method that duplicates the operand at the top of the stack.
     */
    public static void duplicate(){
        try {
            s.push(s.peek());
        } catch (EmptyStackException e){
            System.out.println("Error: too few operands");
            errorFlag = true;
            clear();
            return;
        }
    }

    /**
     *  method that outputs current top stack value at the begining of the line.
     */
    public static void output(){
        try {
            System.out.print(s.peek()+ " ");
        } catch (EmptyStackException e){
            error("Error: too few operands");
            return;
        }
    }
}
