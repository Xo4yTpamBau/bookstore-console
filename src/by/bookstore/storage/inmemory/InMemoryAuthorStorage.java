package by.bookstore.storage.inmemory;

import by.bookstore.console.util.ConsoleWriter;
import by.bookstore.entity.Author;
import by.bookstore.storage.AuthorStorage;

import java.util.Arrays;

public class InMemoryAuthorStorage implements AuthorStorage {
    private static Author[] authors = new Author[10];

    static {
        authors[0] = new Author("Name", "Description");
        authors[1] = new Author("Name2", "Description2");
        authors[2] = new Author("Name2", "Description2");
    }


    @Override
    public void save(Author author) {
        for (int i = 0; i < authors.length; i++) {
            if (authors[i] == null) {
                authors[i] = author;
                break;
            }
        }
    }

    @Override
    public void updateDescription(String newDescription, int id) {
        for (int i = 0; i < authors.length; i++) {
            if (authors[i] == null) break;
            if (authors[i].getId() == id)
                authors[i].setDescription(newDescription);
        }
    }

    @Override
    public void delete(String name) {
        for (int i = 0; i < authors.length; i++) {
            if (authors[i] == null) break;
            if (authors[i].getName().equals(name)) {
                for (int j = i; j < authors.length - 1; j++) {
                    authors[j] = authors[j + 1];
                }
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < authors.length; i++) {
            if (authors[i] == null) break;
            if (authors[i].getId() == id) {
                for (int j = i; j < authors.length - 1; j++) {
                    authors[j] = authors[j + 1];
                }
                break;
            }
        }
    }

    @Override
    public Author[] getAll() {
        int count = 0;
        for (int i = 0; i < authors.length; i++) {
            if (authors[i] != null) {
                count++;
            }
        }
        Author[] authors2 = new Author[count];
        for (int j = 0; j < authors2.length; j++) {
            authors2[j] = authors[j];
        }
        return authors2;
    }

    @Override
    public Author getById(int id) {
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].getId() == id)
                return authors[i];
        }
        return null;
    }

    @Override
    public Author getByName(String name) {
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].getName().equals(name)) {
                return authors[i];
            }
        }
        return null;
    }

    @Override
    public boolean contains(int id) {
        for (int i = 0; i < authors.length; i++) {
            if (authors[i] == null) break;
            if (authors[i].getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(String name) {
        for (int i = 0; i < authors.length; i++) {
            if (authors[i] == null) break;
            if (authors[i].getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
