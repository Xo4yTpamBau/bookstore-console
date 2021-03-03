package by.bookstore.storage.inmemory;

import by.bookstore.console.util.ConsoleWriter;
import by.bookstore.entity.Address;
import by.bookstore.storage.AddressStorage;

import java.security.CodeSource;
import java.util.Arrays;

public class InMemoryAddressStorage implements AddressStorage {
    private static Address[] addresses = new Address[10];

    static {
        addresses[0] = new Address("Street", 10);
        addresses[1] = new Address("Street2", 20);
        addresses[2] = new Address("Street3", 30);

    }


    @Override
    public void save(Address address) {
        for (int i = 0; i < addresses.length; i++) {
            if (addresses[i] == null) {
                addresses[i] = address;
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < addresses.length; i++) {
            if (addresses[i] == null) break;
            if (addresses[i].getId() == id) {
                for (int j = i; j < addresses.length - 1; j++) {
                    addresses[j] = addresses[j + 1];
                }
                break;
            }
        }
    }

    @Override
    public void delete(Address address) {
        for (int i = 0; i < addresses.length; i++) {
            if (addresses[i] == null) break;
            if (addresses[i].equals(address)) {
                for (int j = i; j < addresses.length - 1; j++) {
                    addresses[j] = addresses[j + 1];
                }
                break;
            }
        }
    }

    @Override
    public String updateStreet(String street, int id) {
        for (int i = 0; i < addresses.length; i++) {
            if (addresses[i] == null) break;
            if (addresses[i].getId() == id) {
                addresses[i].setStreet(street);
                break;
            }
        }
        return null;
    }

    @Override
    public int updateHome(int home, int id) {
        for (int i = 0; i < addresses.length; i++) {
            if (addresses[i] == null) break;
            if (addresses[i].getId() == id) {
                addresses[i].setHome(home);
                break;
            }
        }
        return 0;
    }

    @Override
    public Address[] getAll() {
        int count = 0;
        for (int i = 0; i < addresses.length; i++) {
            if (addresses[i] != null) {
                count++;
            }

        }
        Address[] all = new Address[count];
        for (int j = 0; j < all.length; j++) {
            all[j] = addresses[j];
        }
        return all;

    }

    @Override
    public Address[] getStreet(String street) {

        int count = 0;
        for (int i = 0; i < addresses.length; i++) {
            if (addresses[i] == null) break;
            if (addresses[i] != null) {
                count++;
            }
        }

        Address[] all = new Address[count];
        for (int j = 0; j < all.length; j++) {
            if (addresses[j].getStreet().equals(street)) {
                all[j] = addresses[j];
            }
        }
        return all;
    }

    @Override
    public Address[] getHome(int home) {
        int count = 0;
        for (int i = 0; i < addresses.length; i++) {
            if (addresses[i] == null) break;
            if (addresses[i] != null) {
                count++;
            }
        }
        Address[] all = new Address[count];
        for (int j = 0; j < all.length; j++) {
            if (addresses[j].getHome() == home) {
                all[j] = addresses[j];
            }
        }
        return all;
    }

    @Override
    public Address getById(int id) {
        for (int i = 0; i < addresses.length; i++) {
            if (addresses[i] == null) break;
            if (addresses[i].getId() == id) {
                return addresses[i];
            }
        }
        return null;
    }

    @Override
    public boolean contains(int id) {
        for (int i = 0; i < addresses.length; i++) {
            if (addresses[i] == null) break;

            if (addresses[i].getId() == id) {
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean contains(Address address) {

        for (int i = 0; i < addresses.length; i++) {
            if (addresses[i] == null) break;
            if (addresses[i].equals(address)) {
                return true;
            }
        }
        return false;
    }
}
