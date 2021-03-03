package by.bookstore.storage.file;

import by.bookstore.entity.Author;
import by.bookstore.entity.Book;
import by.bookstore.storage.BookStorage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileBookStorage extends AbstractFileStorage implements BookStorage {


    @Override
    public void save(Book book) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(getLastId(PATH_BOOK) + 1).append(" ")
                .append(book.getTitle()).append(" ")
                .append(book.getDescription()).append(" ")
                .append(book.getAuthor().getId()).append(" ")
                .append(book.getPrice());
        String s = stringBuilder.toString();

        try (FileWriter fileWriter = new FileWriter(PATH_BOOK, true)) {
            fileWriter.write(s);
            fileWriter.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        List<Book> all = new ArrayList<>(Arrays.asList(getAll()));
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId() == id) {
                all.remove(i);
                break;
            }
        }
        writeFileBook(all);
    }

    @Override
    public void delete(String title) {
        List<Book> all = new ArrayList<>(Arrays.asList(getAll()));
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getTitle().equals(title)) {
                all.remove(i);
                break;
            }
        }
        writeFileBook(all);
    }

    @Override
    public String updateDescription(String newDescription, int id) {
        List<Book> all = Arrays.asList(getAll());
        for (Book book : all) {
            if (book.getId() == id) {
                book.setDescription(newDescription);
                break;
            }
        }
        writeFileBook(all);
        return null;
    }

    @Override
    public Author updateAuthor(Author newAuthor, int id) {
        List<Book> all = Arrays.asList(getAll());
        for (Book book : all) {
            if (book.getId() == id) {
                book.setAuthor(newAuthor);
            }
        }
        writeFileBook(all);
        return null;
    }

    private void writeFileBook(List<Book> all) {
        try (FileWriter fileWriter = new FileWriter(PATH_BOOK)) {
            for (Book book : all) {
                fileWriter.write(String.format("%d %s %s %d %f",
                        book.getId(),
                        book.getTitle(),
                        book.getDescription(),
                        book.getAuthor().getId(),
                        book.getPrice()));
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int updatePrice(int newPrice, int id) {
        List<Book> all = Arrays.asList(getAll());
        for (Book book : all) {
            if (book.getId() == id) {
                book.setPrice(newPrice);
                break;
            }
        }
        writeFileBook(all);
        return -1;
    }

    @Override
    public Book[] getAll() {
        ArrayList<Book> all = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_BOOK))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] s = line.split(" ");
                all.add(new Book(Integer.parseInt(s[BOOK_ID]),
                        s[BOOK_TITLE],
                        s[BOOK_DESCRIPTION],
                        getAuthorById(s[BOOK_AUTHOR_ID]),
                        Double.parseDouble(s[BOOK_PRICE])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return all.toArray(new Book[0]);
    }

    @Override
    public Book getById(int id) {
        Book[] all = getAll();
        for (Book book : all) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    @Override
    public Book getByTitle(String title) {
        Book[] all = getAll();
        for (Book book : all) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public Book[] getAllByAuthor(Author author) {
        Book[] all = getAll();
        ArrayList<Book> newAll = new ArrayList<>();
        for (Book book : all) {
            if (book.getAuthor().getId() == author.getId()) {
                newAll.add(book);
            }
        }
        return newAll.toArray(new Book[0]);
    }

    @Override
    public Book[] getAllByPrice(double price) {
        Book[] all = getAll();
        ArrayList<Book> newAll = new ArrayList<>();
        for (Book book : all) {
            if (book.getPrice() == price) {
                newAll.add(book);
            }
        }
        return newAll.toArray(new Book[0]);
    }

    @Override
    public boolean contains(int id) {
        Book[] all = getAll();
        for (Book book : all) {
            if (book.getId() == id) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean contains(String title) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_BOOK))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] s = line.split(" ");
                if (s[BOOK_TITLE].equals(title)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

