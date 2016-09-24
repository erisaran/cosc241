package week12;

import java.util.*;

/**
 * Skeleton of the recursive implementation of a general tree.
 * 
 * @author Michael Albert
 * @param <T> The type of values stored in the tree.
 */
public class Tree<T> {
    
    private T rootValue;
    private List<Tree<T>> children;
    private int indentLvl = 0;
    
    public Tree(T rootValue, List<Tree<T>> children) {
        this.rootValue = rootValue;
        this.children = children;
    }

    public Tree(T rootValue) {
        this(rootValue, new ArrayList<Tree<T>>());
    }
    
    public int size() {
        int count = 1;
        for (Tree<T> t : children){
            count += t.size();
        }
        return count;
    }
    
    public int maxDegree() {
        if (rootValue == null){
            return 0;
        }
        int largest = children.size();
        for (Tree<T> t : children){
            int x = t.maxDegree();
            if (x > largest){
                largest = x;
            }
        }
        return largest;
    }
    
    public void add(Tree<T> child) {
        this.children.add(child);
    }

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
    
    public List<T> postOrder() {
        List<T> post = new ArrayList<T>();
        for (Tree<T> t : children){
            List<T> x = t.postOrder();
            for (T q : x ){
                post.add(q);
            }
        }
        post.add(rootValue);
        return post;
    }
    
    public String toString() {
        if (children.isEmpty()) {
            return rootValue.toString();
        }
        return rootValue.toString() + ' ' + children.toString();
    }
    
    public String toIndentedString() {
        String in = "";
        String indent = "";
        for (int i = 0;i < indentLvl;i++){
            indent += "  ";
        }
        indentLvl ++;
        in += indent + rootValue + "\n";
        for (Tree<T> t : children){
            t.indentLvl += indentLvl;
            in += t.toIndentedString(); 
        }
        return in;
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
