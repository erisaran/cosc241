package week03;

/**
 *  A recursive representation of a tower of blocks.
 *
 * @author Michael Albert
 */
public class Tower{

    /** The top block. */
    private char top;
    
    /** The rest of the tower. */
    private Tower rest;

    /**
     * Creates a new empty Tower.
     */
    public Tower() {
        this.top = ' ';
        this.rest = null;
    }
    
    /**
     *  External classes can only create empty towers and manipulate
     *  them using public methods, because this constructor is
     *  private.
     * @param top the top block in this tower
     * @param rest the rest of the tower
     */
    private Tower(char top, Tower rest) {
        this.top = top;
        this.rest = rest;
    }

    /**
     *  Returns true if this tower is empty, otherwise false.  Empty
     *  towers are represented with the top block being a space
     *  character.
     * @return whether the tower is empty or not.
     */
    public boolean isEmpty() {
        return top == ' ';
    }
        
    /**
     *  Creates a new tower by adding the given block to the top of
     *  this tower.
     * @param block a block to add to the top of this tower.
     * @return a new tower created by adding a block to the top of
     * this tower.
     */
    public Tower add(char block) {
        return new Tower(block, this);
    }

    /**
     *  Returns height of the tower.
     *  @return height of tower.
     */
    public int height(){
        if (isEmpty()){
            return 0;
        }
        return 1 + rest.height() ;

    }//end method
        
    /**
     *  Returns number of blocks equal to a given char.
     *  @param c char representing a color.
     *  @return number of blocks equal to the char.
     */
    public int count(char c){
        int count = 0;
        if (isEmpty()){
            return count;
        }else if(top == c){
            count ++;
        }
        return count + rest.count(c);
    }//end method
            
        
}//end class
