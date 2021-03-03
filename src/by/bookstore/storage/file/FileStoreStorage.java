package by.bookstore.storage.file;

import by.bookstore.entity.Address;
import by.bookstore.entity.Store;
import by.bookstore.storage.StoreStorage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileStoreStorage extends AbstractFileStorage implements StoreStorage {


    @Override
    public void save(Store store) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(getLastId(PATH_STORE) + 1).append(" ")
                .append(store.getTitle()).append(" ")
                .append(store.getAddress().getId()).append(" ");
        String s = stringBuilder.toString();

        try (FileWriter fileWriter = new FileWriter(PATH_STORE, true)) {
            fileWriter.write(s);
            fileWriter.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        List<Store> all = new ArrayList<>(Arrays.asList(getAll()));
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId() == id) {
                all.remove(i);
                break;
            }
        }
        writeStringStore(all);
    }

    @Override
    public void delete(String title) {
        List<Store> all = new ArrayList<>(Arrays.asList(getAll()));
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getTitle().equals(title)) {
                all.remove(i);
                break;
            }
        }
        writeStringStore(all);
    }

    private void writeStringStore(List<Store> all) {
        try (FileWriter fileWriter = new FileWriter(PATH_STORE)) {
            for (Store store : all) {
                fileWriter.write(String.format("%d %s %d",
                        store.getId(),
                        store.getTitle(),
                        store.getAddress().getId()));
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String updateTitle(String title, int id) {
        List<Store> all = new ArrayList<>(Arrays.asList(getAll()));
        for (Store store : all) {
            if (store.getId() == id) {
                store.setTitle(title);
            }
        }
        writeStringStore(all);
        return null;
    }

    @Override
    public Address updateAddress(Address address, int id) {
        List<Store> all = new ArrayList<>(Arrays.asList(getAll()));
        for (Store store : all) {
            if (store.getId() == id) {
                store.getAddress().setId(address.getId());
            }
        }
        writeStringStore(all);
        return null;
    }

    @Override
    public Store[] getAll() {
        List<Store> all = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_STORE))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] s = line.split(" ");
                all.add(new Store(Integer.parseInt(s[STORE_ID]),
                        s[STORE_TITLE],
                        getAddressById(s[STORE_ADDRESS_ID])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return all.toArray(new Store[0]);
    }

    @Override
    public Store getByTitle(String title) {
        Store[] all = getAll();
        for (Store store : all) {
            if (store.getTitle().equals(title)) {
                return store;
            }
        }
        return null;
    }

    @Override
    public Store getById(int id) {
        return getStoreById(String.valueOf(id));
    }

    @Override
    public boolean contains(String title) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_STORE))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] s = line.split(" ");
                if (s[STORE_TITLE].equals(title)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean contains(int id) {
        Store[] all = getAll();
        for (Store store : all) {
            if (store.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
