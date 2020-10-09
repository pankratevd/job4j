package pooh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class HandlerGet {
    private ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue;

    public HandlerGet(ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue) {
        this.queue = queue;
    }

    protected void handle(BufferedReader in, PrintWriter out) throws IOException {
        out.println("HTTP/1.1 200 OK");
        //out.println("Content-Type: text/html; charset=utf-8");
        out.println();
        if (!queue.get("weather").isEmpty()) {
            out.println(queue.get("weather").poll());
        }
        out.println("Queue size: " + queue.get("weather").size());
        out.flush();
    }
}
