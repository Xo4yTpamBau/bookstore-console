package by.bookstore.console.action;

import by.bookstore.entity.Book;

public interface BookAction {
    void add();

    void deleteById();

    void deleteByTitle();

    void updateDescription();

    void updateAuthor();

    void updatePrice();

    void findAll();

    void findAllForUser();

    void findBookById();

    void findBookByTitle();

    void findBookByTitleForUser();

    void findAllByAuthor();

    void findAllByAuthorForUser();

    void findAllByPrice();

    void findAllByPriceForUser();
}
