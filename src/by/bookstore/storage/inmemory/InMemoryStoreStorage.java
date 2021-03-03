package by.bookstore.storage.inmemory;

import by.bookstore.entity.Address;
import by.bookstore.entity.Store;
import by.bookstore.storage.StoreStorage;

import java.util.Arrays;

public class InMemoryStoreStorage implements StoreStorage {
    private static Store[] stores = new Store[10];

    static {
        stores[0] = new Store("Test", new Address("Test", 11));
        stores[1] = new Store("Test2", new Address("Test2", 12));
        stores[2] = new Store("Test3", new Address("Test3", 13));
    }

    @Override
    public void save(Store store) {
        for (int i = 0; i < stores.length; i++) {
            if (stores[i] == null) {
                stores[i] = store;
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < stores.length; i++) {
            if (stores[i] == null) break;
            if (stores[i].getId() == id) {
                for (int j = i; j < stores.length - 1; j++) {
                    stores[j] = stores[j + 1];
                }
                break;
            }
        }
    }

    @Override
    public void delete(String title) {
        for (int i = 0; i < stores.length; i++) {
            if (stores[i] == null) break;
            if (stores[i].getTitle().equals(title)) {
                for (int j = i; j < stores.length - 1; j++) {
                    stores[j] = stores[j + 1];
                }
                break;
            }
        }
    }


    @Override
    public String updateTitle(String title, int id) {
        for (int i = 0; i < stores.length; i++) {
            if (stores[i] == null) break;
            if (stores[i].getId() == id) {
//                String title1 = stores[i].getTitle();
                stores[i].setTitle(title);
//                return title1;
            }
        }
        return null;
    }

    @Override
    public Address updateAddress(Address address, int id) {
        for (int i = 0; i < stores.length; i++) {
            if (stores[i] == null) break;
            if (stores[i].getId() == id) {
//                Address address1 = stores[i].getAddress();
                stores[i].setAddress(address);
//                return address1;
            }
        }
        return null;
    }

    @Override
    public Store[] getAll() {
        int count = 0;
        for (int i = 0; i < stores.length; i++) {
            if (stores[i] == null) break;
            if (stores[i] != null) {
                count++;
            }
        }
        Store[] all = new Store[count];
        for (int j = 0; j < all.length; j++) {
            all[j] = stores[j];

        }
        return all;
    }

    @Override
    public Store getByTitle(String title) {
        for (int i = 0; i < stores.length; i++) {
            if (stores[i] == null) break;
            if (stores[i].getTitle().equals(title)) {
                return stores[i];
            }
        }
        return null;
    }

    @Override
    public Store getById(int id) {
        for (int i = 0; i < stores.length; i++) {
            if (stores[i] == null) break;
            if (stores[i].getId() == id) {
                return stores[i];
            }
        }
        return null;
    }

    @Override
    public boolean contains(String title) {
        for (int i = 0; i < stores.length; i++) {
            if (stores[i] == null) break;
            if (stores[i].getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(int id) {
        for (int i = 0; i < stores.length; i++) {
            if (stores[i] == null) break;
            if (stores[i].getId() == id) {
                return true;
            }
        }
        return false;
    }
}
