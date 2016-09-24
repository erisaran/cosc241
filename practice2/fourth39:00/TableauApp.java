package week12;

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
        int[][] valid = {{1, 4, 5, 10, 11}, {2, 6, 8}, {3, 9, 12}, {7}};
        System.out.println(TableauApp.toString(valid));
        System.out.println(TableauApp.columnValuesIncrease(valid));
    }

    /**
     * Determines whether the array passed to it is a valid tableau or not.
     *
     * @param t a two-dimensional array to test for tableau-ness.
     *
     * @return true if the parameter is a valid tableau, otherwise false
     */
    public static boolean isTableau(int[][] t){
        if (rowLengthsDecrease(t) && rowValuesIncrease(t) &&
            columnValuesIncrease(t) && isSetOf1toN(t)){
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

    public static boolean rowLengthsDecrease(int[][] t){
        for (int i = 0; i<t.length-1;i++){
            if (t[i].length < t[i+1].length){
                return false;
            }
        }
        return true;
    }

    public static boolean rowValuesIncrease(int[][] t){
        for (int i = 0;i<t.length;i++){
            for (int x = 0;x<t[i].length -1;x++){
                if (t[i][x] > t[i][x+1]){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean columnValuesIncrease(int[][] t){
        for (int i = 0;i < t.length -1;i++){
            for (int x = 0;x < t[i+1].length;x++){
                if (t[i][x] > t[i+1][x]){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isSetOf1toN(int [][] t){
        int size = 0;
        for (int i = 0;i<t.length;i++){
            for (int x : t[i]){
                size ++;
            }
        }
        int count = 0;
        int c = 1;
        while (c <= size){
            boolean f = false;
            for (int i = 0; i<t.length;i++){
                for (int x =0; x<t[i].length;x++){
                    if (!f && c == t[i][x]){
                        count ++;
                        f = true;
                    }
                }
            }
            c ++;
        }
        if (count == size){
            return true;
        }
        return false;
    }
    
}
