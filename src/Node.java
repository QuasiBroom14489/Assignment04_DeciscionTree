import java.util.ArrayList;

public class Node {

    // Fields

    private String attribute;
    private int attributeIndex;

    // Parallel arrayList
    private ArrayList<Node> branches = new ArrayList<Node>();
    private ArrayList<String> values = new ArrayList<String>();

    // leaf
    private String output = "";

    // Constructors
    public Node() {

    }

    public Node(String attr, int index) {
        attribute = attr;
        attributeIndex = index;
    }

    public Node(String out) {
        output = out;
    }

    // Methods

    // adds branch Node n and String Value v
    public void addBranch(Node n, String v) {
        branches.add(n);
        values.add(v);
    }

    // Recursive search for output leaf
    public String classify(Attributes instance) {
        System.out.println(branches.size() + " Branches");
        System.out.println(attribute + " Attribute");

        // Exit Condition - Base Case
        if (branches.size() == 0) { // No Branches

            return output;
        } else if (attribute == null) {

            return output;
        } else {

            String value = instance.getValues().get(attributeIndex);
            System.out.println(value + " Value");
            int branchIndex = values.indexOf(value);
            return branches.get(branchIndex).classify(instance);
        }

    }

    // Can be set up to recursively print the entire tree
    public String toString() {
        return output;
    }

}
