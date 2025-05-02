import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DataSet {

    private ArrayList<Record> data = new ArrayList<Record>();
    private Attributes attrs = new Attributes();// Store header names

    public DataSet() {

    }

    public DataSet(String path, int classIndex) {

        try {
            File file = new File(path);
            Scanner input = new Scanner(file);

            // get each attribute
            String header[] = input.nextLine().split(",");
            // System.out.println(Arrays.toString(header));

            for (int i = 0; i < header.length; i++) {
                if (i != classIndex) {
                    attrs.add(header[i]);
                }

            }

            while (input.hasNext()) {
                String line[] = input.nextLine().split(",");
                // System.out.println(Arrays.toString(line));
                ArrayList<String> a = new ArrayList<String>();
                String c = "";

                for (int i = 0; i < line.length; i++) {
                    // System.out.println(i);
                    if (i == classIndex) {
                        c = line[i];
                        // System.out.println(c);
                    } else {
                        a.add(line[i]);
                    }
                }

                Attributes atts = new Attributes(a);
                // System.out.println(atts.toString());
                Record r = new Record(atts, c);
                // System.out.println(r.toString());
                data.add(r);
                // System.out.println(data.toString());

            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addRecord(Record r) {
        data.add(r);
    }

    public DataSet removeAttributeAtIndex(int index) {
        // Copy of DataSet with Attribute at index removed from records
        DataSet output = new DataSet();
        for (Record r : data) {
            r.removeAttributeAtIndex(index);
            output.addRecord(r);
        }
        return output;
    }

    public DataSet removeAttributeByName(String name) {
        DataSet output = new DataSet();
        for (Record r : data) {
            r.removeAttributeByName(name);
            output.addRecord(r);
        }
        return output;
    }

    public ArrayList<Record> getData() {
        return data;
    }

    public Record getDataAtIndex(int index) {
        return data.get(index);
    }

    public Attributes getAttributesList() {
        return attrs;
    }

    public String toString() {
        return attrs.toString() + " /n" + data.toString();
    }

    public int getSize() {
        return data.size();
    }

    public void setAttributes(Attributes a) {
        attrs = a;
    }

}
