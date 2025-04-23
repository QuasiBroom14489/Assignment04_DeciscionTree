import java.util.ArrayList;

public class DataSet {

    private ArrayList<Record> data;
    private Attributes attrs;// Store header names

    public DataSet() {

    }

    public DataSet(String path, int classIndex) {

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
