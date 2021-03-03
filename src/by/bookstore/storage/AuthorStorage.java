package by.bookstore.storage;

import by.bookstore.entity.Author;

public interface AuthorStorage {
    void save(Author author);

    void updateDescription(String newDescription, int id);

    void delete(String name);

    void delete(int id);

    Author[] getAll();

    Author getById(int id);

    Author getByName(String name);

    boolean contains(int id);

    boolean contains(String name);
}
