package cache;

public class MainCache {
    public static void main(String[] args) {

        String file1 = "name.txt";
        String file2 = "address.txt";
        ICache cache = new Cache("./junior/jchapter_005/src/main/resources");

        System.out.println("Read from disk");
        long start = System.currentTimeMillis();
        System.out.println(cache.getFile(file1));
        System.out.println(cache.getFile(file2));
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start));

        System.out.println();
        System.out.println("Read from cache");
        start = System.currentTimeMillis();
        System.out.println(cache.getFile(file1));
        System.out.println(cache.getFile(file2));
        end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start));
    }
}
