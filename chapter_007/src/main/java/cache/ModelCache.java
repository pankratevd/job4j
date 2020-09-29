package cache;

import java.util.concurrent.ConcurrentHashMap;

public class ModelCache {
    ConcurrentHashMap<Integer, Base> map = new ConcurrentHashMap<>();


    public void add(Base model) {
        map.putIfAbsent(model.getId(), model);
    }

    public void update(Base model) {
        map.computeIfPresent(model.getId(), (k, v) -> {
            if (model.getVersion() != map.get(k).getVersion() + 1) {
                throw new OptimisticException("Old version: " + map.get(k).getVersion() + " New version: " + model.getVersion());
            }
            return model;
        });
    }

    public void delete(Base model) {
        map.remove(model.getId());
    }

    public Base getModel(int id) {
        return map.get(id);
    }

    @Override

    public String toString() {
        return "ModelCache{"
                + "map=" + map
                + '}';
    }
}
