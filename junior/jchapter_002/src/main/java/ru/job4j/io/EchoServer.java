package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (true) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    int index = str.indexOf("?msg=");
                    String value = str.substring(index + 5, str.indexOf(" ", index + 5));
                    while (!str.isEmpty()) {
                        System.out.println(str);
                        str = in.readLine();
                    }

                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());

                    if ("Exit".equals(value)) {
                        break;
                    }
                    if ("Hello".equals(value)) {
                        out.write("Hello".getBytes());
                    } else {
                        out.write(value.getBytes());
                    }
                }
            }
        }
    }
}
