package com.lesvp.myJourneyCompanion.security;

import lombok.Getter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TokenManager {

    private final String clientId = System.getenv("TWITCH_ID");
    private final String clientSecret = System.getenv("TWITCH_SECRET");
    @Getter
    private String token;
    private static TokenManager instance;

    private TokenManager() throws URISyntaxException, IOException, InterruptedException, ParseException {
        this.token = obtainToken();
    }

    public static synchronized TokenManager getInstance() {
        if (instance == null) {
            try {
                instance = new TokenManager();
            } catch (URISyntaxException | IOException | InterruptedException | ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    public void updateToken() throws URISyntaxException, IOException, ParseException, InterruptedException {
        this.token = obtainToken();
    }

    private String obtainToken() throws URISyntaxException, IOException, InterruptedException, ParseException {
        String authUrl = "https://id.twitch.tv/oauth2/token";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(authUrl + "?client_id=" + clientId + "&client_secret=" + clientSecret + "&grant_type=client_credentials"))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String body = response.body();
        JSONObject jsonBody = (JSONObject) new JSONParser().parse(body);

        return (String) jsonBody.get("access_token");

    }

}
