package email;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void emailTo(User user) {
        pool.submit(new Thread(() -> send(getSubject(user), getBody(user), user.getEmail())));
    }

    public void send(String subject, String body, String email) {
    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String getSubject(User user) {
        return "Notification to "
                + user.getEmail()
                + " to email "
                + user.getEmail();
    }

    private String getBody(User user) {
        return "Add a new event to "
                + user.getEmail();

    }
}
