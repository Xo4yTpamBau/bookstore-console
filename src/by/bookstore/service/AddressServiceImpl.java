package by.bookstore.service;

import by.bookstore.entity.Address;
import by.bookstore.storage.AddressStorage;
import by.bookstore.storage.db.DBAddressStorage;
import by.bookstore.storage.file.FileAddressStorage;
import by.bookstore.storage.inmemory.InMemoryAddressStorage;

public class AddressServiceImpl implements AddressService {
    private AddressStorage addressStorage = new DBAddressStorage();


    @Override
    public boolean save(Address address) {
        if (addressStorage.contains(address)) {
            return false;
        }
        addressStorage.save(address);
        return true;
    }

    @Override
    public void delete(int id) {
        if(addressStorage.contains(id)){
            addressStorage.delete(id);
        }
    }


    @Override
    public void delete(Address address) {
        if (addressStorage.contains(address)){
            addressStorage.delete(address);
        }
    }

    @Override
    public String updateStreet(String street, int id) {
        if (addressStorage.contains(id)){
            return addressStorage.updateStreet(street, id);
        }
        return null;
    }

    @Override
    public int updateHome(int home, int id) {
        if (addressStorage.contains(id)){
            return addressStorage.updateHome(home, id);
        }
        return -1;
    }

    @Override
    public Address[] getAll() { return addressStorage.getAll(); }

    @Override
    public Address[] getStreet(String street) {
        return addressStorage.getStreet(street);
    }

    @Override
    public Address[] getHome(int home) {
        return addressStorage.getHome(home);
    }

    @Override
    public Address getById(int id) {
        if (addressStorage.contains(id)){
            return addressStorage.getById(id);
        }
        return null;
    }
}
