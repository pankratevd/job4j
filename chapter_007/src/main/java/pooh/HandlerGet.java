package pooh;

import com.google.gson.*;
import netscape.javascript.JSObject;

import java.io.BufferedReader;
import java.io.IOException;
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

    protected void handle(BufferedReader in, PrintWriter out) throws IOException {
        System.out.println("Path: " + path);
        out.println("HTTP/1.1 200 OK");
        //out.println("Content-Type: text/html; charset=utf-8");
        out.println();
        if (!queue.get(path.split("/")[2]).isEmpty()) {
            //System.out.println("In IF HeaderGET");
            //System.out.println("MakeJSON: " + makeJson(queue.get(path.split("/")[2]).poll()).toString());
            out.println(makeJson(queue.get(path.split("/")[2]).poll()));
        }
        out.flush();
    }

    private String makeJson(String in) {
        System.out.println("Start make JSON");
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
