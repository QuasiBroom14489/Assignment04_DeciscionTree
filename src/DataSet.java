import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSet {

    private ArrayList<Record> data;
    private Attributes attrs;// Store header names

    public DataSet() {

    }

    public DataSet(String path, int classIndex) {

        try {
            File file = new File(path);
            Scanner input = new Scanner(file);

            // get each attribute
            String header[] = input.nextLine().split(",");
            for (int i = 0; i < header.length; i++) {
                if (i != classIndex) {
                    attrs.add(header[i]);
                }

            }

            while (input.hasNext()) {
                String line[] = input.nextLine().split(",");
                ArrayList<String> a = new ArrayList<String>();
                String c = "";

                for (int i = 0; i < line.length; i++) {
                    if (i == classIndex) {
                        c = line[i];
                    } else {
                        a.add(line[i]);
                    }
                }

                Attributes attrs = new Attributes(a);

            }

        } catch (Exception e) {

        }
    }

    public void addRecord(Record r) {

    }

    public DataSet removeAttributeAtIndex(int index) {
        return null;
    }

    public DataSet removeAttributeByName(String name) {
        return null;
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
        return null;
    }

    public int getSize() {
        return data.size();
    }

    public void setAttributes(Attributes a) {
        attrs = a;
    }

}
