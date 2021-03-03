package by.bookstore.console.action;

import by.bookstore.entity.Address;

public interface AddressAction {
    void save();

    void deleteById();

    void deleteByAddress();

    void updateStreet();

    void updateHome();

    void getAll();

    void getStreet();

    void getHome();

    void getById();
}
