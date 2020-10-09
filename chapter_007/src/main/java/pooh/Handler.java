package pooh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Handler {
    private ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue;

    public Handler(ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue) {
        this.queue = queue;
    }

    void process(Socket s) throws IOException {
        try (s;
             BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
             PrintWriter out = new PrintWriter(s.getOutputStream())
        ) {
            String method = "";
            int length = 0;
            String line = in.readLine();
            if (!line.isEmpty()) {
                method = line.split(" ")[0];
            }
            while (!line.isEmpty()) {
                if (line.contains("Content-Length")) {
                    length = Integer.parseInt(line.split(":")[1].trim());
                }
                line = in.readLine();
            }
            switch (method.toUpperCase()) {
                case "POST":
                    new HandlerPost(queue).handle(length, in, out);
                    break;
                case "GET":
                    new HandlerGet(queue).handle(in, out);
                    break;
                default:
                    s.getOutputStream().write("HTTP/1.1 400 Bad Request\r\n".getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
