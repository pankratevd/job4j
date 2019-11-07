package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Map {

    private HashMap<User, ArrayList<Account>> map = new HashMap<>();

    public void addUser(User user) {
        this.map.putIfAbsent(user, new ArrayList());
    }

    public void deleteUser(User user) {
        this.map.remove(user);
    }

    public void addAccountToUser(String passport, Account account) {
        User user = findUserByPassport(passport);
        if (user != null) {
            map.get(user).add(account);
        }
    }

    public void deleteAccountFromUser(String passport, Account account) {
        User user = findUserByPassport(passport);
        if (user != null) {
            for (Account account1 : map.get(user)) {
                if (account1.equals(account)) {
                    map.get(user).remove(account);
                }
            }
        }
    }

    public List<Account> getUserAccounts(String passport) {
        ArrayList<Account> result = new ArrayList<>();
        map.entrySet().stream()
                .filter(k -> k.getKey().getPassport().equals(passport))
                .forEach(v -> result.addAll(v.getValue()));

        return result;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String dstRequisite, double amount) {
        boolean result = false;
        Account srcAccount = this.getUserAccounts(srcPassport).get(this.getUserAccounts(srcPassport).indexOf(new Account(srcRequisite)));
        Account dstAccount = this.getUserAccounts(destPassport).get(this.getUserAccounts(destPassport).indexOf(new Account(dstRequisite)));
        if (srcAccount.getValue() >= amount && dstAccount != null) {
            srcAccount.setValue(srcAccount.getValue() - amount);
            dstAccount.setValue(dstAccount.getValue() + amount);
            result = true;
        }

        return result;
    }

    boolean isUserExist(User user) {
        return map.containsKey(user);
    }

    User findUserByPassport(final String passport) {
        List<User> list = map.keySet().stream()
                .filter(k -> k.getPassport().equals(passport))
                .collect(Collectors.toList());
        return list.size() == 0 ? null : list.get(0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (User user : map.keySet()) {
            sb.append(user.toString())
                    .append(": ")
                    .append(map.get(user).toString());
        }
        return sb.toString();
    }
}
