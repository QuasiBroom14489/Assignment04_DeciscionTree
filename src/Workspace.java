public class Workspace {
    public static void main(String[] args) {
        // Build out a tree
        Node leaf1 = new Node("No");
        Node leaf2 = new Node("Yes");
        Node leaf3 = new Node("No");
        Node leaf4 = new Node("Yes");

        // create root and pranches
        Node root = new Node("Pat", 4);
        Node node2 = new Node("Hun", 3);

        // tie everything together
        root.addBranch(leaf1, "none");
        root.addBranch(leaf2, "some");
        root.addBranch(node2, "full");
        node2.addBranch(leaf3, "No");

        String path = "Restaurant_Data.csv";
        DataSet data = new DataSet(path, 10);

        Attributes test = data.getDataAtIndex(0).getAttributes();
        String result = root.classify(test);
        System.out.println(result);

    }

}
