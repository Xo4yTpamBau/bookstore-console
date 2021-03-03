package by.bookstore.service;

import by.bookstore.entity.Address;
import by.bookstore.entity.Store;
import by.bookstore.storage.StoreStorage;
import by.bookstore.storage.db.DBStoreStorage;
import by.bookstore.storage.file.FileStoreStorage;
import by.bookstore.storage.inmemory.InMemoryStoreStorage;

public class StoreServiceImpl implements StoreService {
    private StoreStorage storeStorage = new DBStoreStorage();


    @Override
    public boolean save(Store store) {
        if (storeStorage.contains(store.getTitle())) {
            return false;
        }
        storeStorage.save(store);
        return true;
    }

    @Override
    public void delete(int id) {
        if (storeStorage.contains(id)) {
            storeStorage.delete(id);
        }
    }

    @Override
    public void delete(String title) {
        if (storeStorage.contains(title)) {
            storeStorage.delete(title);
        }
    }

    @Override
    public String updateTitle(String title, int id) {
        if (storeStorage.contains(id)) {
            if (storeStorage.contains(title)) {
                return null;
            } else {
                storeStorage.updateTitle(title, id);
            }
        }
        return null;
    }

    @Override
    public Address updateAddress(Address address, int id) {
        if (storeStorage.contains(id)) {
            storeStorage.updateAddress(address, id);
        }
        return null;
    }

    @Override
    public Store[] getAll() {
        return storeStorage.getAll();
    }

    @Override
    public Store getByTitle(String title) {
        if (storeStorage.contains(title)) {
           return storeStorage.getByTitle(title);
        }
        return null;
    }

    @Override
    public Store getById(int id) {
        if (storeStorage.contains(id)) {
          return   storeStorage.getById(id);
        }
        return null;
    }
}
