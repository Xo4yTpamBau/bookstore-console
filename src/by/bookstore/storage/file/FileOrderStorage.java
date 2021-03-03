package by.bookstore.storage.file;

import by.bookstore.entity.*;
import by.bookstore.storage.OrderStorage;

import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileOrderStorage extends AbstractFileStorage implements OrderStorage {


    private int getIdByStatus(Status status) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_STATUS))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] s = line.split(" ");
                if (s[STATUS].equals(status.name())) {
                    return Integer.parseInt(s[STATUS_ID]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private Status getStatusById(String id) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_STATUS))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] s = line.split(" ");
                if (s[STATUS_ID].equals(id)) {
                    return Status.valueOf(s[STATUS]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Order order) {
        String s = getString(order);
        try (FileWriter fileWriter = new FileWriter(PATH_ORDERS, true)) {
            fileWriter.write(s);
            fileWriter.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getString(Order order) {
        StringBuilder stringBuilder = new StringBuilder();
        if (order.getId() != 0) {
            stringBuilder.append(order.getId()).append(" ");
        } else {
            stringBuilder.append(getLastId(PATH_ORDERS) + 1).append(" ");
        }
        stringBuilder.
                append(order.getUser().getId()).append(" ").
                append(order.isDelivery()).append(" ").
                append(getIdByStatus(order.getStatus())).append(" ");
        Book[] books = order.getBooks();
        if (books.length == 1) {
            stringBuilder.append("0");
        } else {
            for (Book book : books) {
                stringBuilder.append(book.getId()).append(",");
            }
        }
        if (order.isDelivery()) {
            stringBuilder.
                    append(" ").append(getIdByAddress(order.getAddress().getStreet(), String.valueOf(order.getAddress().getHome()))).append(" ").
                    append("0");
        } else {
            stringBuilder.
                    append(" ").append("0").append(" ").
                    append(order.getStore().getId());
        }
        return stringBuilder.toString();
    }


    @Override
    public void update(Status status, int id) {
        List<Order> all = Arrays.asList(findAll());
        for (Order order : all) {
            if (order.getId() == id) {
                order.setStatus(status);
                break;
            }
        }
        writeFileOrder(all);
    }

    @Override
    public void update(Address address, int id) {
        List<Order> all = Arrays.asList(findAll());
        for (Order order : all) {
            if (order.getId() == id) {
                order.setAddress(address);
                break;
            }
        }
        writeFileOrder(all);
    }

    @Override
    public void update(Store store, int id) {
        List<Order> all = Arrays.asList(findAll());
        for (Order order : all) {
            if (order.getId() == id) {
                order.setStore(store);
                break;
            }
        }
        writeFileOrder(all);
    }

    @Override
    public void update(boolean isDelivery, int id) {
        List<Order> all = Arrays.asList(findAll());
        for (Order order : all) {
            if (order.getId() == id) {
                order.setDelivery(isDelivery);
                break;
            }
        }
        writeFileOrder(all);
    }

    @Override
    public void delete(int id) {
        List<Order> all = new ArrayList<>(Arrays.asList(findAll()));
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getId() == id) {
                all.remove(i);
                break;
            }
        }
        writeFileOrder(all);
    }

    @Override
    public void delete(Store store) {
        List<Order> all = new ArrayList<>(Arrays.asList(findAll()));
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getStore() == null) continue;
            if (all.get(i).getStore().equals(store)) {
                all.remove(i--);
            }
        }
        writeFileOrder(all);
    }

    @Override
    public void addBook(Book book, int id) {
        List<Order> all = new ArrayList<>(Arrays.asList(findAll()));
        for (Order order : all) {
            if (order.getId() == id) {
                List<Book> books = new ArrayList<>(Arrays.asList(order.getBooks()));
                books.add(book);
                order.setBooks(books.toArray(new Book[0]));
                break;
            }
        }
        writeFileOrder(all);
    }

    @Override
    public void deleteBook(int bookId, int id) {
        List<Order> all = new ArrayList<>(Arrays.asList(findAll()));
        for (Order order : all) {
            if (order.getId() == id) {
                List<Book> books = new ArrayList<>(Arrays.asList(order.getBooks()));
                for (int i = 0; i < books.size(); i++) {
                    if (books.get(i).getId() == bookId) {
                        books.remove(i);
                        order.setBooks(books.toArray(new Book[0]));
                        break;
                    }
                }

            }
        }
        writeFileOrder(all);
    }

    private void writeFileOrder(List<Order> all) {
        try (FileWriter fileWriter = new FileWriter(PATH_ORDERS)) {
            for (Order order : all) {
                String orderString = getString(order);
                fileWriter.write(orderString);
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Book[] getBooks(String books) {
        List<Book> all = new ArrayList<>();
        String[] split = books.split(",");
        for (String value : split) {
            FileBookStorage fileBookStorage = new FileBookStorage();
            all.add(fileBookStorage.getById(Integer.parseInt(value)));
        }
        return all.toArray(new Book[0]);
    }

    @Override
    public Order[] findAll() {
        List<Order> all = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_ORDERS))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] s = line.split(" ");
                if (Boolean.parseBoolean(s[ORDERS_DELIVERY])) {
                    all.add(new Order(Integer.parseInt(s[ORDERS_ID]),
                            getUserById(s[ORDERS_USER_ID]),
                            getStatusById(s[ORDERS_STATUS_ID]),
                            getBooks(s[ORDERS_BOOKS_ID]),
                            getAddressById(s[ORDERS_ADDRESS])));
                } else {
                    all.add(new Order(Integer.parseInt(s[ORDERS_ID]),
                            getUserById(s[ORDERS_USER_ID]),
                            getStatusById(s[ORDERS_STATUS_ID]),
                            getBooks(s[ORDERS_BOOKS_ID]),
                            getStoreById(s[ORDERS_STORE_ID])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return all.toArray(new Order[0]);
    }

    @Override
    public Order[] findAll(Status status) {
        List<Order> allStatus = new ArrayList<>();
        Order[] all = findAll();
        for (Order order : all) {
            if (order.getStatus().equals(status)) {
                allStatus.add(order);
            }
        }
        return allStatus.toArray(new Order[0]);
    }

    @Override
    public Order[] findAll(Address address) {
        List<Order> allAddress = new ArrayList<>();
        Order[] all = findAll();
        for (Order order : all) {
            if (order.getAddress() == null) continue;
            if (order.getAddress().equals(address)) {
                allAddress.add(order);
            }
        }
        return allAddress.toArray(new Order[0]);
    }

    @Override
    public Order[] findAll(User user) {
        List<Order> allUser = new ArrayList<>();
        Order[] all = findAll();
        for (Order order : all) {
            if (order.getUser().equals(user)) {
                allUser.add(order);
            }
        }
        return allUser.toArray(new Order[0]);
    }

    @Override
    public Order[] findAll(Store store) {
        List<Order> allStore = new ArrayList<>();
        Order[] all = findAll();
        for (Order order : all) {
            if (order.getStore() == null) continue;
            if (order.getStore().equals(store)) {
                allStore.add(order);
            }
        }
        return allStore.toArray(new Order[0]);
    }

    @Override
    public Order findById(int id) {
        Order[] all = findAll();
        for (Order order : all) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    @Override
    public Order[] findAll(User user, Status status) {
        List<Order> allUserStatus = new ArrayList<>();
        Order[] all = findAll();
        for (Order order : all) {
            if (order.getUser().equals(user) && order.getStatus().equals(status)) {
                allUserStatus.add(order);
            }
        }
        return allUserStatus.toArray(new Order[0]);
    }

    @Override
    public boolean contains(int id) {
        Order[] all = findAll();
        for (Order order : all) {
            if (order.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Order order) {
        Order[] all = findAll();
        for (Order value : all) {
            if (value.equals(order)) {
                return true;
            }
        }
        return false;
    }

}
