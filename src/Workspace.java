public class Workspace {
    public static void main(String[] args) {

        // testDataSetUtils();

        // testNodes();

        testTreeBuilder();

    }

    /**
     * Runs a test on the Accuracy of your tree
     */
    public static void testTreeBuilder() {
        String path = "Restaurant_Data.csv";
        DataSet data = new DataSet(path, 10);

        TreeBuilder treeBuilder = new TreeBuilder();

        Attributes attributes = data.getAttributesList();

        Node cls = treeBuilder.buildTree(data, attributes, data);

        double total = 0;
        double right = 0;

        for (int i = 0; i < data.getData().size(); i++) {
            total++;
            Attributes sample = data.getData().get(i).getAttributes();
            String correct = data.getData().get(i).getClassification();
            String classification = cls.classify(sample);
            // why is the classification pat instead of yes?
            // System.out.println(cls.classify(sample));
            if (correct.equals(classification)) {
                right++;
            }
            System.out.println(correct + ", " + classification);

        }
        double percentCorrect = (right / total) * 100;
        System.out.println("Percent Correct: " + percentCorrect);

    }

    /**
     * Tests the Math information Gain for Each Attribute Header
     */
    public static void testDataSetUtils() {
        Util util = new Util();

        String path = "Restaurant_Data.csv";
        DataSet data = new DataSet(path, 10);

        // double gain = util.getGain(data, 4);
        // System.out.println("Gain: " + gain);

        for (int i = 0; i < data.getAttributesList().getValues().size(); i++) {
            String attr = data.getAttributesList().getValues().get(i);
            double gain = util.getGain(data, attr);
            System.out.println(attr + " Gain: " + gain);
        }

    }

    /**
     * Hand build and test a small Tree
     * To test Node Structure
     */
    public static void testNodes() {
        // Build out a tree
        Node leaf1 = new Node("No");
        Node leaf2 = new Node("Yes");
        Node leaf3 = new Node("No");
        Node leaf4 = new Node("Yes");

        // create root and pranches
        Node root = new Node("Pat", 4);
        Node node2 = new Node("Hun", 3);

        // tie everything together
        root.addBranch(leaf1, "None");
        root.addBranch(leaf2, "Some");
        root.addBranch(node2, "Full");
        node2.addBranch(leaf3, "No");

    }

}
