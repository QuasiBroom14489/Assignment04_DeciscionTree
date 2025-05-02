import java.util.ArrayList;

public class TreeBuilder {
    // Fields - The util instance has the
    // Math methods to compute information gain
    private Util util = new Util();

    /**
     * Recurse Decision Tree Learning Algorithm. Builds a Liked
     * List of Nodes representing a Decision Tree
     * 
     * @param examples        DataSet object with records to search.
     *                        This is a subset of parent_examples. On first call,
     *                        examples
     *                        and parent_examples are the same set.
     * 
     * @param attributes      List of Header Names to Search. This will
     *                        be copied and then reduced by one attribute per
     *                        recursive call.
     * @param parent_examples The superset of Records where examples are
     *                        drawn from.
     * 
     * @return A Linked List Decision Tree Classifier (Hypothesis)
     *         with a reference to the Root Node.
     */
    public Node buildTree(DataSet examples, Attributes attributes, DataSet parent) {

        // Base Case

        // no more examples to split on
        if (examples.getSize() == 0) {
            System.out.println(getPluralityValue(parent) + " Base Case 1");
            Node node = new Node(getPluralityValue(parent));
            node.toString();
            return node;
        }

        // all the examples have the same classification
        if (sameExamples(examples)) {
            System.out.println(examples.getDataAtIndex(0).getClassification() + " Base Case 2");
            Node node = new Node(examples.getDataAtIndex(0).getClassification());
            System.out.println(node.toString());
            return node;
        }

        // no more attributes to split on
        if (attributes.getValues().size() == 0) {
            return new Node(getPluralityValue(examples));
        }

        // Recursion
        // System.out.println(getA(examples, attributes));
        Attributes best = new Attributes(examples.getAttributesList().getValues());
        best.removeAttributeByName(getA(examples, attributes));
        // System.out.println(best.toString());
        Node node = new Node(getA(examples, attributes),
                examples.getAttributesList().getValues().indexOf(getA(examples, attributes)));
        // System.out.println(node.toString());

        ArrayList<String> values = getAttributeValues(examples, getA(examples, attributes));

        for (String value : values) {
            DataSet subset = getDataSubSet(examples, getA(examples, attributes), value);
            System.out.println(subset.getSize());
            Node branch = buildTree(subset, best, examples);
            node.addBranch(branch, value);
            branch.toString();
        }

        // Placeholder
        return node;

    }

    /**
     * @param dataset - A collection of Records and Attributes
     * 
     * @return String with classification value with highest
     *         "vote". Eg if the dataset has five records and three
     *         records have a "Yes" classification and two records have
     *         a "no", then the method returns "yes".
     */
    public String getPluralityValue(DataSet dataset) {
        int yes = 0;
        int no = 0;
        for (Record r : dataset.getData()) {
            if (r.getClassification().equals("Yes")) {
                yes++;
            } else {
                no++;
            }
        }
        if (yes > no) {
            return "Yes";
        } else {
            return "No";
        }

    }

    /**
     * Checks if all Records have the same classification.
     * For example, if all records in dataset are classified as
     * "Yes", then the method returns true.
     * 
     * @param dataset - A collection of Records and Attributes
     * @return
     */
    public boolean sameExamples(DataSet dataset) {
        String classification = dataset.getDataAtIndex(0).getClassification();
        for (Record r : dataset.getData()) {
            if (!r.getClassification().equals(classification)) {
                return false;
            }
        }
        return true;
    }

    /**
     * A variation of the "find the max" algorithm.
     * Compares all the Attributes in a DataSet and returns
     * The Attribute (Header Name) of the Attribute with
     * the highest information gain.
     * 
     * @param examples   - A DataSet object
     * @param attributes - A list of the Header Row Names
     * @return a String that represents the Attribute with the highest
     *         information gain. Uses the Util.getGain() method
     */
    public String getA(DataSet examples, Attributes attributes) {
        double maxGain = 0;
        String bestAttribute = "";

        for (String a : attributes.getValues()) {
            double gain = util.getGain(examples, a);
            if (gain > maxGain) {
                maxGain = gain;
                bestAttribute = a;
            }
        }
        return bestAttribute;
    }

    /**
     * Returns all records of Attribute attr with value vK
     * 
     * @param data - A Collection of Records
     * 
     * @param attr - String with Attribute needing the search
     * 
     * @param vK   - The String representing a possible value in the
     *             attribute attr
     * 
     * @return A new DataSet filtered with only records with value
     *         vK in Attribute attr
     */
    public DataSet getDataSubSet(DataSet data, String attr, String vK) {
        DataSet subset = new DataSet();
        subset.setAttributes(data.getAttributesList());

        for (Record r : data.getData()) {
            if (r.getAttributesAtIndex(data.getAttributesList().getValues().indexOf(attr)).equals(vK)) {
                subset.addRecord(r);
            }
        }

        // Iterate and collect Subset

        return subset;
    }

    /**
     * Returns an ArrayList of the possible values in an attribute
     * 
     * @param data      A DataSet with a collection of records
     * @param attribute the Header Row name to search
     * @return An ArrayList of Strings representing all the possible values
     *         in the Attribute attribute.
     */
    public ArrayList<String> getAttributeValues(DataSet data, String attribute) {

        ArrayList<String> uniqueValues = new ArrayList<>();
        for (Record r : data.getData()) {
            String val = r.getAttributesAtIndex(data.getAttributesList().getValues().indexOf(attribute));
            if (!uniqueValues.contains(val)) {
                uniqueValues.add(val);
            }
        }
        return uniqueValues;
    }

    /**
     * For debugging during development
     */
    public static void main(String[] args) {

        TreeBuilder cls = new TreeBuilder();

        String path = "Restaurant_Data.csv";
        DataSet data = new DataSet(path, 10);

        System.out.println(cls.getA(data, data.getAttributesList()));

    }
}
