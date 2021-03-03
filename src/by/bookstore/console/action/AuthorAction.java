package by.bookstore.console.action;

import by.bookstore.entity.Author;

public interface AuthorAction {

    void save();

    void updateDescription();

    void deleteByName();

    void deleteById();

    void getAll();

    void getById();

    void getByName();

}
