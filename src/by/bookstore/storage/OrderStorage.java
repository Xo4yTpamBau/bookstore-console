package by.bookstore.storage;

import by.bookstore.entity.*;

public interface OrderStorage {
    void save(Order order);

    void update(Status status, int id);

    void update(Address address, int id);

    void update(Store store, int id);

    void update(boolean isDelivery, int id);

    void delete(int id);

    void delete(Store store);

    void addBook(Book book, int id);

    void deleteBook(int bookId, int id);

    Order[] findAll();

    Order[] findAll(Status status);

    Order[] findAll(Address address);

    Order[] findAll(User user);

    Order[] findAll(Store store);

    Order findById(int id);

    Order[] findAll(User user, Status status);

    boolean contains(int id);

    boolean contains(Order order);
}
