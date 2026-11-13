package gmklosinski.githubuseractivity;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URL;

public class Activity {
    private final String username;

    public Activity(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException(
                    "Usage: Githubuseractivity <username>"
            );
        }

        username = args[0];

        System.out.println("activity viewer created");
    }

    void fetch() throws IOException {
        System.out.println("fetch invoked");
        final int per_page = 1;
        final String urlPattern =
                "api.github.com/users/%s/events/public?per_page=%d";

        final String urlText = String.format(
                urlPattern,
                username,
                per_page
        );

        URL url = new URL(urlText);
        HttpsURLConnection connection =
                (HttpsURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        }

    void prettyPrint() {
        System.out.println("printing invoked");
    }
    
}
