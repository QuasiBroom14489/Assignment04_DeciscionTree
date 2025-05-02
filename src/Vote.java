import java.util.ArrayList;

public class Vote {
    private String attr;
    private int num;

    public Vote(String s) {
        attr = s;
        num++;
    }

    public void addVote() {
        num++;
    }

    public String getName() {
        return attr;
    }

    public int getVotes() {
        return num;
    }

    public boolean equals(Vote other) {
        return other.getName().equals(attr);
    }

    /**
     * For Testing
     * 
     * @param args
     */
    public static void main(String[] args) {

        ArrayList<Vote> votes = new ArrayList<Vote>();

        Vote yes = new Vote("Yes");
        Vote no = new Vote("No");
        Vote maybe = new Vote("Maybe");

        votes.add(yes);
        votes.add(no);

        System.out.println(votes.indexOf(maybe));

    }

}
