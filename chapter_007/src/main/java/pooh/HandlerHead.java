package pooh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class HandlerHead {
    private ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue;

    public HandlerHead(ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue) {
        this.queue = queue;
    }

    void process(Socket s) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
             PrintWriter out = new PrintWriter(s.getOutputStream())
        ) {
            String method = "";
            String path = "";
            int length = 0;
            String line = in.readLine();
            if (!line.isEmpty() && line.split(" ").length > 1) {
                method = line.split(" ")[0];
                path = line.split(" ")[1];
            }
            while (!line.isEmpty()) {
                if (line.contains("Content-Length")) {
                    length = Integer.parseInt(line.split(":")[1].trim());
                }
                line = in.readLine();
            }
            System.out.println("Method: " + method + " path: " + path);
            switch (method.toUpperCase()) {
                case "POST":
                    new HandlerPost(queue, path).handle(length, in, out);
                    break;
                case "GET":
                    System.out.println("Path in GET :" + path);
                    new HandlerGet(queue, path).handle(in, out);
                    break;
                default:
                    s.getOutputStream().write("HTTP/1.1 400 Bad Request\r\n".getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
