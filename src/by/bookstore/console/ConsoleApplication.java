package by.bookstore.console;

import by.bookstore.console.action.*;
import by.bookstore.console.util.ConsoleReader;
import by.bookstore.console.util.ConsoleWriter;
import by.bookstore.entity.*;

public class ConsoleApplication {
    public static Session session;

    private UserAction userAction = new UserActionImpl();
    private BookAction bookAction = new BookActionImpl();
    private AuthorAction authorAction = new AuthorActionImpl();
    private AddressAction addressAction = new AddressActionImpl();
    private StoreAction storeAction = new StoreActionImpl();
    private OrderAction orderAction = new OrderActionImpl();

    public void run() {
        while (true) {
            if (session == null) {
                guestMenu();
                switch (ConsoleReader.readInt()) {
                    case 0:
                        return;
                    case 1:
                        userAction.registration();
                        break;
                    case 2:
                        userAction.authorization();
                        break;
                    default:
                        ConsoleWriter.writeString("Operation not found");
                }
            } else if (session.getUser().getRole().equals(Role.USER)) {
                userMenu();
                switch (ConsoleReader.readInt()) {
                    case 0:
                        return;
                    case 1:
                        bookAction.findAllForUser();
                        break;
                    case 2:
                        bookAction.findAllByAuthorForUser();
                        break;
                    case 3:
                        bookAction.findBookByTitleForUser();
                        break;
                    case 4:
                        bookAction.findAllByPriceForUser();
                        break;
                    case 5:
                        accountMenu();
                        switch (ConsoleReader.readInt()) {
                            case 0:
                                continue;
                            case 1:
                                userAction.updateNameForUser();
                                break;
                            case 2:
                                userAction.updatePasswordForUser();
                                break;
                            case 3:
                                userAction.updateAgeForUser();
                                break;
                            case 4:
                                userAction.updateAddressForUser();
                                break;
                            default:
                                ConsoleWriter.writeString("Operation not found!");
                        }
                        break;
                    case 6:
                        orderMenuForUser();
                        switch (ConsoleReader.readInt()) {
                            case 0:
                                continue;
                            case 1:
                                orderAction.findAllForUser();
                                break;
                            case 2:
                                orderAction.findAllByUserAndStatus();
                                break;
                            default:
                                ConsoleWriter.writeString("Operation not found");
                        }
                        break;
                    case 7:
                        basketMenu();
                        switch (ConsoleReader.readInt()) {
                            case 0:
                                continue;
                            case 1:
                                if (ConsoleApplication.session.getBasket().size() > 0) {
                                    orderAction.save();
                                    break;
                                }
                        }
                        break;
                    case 8:
                        userAction.logout();
                        break;
                }
            } else if (session.getUser().getRole().equals(Role.ADMIN)) {
                adminMenu();
                switch (ConsoleReader.readInt()) {
                    case 0:
                        return;
                    case 1:
                        bookMenu();
                        switch (ConsoleReader.readInt()) {
                            case 0:
                                continue;
                            case 1:
                                bookAction.add();
                                break;
                            case 2:
                                bookAction.deleteById();
                                break;
                            case 3:
                                bookAction.deleteByTitle();
                                break;
                            case 4:
                                bookAction.updateDescription();
                                break;
                            case 5:
                                bookAction.updateAuthor();
                                break;
                            case 6:
                                bookAction.updatePrice();
                                break;
                            case 7:
                                bookAction.findAll();
                                break;
                            case 8:
                                bookAction.findBookById();
                                break;
                            case 9:
                                bookAction.findBookByTitle();
                                break;
                            case 10:
                                bookAction.findAllByAuthor();
                                break;
                            case 11:
                                bookAction.findAllByPrice();
                                break;
                            default:
                                ConsoleWriter.writeString("Operation not found!");
                        }
                        break;

                    case 2:
                        authorMenu();
                        switch (ConsoleReader.readInt()) {
                            case 0:
                                continue;
                            case 1:
                                authorAction.save();
                                break;
                            case 2:
                                authorAction.updateDescription();
                                break;
                            case 3:
                                authorAction.deleteByName();
                                break;
                            case 4:
                                authorAction.deleteById();
                                break;
                            case 5:
                                authorAction.getAll();
                                break;
                            case 6:
                                authorAction.getById();
                                break;
                            case 7:
                                authorAction.getByName();
                                break;
                            default:
                                ConsoleWriter.writeString("Operation not found!");
                        }
                        break;

                    case 3:
                        addressMenu();
                        switch (ConsoleReader.readInt()) {
                            case 0:
                                continue;
                            case 1:
                                addressAction.save();
                                break;
                            case 2:
                                addressAction.deleteById();
                                break;
                            case 3:
                                addressAction.deleteByAddress();
                                break;
                            case 4:
                                addressAction.updateStreet();
                                break;
                            case 5:
                                addressAction.updateHome();
                                break;
                            case 6:
                                addressAction.getAll();
                                break;
                            case 7:
                                addressAction.getStreet();
                                break;
                            case 8:
                                addressAction.getHome();
                                break;
                            case 9:
                                addressAction.getById();
                                break;
                            default:
                                ConsoleWriter.writeString("Operation not found!");
                        }
                        break;

                    case 4:
                        storeMenu();
                        switch (ConsoleReader.readInt()) {
                            case 0:
                                continue;
                            case 1:
                                storeAction.save();
                                break;
                            case 2:
                                storeAction.deleteById();
                                break;
                            case 3:
                                storeAction.deleteByTitle();
                                break;
                            case 4:
                                storeAction.updateTitle();
                                break;
                            case 5:
                                storeAction.updateAddress();
                                break;
                            case 6:
                                storeAction.getAll();
                                break;
                            case 7:
                                storeAction.getByTitle();
                                break;
                            case 8:
                                storeAction.getById();
                                break;
                            default:
                                ConsoleWriter.writeString("Operation not found!");
                        }
                        break;

                    case 5:
                        orderMenu();
                        switch (ConsoleReader.readInt()) {
                            case 0:
                                continue;
                            case 1:
                                orderAction.save();
                                break;
                            case 2:
                                orderAction.updateByStatus();
                                break;
                            case 3:
                                orderAction.updateByAddress();
                                break;
                            case 4:
                                orderAction.updateByStore();
                                break;
                            case 5:
                                orderAction.updateByIsDelivery();
                                break;
                            case 6:
                                orderAction.deleteById();
                                break;
                            case 7:
                                orderAction.deleteByStore();
                                break;
                            case 8:
                                orderAction.addBook();
                                break;
                            case 9:
                                orderAction.deleteBook();
                                break;
                            case 10:
                                orderAction.findAll();
                                break;
                            case 11:
                                orderAction.findAllByStatus();
                                break;
                            case 12:
                                orderAction.findAllByAddress();
                                break;
                            case 13:
                                orderAction.findAllByUser();
                                break;
                            case 14:
                                orderAction.findAllByStore();
                                break;
                            case 15:
                                orderAction.findById();
                                break;
                            default:
                                ConsoleWriter.writeString("Operation not found!");
                        }
                        break;

                    case 6:
                        userMenuForAdmin();
                        switch (ConsoleReader.readInt()) {
                            case 0:
                                continue;
                            case 1:
                                userAction.save();
                                break;
                            case 2:
                                userAction.updateName();
                                break;
                            case 3:
                                userAction.updatePassword();
                                break;
                            case 4:
                                userAction.updateAge();
                                break;
                            case 5:
                                userAction.updateAddress();
                                break;
                            case 6:
                                userAction.deleteById();
                                break;
                            case 7:
                                userAction.deleteByLogin();
                                break;
                            case 8:
                                userAction.findAll();
                                break;
                            case 9:
                                userAction.findAllByAge();
                                break;
                            case 10:
                                userAction.findAllByName();
                                break;
                            case 11:
                                userAction.findByAddress();
                                break;
                            case 12:
                                userAction.findById();
                                break;
                            case 13:
                                userAction.findByLogin();
                                break;
                            default:
                                ConsoleWriter.writeString("Operation not found!");
                        }
                        break;
                    case 7:
                        userAction.logout();
                        break;
                }
            }
        }
    }

    private void basketMenu() {
        Book[] books = ConsoleApplication.session.getBasket().getBooks();
        for (int i = 0; i < books.length; i++) {
            ConsoleWriter.writeString((i + 1) + " Book: " + books[i].getTitle());
        }
        ConsoleWriter.writeString("Total price: " + ConsoleApplication.session.getBasket().getTotalPrice());
        ConsoleWriter.writeString("0 - Back");
        if (ConsoleApplication.session.getBasket().size() > 0) {
            ConsoleWriter.writeString("1 - Create order");
        }
    }

    private void accountMenu() {
        User user = session.getUser();
        ConsoleWriter.writeString("Username: " + user.getLogin());
        ConsoleWriter.writeString("Name: " + user.getName());
        ConsoleWriter.writeString("Age: " + user.getAge());
        ConsoleWriter.writeString("Address: " + user.getAddress().getStreet() + " home " + user.getAddress().getHome());
        ConsoleWriter.writeString("");
        ConsoleWriter.writeString("0 - Back");
        ConsoleWriter.writeString("1 - Update name");
        ConsoleWriter.writeString("2 - Update password");
        ConsoleWriter.writeString("3 - Update age");
        ConsoleWriter.writeString("4 - Update address");

    }

    private void adminMenu() {
        ConsoleWriter.writeString("Hello " + session.getUser().getName());
        ConsoleWriter.writeString("0 - Exit");
        ConsoleWriter.writeString("1 - Book menu");
        ConsoleWriter.writeString("2 - Author menu");
        ConsoleWriter.writeString("3 - Address menu");
        ConsoleWriter.writeString("4 - Store menu");
        ConsoleWriter.writeString("5 - Order menu");
        ConsoleWriter.writeString("6 - User menu");
        ConsoleWriter.writeString("7 - Logout");
    }

    private void bookMenu() {
        ConsoleWriter.writeString("0 - Back");
        ConsoleWriter.writeString("1 - Save");
        ConsoleWriter.writeString("2 - Delete by id");
        ConsoleWriter.writeString("3 - delete by title");
        ConsoleWriter.writeString("4 - update description");
        ConsoleWriter.writeString("5 - update author");
        ConsoleWriter.writeString("6 - update price");
        ConsoleWriter.writeString("7 - find all");
        ConsoleWriter.writeString("8 - find book by id");
        ConsoleWriter.writeString("9 - find book by title");
        ConsoleWriter.writeString("10 - find all by author");
        ConsoleWriter.writeString("11 - find all by price");
    }

    private void authorMenu() {
        ConsoleWriter.writeString("0 - Back");
        ConsoleWriter.writeString("1 - Save");
        ConsoleWriter.writeString("2 - Update description");
        ConsoleWriter.writeString("3 - Delete by name");
        ConsoleWriter.writeString("4 - Delete by id");
        ConsoleWriter.writeString("5 - Get all");
        ConsoleWriter.writeString("6 - Get by id");
        ConsoleWriter.writeString("7 - Get by name");
    }

    private void addressMenu() {
        ConsoleWriter.writeString("0 - Back");
        ConsoleWriter.writeString("1 - Save");
        ConsoleWriter.writeString("2 - Delete by id ");
        ConsoleWriter.writeString("3 - Delete by address");
        ConsoleWriter.writeString("4 - Update street");
        ConsoleWriter.writeString("5 - Update home");
        ConsoleWriter.writeString("6 - Get all");
        ConsoleWriter.writeString("7 - Get street");
        ConsoleWriter.writeString("8 - Get home");
        ConsoleWriter.writeString("9 - Get by id");
    }

    private void storeMenu() {
        ConsoleWriter.writeString("0 - Back");
        ConsoleWriter.writeString("1 - Save");
        ConsoleWriter.writeString("2 - Delete by id");
        ConsoleWriter.writeString("3 - Delete by title");
        ConsoleWriter.writeString("4 - Update title");
        ConsoleWriter.writeString("5 - Update address");
        ConsoleWriter.writeString("6 - Get all");
        ConsoleWriter.writeString("7 - Get by title");
        ConsoleWriter.writeString("8 - Get by id");
    }

    private void orderMenu() {
        ConsoleWriter.writeString("0 - Back");
        ConsoleWriter.writeString("1 - Save");
        ConsoleWriter.writeString("2 - Update status");
        ConsoleWriter.writeString("3 - Update address");
        ConsoleWriter.writeString("4 - Update store");
        ConsoleWriter.writeString("5 - Update isDelivery");
        ConsoleWriter.writeString("6 - Delete by id");
        ConsoleWriter.writeString("7 - Delete by store");
        ConsoleWriter.writeString("8 - Add book");
        ConsoleWriter.writeString("9 - Delete book");
        ConsoleWriter.writeString("10 - Find all");
        ConsoleWriter.writeString("11 - Find all status");
        ConsoleWriter.writeString("12 - Find all address");
        ConsoleWriter.writeString("13 - Find all user");
        ConsoleWriter.writeString("14 - Find all store");
        ConsoleWriter.writeString("15 - Find by id");
    }

    private void orderMenuForUser() {
        ConsoleWriter.writeString("0 - Back");
        ConsoleWriter.writeString("1 - Find all");
        ConsoleWriter.writeString("2 - Find active");
    }

    private void userMenuForAdmin() {
        ConsoleWriter.writeString("0 - Back");
        ConsoleWriter.writeString("1 - Save");
        ConsoleWriter.writeString("2 - Update name");
        ConsoleWriter.writeString("3 - Update password");
        ConsoleWriter.writeString("4 - Update age");
        ConsoleWriter.writeString("5 - Update address");
        ConsoleWriter.writeString("6 - Delete by id");
        ConsoleWriter.writeString("7 - Delete by login");
        ConsoleWriter.writeString("8 - Find all");
        ConsoleWriter.writeString("9 - Find  all by age");
        ConsoleWriter.writeString("10 - Find all by name");
        ConsoleWriter.writeString("11 - Find by address");
        ConsoleWriter.writeString("12 - Find by id");
        ConsoleWriter.writeString("13 - Find by login");
    }

    private void userMenu() {
        ConsoleWriter.writeString("Hello " + session.getUser().getName());
        ConsoleWriter.writeString("0 - Exit");
        ConsoleWriter.writeString("1 - Find all books");
        ConsoleWriter.writeString("2 - Search book by author");
        ConsoleWriter.writeString("3 - Search book by title");
        ConsoleWriter.writeString("4 - Search book by price");
        ConsoleWriter.writeString("5 - Account");
        ConsoleWriter.writeString("6 - Order menu");
        ConsoleWriter.writeString("7 - Basket");
        ConsoleWriter.writeString("8 - Logout");
    }

    private void guestMenu() {
        ConsoleWriter.writeString("Hello Guest");
        ConsoleWriter.writeString("0 - Exit");
        ConsoleWriter.writeString("1 - Registration");
        ConsoleWriter.writeString("2 - Authorization");
    }
}
