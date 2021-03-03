package by.bookstore.storage.inmemory;

import by.bookstore.entity.*;
import by.bookstore.storage.OrderStorage;

public class InMemoryOrderStorage implements OrderStorage {

    private static Order[] orders = new Order[10];

    static {
        orders[0] = new Order(new User(2, "User", "user", "user", 22, new Address("test", 23), Role.USER),
                new Book[]{
                        new Book("test", "test", new Author("test", "test"), 22),
                        new Book("test", "test", new Author("test", "test"), 22)
                }, new Address("tets", 22));
        orders[1] = new Order(new User(2, "User", "user", "user", 22, new Address("test", 23), Role.USER),
                new Book[]{
                        new Book("test", "test", new Author("test", "test"), 22),
                        new Book("test", "test", new Author("test", "test"), 22)
                }, new Address("tets", 22));
    }

    @Override
    public void save(Order order) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) {
                orders[i] = order;
                break;
            }
        }
    }

    @Override
    public void update(Status status, int id) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) break;
            if (orders[i].getId() == id) {
                orders[i].setStatus(status);
            }
        }
    }

    @Override
    public void update(Address address, int id) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) break;
            if (orders[i].getId() == id) {
                orders[i].setAddress(address);
            }
        }
    }

    @Override
    public void update(Store store, int id) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) break;
            if (orders[i].getId() == id) {
                orders[i].setStore(store);
            }
        }

    }

    @Override
    public void update(boolean isDelivery, int id) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) break;
            if (orders[i].getId() == id) {
                orders[i].setDelivery(isDelivery);
            }
        }
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) break;
            if (orders[i].getId() == id) {
                orders[i] = null;
                for (int j = i; j < orders.length - 1; j++) {
                    orders[j] = orders[j + 1];
                }
            }
        }
    }

    @Override
    public void delete(Store store) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) break;
            if (orders[i].getStore().equals(store)) {
                for (int j = i; j < orders.length; j++) {
                    orders[j] = orders[j + 1];
                }
            }
        }
    }

    @Override
    public void addBook(Book book, int id) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].getId() == id) {
                Order order = orders[i];
                Book[] books = order.getBooks();
                for (int i1 = 0; i1 < books.length; i1++) {
                    if (books[i1] == null) {
                        books[i1] = book;
                        break;
                    }
                }
                break;
            }
        }
    }

    @Override
    public void deleteBook(int bookId, int id) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) break;
            if (orders[i].getId() == id) {
                Order order = orders[i];
                Book[] books = order.getBooks();
                for (int j = 0; j < books.length; j++) {
                    if (books[j].getId() == bookId) {
                        books[j] = null;
                        for (int k = j; k < books.length - 1; k++) {
                            books[k] = books[k + 1];
                        }
                        break;
                    }
                }
                break;
            }
        }
    }

    @Override
    public Order[] findAll() {
        int count = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null) {
                count++;
            }
        }

        Order[] all = new Order[count];
        for (int i = 0; i < all.length; i++) {
            all[i] = orders[i];

        }
        return all;
    }

    @Override
    public Order[] findAll(Status status) {
        int count = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null && orders[i].getStatus().equals(status)) {
                count++;
            }
        }

        Order[] all = new Order[count];
        for (int i = 0, g = 0; i < all.length; i++) {
            if (orders[i] != null && orders[i].getStatus().equals(status))
                all[g] = orders[i];
            g++;

        }
        return all;
    }

    @Override
    public Order[] findAll(Address address) {
        int count = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null && orders[i].getAddress() != null && orders[i].getAddress().equals(address)) {
                count++;
            }
        }

        Order[] all = new Order[count];
        for (int i = 0, g = 0; i < all.length; i++) {
            if (orders[i] != null && orders[i].getAddress() != null && orders[i].getAddress().equals(address))
                all[g] = orders[i];
            g++;

        }
        return all;
    }


    @Override
    public Order[] findAll(User user) {
        int count = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null && orders[i].getUser().equals(user)) {
                count++;
            }
        }

        Order[] all = new Order[count];
        for (int i = 0, g = 0; i < all.length; i++) {
            if (orders[i] != null && orders[i].getUser().equals(user))
                all[g] = orders[i];
            g++;

        }
        return all;
    }

    @Override
    public Order[] findAll(Store store) {
        int count = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null && orders[i].getStore().equals(store)) {
                count++;
            }
        }

        Order[] all = new Order[count];
        for (int i = 0, g = 0; i < all.length; i++) {
            if (orders[i] != null && orders[i].getStore().equals(store))
                all[g] = orders[i];
            g++;

        }
        return all;
    }

    @Override
    public Order findById(int id) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i].getId() == id) {
                return orders[i];
            }
        }
        return null;
    }

    @Override
    public Order[] findAll(User user, Status status) {
        int count = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null && orders[i].getStatus().equals(status) && orders[i].getUser().equals(user)) {
                count++;
            }
        }

        Order[] all = new Order[count];
        for (int i = 0, j = 0; i < orders.length; i++) {
            if (orders[i] != null && orders[i].getStatus().equals(status) && orders[i].getUser().equals(user)) {
                all[j++] = orders[i];
            }
        }
        return all;
    }

    @Override
    public boolean contains(int id) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) break;
            if (orders[i].getId() == id)
                return true;
        }
        return false;
    }

    @Override
    public boolean contains(Order order) {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) break;
            if (orders[i].equals(order))
                return true;
        }
        return false;
    }
}
