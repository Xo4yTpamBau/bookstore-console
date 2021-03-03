package by.bookstore.console.action;

import by.bookstore.entity.Address;
import by.bookstore.entity.Store;

public interface StoreAction {
    void save();

    void deleteById();

    void deleteByTitle();

    void updateTitle();

    void updateAddress();

    void getAll();

    void getByTitle();

    void getById();
}
