package ru.job4j.statistic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Analyze {

    public Info diff(List<User> previous, List<User> current) {

        Info info = new Info();

        Map<Integer, String> prev = new HashMap<>();

        Map<Integer, String> curr = new HashMap<>();

        previous.forEach(u -> prev.put(u.id, u.name));

        current.forEach(u -> curr.put(u.id, u.name));

        info.added = (int) curr.entrySet().stream().filter(e -> !prev.containsKey(e.getKey())).count();

        info.deleted = (int) prev.entrySet().stream().filter(e -> !curr.containsKey(e.getKey())).count();

        info.changed = (int) prev.entrySet().stream().filter(e-> (curr.containsKey(e.getKey()) && (!e.getValue().equals(curr.get(e.getKey()))))).count();

        return info;
    }

    public static class User {
        private int id;
        private String name;


        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id
                    && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        @Override
        public String toString() {
            return "User{id=" + id
                    + ", name='" + name + '\''
                    + '}';
        }
    }

    public static class Info {

        private int added;
        private int changed;
        private int deleted;

        public int getAdded() {
            return added;
        }

        public int getChanged() {
            return changed;
        }

        public int getDeleted() {
            return deleted;
        }

    }
}
