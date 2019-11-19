package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {

    public Iterator<Integer> convert(Iterator<Iterator<Integer>> its) {

        return new Iterator<Integer>() {
            Iterator<Integer> it = its.next();

            @Override
            public boolean hasNext() {
                boolean result = false;

                if (!it.hasNext()) {
                    while (its.hasNext()) {
                        it = its.next();
                        if (it.hasNext()) {
                            result = true;
                            break;
                        }
                    }
                } else {
                    result = true;
                }
                return result;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return it.next();
            }
        };
    }


}
