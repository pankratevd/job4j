package concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
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
    }

    public static void main(String[] args) {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        progress.interrupt();
    }
}
