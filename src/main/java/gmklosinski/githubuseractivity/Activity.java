package gmklosinski.githubuseractivity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Activity {
    private final String username;
    private final int maxEvents = 100;
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
        final String urlPattern =
                "https://api.github.com/users/%s/events/public?per_page=%d";

        final String urlText = String.format(
                urlPattern,
                username,
                maxEvents
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

    void prettyPrint() throws JsonProcessingException {
        System.out.println("printing invoked");

        ObjectMapper mapper = new ObjectMapper();

        JsonNode tree = mapper.readTree(activityString);

        List<Integer> weekAgoPushes = new ArrayList<>(maxEvents);

        Iterator<JsonNode> eventIterator = tree.values();

        LocalDate now = LocalDate.now();

        while (eventIterator.hasNext()) {
            JsonNode event = eventIterator.next();

            if (!event.get("type").asText().equals("PushEvent")) {
                continue;
            }

            String dateString = event.get("created_at").asText()
                    .substring(0, 10);

            LocalDate date = LocalDate.parse(dateString);

            long weeksElapsed = ChronoUnit.WEEKS.between(
                    date,
                    now
            );

            if (weeksElapsed >= 27) {
                break;
            }

            weekAgoPushes.add((int) weeksElapsed);
        }

        if (weekAgoPushes.isEmpty()) {
            System.out.println("No pushes registered.");
            return;
        }

        final int weeksAccounted = 27;

        int[] weekPushCount = new int[weeksAccounted];

        for (int week : weekAgoPushes) {
            weekPushCount[week]++;
        }

        int week;

        for (
                week = 0;
                week <= weekAgoPushes.get(weekAgoPushes.size() - 1);
                week++) {
            System.out.print(weekPushCount[week]);
        }

        for (; week < weeksAccounted; week++) {
            System.out.print("?");
        }
    }

}
