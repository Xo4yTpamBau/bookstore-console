package by.bookstore.storage;

import by.bookstore.entity.Address;
import by.bookstore.entity.Store;

public interface StoreStorage {
    void save(Store store);

    void delete(int id);

    void delete(String title);

    String updateTitle(String title, int id);

    Address updateAddress(Address address, int id);

    Store[] getAll();

    Store getByTitle(String title);

    Store getById(int id);

    boolean contains(String title);

    boolean contains(int id);
}
