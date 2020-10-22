package pooh;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerPooh {
    ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();
    ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(5555);
        while (true) {
            Socket s = serverSocket.accept();

            pool.submit(() -> {
                new HandlerHead(queue).process(s);
            });

        }
    }

    public static void main(String[] args) throws IOException {
        ServerPooh serverPooh = new ServerPooh();
        serverPooh.start();
    }
}
