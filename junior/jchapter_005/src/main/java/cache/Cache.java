package cache;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Cache implements ICache {
    HashMap<String, SoftReference<String>> map = new HashMap<>();
    private final String dir;

    public Cache(String dir) {
        this.dir = dir;
    }

    @Override
    public String getFile(String name) {
        String result = null;
        String file = dir + "/" + name;
        if (map.get(file) == null || map.get(file).get() == null) {
            try {
                if (load(file)) {
                    result = map.get(file).get();
                }
            } catch (IOException e) {
                e.getMessage();
            }
        } else {
            result = map.get(file).get();
        }
        return result;
    }

    private boolean load(String name) throws IOException {
        boolean result = false;
        if (map.get(name) == null) {
            try {
                map.put(name, new SoftReference<>((Files.readString(Paths.get(name)))));
                result = true;
            } catch (IOException e) {
                throw e;
            }
        }
        return result;
    }
}
