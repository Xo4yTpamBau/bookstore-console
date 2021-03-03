package by.bookstore.service;

import by.bookstore.entity.Address;

public interface AddressService {
    boolean save(Address address);

    void delete(int id);

    void delete(Address address);

    String updateStreet(String street, int id);

    int updateHome(int home, int id);

    Address[] getAll();

    Address[] getStreet(String street);

    Address[] getHome(int home);

    Address getById(int id);
}
