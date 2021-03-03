package by.bookstore.console.action;

import by.bookstore.console.util.ConsoleReader;
import by.bookstore.console.util.ConsoleWriter;
import by.bookstore.console.validator.AuthorValidator;
import by.bookstore.entity.Author;
import by.bookstore.service.AuthorService;
import by.bookstore.service.AuthorServiceImpl;

public class AuthorActionImpl implements AuthorAction {
    private AuthorService authorService = new AuthorServiceImpl();

    @Override
    public void save() {

        ConsoleWriter.writeString("Enter the name author");
        String name = ConsoleReader.readString();

        if (AuthorValidator.invalidName(name)){
            ConsoleWriter.writeString("name is invalidate");
        }

        ConsoleWriter.writeString("Enter the description author");
        String description = ConsoleReader.readString();

        if (AuthorValidator.invalidDescription(description)){
            ConsoleWriter.writeString("Description is invalidate");
        }

        Author author = new Author(name, description);
        authorService.save(author);

        ConsoleWriter.writeString("The author was saved. \n");
    }

    @Override
    public void updateDescription() {
        ConsoleWriter.writeString("Enter the new description");
        String newDescription = ConsoleReader.readString();

        if (AuthorValidator.invalidDescription(newDescription)){
            ConsoleWriter.writeString("newDescription is invalidate");
        }

        ConsoleWriter.writeString("Enter the id");
        int id = ConsoleReader.readInt();

        if (AuthorValidator.invalidId(id)){
            ConsoleWriter.writeString("id is invalidate");
        }

        authorService.updateDescription(newDescription, id);
        ConsoleWriter.writeString("The description was update. \n");
    }

    @Override
    public void deleteByName() {
        ConsoleWriter.writeString("Enter the name");
        String name = ConsoleReader.readString();

        if (AuthorValidator.invalidName(name)){
            ConsoleWriter.writeString("Name is invalidate");
        }

        authorService.delete(name);
        ConsoleWriter.writeString("The author was deleted. \n");
    }

    @Override
    public void deleteById() {

        ConsoleWriter.writeString("Enter the id");
        int id = ConsoleReader.readInt();

        if (AuthorValidator.invalidId(id)){
            ConsoleWriter.writeString("id is invalidate");
        }

        authorService.delete(id);
        ConsoleWriter.writeString("The author was deleted. \n");
    }

    @Override
    public void getAll() {
        ConsoleWriter.writeString("Author:");
        Author [] all = authorService.getAll();
        for (int i = 0; i < all.length; i++) {
            ConsoleWriter.writeString((i + 1) + ". " + all[i].getName() + "\n" + all[i].getDescription());
        }
        ConsoleWriter.writeString(" ");
    }

    @Override
    public void getById() {
        ConsoleWriter.writeString("Enter the id");
        int id = ConsoleReader.readInt();

        if (AuthorValidator.invalidId(id)){
            ConsoleWriter.writeString("id is invalidate");
        }

        Author author = authorService.getById(id);
        ConsoleWriter.writeString("Author: " + author.getName() + "\n" + author.getDescription() + "\n");

    }

    @Override
    public void getByName() {

        ConsoleWriter.writeString("Enter the name");
        String name = ConsoleReader.readString();

        if (AuthorValidator.invalidName(name)){
            ConsoleWriter.writeString("name is invalidate");
        }

        Author author = authorService.getByName(name);
        ConsoleWriter.writeString("Author: " + author.getName() + "\n" + author.getDescription() + "\n");
    }
}
