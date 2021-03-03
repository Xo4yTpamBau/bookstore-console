package by.bookstore.console.action;

import by.bookstore.entity.*;

public interface OrderAction {
    void save ();

    void updateByStatus();

    void updateByAddress();

    void updateByStore();

    void updateByIsDelivery();

    void deleteById();

    void deleteByStore();

    void addBook();

    void deleteBook();

    void findAll();

    void findAllForUser();

    void findAllByStatus();

    void findAllByUserAndStatus();

    void findAllByAddress();

    void findAllByUser();

    void findAllByStore();

    void findById();
}
