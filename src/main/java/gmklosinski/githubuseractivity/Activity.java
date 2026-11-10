package gmklosinski.githubuseractivity;

public class Activity {

    public Activity(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException(
                    "Usage: Githubuseractivity <username>"
            );
        }
        
        System.out.println("activity viewer created");
    }

    void fetch() {
        System.out.println("fetch invoked");
    }

    void prettyPrint() {
        System.out.println("printing invoked");
    }
    
}
