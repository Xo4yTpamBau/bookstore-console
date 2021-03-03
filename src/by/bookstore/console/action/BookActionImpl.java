package by.bookstore.console.action;

import by.bookstore.console.ConsoleApplication;
import by.bookstore.console.util.ConsoleReader;
import by.bookstore.console.util.ConsoleWriter;
import by.bookstore.console.validator.BookValidator;
import by.bookstore.entity.Author;
import by.bookstore.entity.Book;
import by.bookstore.service.AuthorService;
import by.bookstore.service.AuthorServiceImpl;
import by.bookstore.service.BookService;
import by.bookstore.service.BookServiceImpl;
import by.bookstore.storage.BookStorage;
import by.bookstore.storage.db.DBBookStorage;

public class BookActionImpl implements BookAction {
    private BookService bookService = new BookServiceImpl();
    private AuthorService authorService = new AuthorServiceImpl();
    private BookStorage bookStorage = new DBBookStorage();

    @Override
    public void add() {
        ConsoleWriter.writeString("Enter book title");
        String title = ConsoleReader.readString();

        if (BookValidator.invalidTitle(title)) {
            ConsoleWriter.writeString("Title is invalidate");
            return;
        }

        ConsoleWriter.writeString("Enter description");
        String description = ConsoleReader.readString();

        if (BookValidator.invalidDescription(description)) {
            ConsoleWriter.writeString("Description is invalidate");
            return;
        }

        ConsoleWriter.writeString("Choose author:");
        Author[] all = authorService.getAll();
        int count = 1;
        ConsoleWriter.writeString("Author: ");
        for (Author author : all) {
            ConsoleWriter.writeString(count + ". " + author.getName());
            count++;
        }
        int i = ConsoleReader.readInt() - 1;
        Author author = all[i];

        ConsoleWriter.writeString("Enter price");
        int price = ConsoleReader.readInt();

        if (BookValidator.invalidPrice(price)) {
            ConsoleWriter.writeString("Price is invalidate");
            return;
        }

        Book book = new Book(title, description, author, price);
        bookService.add(book);
        ConsoleWriter.writeString("The book was saved. \n");
    }

    @Override
    public void deleteById() {
        ConsoleWriter.writeString("Enter book id");
        int id = ConsoleReader.readInt();

        if (BookValidator.invalidId(id)) {
            ConsoleWriter.writeString("Id is invalidate");
            return;
        }

        bookService.delete(id);
        ConsoleWriter.writeString("The book was deleted. \n");
    }

    @Override
    public void deleteByTitle() {
        ConsoleWriter.writeString("Enter book Title");
        String title = ConsoleReader.readString();

        if (BookValidator.invalidTitle(title)) {
            ConsoleWriter.writeString("Title is invalidate");
            return;
        }

        bookService.delete(title);
        ConsoleWriter.writeString("The book was deleted. \n");
    }

    @Override
    public void updateDescription() {

        ConsoleWriter.writeString("Enter new Description");
        String description = ConsoleReader.readString();

        if (BookValidator.invalidDescription(description)) {
            ConsoleWriter.writeString("Description is invalidate");
            return;
        }

        ConsoleWriter.writeString("Enter id");
        int id = ConsoleReader.readInt();

        if (BookValidator.invalidId(id)) {
            ConsoleWriter.writeString("Id is invalidate");
            return;
        }

        bookService.updateDescription(description, id);
        ConsoleWriter.writeString("The description was update. \n");
    }

    @Override
    public void updateAuthor() {

        Author[] all = authorService.getAll();
        ConsoleWriter.writeString("Author: ");
        for (int i = 0; i < all.length; i++) {
            ConsoleWriter.writeString((i + 1) + ". " + all[i].getName());
        }

        ConsoleWriter.writeString("Choose the author:");
        int i1 = ConsoleReader.readInt() - 1;
        Author author = all[i1];


        ConsoleWriter.writeString("Enter id");
        int id = ConsoleReader.readInt();

        if (BookValidator.invalidId(id)) {
            ConsoleWriter.writeString("Id is invalidate");
            return;
        }

        bookService.updateAuthor(author, id);
        ConsoleWriter.writeString("The Author was update. \n");
    }

    @Override
    public void updatePrice() {
        ConsoleWriter.writeString("Enter new price");
        int price = ConsoleReader.readInt();

        if (BookValidator.invalidPrice(price)) {
            ConsoleWriter.writeString("Price is invalidate");
            return;
        }

        ConsoleWriter.writeString("Enter id");
        int id = ConsoleReader.readInt();

        if (BookValidator.invalidId(id)) {
            ConsoleWriter.writeString("Id is invalidate");
            return;
        }

        bookService.updatePrice(price, id);
        ConsoleWriter.writeString("The price was update. \n");
    }

    @Override
    public void findAll() {
        Book[] all = bookService.findAll();
        int count = 0;
        ConsoleWriter.writeString("Book:");
        for (Book book : all) {
            ConsoleWriter.writeString((count++ + 1) + ". " + book.getTitle());
        }
        ConsoleWriter.writeString(" ");
    }

    @Override
    public void findAllForUser() {
        Book[] all = bookService.findAll();
        ConsoleWriter.writeString("Book:");
        for (int i = 0; i < all.length; i++) {
            ConsoleWriter.writeString((i + 1) + ". " + all[i].getTitle());
        }
        ConsoleWriter.writeString("Choose the book:");
        int i = ConsoleReader.readInt() - 1;
        ConsoleWriter.writeString("0 - Back");
        ConsoleWriter.writeString("1 - Add to basket");
        switch (ConsoleReader.readInt()) {
            case 0:
                return;
            case 1:
                Book book = all[i];
                ConsoleApplication.session.getBasket().addBook(book);
                break;
            default:
                ConsoleWriter.writeString("Operation not found!");
        }
        ConsoleWriter.writeString(" ");
    }

    @Override
    public void findBookById() {
        ConsoleWriter.writeString("Enter id");
        int id = ConsoleReader.readInt();

        if (BookValidator.invalidId(id)) {
            ConsoleWriter.writeString("Id is invalidate");
            return;
        }

        Book bookById = bookService.findBookById(id);
        ConsoleWriter.writeString("Book: " + bookById.getTitle() + "\n" + bookById.getAuthor().getName() + "\n"
                + bookById.getDescription() + "\n" + bookById.getPrice() + "\n" );
    }

    @Override
    public void findBookByTitle() {
        ConsoleWriter.writeString("Enter title");
        String title = ConsoleReader.readString();

        if (BookValidator.invalidTitle(title)) {
            ConsoleWriter.writeString("Title is invalidate");
            return;
        }

        Book bookByTitle = bookService.findBookByTitle(title);
        ConsoleWriter.writeString("Book: " + bookByTitle.getTitle() + "\n" + bookByTitle.getAuthor().getName() + "\n"
                + bookByTitle.getDescription() + "\n" + bookByTitle.getPrice() + "\n" );
    }

    @Override
    public void findBookByTitleForUser() {
        ConsoleWriter.writeString("Enter the title");
        String title = ConsoleReader.readString();


        ConsoleWriter.writeString("0 - Back");
        ConsoleWriter.writeString("1 - Add book");
        switch (ConsoleReader.readInt()){
            case 0: return;
            case 1: ConsoleApplication.session.getBasket().addBook(bookService.findBookByTitle(title));
            break;
            default:
                ConsoleWriter.writeString("Operation not found!");
        }
        ConsoleWriter.writeString(" ");
    }

    @Override
    public void findAllByAuthor() {


        Author[] all = authorService.getAll();
        ConsoleWriter.writeString("Author: ");

        for (int i = 0; i < all.length; i++) {
            ConsoleWriter.writeString((i + 1) + ". " + all[i].getName());
        }

        ConsoleWriter.writeString("Choose author:");
        int i = ConsoleReader.readInt() - 1;
        Author author = all[i];

        Book[] allByAuthor = bookService.findAllByAuthor(author);
        ConsoleWriter.writeString("Book: ");
        for (int j = 0; j < allByAuthor.length; j++) {
            ConsoleWriter.writeString((j + 1) + ". " + allByAuthor[j].getTitle());
        }
        ConsoleWriter.writeString(" ");
    }

    @Override
    public void findAllByAuthorForUser() {
        Author[] all = authorService.getAll();

        ConsoleWriter.writeString("Author: ");

        for (int i = 0; i < all.length; i++) {
            ConsoleWriter.writeString((i + 1) + ". " + all[i].getName());
        }

        ConsoleWriter.writeString("Choose author:");
        int i1 = ConsoleReader.readInt() - 1;
        Author author = all[i1];

        Book[] allByAuthor = bookService.findAllByAuthor(author);
        ConsoleWriter.writeString("Book: ");
        for (int i = 0; i < allByAuthor.length; i++) {
            ConsoleWriter.writeString((i + 1) + allByAuthor[i].getTitle());
        }

        ConsoleWriter.writeString("Choose the book:");
        int i = ConsoleReader.readInt() - 1;
        Book book = allByAuthor[i];

        ConsoleWriter.writeString("0 - Back");
        ConsoleWriter.writeString("1 - Add book");
        switch (ConsoleReader.readInt()){
            case 0: return;
            case 1: ConsoleApplication.session.getBasket().addBook(book);
            break;
            default:
                ConsoleWriter.writeString("Operation not found!");
        }
        ConsoleWriter.writeString(" ");
    }

    @Override
    public void findAllByPrice() {
        ConsoleWriter.writeString("Enter price");
        int price = ConsoleReader.readInt();

        if (BookValidator.invalidPrice(price)) {
            ConsoleWriter.writeString("Price is invalidate");
            return;
        }

        Book[] allByPrice = bookService.findAllByPrice(price);
        ConsoleWriter.writeString("Book: ");
        for (int a = 0; a < allByPrice.length; a++) {
            ConsoleWriter.writeString((a + 1)+ ". " + allByPrice[a].getTitle());
        }
        ConsoleWriter.writeString(" ");
    }

    @Override
    public void findAllByPriceForUser() {
        ConsoleWriter.writeString("Enter the price");
        int price = ConsoleReader.readInt();

        if (BookValidator.invalidPrice(price)){
            ConsoleWriter.writeString("Price is invalidate");
            return;
        }

        Book[] allByPrice = bookService.findAllByPrice(price);
        ConsoleWriter.writeString("Book: ");
        for (int i = 0; i < allByPrice.length; i++) {
            ConsoleWriter.writeString((i + 1) + ". " + allByPrice[i].getTitle());
        }

        ConsoleWriter.writeString("Choose book:");
        int i = ConsoleReader.readInt() - 1 ;
        Book book = allByPrice[i];

        ConsoleWriter.writeString("0 - Back");
        ConsoleWriter.writeString("1 - Add to basket");

        switch (ConsoleReader.readInt()){
            case 0: return;
            case 1: ConsoleApplication.session.getBasket().addBook(book);
            break;
            default:
                ConsoleWriter.writeString("Operation not found!");
        }
        ConsoleWriter.writeString(" ");
    }
}
