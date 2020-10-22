package pooh;

import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class HandlerGet {
    private ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue;
    private String path;

    public HandlerGet(ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue, String path) {
        this.path = path;
        this.queue = queue;
    }

    protected void handle(PrintWriter out) {
        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/html; charset=utf-8");
        out.println();
        if (!queue.get(path.split("/")[2]).isEmpty()) {
            out.println(makeJson(queue.get(path.split("/")[2]).poll()));
        }
        out.flush();
    }

    private String makeJson(String in) {
        return new StringBuilder()
                .append("{ ")
                .append("\"queue\" : ")
                .append("\"")
                .append(path.split("/")[2])
                .append("\", ")
                .append("\"text\" : ")
                .append("\"")
                .append(in)
                .append("\" }")
                .toString();
    }
}
