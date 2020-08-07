package demogc;

import java.util.ArrayList;
import java.util.List;

import static com.carrotsearch.sizeof.RamUsageEstimator.sizeOf;

public class Start {

    public static void main(String[] args) {
        List<User> arr = new ArrayList<>();
        System.out.println(sizeOf(new User()));
        System.out.println(sizeOf(new User()));
        System.out.println(sizeOf(new User()));
        System.out.println(sizeOf(new User()));
        for (int i = 0; i < 30000; i++) {
            arr.add(new User());
        }
    }
}
