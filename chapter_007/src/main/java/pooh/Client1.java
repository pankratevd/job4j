package pooh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Client1 {
    public void start() throws IOException {
        URL url = new URL("http://localhost:5555/");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        String jsonInputString = "{\"queue\" : \"weather\",\"text\" : \"temperature +18 C\"}";
        try {
            OutputStream os = con.getOutputStream();
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
            os.write("\n".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Client1().start();
       for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                try {
                    new Client1().start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            new Thread(()-> {
                try {
                    new Client2().start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
