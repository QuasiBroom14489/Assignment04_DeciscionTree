import java.util.ArrayList;
import java.util.List;

public class Attributes {
    private ArrayList<String> fields;

    public Attributes() {
        fields = new ArrayList<String>();
    }

    public Attributes(List<String> input) {
        fields = new ArrayList<String>();
        for (String s : input) {
            fields.add(s);
        }

    }

    public Attributes(Attributes a) {
        fields = new ArrayList<String>();
        for (String s : a.getValues()) {
            fields.add(s);
        }
    }

    public ArrayList<String> getValues() {
        return fields;
    }

    public ArrayList<String> getCopyValues() {
        ArrayList<String> output = new ArrayList<String>();
        for (String s : fields) {
            output.add(s);
        }
        return output;
    }

    public String toString() {

        return fields.toString();

    }

    public void removeAttributeAtIndex(int index) {
        fields.remove(index);
    }

    public void removeAttributeByName(String name) {
        fields.remove(name);
    }

    public void add(String string) {
        fields.add(string);
    }

}
