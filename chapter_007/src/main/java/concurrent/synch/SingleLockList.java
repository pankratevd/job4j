package concurrent.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.DynamicList;

import java.util.Iterator;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this")
    private final DynamicList<T> list = new DynamicList<>();

    public synchronized void add(T value) {
        list.add(value);
    }

    public synchronized T get(int index) {
        return list.get(index);
    }


    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.list).iterator();
    }

    private synchronized DynamicList<T> copy(DynamicList<T> list) {
        DynamicList<T> list1 = new DynamicList<>();
        list.forEach(list1::add);
        return list1;
    }
}
