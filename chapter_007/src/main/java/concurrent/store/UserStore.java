package concurrent.store;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStore {
    @GuardedBy("this")
    private final Map<Integer, User> store = new HashMap<>();

    public synchronized boolean add(User user) {
        boolean result = false;
        if (!store.containsKey(user.getId())) {
            store.put(user.getId(), user);
            result = true;
        }
        return result;
    }

    public synchronized boolean update(User user) {
        boolean result = false;
        if (store.containsKey(user.getId())) {
            store.put(user.getId(), user);
            result = true;
        }
        return result;
    }

    public synchronized boolean delete(User user) {
        boolean result = false;
        if (store.containsKey(user.getId())) {
            store.remove(user.getId());
            result = true;
        }
        return result;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        if (store.containsKey(fromId) && store.containsKey(toId)
                && store.get(fromId).getAmount() >= amount) {
            store.get(fromId).setAmount(store.get(fromId).getAmount() - amount);
            store.get(toId).setAmount(store.get(toId).getAmount() + amount);
            result = true;
        }
        return result;
    }
}
