package by.bookstore.storage.file;

import by.bookstore.entity.Author;
import by.bookstore.entity.Store;
import by.bookstore.storage.AuthorStorage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileAuthorStorage extends AbstractFileStorage implements AuthorStorage {


    @Override
    public void save(Author author) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append(getLastId(PATH_AUTHOR) + 1).append(" ")
                .append(author.getName()).append(" ")
                .append(author.getDescription());
        String s = stringBuilder.toString();

        try (FileWriter fileWriter = new FileWriter(PATH_AUTHOR, true)) {
            fileWriter.write(s);
            fileWriter.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDescription(String newDescription, int id) {
        List<Author> all = Arrays.asList(getAll());
        for (Author author : all) {
            if (author.getId() == id) {
                author.setDescription(newDescription);
                break;
            }
        }
        writeFileAuthor(all);
    }

    @Override
    public void delete(String name) {
        List<Author> all = new ArrayList<>(Arrays.asList(getAll()));
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getName().equals(name)) {
                all.remove(i);
                break;
            }
        }
        writeFileAuthor(all);
    }

    @Override
    public void delete(int id) {
        List<Author> all = new ArrayList<>(Arrays.asList(getAll()));
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId() == id) {
                all.remove(i);
                break;
            }
        }
        writeFileAuthor(all);
    }

    private void writeFileAuthor(List<Author> all) {
        try (FileWriter fileWriter = new FileWriter(PATH_AUTHOR)) {
            for (Author author : all) {
                fileWriter.write(String.format("%d %s %s",
                        author.getId(),
                        author.getName(),
                        author.getDescription()));
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Author[] getAll() {
        ArrayList<Author> all = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_AUTHOR))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] s = line.split(" ");
                all.add(new Author(Integer.parseInt(s[AUTHOR_ID]),
                        s[AUTHOR_NAME],
                        s[AUTHOR_DESCRIPTION]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return all.toArray(new Author[0]);
    }

    @Override
    public Author getById(int id) {
        return getAuthorById(String.valueOf(id));
    }

    @Override
    public Author getByName(String name) {
        Author[] all = getAll();
        for (Author author : all) {
            if (author.getName().equals(name)) {
                return author;
            }
        }
        return null;
    }

    @Override
    public boolean contains(int id) {
        Author[] all = getAll();
        for (Author author : all) {
            if (author.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(String name) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_AUTHOR))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] s = line.split(" ");
                if (s[AUTHOR_NAME].equals(name)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

