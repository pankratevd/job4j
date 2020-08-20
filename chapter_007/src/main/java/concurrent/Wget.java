package concurrent;

import java.io.*;
import java.net.URL;

public class Wget {
    void download(String address, int speed) throws IOException {
        int target = speed * 1024;
        URL url = new URL(address);
        try (InputStream in = url.openStream();
             OutputStream out = new FileOutputStream(new File(address.substring(address.lastIndexOf("/") + 1)));
        ) {
            int bytesRead;
            byte[] buf = new byte[1024];
            long start = System.currentTimeMillis();
            long time;
            long expected;
            long countBytes = 0;
            int sleep;
            while ((bytesRead = in.readNBytes(buf, 0, 1024)) != 0) {
                countBytes += bytesRead;
                out.write(buf, 0, bytesRead);
                time = (System.currentTimeMillis() - start) / 1000;
                expected = target * time;
                sleep = (int) (countBytes - expected) / 1000;

                if (sleep > 0) {
                    try {
                        Thread.sleep(sleep);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        Thread thread = new Thread(() -> {
            char[] arr = {'-', '\\', '|', '/'};
            int i = 0;
            while (!Thread.currentThread().isInterrupted()) {
                System.out.print("\rLoading: " + arr[i++]);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                if (i == 4) {
                    i = 0;
                }
            }
        });

        thread.start();

        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        new Wget().download(url, speed);
        thread.interrupt();
        System.out.println("\rFile: " + url.substring(url.lastIndexOf("/") + 1) + " is loaded.");
    }
}
