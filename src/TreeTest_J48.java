import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instances;

public class TreeTest_J48 {

    public void testTreeJ48(String trainingPath, String testingPath, int classIndex) {

        Instances data;
        Instances dataTest;

        try {

            System.out.println("Building J48 Tree");
            // Training Set
            DataSource source = new DataSource(trainingPath);
            data = source.getDataSet();
            data.setClassIndex(classIndex);

            // Testing Set
            DataSource sourceTest = new DataSource(testingPath);
            dataTest = sourceTest.getDataSet();
            dataTest.setClassIndex(classIndex);

            // Build the J48 Tree and Train with Training Data
            J48 cls = new J48();
            cls.buildClassifier(data);

            final JFrame jf = new JFrame("J48 Decision Tree");
            jf.setSize(800, 600);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jf.setLayout(new BorderLayout());

            TreeVisualizer tv = new TreeVisualizer(null, cls.graph(), new PlaceNode2());

            jf.add(tv, BorderLayout.CENTER);
            jf.setVisible(true);
            tv.fitToScreen();

            // Evaluate Tree - Training Set
            System.out.println("Evaluating Tree for Training Set:");
            Evaluation eval = new Evaluation(data);
            eval.evaluateModel(cls, data);
            System.out.println("Error rate Training Set: " + eval.errorRate());
            System.out.println(eval.toSummaryString());
            double[][] confusionMatrix = eval.confusionMatrix();
            String out = getMatrixString(confusionMatrix);
            System.out.println("Confusion Matrix Training Set:");
            System.out.println(out);

            // Evaluate Tree - Testing Set
            System.out.println("Evaluating Tree for Testing Set:");
            eval = new Evaluation(dataTest);
            eval.evaluateModel(cls, dataTest);
            System.out.println("Error rate Testing Set: " + eval.errorRate());
            System.out.println(eval.toSummaryString());
            confusionMatrix = eval.confusionMatrix();
            System.out.println("Confusion Matrix Testing Set:");
            out = getMatrixString(confusionMatrix);
            System.out.println(out);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String getMatrixString(double[][] m) {
        String out = "";

        for (int r = 0; r < m.length; r++) {
            String line = "";
            for (int c = 0; c < m[0].length; c++) {
                line += (int) m[r][c] + " ";
            }
            line += "\n";
            out += line;
        }

        return out;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        // Create Instance and Test
        TreeTest_J48 decisionTree = new TreeTest_J48();

        // Define Paths for Training and Testing Data
        String trainingPath = "iris_train.csv";
        String testingPath = "iris_test.csv";
        int classIndex = 4;

        // Run Tree Build and Test
        decisionTree.testTreeJ48(trainingPath, testingPath, classIndex);

    }

}