package by.bookstore.storage;

import by.bookstore.entity.Author;
import by.bookstore.entity.Book;

public interface BookStorage {
    void save(Book book);

    void delete(int id);

    void delete(String title);

    String updateDescription(String newDescription, int id);

    Author updateAuthor(Author newAuthor, int id);

    int updatePrice(int newPrice, int id);

    Book[] getAll();

    Book getById(int id);

    Book getByTitle(String title);

    Book[] getAllByAuthor(Author author);

    Book[] getAllByPrice(double price);

    boolean contains(int id);

    boolean contains(String title);
}
