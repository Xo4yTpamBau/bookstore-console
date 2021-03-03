package by.bookstore.console.action;

import by.bookstore.console.ConsoleApplication;
import by.bookstore.console.util.ConsoleReader;
import by.bookstore.console.util.ConsoleWriter;
import by.bookstore.console.validator.OrderValidator;
import by.bookstore.entity.*;
import by.bookstore.service.*;

import java.util.Arrays;

public class OrderActionImpl implements OrderAction {
    private OrderService orderService = new OrderServiceImpl();
    private StoreService storeService = new StoreServiceImpl();
    private AddressService addressService = new AddressServiceImpl();
    private BookService bookService = new BookServiceImpl();
    private UserService userService = new UserServiceImpl();

    @Override
    public void save() {
        ConsoleWriter.writeString("Chose is Delivery: 0 - Back, 1 - Pickup 2 - Delivery");
        int i = ConsoleReader.readInt();

        User user = ConsoleApplication.session.getUser();
        Book[] books = ConsoleApplication.session.getBasket().getBooks();
        switch (i) {
            case 0:
                return;
            case 1:
                Store[] all = storeService.getAll();
                ConsoleWriter.writeString("Store: ");
                for (int i1 = 0; i1 < all.length; i1++) {
                    ConsoleWriter.writeString((i1 + 1) + ". " + all[i1].getTitle());
                }
                ConsoleWriter.writeString("Choose the Store");
                int ii = ConsoleReader.readInt() - 1;
                Store store = all[ii];
                orderService.save(new Order(user, books, store));
                break;
            case 2:
                orderService.save(new Order(user, books, user.getAddress()));
                break;
            default:
                ConsoleWriter.writeString("Operation not found");
        }
        ConsoleApplication.session.setBasket(new Basket());
        ConsoleWriter.writeString("The order was saved. \n");
    }

    @Override
    public void updateByStatus() {
        ConsoleWriter.writeString("enter the id");
        int id = ConsoleReader.readInt();

        if (OrderValidator.invalidId(id)) {
            ConsoleWriter.writeString("id is invalidate");
        }

        ConsoleWriter.writeString("Chose is Status: 0 - Back, 1 - Active 2 - Close");
        int i = ConsoleReader.readInt();

        Status status = Status.ACTIVE;

        switch (i) {
            case 0:
                return;
            case 1:
                status = Status.ACTIVE;
                break;
            case 2:
                status = Status.CLOSE;
                break;
        }
                orderService.update(status, id);
                ConsoleWriter.writeString("The status was update. \n");
    }

    @Override
    public void updateByAddress() {

        ConsoleWriter.writeString("enter the id");
        int id = ConsoleReader.readInt();

        if (OrderValidator.invalidId(id)) {
            ConsoleWriter.writeString("id is invalidate");
        }

        Address[] all = addressService.getAll();
        ConsoleWriter.writeString("Address: ");
        for (int i = 0; i < all.length; i++) {
            ConsoleWriter.writeString((i + 1) + ". " + all[i].getStreet());
        }

        ConsoleWriter.writeString("Choose the address:");
        int i = ConsoleReader.readInt() - 1;
        Address address = all[i];

        orderService.update(address, id);
        ConsoleWriter.writeString("The address was update. \n");
    }

    @Override
    public void updateByStore() {

        ConsoleWriter.writeString("enter the id");
        int id = ConsoleReader.readInt();

        if (OrderValidator.invalidId(id)) {
            ConsoleWriter.writeString("id is invalidate");
        }


        Store[] all = storeService.getAll();
        ConsoleWriter.writeString("Store: ");
        for (int i = 0; i < all.length; i++) {
            ConsoleWriter.writeString((i + 1) + ". " + all[i].getTitle());
        }

        ConsoleWriter.writeString("Choose the Store:");
        int i = ConsoleReader.readInt() - 1;
        Store store = all[i];

        orderService.update(store, id);
        ConsoleWriter.writeString("The store was update. \n");
    }

    @Override
    public void updateByIsDelivery() {

        ConsoleWriter.writeString("enter the id");
        int id = ConsoleReader.readInt();

        if (OrderValidator.invalidId(id)) {
            ConsoleWriter.writeString("id is invalidate");
        }

        ConsoleWriter.writeString("Chose is Delivery: 0 - Back, 1 - Pickup 2 - Delivery");
        int i = ConsoleReader.readInt();

        boolean isDelivery;

        switch (i) {
            case 0:
                return;
            case 1:
                isDelivery = false;
            case 2:
                isDelivery = true;

                orderService.update(isDelivery, id);
                ConsoleWriter.writeString("The isDelivery was update. \n");
        }
    }

    @Override
    public void deleteById() {

        ConsoleWriter.writeString("enter the id");
        int id = ConsoleReader.readInt();

        if (OrderValidator.invalidId(id)) {
            ConsoleWriter.writeString("id is invalidate");
        }

        orderService.delete(id);
        ConsoleWriter.writeString("The order was delete. \n");
    }

    @Override
    public void deleteByStore() {

        Store[] all = storeService.getAll();
        ConsoleWriter.writeString("Store: ");
        for (int i = 0; i < all.length; i++) {
            ConsoleWriter.writeString((i + 1) + ". " + all[i].getTitle());
        }

        ConsoleWriter.writeString("Choose the Store");
        int i = ConsoleReader.readInt() - 1;
        Store store = all[i];

        orderService.delete(store);
        ConsoleWriter.writeString("The order was delete. \n");
    }

    @Override
    public void addBook() {

        ConsoleWriter.writeString("enter the id");
        int id = ConsoleReader.readInt();

        if (OrderValidator.invalidId(id)) {
            ConsoleWriter.writeString("id is invalidate");
        }


        Book[] all = bookService.findAll();
        ConsoleWriter.writeString("Book: ");
        for (int i = 0; i < all.length; i++) {
            ConsoleWriter.writeString((i + 1) + ". " + all[i].getTitle());
        }

        ConsoleWriter.writeString("Choose the Book");
        int i = ConsoleReader.readInt() - 1;
        Book book = all[i];

        orderService.addBook(book, id);
        ConsoleWriter.writeString("The book was added. \n");
    }

    @Override
    public void deleteBook() {

        ConsoleWriter.writeString("enter the id");
        int id = ConsoleReader.readInt();

        if (OrderValidator.invalidId(id)) {
            ConsoleWriter.writeString("id is invalidate");
        }


        Book[] all = bookService.findAll();
        ConsoleWriter.writeString("Book: ");
        for (int i = 0; i < all.length; i++) {
            ConsoleWriter.writeString((i + 1) + ". " + all[i].getTitle() + " id " + all[i].getId());
        }

        ConsoleWriter.writeString("Choose the Book");
        int bookId = ConsoleReader.readInt();

        orderService.deleteBook(bookId, id);
        ConsoleWriter.writeString("The book was deleted. \n");
    }

    @Override
    public void findAll() {

        Order[] all = orderService.findAll();
        int count = 1;
        ConsoleWriter.writeString("Order: ");
        for (Order order : all) {
            ConsoleWriter.writeString(count + ". " + order.getId());
            count++;
        }
        ConsoleWriter.writeString(" ");
    }

    @Override
    public void findAllForUser() {
        int count = 1;
        Order[] allUser = orderService.findAll(userService.findById(ConsoleApplication.session.getUser().getId()));
        ConsoleWriter.writeString("Order: ");
        for (Order order : allUser) {
            ConsoleWriter.writeString(count + ". " + order.getId());
            count++;
        }
        ConsoleWriter.writeString(" ");
    }


    @Override
    public void findAllByStatus() {

        ConsoleWriter.writeString("Chose is Status: 0 - Back, 1 - Active 2 - Close");
        int i = ConsoleReader.readInt();

        int count = 1;
        ConsoleWriter.writeString("Order: ");

        switch (i) {
            case 0:
                return;
            case 1:
                Order[] allActive = orderService.findAll(Status.ACTIVE);
                for (Order order : allActive) {
                    ConsoleWriter.writeString(count + ". " + order.getId());
                    count++;
                }
                break;
            case 2:
                Order[] allClose = orderService.findAll(Status.CLOSE);
                for (Order order : allClose) {
                    ConsoleWriter.writeString(count + ". " + order.getId());
                    count++;
                }
                break;
        }
        ConsoleWriter.writeString(" ");
    }

    @Override
    public void findAllByUserAndStatus() {
        ConsoleWriter.writeString("Choose the status: 0 - Back, 1 - Active, 2 - Close");
        int i = ConsoleReader.readInt();

        switch (i) {
            case 0:
                return;
            case 1:
                Order[] all = orderService.findAll(ConsoleApplication.session.getUser(), Status.ACTIVE);
                for (int j = 0; j < all.length; j++) {
                    if (all[j] == null) break;
                    ConsoleWriter.writeString("Order: " + all[j].getId());
                }
                break;
            case 2:
                Order[] all2 = orderService.findAll(ConsoleApplication.session.getUser(), Status.CLOSE);
                for (int j = 0; j < all2.length; j++) {
                    if (all2[j] == null) break;
                    ConsoleWriter.writeString("Order: " + all2[j].getId());
                }
                break;
        }
    }

    @Override
    public void findAllByAddress() {


        Address[] all = addressService.getAll();
        ConsoleWriter.writeString("Address: ");
        for (int i = 0; i < all.length; i++) {
            ConsoleWriter.writeString((i + 1) + ". " + all[i].getStreet());
        }

        ConsoleWriter.writeString("Choose the address");
        int i = ConsoleReader.readInt() - 1;
        Address address = all[i];

        int count = 1;
        Order[] allAddress = orderService.findAll(address);
        ConsoleWriter.writeString("Order: ");
        for (Order order : allAddress) {
            ConsoleWriter.writeString(count + ". " + order.getId());
            count++;
        }
        ConsoleWriter.writeString(" ");
    }

    @Override
    public void findAllByUser() {

        User[] all = userService.findAll();
        ConsoleWriter.writeString("User: ");
        for (int i = 0; i < all.length; i++) {
            ConsoleWriter.writeString((i + 1) + ". " + all[i].getLogin());
        }

        ConsoleWriter.writeString("Choose the user");
        int i = ConsoleReader.readInt() - 1;
        User user = all[i];

        int count = 1;
        Order[] allUser = orderService.findAll(user);
        ConsoleWriter.writeString("Order: ");
        for (Order order : allUser) {
            ConsoleWriter.writeString(count + ". " + order.getId());
            count++;
        }
        ConsoleWriter.writeString(" ");
    }

    @Override
    public void findAllByStore() {


        Store[] all = storeService.getAll();
        ConsoleWriter.writeString("Store: ");
        for (int i = 0; i < all.length; i++) {
            ConsoleWriter.writeString((i + 1) + ". " + all[i].getTitle());
        }

        ConsoleWriter.writeString("Choose the Store");
        int i = ConsoleReader.readInt() - 1;
        Store store = all[i];

        int count = 1;
        Order[] allStore = orderService.findAll(store);
        ConsoleWriter.writeString("Order: ");
        for (Order order : allStore) {
            ConsoleWriter.writeString(count + ". " + order.getId());
            count++;
        }
        ConsoleWriter.writeString(" ");
    }

    @Override
    public void findById() {

        ConsoleWriter.writeString("enter the id");
        int id = ConsoleReader.readInt();

        if (OrderValidator.invalidId(id)) {
            ConsoleWriter.writeString("id is invalidate");
        }

        Order order = orderService.findById(id);
        Book[] books = order.getBooks();
        if (order.isDelivery()) {
            ConsoleWriter.writeString("Order: " + order.getId() + "\n" + order.getStore().getTitle() + "\n" +
                    order.getAddress().getStreet() + " " + order.getAddress().getHome() + "\n" + order.getStatus());

        } else {
            ConsoleWriter.writeString("Order: " + order.getId() + "\n" + order.getStore().getTitle() + "\n" +
                    order.getStore().getAddress().getStreet() + " " + order.getStore().getAddress().getHome() + "\n" +
                    order.getStatus());

        }
        for (Book book : books) {
            ConsoleWriter.writeString("Book: " + book.getTitle());
        }
    }
}

