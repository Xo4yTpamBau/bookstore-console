package by.bookstore.storage.db;

import by.bookstore.entity.*;
import by.bookstore.storage.OrderStorage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBOrderStorage extends AbstractDBStorage implements OrderStorage {

    private static final String INSERT_ORDER = "insert into orders values (default, ?, ?, ?, ?, ?) returning id";
    private static final String INSERT_ORDER_BOOK = "insert into order_book values (?, ?)";

    @Override
    public void save(Order order) {
        try {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER);
            preparedStatement.setBoolean(1, order.isDelivery());
            preparedStatement.setInt(2, idByStatus(order.getStatus()));
            preparedStatement.setInt(3, order.getAddress().getId());
            preparedStatement.setInt(4, order.getStore().getId());
            preparedStatement.setInt(5, order.getUser().getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();


            Book[] books = order.getBooks();
            for (Book book : books) {
                PreparedStatement preparedStatement1 = connection.prepareStatement(INSERT_ORDER_BOOK);
                preparedStatement1.setInt(1, resultSet.getInt(1));
                preparedStatement1.setInt(2, book.getId());
            }

            connection.commit();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private int idByStatus(Status status) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select id from statuses where status = ?");
            preparedStatement.setString(1, status.name());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }


    @Override
    public void update(Status status, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update orders set status_Id = ? where id = ?");
            preparedStatement.setInt(1, idByStatus(status));
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Address address, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update orders set address_id = ? where id = ?");
            preparedStatement.setInt(1, address.getId());
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


    @Override
    public void update(Store store, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update orders set store_id = ? where id = ?");
            preparedStatement.setInt(1, store.getId());
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public void update(boolean isDelivery, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update orders set is_delivery = ? where id = ?");
            preparedStatement.setBoolean(1, isDelivery);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from orders where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Store store) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from orders where store_id = ?");
            preparedStatement.setInt(1, store.getId());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void addBook(Book book, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_BOOK);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, book.getId());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteBook(int bookId, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from order_book where order_id = ? and book_id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, bookId);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Book[] booksById(int orderId) {
        List<Book> all = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from book join author a on a.id = book.author_id join order_book ob on book.id = ob.book_id where ob.order_id = ?");
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        authorGetById(resultSet.getInt(4)),
                        resultSet.getDouble(5));
                all.add(book);
            }
            return all.toArray(new Book[0]);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private Order saveOrderResultSet (ResultSet resultSet) throws SQLException {
        Order order;
        if (resultSet.getBoolean(2)) {
            order = new Order(resultSet.getInt(1),
                    userGetById(resultSet.getInt(6)),
                    Status.valueOf(resultSet.getString(8)),
                    booksById(resultSet.getInt(1)),
                    addressGetById(resultSet.getInt(4)));
        } else {
            order = new Order(resultSet.getInt(1),
                    userGetById(resultSet.getInt(6)),
                    Status.valueOf(resultSet.getString(8)),
                    booksById(resultSet.getInt(1)),
                    storeGetById(resultSet.getInt(5)));
        }
        return order;
    }


    @Override
    public Order[] findAll() {
        List<Order> all = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from orders o" +
                    "         join statuses s on s.id = o.status_id" +
                    "         left join store s2 on s2.id = o.store_id ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                all.add(saveOrderResultSet(resultSet));
            }
            return all.toArray(new Order[0]);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new Order[0];
    }

    @Override
    public Order[] findAll(Status status) {
        List<Order> all = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from orders o" +
                    "         join statuses s on s.id = o.status_id" +
                    "         left join store s2 on s2.id = o.store_id  where s.status = ?");
            preparedStatement.setObject(1, status);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                all.add(saveOrderResultSet(resultSet));
            }
            return all.toArray(new Order[0]);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new Order[0];
    }

    @Override
    public Order[] findAll(Address address) {
        List<Order> all = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from orders o" +
                    "         join statuses s on s.id = o.status_id" +
                    "         left join store s2 on s2.id = o.store_id where o.address_id = ?");
            preparedStatement.setInt(1, address.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                all.add(saveOrderResultSet(resultSet));
            }
            return all.toArray(new Order[0]);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new Order[0];
    }

    @Override
    public Order[] findAll(User user) {
        List<Order> all = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from orders o" +
                    "         join statuses s on s.id = o.status_id" +
                    "         left join store s2 on s2.id = o.store_id where o.user_id = ? ");
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                all.add(saveOrderResultSet(resultSet));
            }
            return all.toArray(new Order[0]);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new Order[0];
    }

    @Override
    public Order[] findAll(Store store) {
        List<Order> all = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from orders o" +
                    "         join statuses s on s.id = o.status_id" +
                    "         left join store s2 on s2.id = o.store_id where o.store_id = ?");
            preparedStatement.setInt(1, store.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                all.add(saveOrderResultSet(resultSet));
            }
            return all.toArray(new Order[0]);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new Order[0];
    }

    @Override
    public Order findById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from orders o" +
                    "         join statuses s on s.id = o.status_id" +
                    "         left join store s2 on s2.id = o.store_id where o.id = ?");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
           return saveOrderResultSet(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Order[] findAll(User user, Status status) {
        List<Order> all = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from orders o" +
                    "         join statuses s on s.id = o.status_id" +
                    "         left join store s2 on s2.id = o.store_id where o.user_id = ? and s.status = ?");
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setObject(2, status);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                all.add(saveOrderResultSet(resultSet));
            }
            return all.toArray(new Order[0]);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new Order[0];
    }

    @Override
    public boolean contains(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select count(*) from orders where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if (resultSet.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean contains(Order order) {
        Order[] all = findAll();
        for (Order order1 : all) {
            if (order1.equals(order)) {
                return true;
            }
        }
        return false;
    }
}
