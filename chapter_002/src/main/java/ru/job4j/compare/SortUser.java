package ru.job4j.compare;

import java.util.*;

public class SortUser {
    public Set<User> sort(List<User> list) {
        return new TreeSet<User>(list);
    }

    public List<User> sortNameLength(List<User> list) {
        List<User> result = new ArrayList(list);
        result.sort(new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                return Integer.compare(user1.getName().length(), user2.getName().length());
            }
        });
        return result;
    }

    public List<User> sortByAllFields(List<User> list) {
        List<User> resultList = new ArrayList<>(list);
        resultList.sort(new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                int result;
                if (user1.getName().compareTo(user2.getName()) == 0) {
                    result = Integer.compare(user1.getAge(), user2.getAge());
                } else {
                    result = user1.getName().compareTo(user2.getName()) < 0 ? -1 : 1;
                }
                return result;
            }
        });

        return resultList;
    }
}
