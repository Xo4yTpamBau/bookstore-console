package by.bookstore.storage;

import by.bookstore.entity.Address;
import by.bookstore.entity.User;

public interface UserStorage {
    void save(User user);

    void updateName(String name, int id);

    void updatePassword(String password, int id);

    void updateAge(int age, int id);

    void updateAddress(Address address, int id);

    void deleteById(int id);

    void deleteByLogin(String login);

    User[] findAll();

    User[] findAllByAge(int age);

    User[] findAllByName(String name);

    User findByAddress(Address address);

    User findById(int id);

    User findByLogin(String login);

    boolean contains(int id);

    boolean contains(String login);
}
