package by.bookstore.storage.inmemory;

import by.bookstore.entity.Address;
import by.bookstore.entity.Role;
import by.bookstore.entity.User;
import by.bookstore.storage.UserStorage;

import java.lang.reflect.Array;
import java.util.Arrays;


public class InMemoryUserStorage implements UserStorage {
    private static User[] users = new User[10];

    static {
        users[0] = new User(1, "Admin", "admin", "admin", 22, new Address("test", 44), Role.ADMIN);
        users[1] = new User(2, "User", "user", "user", 22, new Address("test", 23), Role.USER);
    }

    @Override
    public void save(User user) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) {
                users[i] = user;
                break;
            }
        }
    }

    @Override
    public void updateName(String name, int id) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) break;
            if (users[i].getId() == id) {
                users[i].setName(name);
            }
        }
    }

    @Override
    public void updatePassword(String password, int id) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) break;
            if (users[i].getId() == id) {
                users[i].setPassword(password);
            }
        }
    }

    @Override
    public void updateAge(int age, int id) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) break;
            if (users[i].getId() == id) {
                users[i].setAge(age);
            }
        }
    }

    @Override
    public void updateAddress(Address address, int id) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) break;
            if (users[i].getId() == id) {
                users[i].setAddress(address);
            }

        }
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) break;
            if (users[i].getId() == id) {
                users[i] = null;
                for (int j = i; j < users.length - 1; j++) {
                    users[j] = users[j + 1];
                }
            }
        }
    }

    @Override
    public void deleteByLogin(String login) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) break;
            if (users[i].getLogin().equals(login)) {
                users[i] = null;
                for (int j = i; j < users.length - 1; j++) {
                    users[j] = users[j + 1];
                }
            }
        }
    }

    @Override
    public User[] findAll() {
        int count = 0;
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                count++;
            }
        }
        User[] all = new User[count];
        for (int i = 0; i < all.length; i++) {
            all[i] = users[i];

        }
        return all;
    }

    @Override
    public User[] findAllByAge(int age) {
        int count = 0;
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null && users[i].getAge() == age) {
                count++;
            }
        }
        User[] allAge = new User[count];
        for (int i = 0, g = 0; i < users.length; i++) {
            if (users[i] != null && users[i].getAge() == age) {
                allAge[g] = users[i];
                g++;
            }
        }
        return allAge;
    }

    @Override
    public User[] findAllByName(String name) {
        int count = 0;
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null && users[i].getName().equals(name)) {
                count++;
            }
        }
        User[] allName = new User[count];
        for (int i = 0, g = 0; i < users.length; i++) {
            if (users[i] != null && users[i].getName().equals(name)) {
                allName[g] = users[i];
                g++;
            }
        }
        return allName;
    }

    @Override
    public User findByAddress(Address address) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) break;
            if (users[i].getAddress().equals(address)) {
                return users[i];
            }
        }
        return null;
    }

    @Override
    public User findById(int id) {
        for (int i = 0; i < users.length; i++) {
            if (users[i].getId() == id) {
                return users[i];
            }
        }
        return null;
    }

    @Override
    public User findByLogin(String login) {
        for (int i = 0; i < users.length; i++) {
            if (users[i].getLogin().equals(login)) {
                return users[i];
            }
        }
        return null;
    }

    @Override
    public boolean contains(int id) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) break;
            if (users[i].getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(String login) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) break;

            if (users[i].getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }
}
