package by.bookstore.service;

import by.bookstore.entity.*;

public interface OrderService {
    boolean save (Order order);

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

    Order[] findAll(User user, Status status);

    Order findById(int id);
}
