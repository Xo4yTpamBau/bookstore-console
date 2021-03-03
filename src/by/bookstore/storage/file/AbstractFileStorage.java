package by.bookstore.storage.file;

import by.bookstore.entity.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public abstract class AbstractFileStorage {
    protected static final String PATH_USERS = "users.txt";
    protected static final String PATH_ROLES = "roles.txt";
    protected static final String PATH_ADDRESS = "address.txt";
    protected static final String PATH_STORE = "store.txt";
    protected static final String PATH_ORDERS = "order.txt";
    protected static final String PATH_BOOK = "books.txt";
    protected static final String PATH_STATUS = "status.txt";
    protected static final String PATH_STORES = "store.txt";
    protected static final String PATH_AUTHOR = "authors.txt";

    protected static final int ORDERS_ID = 0;
    protected static final int ORDERS_USER_ID = 1;
    protected static final int ORDERS_DELIVERY = 2;
    protected static final int ORDERS_STATUS_ID = 3;
    protected static final int ORDERS_BOOKS_ID = 4;
    protected static final int ORDERS_ADDRESS = 5;
    protected static final int ORDERS_STORE_ID = 6;

    protected static final int STATUS_ID = 0;
    protected static final int STATUS = 1;

    protected static final int USER_ID = 0;
    protected static final int USER_NAME = 1;
    protected static final int USER_LOGIN = 2;
    protected static final int USER_PASSWORD = 3;
    protected static final int USER_AGE = 4;
    protected static final int USER_ADDRESS_ID = 5;
    protected static final int USER_ROLE = 6;

    protected static final int STORE_ID = 0;
    protected static final int STORE_TITLE = 1;
    protected static final int STORE_ADDRESS_ID = 2;

    protected static final int BOOK_ID = 0;
    protected static final int BOOK_TITLE = 1;
    protected static final int BOOK_DESCRIPTION = 2;
    protected static final int BOOK_AUTHOR_ID = 3;
    protected static final int BOOK_PRICE = 4;

    protected static final int AUTHOR_ID = 0;
    protected static final int AUTHOR_NAME = 1;
    protected static final int AUTHOR_DESCRIPTION = 2;

    protected static final int ADDRESS_ID = 0;
    protected static final int ADDRESS_STREET = 1;
    protected static final int ADDRESS_HOME = 2;

    protected static final int ROLE_ID = 0;
    protected static final int ROLE = 1;

    protected int getLastId(String path) {
        if (new File(path).length() == 0) return 0;
        String[] s = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                s = line.split(" ");
            }
            if (s == null) return 0;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(s[0]);
    }

    protected Author getAuthorById(String id) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_AUTHOR))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] s = line.split(" ");
                if (s[AUTHOR_ID].equals(id)) {
                    return new Author(Integer.parseInt(s[AUTHOR_ID]),
                            s[AUTHOR_NAME],
                            s[AUTHOR_DESCRIPTION]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected Address getAddressById(String addressId) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_ADDRESS))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] s = line.split(" ");
                if (s[ADDRESS_ID].equals(addressId)) {
                    return new Address(Integer.parseInt(s[ADDRESS_ID]),
                            s[ADDRESS_STREET],
                            Integer.parseInt(s[ADDRESS_HOME]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected User getUserById(String userId) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_USERS))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] s = line.split(" ");
                if (s[USER_ID].equals(userId)) {
                    return new User(Integer.parseInt(s[USER_ID]),
                            s[USER_NAME],
                            s[USER_LOGIN],
                            s[USER_PASSWORD],
                            Integer.parseInt(s[USER_AGE]),
                            getAddressById(s[USER_ADDRESS_ID]),
                            Role.valueOf(s[USER_ROLE]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected int getIdByAddress(String street, String home) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_ADDRESS))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] s = line.split(" ");
                if (s[ADDRESS_STREET].equals(street) && s[ADDRESS_HOME].equals(home)) {
                    return Integer.parseInt(s[ADDRESS_ID]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    protected Store getStoreById(String storeId) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_STORES))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] s = line.split(" ");
                if (s[STORE_ID].equals(storeId)) {
                    return new Store(Integer.parseInt(s[STORE_ID]),
                            s[STORE_TITLE],
                            getAddressById(s[STORE_ADDRESS_ID]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



}
