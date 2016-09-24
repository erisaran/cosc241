package week05;

/**
 * Skeleton code for an array based implementation of Young's tableau.
 *
 * @author Iain Hewson
 */
public class TableauApp {

    /**
     * The main method is just used for testing.
     *
     * @param args command line arguments are not used.
     */
    public static void main(String[] args) {
        /* int[][] valid = {{1, 4, 5, 10, 11}, {2, 6, 8}, {3, 9, 12}, {7}};
        System.out.println(TableauApp.toString(valid));
        System.out.println(TableauApp.rowLengthsDecrease(valid));
        System.out.println(TableauApp.rowValuesIncrease(valid));
        System.out.println(TableauApp.columnValuesIncrease(valid));
        System.out.println(TableauApp.isSetOf1toN(valid)); */
    }

    /**
     * Determines whether the array passed to it is a valid tableau or not.
     *
     * @param t a two-dimensional array to test for tableau-ness.
     *
     * @return true if the parameter is a valid tableau, otherwise false
     */
    public static boolean isTableau(int[][] t){
        boolean a = rowLengthsDecrease(t);
        boolean b = rowValuesIncrease(t);
        boolean c = columnValuesIncrease(t);
        if (a && b && c){
            return true;
        }
        return false;
    }

    /**
     *  Returns a string representation of an array based tableau.
     *
     * @param t a two-dimensional array which represents a tableau.
     *
     * @return a string representation of an array based tableau.
     */
    public static String toString(int[][] t) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                result.append(String.format("%-4s", t[i][j]));
            }
            if (i < t.length-1) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    /**
     *  Returns true if the array is a tableau.
     *  @param t a 2d array representing a tableau.
     *  @return true if the row lengths always decrease.
     */
    public static boolean rowLengthsDecrease(int [] [] t){
        for (int i = 0;i<t.length-1;i++){
            if (t[i].length < t[i+1].length){         
                return false;
            }
        }
        return true;
    }

    /**
     *  Returns true is the row values increase.
     *  @param t a 2d array of a tableau.
     *  @return true if the row values always increase.
     */
    public static boolean rowValuesIncrease(int [] [] t){
        for (int i = 0;i<t.length;i++){
            for (int x = 0;x<t[i].length-1;x++){
                if (t[i][x] > t[i][x+1]){
                    return false;
                }
            }
        }return true;
    }

    /**
     *  Returns true if the column values increase.
     *  @param t a 2d array of a tableau.
     *  @return true is the column values always increase.
     */
    public static boolean columnValuesIncrease(int [][] t){
        for (int i = 0;i< t.length-1;i++){
            for (int x = 0;x<t[i+1].length;x++){
                if (t[i][x] > t[i+1][x]){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *  Returns true if there is a set of ints of 1,2,...,n.
     *  @param t a 2d array of tableau.
     *  @return true if there is a set of 1,2,...,n ints.
     */
    public static boolean isSetOf1toN(int[][] t){
        int c = 1;
        int count = 0;
        int length = 0;
        boolean wasFound = false;
        for (int [] a : t){
            length += a.length;
        }
        while (c <=length){            
            for (int i = 0;i<t.length;i++){
                for (int x = 0;x<t[i].length;x++){
                    if ((c == t[i][x]) && !wasFound){
                        count ++;
                        wasFound = true;
                    }
                }
            }
            c ++;
            wasFound = false;
        }
        if (count == length){
            return true;
        }
        return false;
    }
}
