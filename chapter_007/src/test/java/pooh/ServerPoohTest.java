package pooh;

import org.junit.Test;

import java.io.IOException;

public class ServerPoohTest {
    @Test
    public void testSender() throws IOException {
    /*    ServerPooh serverPooh = new ServerPooh();
        serverPooh.start();*/

        System.out.println("Server started...");
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                try {
                    new Client1().start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        //assertThat(serverPooh.queue.get("queue").size(), is(100));
    }

}