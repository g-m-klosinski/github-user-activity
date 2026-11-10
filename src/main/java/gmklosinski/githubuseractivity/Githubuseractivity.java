package gmklosinski.githubuseractivity;

public class Githubuseractivity {

    public static void main(String[] args) {
        try {
            var activity = new Activity(args);
            activity.fetch();
            activity.prettyPrint();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
