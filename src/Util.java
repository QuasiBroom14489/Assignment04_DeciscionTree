import java.text.AttributedCharacterIterator.Attribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Util {

    // public static void main(String[] args) {
    // DataSet data = new DataSet("Restaurant_Data.csv", 10);
    // Util util = new Util();

    // for (int i = 0; i <= 10; i++) {
    // double q = i / 10.0;
    // System.out.println(q + ", " + util.getB(q));
    // }
    // }

    public Util() {

    }

    public double getB(double q) {

        if (q < 0.001 || q > 0.999) {
            return 0;
        } else {
            return -q * log2(q) - (1 - q) * log2(1 - q);
        }
    }

    private double log2(double x) {
        return Math.log(x) / Math.log(2);
    }

    public double getRemainder(DataSet data, String attr) {
        double remainder = 0;

        ArrayList<String> attrs = data.getAttributesList().getValues();
        int index = attrs.indexOf(attr);

        // System.out.println(attrs.toString());
        // have to find all of the possible answers for the attribute

        // Get all unique values for the attribute
        ArrayList<String> uniqueValues = new ArrayList<>();
        for (Record r : data.getData()) {
            String val = r.getAttributesAtIndex(index);
            if (!uniqueValues.contains(val)) {
                uniqueValues.add(val);
            }
        }

        for (String val : uniqueValues) {
            double pK = 0;
            double nK = 0;

            for (Record r : data.getData()) {
                if (r.getAttributesAtIndex(index).equals(val)) {
                    if (r.getClassification().equals("Yes")) {
                        pK++;
                    } else if (r.getClassification().equals("No")) {
                        nK++;
                    }
                }
            }

            double pn = pK + nK;

            // System.out.println(pK);
            // System.out.println(nK);
            // System.out.println(pn);
            // remainder += ((pK + nK) / pn) * getB(pK / (pK + nK));
            remainder += (pn / data.getData().size()) * getB(pK / pn);
        }

        return remainder;
    }

    public double getGain(DataSet data, String attr) {
        // double gain = getB()
        double p = 0;

        for (Record r : data.getData()) {
            if (r.getClassification().equals("Yes")) {
                p++;
            }
        }
        return getB(p / data.getSize()) - getRemainder(data, attr);

    }

    public String getImportance(Attributes attrs, DataSet data) {
        String importance = "";
        double maxGain = 0;
        for (String s : attrs.getValues()) {
            double gain = getGain(data, s);
            if (gain > maxGain) {
                maxGain = gain;
                importance = s;
            }
        }
        return importance + maxGain;
    }

}
