package week10;

import java.util.*;

/**
 * recursive implementation of a general tree.
 * 
 * @author Ben Dutton, Michael Albert
 * @param <T> The type of values stored in the tree.
 */
public class Tree<T> {

    /** Specifies the root value of the Tree<T> object. */
    private T rootValue;
    /** Specifies a list of children. */
    private List<Tree<T>> children;

    /** creates and indentation lvl counter. */
    private int indentLvl = 0;
    
    /**
     *  Constructor for a Tree object with children.
     *  @param rootValue of the tree.
     *  @param children a list of children for the tree.
     */
    public Tree(T rootValue, List<Tree<T>> children) {
        this.rootValue = rootValue;
        this.children = children;
    }

    /**
     *  Constructor for a Tree object.
     *  @param rootValue generic T object representing the root value.
     */
    public Tree(T rootValue) {
        this(rootValue, new ArrayList<Tree<T>>());
    }

    /**
     *  Determines the size of the tree recursively.
     *  @return count of all items in the tree.
     */
    public int size() {
        int count = 0;
        if (rootValue != null){
            count ++;
            if (children != null){
                for (Tree<T> t : children){
                    count += t.size();
                }
            }
            return count;
        }
        return count;
    }

    /**
     *  Determines what the largest amount of children is in the tree.
     *  @return highest number of children that any item has.
     */
    public int maxDegree() {
        int highest = 0;
        if (rootValue != null){
            highest = 1;
            if (children != null){
                highest = children.size();
                for (Tree<T> t : children){
                    int x = t.maxDegree();
                    if (x > highest){
                        highest = x;
                    }
                }
            }
        }
        return highest;
    }

    /**
     *  adds a child to the tree.
     *  @param child a tree<T> object representing children.
     */
    public void add(Tree<T> child) {
        this.children.add(child);
    }
    /**
     *  finds a specific value in the tree.
     *  @param value that needs to be found.
     *  @return tree object that matches specified input.
     */
    public Tree<T> find(T value) {
        if (rootValue.equals(value)) {
            return this;
        }
        for (Tree<T> child : children) {
            Tree<T> match = child.find(value);
            if (match != null) {
                return match;
            }
        }
        return null;
    }

    /**
     *  prints out items from the lowest of each branch to the highest.
     *  @return post order list object representing items in postorder.
     */
    public List<T> postOrder() {
        List<T> post = new ArrayList<T>();
        for (Tree<T> t : children){
            if (children == null){
            }else {
                List<T> x = t.postOrder();
                for (T q : x){
                    post.add(q);
                }
            }
        }
        post.add(rootValue);
        return post;
    }

    /**
     *  converts the tree to a string.
     *  @return string representation of the tree.
     */
    public String toString() {
        if (children.isEmpty()) {
            return rootValue.toString();
        }
        return rootValue.toString() + ' ' + children.toString();
    }





    
    /**
     *  INCOMPLETE.
     *  represents the tree as lines of strings indented to what branch they.
     *  are located.
     *  @return indented string of the tree.
     */
    public String toIndentedString() {
        String indented = "";
        int ch = 0;
        indented += rootValue + "\n";
        indentLvl ++;
        for (Tree<T> child : children){
            for (int i = 0;i< indentLvl;i ++){
                indented += "  ";
            }
            ch ++;
            child.indentLvl += indentLvl;
            if (ch >= 2){
                child.indentLvl --;
            }
            if (child.children == null){
                return child.rootValue.toString();
            }
            indented += child.toIndentedString();
        }
        return indented;
    }

    /** A helper method for testing (used by main).  Searches tree for
     *  the given target and adds white space separated children to
     *  the tree matching target if there is one.
     *
     * @param target the root value to seach for.
     * @param children a white space separated list of children to add
     * to the tree whose value matches target.
     */
    private static void addChildren(String target, String children) {
        Tree<String> parent = tree.find(target);
        if (parent != null) {
            for (String child : children.split(" ")) {
                parent.add(new Tree<>(child));
            }
        }
    }

    /** A tree instance used for testing. */
    private static Tree<String> tree;

    /**
     * Entry point of the program (used for testing).
     *
     * @param args command line arguments are not used.
     */
    public static void main(String[] args) {
        System.out.println("Creating tree\n-------------");
        tree = new Tree<>("food");
        System.out.print(tree + "\nsize: " + tree.size());
        System.out.println(", max degree: " + tree.maxDegree());
        System.out.println("\nAdding children\n----------------");
        addChildren("food", "meat fruit vegetable");
        System.out.print(tree + "\nsize: " + tree.size());
        System.out.println(", max degree: " + tree.maxDegree());
        System.out.println("\nAdding deeper children\n----------------------");
        addChildren("meat", "chicken beef fish");
        addChildren("fish", "salmon cod tuna shark");
        addChildren("vegetable", "cabbage");
        System.out.print(tree + "\nsize: " + tree.size());
        System.out.println(", max degree: " + tree.maxDegree());
        System.out.println("\nPostorder\n---------");
        System.out.println(tree.postOrder());
        System.out.println("\nIndented string\n---------------");
        System.out.print(tree.toIndentedString());
    }

}
