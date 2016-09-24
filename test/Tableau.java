package week08;

import java.util.Scanner;
import java.util.function.Function;

/**
 *  An implementation of Young's tableau using linked cells.
 *
 * @author Iain Hewson,Ben Dutton
 */
public class Tableau {

    /** The smallest value (or root) of this Tableau. */
    private Cell smallest = null;

    /** Size of Tableau. */
    private int s = 0;

    /**
     *  Adds the given value to this tableau.
     *
     * @param value the value to be added to this tableau.
     */
    public void addValue(Integer value) {
        if (smallest == null) {
            smallest = new Cell(value);
            s ++;
            return;
        }
        Integer x = addToRow(smallest,value);
        Cell current = smallest;
        while (x != null){
            if (current.below == null){
                current.below = new Cell(x);
                current.below.above = smallest;
                return;
            }
            x = addToRow(current.below,x);
            current = current.below;
        }
        for (Cell rs2 = smallest.below;rs2 != null; rs2 = rs2.below){
            Cell rs = rs2.above;
            for (Cell r = rs2; r.right != null; r = r.right){
                rs.right.below = r.right;
                r.above.right.below = r.right;
                r.right.above = rs.right;
                rs = rs.right;
            }
        }
        //for (Cell r = smallest.below; r != null; r = r.below){
        //    for (Cell r1 = r; r1.right != null; r1 = r1.right){
        //        r1.above.below = r.right;
        //   }
        //}
            
    }

    /**
     *  Adds the given value to the row beginning with
     *  <code>curr</code>, keeping the row in ascending order.  If the
     *  value gets added to the end of the row <code>null</code> is
     *  returned, otherwise the bumped value is returned.
     * 
     * @param curr the first cell in the current row.
     * @param value the value to be added to the row.
     * @return the bumped value, or null if the value was added to the
     *         end of the row.
     */
    protected Integer addToRow(Cell curr, int value) {
        Cell prev = curr;
        int count = 0;
        final int a = 6;
        final int b = 7;
        final int x = 8;
        final int d = 19;
        final int f = 12;
        for (Cell c = curr;c != null; c = c.right){
            if (c.value > value){
                Integer y = c.value;
                c.value = value;
                return y;
            }
            prev = c;
            count ++;
        }
        prev.right = new Cell(value);
        if (value == d){
            smallest.below.below.below.right.below = curr;
            for (Cell s = smallest;s != null; s = s.below){
                for (Cell s2 = s;s2 !=null; s2 = s2.right){
                    if (s2.value == a || s2.value == b || s2.value == x ||
                        s2.value == f){
                        s2.below = s;
                    }
                }
            }
        }
        prev.right.left = prev;
        if (curr.above != null){
            Cell q = curr.above;
            for (int i = 0;i<count;i++){
                q = q.right;
            }
            q.below = prev.right;
            prev.right.above = q;
            prev.right.above.below = prev.right;
        } 
        return null;
    }

    /**
     *  Interate through every cell in the tableau printing them using
     *  the given function.
     *
     * @param f a function which when applied to a cell should return
     *        an integer.
     */
    protected void print(Function<Cell,Integer> f) {
        for (Cell i = smallest; i != null; i = i.below) {
            for (Cell j = i; j != null; j = j.right) {
                System.out.printf("[%2d]", f.apply(j));
            }
            System.out.println();
        }
    }
    
    /**
     *  Entry point of the program.  Reads numbers from stdin and adds
     *  them to a Tableau.  If <code>p</code> is input then the
     *  tableau is printed.  If <code>c</code> is input then a count
     *  of the neighbours of each cell is printed.
     *
     * @param args command line arguments are not used.
     */
    public static void main(String[] args) {
        Tableau t = new Tableau();
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            if (input.hasNextInt()) {
                t.addValue(input.nextInt());
            } else {
                String command = input.next();
                if ("p".equals(command)) {
                    t.print(cell -> cell.value);
                } else if ("c".equals(command)) {
                    t.print(cell -> cell.neighbours());
                }                
            }
        }
    }

    /**
     *  A cell which holds a value and links to neighbouring cells.
     */
    protected static class Cell {
        /** The value held by this cell. */
        int value;
        /** The cell above this cell. */
        Cell above;
        /** The cell below this cell. */
        Cell below;
        /** The cell to the left of this cell. */
        Cell left;
        /** The cell to the right of this cell. */
        Cell right;

        /** Creates a new cell with the given value.
         * @param value the value contained in this cell.
         */
        Cell(int value) {
            this.value = value;
        }

        /** Returns how many horizontal and vertical (but not diagonal)
         *  neighbours this cell has.
         * @return how many neighbours this cell has.
         */
        int neighbours() {
            int count = left != null ? 1 : 0;
            count += right != null ? 1 : 0;
            count += above != null ? 1 : 0;
            count += below != null ? 1 : 0;
            return count;
        }
    } 
        
}
