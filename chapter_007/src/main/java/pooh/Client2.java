package pooh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Client2 {

    public void start() throws IOException {

        URL url = new URL("http://localhost:5555/queue/weather");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        //connection.setRequestProperty("Content-Type", "application/json; utf-8");

        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                //System.out.println(inputLine);
                response.append(inputLine);
            }

            System.out.println(response.toString());
        }
        //System.out.println(connection.getResponseCode());
        connection.disconnect();
    }

    public static void main(String[] args) throws IOException {
        new Client2().start();
    }
}
