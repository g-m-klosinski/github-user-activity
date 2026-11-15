package gmklosinski.githubuseractivity;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Activity {
    private final String username;
    private String activityString;

    public Activity(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException(
                    "Usage: Githubuseractivity <username>"
            );
        }

        username = args[0];

        System.out.println("activity viewer created");
    }

    void fetch() throws IOException, InterruptedException {
        System.out.println("fetch invoked");
        final int per_page = 1;
        final String urlPattern =
                "https://api.github.com/users/%s/events/public?per_page=%d";

        final String urlText = String.format(
                urlPattern,
                username,
                per_page
        );

        final HttpClient client = HttpClient.newHttpClient();

        final HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlText))
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        activityString = response.body();
    }

    void prettyPrint() {
        System.out.println("printing invoked");

        System.out.println(activityString);
    }
    
}
