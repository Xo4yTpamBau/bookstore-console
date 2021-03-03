package by.bookstore.service;

import by.bookstore.entity.Address;
import by.bookstore.entity.Store;

public interface StoreService {
    boolean save(Store store);

    void delete(int id);

    void delete(String title);

    String updateTitle(String title, int id);

    Address updateAddress(Address address, int id);

    Store[] getAll();

    Store getByTitle(String title);

    Store getById(int id);
}
