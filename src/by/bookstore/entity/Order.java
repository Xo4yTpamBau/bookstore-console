package by.bookstore.entity;

import java.util.Arrays;
import java.util.Objects;

public class Order {
    private int id;

    private boolean isDelivery;
    private User user;
    private Status status = Status.ACTIVE;
    private Book[] books;

    private Address address;
    private Store store;

    public Order() {
    }

    public Order(int id, User user, Status status, Book[] books, Store store) {
        this.id = id;
        this.user = user;
        this.status = status;
        this.books = books;
        this.store = store;
    }

    public Order(User user, Book[] books, Address address) {
        this.user = user;
        this.books = books;
        this.address = address;
        this.isDelivery = true;
    }

    public Order(User user, Book[] books, Store store) {
        this.user = user;
        this.books = books;
        this.store = store;
    }

    public Order(int id, User user, Status status, Book[] books, Address address) {
        this.id = id;
        this.user = user;
        this.status = status;
        this.books = books;
        this.address = address;
        this.isDelivery = true;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDelivery() {
        return isDelivery;
    }

    public void setDelivery(boolean delivery) {
        isDelivery = delivery;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return isDelivery == order.isDelivery &&
                Objects.equals(user, order.user) &&
                status == order.status &&
                Arrays.equals(books, order.books);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(isDelivery, user, status);
        result = 31 * result + Arrays.hashCode(books);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", isDelivery=" + isDelivery +
                ", user=" + user +
                ", status=" + status +
                ", books=" + Arrays.toString(books) +
                ", address=" + address +
                ", store=" + store +
                '}';
    }
}
