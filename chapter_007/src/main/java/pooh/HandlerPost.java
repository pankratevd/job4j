package pooh;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class HandlerPost {
    private ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue;
    private String path;

    public HandlerPost(ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue, String path) {
        this.queue = queue;
        this.path = path;
    }

    protected void handle(int length, BufferedReader in, PrintWriter out) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char ch = (char) in.read();
            sb.append(ch);
        }
        out.write("HTTP/1.1 200 OK\r\n");
        out.flush();

        Gson gson = new Gson();
        Map<String, String> map = gson.fromJson(sb.toString(), Map.class);
        addMessage(map);
    }

    private void addMessage(Map<String, String> map) {
        String queueName = map.get(path.replaceAll("/", ""));
        String value = map.get("text");
        if (queue.containsKey(queueName)) {
            queue.get(queueName).add(value);
        } else {
            ConcurrentLinkedQueue<String> newValue = new ConcurrentLinkedQueue<>();
            newValue.add(value);
            queue.put(queueName, newValue);
        }
    }
}
