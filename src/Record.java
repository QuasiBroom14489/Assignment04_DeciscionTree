import java.util.ArrayList;

public class Record {

    private Attributes attributes; // Xn - List of describers - all the info
    private String classification; // Yn - Single word class - Yes/No

    public Record(Attributes a, String c) {
        attributes = a;
        classification = c;

    }

    public Record(ArrayList<String> a, String c) {
        attributes = new Attributes(a);
        classification = c;

    }

    public Attributes getAttributes() {
        return attributes;
    }

    public String getClassification() {
        // System.out.println(classification + " Record Classification");
        return classification;
    }

    public String getAttributesAtIndex(int index) {
        return attributes.getValues().get(index);
    }

    public String toString() {
        return attributes.toString() + " Class: " + classification;
    }

    public void removeAttributeAtIndex(int index) {
        attributes.removeAttributeAtIndex(index);
    }

    public void removeAttributeByName(String name) {
        attributes.removeAttributeByName(name);
    }

}
