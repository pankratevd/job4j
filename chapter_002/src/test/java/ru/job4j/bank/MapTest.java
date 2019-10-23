package ru.job4j.bank;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class MapTest {

    @Test
    public void checkUserWhenTrue() {
        Map map = new Map();
        User user = new User("Ivan", "4004");
        map.addUser(user);
        assertTrue(map.isUserExist(user));
    }

    @Test
    public void checkUserWhenFalse() {
        Map map = new Map();
        User user = new User("Ivan", "4004");
        User user1 = new User("Ivan", "4005");
        map.addUser(user);
        assertFalse(map.isUserExist(user1));
    }

    @Test
    public void addUser() {
        Map map = new Map();
        User user = new User("Ivan", "4004");
        map.addUser(user);
        assertTrue(map.isUserExist(user));
    }

    @Test
    public void addUserWhenExist() {
        Map map = new Map();
        User user = new User("Ivan", "4004");
        Account account = new Account("810");
        map.addUser(user);
        map.addAccountToUser("4004", account);
        map.addUser(user);
        Account expected = account;
        assertThat(expected, is(account));
    }

    @Test
    public void deleteUserWhenExist() {
        Map map = new Map();
        User user = new User("Ivan", "4004");
        User user1 = new User("Ivan", "4005");
        map.addUser(user);
        map.addUser(user1);
        assertTrue(map.isUserExist(user));
        map.deleteUser(user);
        assertFalse(map.isUserExist(user));
    }


    @Test
    public void addAccountToUser() {
        Map map = new Map();
        User user = new User("Ivan", "4004");
        User user1 = new User("Ivan", "4005");
        Account account = new Account("810");
        map.addUser(user);
        map.addUser(user1);
        map.addAccountToUser("4004", account);
        Account result = map.getUserAccounts("4004").get(0);
        Account expected = account;
        assertThat(expected, is(result));
    }

    @Test
    public void getUserAccounts() {
        Map map = new Map();
        User user = new User("Ivan", "4004");
        map.addUser(user);
        Account account1 = new Account("810_1");
        Account account2 = new Account("810_2");
        Account account3 = new Account("810_3");
        map.addAccountToUser("4004", account1);
        map.addAccountToUser("4004", account2);
        map.addAccountToUser("4004", account3);
        List result = map.getUserAccounts("4004");
        List expected = new ArrayList();
        expected.add(account1);
        expected.add(account2);
        expected.add(account3);
        System.out.println(map.getUserAccounts("4004").indexOf(new Account("810_1")));
        System.out.println(map.toString());
        assertThat(expected, is(result));
    }

    @Test
    public void deleteAccountFromUser() {
        Map map = new Map();
        User user = new User("Ivan", "4004");
        map.addUser(user);
        Account account1 = new Account("810_1");
        Account account2 = new Account("810_2");
        Account account3 = new Account("810_3");
        map.addAccountToUser("4004", account1);
        map.addAccountToUser("4004", account2);
        map.addAccountToUser("4004", account3);
        map.deleteAccountFromUser("4004", account2);
        List<Account> result = map.getUserAccounts("4004");
        List<Account> expected = new ArrayList<>();
        expected.add(account1);
        expected.add(account3);
        assertThat(expected, is(result));
    }

    @Test
    public void findUserByPassportWhenExist() {
        Map map = new Map();
        User user = new User("Ivan", "4004");
        User user1 = new User("Ivan", "4005");
        map.addUser(user);
        map.addUser(user1);
        String result = map.findUserByPassport("4004").getName();
        String expected = "Ivan";
        assertThat(expected, is(result));
    }

    @Test
    public void transferMoneyWhenTrue() {
        Map map = new Map();
        User user = new User("Ivan", "4004");
        map.addUser(user);
        Account account1 = new Account("810_1", 1000);
        Account account2 = new Account("810_2", 500);
        map.addAccountToUser("4004", account1);
        map.addAccountToUser("4004", account2);
        boolean result = map.transferMoney("4004", "810_1", "4004", "810_2", 999);
        assertTrue(result);
        assertEquals(account1.getValue(), 1, 0.001);
        assertEquals(account2.getValue(), 1499, 0.001);
    }

    @Test
    public void transferMoneyWhenFalse() {
        Map map = new Map();
        User user = new User("Ivan", "4004");
        map.addUser(user);
        Account account1 = new Account("810_1", 1000);
        Account account2 = new Account("810_2", 500);
        map.addAccountToUser("4004", account1);
        map.addAccountToUser("4004", account2);
        boolean result = map.transferMoney("4004", "810_1", "4004", "810_2", 1001);
        assertFalse(result);
        assertEquals(account1.getValue(), 1000, 0.001);
        assertEquals(account2.getValue(), 500, 0.001);
    }

    @Test
    public void transferMoneyToOtherUser() {
        Map map = new Map();
        User user = new User("Ivan", "4004");
        User user1 = new User("Ivan", "4005");
        map.addUser(user);
        map.addUser(user1);
        Account account1 = new Account("810_1", 1000);
        Account account2 = new Account("810_2", 500);
        map.addAccountToUser("4004", account1);
        map.addAccountToUser("4005", account2);
        boolean result = map.transferMoney("4004", "810_1", "4005", "810_2", 999);
        assertTrue(result);
        assertEquals(account1.getValue(), 1, 0.001);
        assertEquals(account2.getValue(), 1499, 0.001);
    }

    @Test
    public void findUserByPassportWhenNotExist() {
        Map map = new Map();
        User user = new User("Ivan", "4004");
        User user1 = new User("Ivan", "4005");
        map.addUser(user);
        map.addUser(user1);
        User result = map.findUserByPassport("4007");
        assertNull(result);
    }

}