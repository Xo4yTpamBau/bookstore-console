package by.bookstore.service;

import by.bookstore.entity.Address;
import by.bookstore.entity.User;
import by.bookstore.storage.UserStorage;
import by.bookstore.storage.db.DBUserStorage;
import by.bookstore.storage.file.FileUserStorage;
import by.bookstore.storage.inmemory.InMemoryUserStorage;

public class UserServiceImpl implements UserService {
    private UserStorage userStorage = new DBUserStorage();


    @Override
    public boolean save(User user) {
        if (userStorage.contains(user.getLogin())) {
            return false;
        }
        userStorage.save(user);
        return true;
    }

    @Override
    public void updateName(String name, int id) {
        if (userStorage.contains(id)) {
            userStorage.updateName(name, id);
        }
    }

    @Override
    public void updatePassword(String password, int id) {
        if (userStorage.contains(id)) {
            userStorage.updatePassword(password, id);
        }
    }

    @Override
    public void updateAge(int age, int id) {
        if (userStorage.contains(id)) {
            userStorage.updateAge(age, id);
        }
    }

    @Override
    public void updateAddress(Address address, int id) {
        if (userStorage.contains(id)) {
            userStorage.updateAddress(address, id);
        }
    }

    @Override
    public void deleteById(int id) {
        if (userStorage.contains(id)) {
            userStorage.deleteById(id);
        }
    }

    @Override
    public void deleteByLogin(String login) {
        if (userStorage.contains(login)) {
            userStorage.deleteByLogin(login);
        }
    }

    @Override
    public User[] findAll() {
        return userStorage.findAll();
    }

    @Override
    public User[] findAllByAge(int age) {
        return userStorage.findAllByAge(age);
    }

    @Override
    public User[] findAllByName(String name) {
        return userStorage.findAllByName(name);
    }

    @Override
    public User findByAddress(Address address) {
        return userStorage.findByAddress(address);
    }

    @Override
    public User findById(int id) {
        if (userStorage.contains(id)) {
            return userStorage.findById(id);
        }
        return null;
    }

    @Override
    public User findByLogin(String login) {
        if (userStorage.contains(login)) {
            return userStorage.findByLogin(login);
        }
        return null;
    }
}
