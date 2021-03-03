package by.bookstore.service;

import by.bookstore.entity.Author;
import by.bookstore.storage.AuthorStorage;
import by.bookstore.storage.db.DBAuthorStorage;
import by.bookstore.storage.file.FileAuthorStorage;
import by.bookstore.storage.inmemory.InMemoryAuthorStorage;

public class AuthorServiceImpl implements AuthorService {
    private AuthorStorage authorStorage = new DBAuthorStorage();



    @Override
    public boolean save(Author author) {
        if (authorStorage.contains(author.getName())) {
            return false;
        }
        authorStorage.save(author);
        return true;
    }

    @Override
    public void updateDescription(String newDescription, int id) {
        if (authorStorage.contains(id)){
            authorStorage.updateDescription(newDescription, id);
        }
    }

    @Override
    public void delete(String name) {
        if (authorStorage.contains(name)){
            authorStorage.delete(name);
        }
    }

    @Override
    public void delete(int id) {
        if (authorStorage.contains(id)){
            authorStorage.delete(id);
        }
    }

    @Override
    public Author[] getAll() {
        return authorStorage.getAll();
    }

    @Override
    public Author getById(int id) {
        if (authorStorage.contains(id)){
            return authorStorage.getById(id);
        }
        return null;
    }

    @Override
    public Author getByName(String name) {
        if (authorStorage.contains(name)){
            return authorStorage.getByName(name);
        }
        return null;
    }
}
