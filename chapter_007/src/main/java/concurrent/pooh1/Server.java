package concurrent.pooh1;

import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> topic = new ConcurrentHashMap<>();
    ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void start() throws IOException {
        ServerSocket socket = new ServerSocket(5555);
        while (true) {
            Socket s = socket.accept();
            pool.submit(new Thread(() -> {
                try {
                    handler(s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));
        }
    }

    private void handler(Socket s) throws IOException {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedWriter out= new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));) {
            String line = in.readLine();
            int length = 0;
            if (line.contains("POST")) {
                if (line.contains("queue/"))
                while (!line.isEmpty()) {
                    if (line.contains("Length")) {
                        length = Integer.parseInt(line.split(":")[1].trim());
                        line=in.readLine();
                    }
                }
                for (int i = 0; i < length; i++) {
                    sb.append(in.read());
                }
                String[] arr = new Gson().fromJson(sb.toString(), String.class).split(",");

            }
        }
    }
}
