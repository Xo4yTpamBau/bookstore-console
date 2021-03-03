package by.bookstore.service;

import by.bookstore.entity.Author;
import by.bookstore.entity.Book;

public interface BookService {
    boolean add(Book book);

    void delete(int id);

    void delete(String title);

    String updateDescription(String newDescription, int id);

    Author updateAuthor(Author newAuthor, int id);

    int updatePrice(int newPrice, int id);

    Book[] findAll();

    Book findBookById(int id);

    Book findBookByTitle(String title);

    Book[] findAllByAuthor(Author author);

    Book[] findAllByPrice(int price);
}
