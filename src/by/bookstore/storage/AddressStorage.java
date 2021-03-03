package by.bookstore.storage;

import by.bookstore.entity.Address;

public interface AddressStorage {
    void save(Address address);
    void delete(int id);
    void delete(Address address);
    String updateStreet(String street, int id);
    int updateHome(int home, int id);

    Address[] getAll();
    Address[] getStreet(String street);
    Address[] getHome(int home);
    Address getById(int id);

    boolean contains(int id);
    boolean contains(Address address);
}
