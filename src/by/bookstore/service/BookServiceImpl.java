package by.bookstore.service;

import by.bookstore.entity.Author;
import by.bookstore.entity.Book;
import by.bookstore.storage.BookStorage;
import by.bookstore.storage.db.DBBookStorage;
import by.bookstore.storage.file.FileBookStorage;
import by.bookstore.storage.inmemory.InMemoryBookStorage;

public class BookServiceImpl implements BookService {
    private BookStorage bookStorage = new DBBookStorage();


    @Override
    public boolean add(Book book) {
        if (bookStorage.contains(book.getTitle())) {
            return false;
        }
        bookStorage.save(book);
        return true;
    }

    @Override
    public void delete(int id) {
        if (bookStorage.contains(id)){
            bookStorage.delete(id);
        }
    }

    @Override
    public void delete(String title) {
        if (bookStorage.contains(title)){
            bookStorage.delete(title);
        }
    }

    @Override
    public String updateDescription(String newDescription, int id) {
        if(bookStorage.contains(id)){
            return bookStorage.updateDescription(newDescription, id);
        }
        return null;
    }

    @Override
    public Author updateAuthor(Author newAuthor, int id) {
        if (bookStorage.contains(id)){
            return bookStorage.updateAuthor(newAuthor, id);
        }
        return null;
    }

    @Override
    public int updatePrice(int newPrice, int id) {
        if (bookStorage.contains(id)){
            return bookStorage.updatePrice(newPrice, id);
        }
        return 0;
    }

    @Override
    public Book[] findAll() {
        return bookStorage.getAll();
    }

    @Override
    public Book findBookById(int id) {
        if (bookStorage.contains(id)){
            return bookStorage.getById(id);
        }
        return null;
    }

    @Override
    public Book findBookByTitle(String title) {
        if (bookStorage.contains(title)) {
            return bookStorage.getByTitle(title);
        }
        return null;
    }

    @Override
    public Book[] findAllByAuthor(Author author) {
        return bookStorage.getAllByAuthor(author);
    }

    @Override
    public Book[] findAllByPrice(int price) {
        return bookStorage.getAllByPrice(price);
    }
}
