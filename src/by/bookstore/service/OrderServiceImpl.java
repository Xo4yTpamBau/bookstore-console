package by.bookstore.service;

import by.bookstore.entity.*;
import by.bookstore.storage.OrderStorage;
import by.bookstore.storage.db.DBOrderStorage;
import by.bookstore.storage.file.FileOrderStorage;
import by.bookstore.storage.inmemory.InMemoryOrderStorage;

public class OrderServiceImpl implements OrderService {

    OrderStorage orderStorage = new FileOrderStorage();

    @Override
    public boolean save(Order order) {
        if (orderStorage.contains(order)){
            return false;
        }
        orderStorage.save(order);
        return true;
    }

    @Override
    public void update(Status status, int id) {
        if (orderStorage.contains(id)){
            orderStorage.update(status, id);
        }
    }

    @Override
    public void update(Address address, int id) {
        if (orderStorage.contains(id)){
            orderStorage.update(address, id);
        }
    }

    @Override
    public void update(Store store, int id) {
        if (orderStorage.contains(id)){
            orderStorage.update(store, id);
        }
    }

    @Override
    public void update(boolean isDelivery, int id) {
        if(orderStorage.contains(id)){
            orderStorage.update(isDelivery, id);
        }
    }

    @Override
    public void delete(int id) {
        if (orderStorage.contains(id)){
            orderStorage.delete(id);
        }
    }

    @Override
    public void delete(Store store) {
        orderStorage.delete(store);
    }

    @Override
    public void addBook(Book book, int id) {
        if (orderStorage.contains(id)){
            orderStorage.addBook(book, id);
        }
    }

    @Override
    public void deleteBook(int bookId, int id) {
        if (orderStorage.contains(id)){
            orderStorage.deleteBook(bookId, id);
        }
    }

    @Override
    public Order[] findAll() { return orderStorage.findAll();}

    @Override
    public Order[] findAll(Status status) { return orderStorage.findAll(status); }

    @Override
    public Order[] findAll(Address address) { return orderStorage.findAll(address); }

    @Override
    public Order[] findAll(User user) { return orderStorage.findAll(user); }

    @Override
    public Order[] findAll(Store store) { return orderStorage.findAll(store); }

    public Order[] findAll(User user, Status status){return orderStorage.findAll(user, status);}

    @Override
    public Order findById(int id) {
       if (orderStorage.contains(id)){
           return orderStorage.findById(id);
       }
       return null;
    }

}
