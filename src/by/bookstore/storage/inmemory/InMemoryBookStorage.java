package by.bookstore.storage.inmemory;

import by.bookstore.entity.Author;
import by.bookstore.entity.Book;
import by.bookstore.storage.BookStorage;

import java.util.Arrays;

public class InMemoryBookStorage implements BookStorage {

    private static Book[] books = new Book[10];

    static {
        books[0] = new Book("Title", "description", null, 10);
        books[1] = new Book("Title2", "description2", null, 20);
        books[2] = new Book("Title3", "description3", null, 30);
    }

    @Override
    public void save(Book book) {
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) {
                books[i] = book;
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) break;
            if (books[i].getId() == id) {
                for (int j = i; j < books.length - 1; j++) {
                    books[j] = books[j + 1];
                }
                break;
            }
        }
    }

    @Override
    public void delete(String title) {
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) break;
            if (books[i].getTitle().equals(title)) {
                for (int j = i; j < books.length - 1; j++) {
                    books[j] = books[j + 1];
                }
                break;
            }
        }
    }

    @Override
    public String updateDescription(String newDescription, int id) {
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) break;
            if (books[i].getId() == id) {
                books[i].setDescription(newDescription);
                break;
            }
        }
        return null;
    }

    @Override
    public Author updateAuthor(Author newAuthor, int id) {
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) break;
            if (books[i].getId() == id) {
                books[i].setAuthor(newAuthor);
                break;
            }
        }
        return null;
    }

    @Override
    public int updatePrice(int newPrice, int id) {
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) break;
            if (books[i].getId() == id) {
                books[i].setPrice(newPrice);
                break;
            }
        }
        return 0;
    }

    @Override
    public Book[] getAll() {
        int count = 0;
        for (int i = 0; i < books.length; i++) {
            if (books[i] != null)
                count++;
        }
        Book[] books2 = new Book[count];
        for (int i = 0; i < books2.length; i++) {
            books2[i] = books[i];
        }
        return books2;
    }

    @Override
    public Book getById(int id) {
        for (int i = 0; i < books.length; i++) {
            if (books[i].getId() == id)
                return books[i];
        }
        return null;
    }

    @Override
    public Book getByTitle(String title) {
        for (int i = 0; i < books.length; i++) {
            if (books[i].getTitle().equals(title)) {
                return books[i];
            }
        }
        return null;
    }

    @Override
    public Book[] getAllByAuthor(Author author) {
        int count = 0;
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) break;
            if (books[i].getAuthor().equals(author)) {
                count++;
            }
        }
        Book[] books2 = new Book[count];
        for (int i = 0, j = 0; i < books2.length; i++, j++) {
            if (books[i].getAuthor().equals(author)) {
                books2[j] = books[i];
            }
        }
        return books2;
    }

    @Override
    public Book[] getAllByPrice(double price) {
        int count = 0;
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) break;
            if (books[i].getPrice() == price) {
                count++;
            }
        }
        Book[] books2 = new Book[count];
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) break;
            if (books[i].getPrice() == price) {
                books2[i] = books[i];
                count++;
            }
        }
        return books2;
    }

    @Override
    public boolean contains(int id) {
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) break;
            if (books[i].getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(String title) {
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) break;
            if (books[i].getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }
}
