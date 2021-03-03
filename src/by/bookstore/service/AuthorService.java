package by.bookstore.service;

import by.bookstore.entity.Author;

public interface AuthorService {

    boolean save(Author author);

    void updateDescription(String newDescription, int id);

    void delete(String name);

    void delete(int id);

    Author[] getAll();

    Author getById(int id);

    Author getByName(String name);
}
