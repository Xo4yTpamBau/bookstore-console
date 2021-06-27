package by.bookstore.storage.db;

import by.bookstore.entity.Address;
import by.bookstore.entity.Store;
import by.bookstore.storage.StoreStorage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBStoreStorage extends AbstractDBStorage implements StoreStorage {

    @Override
    public void save(Store store) {
        try {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement1 = connection.prepareStatement("insert into address values (default, ?, ?) returning id");
            preparedStatement1.setString(1, store.getAddress().getStreet());
            preparedStatement1.setInt(2, store.getAddress().getHome());
            ResultSet resultSet = preparedStatement1.executeQuery();
            resultSet.next();

            PreparedStatement preparedStatement = connection.prepareStatement("insert into store values (default, ? , ?)");
            preparedStatement.setString(1, store.getTitle());
            preparedStatement.setInt(2, resultSet.getInt(1));
            preparedStatement.execute();

            connection.commit();

        } catch (SQLException throwable) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwable.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from store where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }


    @Override
    public void delete(String title) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from store where title = ?");
            preparedStatement.setString(1, title);
            preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }


    @Override
    public String updateTitle(String title, int id) {
        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement("select title from store where id = ?");
            preparedStatement1.setInt(1, id);
            ResultSet resultSet = preparedStatement1.executeQuery();
            resultSet.next();
            PreparedStatement preparedStatement = connection.prepareStatement("update store set title = ? where id = ?");
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
            return resultSet.getString(1);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    @Override
    public Address updateAddress(Address address, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update store set address_id = ?  where id = ?");
            preparedStatement.setInt(1, address.getId());
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
            return null;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }


    @Override
    public Store[] getAll() {
        List<Store> all = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from store s join address a on a.id = s.address_id ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Store store = new Store(resultSet.getInt(1),
                        resultSet.getString(2),
                        new Address(resultSet.getInt(3),
                                resultSet.getString(5),
                                resultSet.getInt(6)));
                all.add(store);
            }
            return all.toArray(new Store[0]);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return new Store[0];
    }

    @Override
    public Store getByTitle(String title) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from store s join address a on a.id = s.address_id where s.title = ?");
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Store(resultSet.getInt(1),
                    resultSet.getString(2),
                    new Address(resultSet.getInt(3),
                            resultSet.getString(5),
                            resultSet.getInt(6)));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    @Override
    public Store getById(int id) {
        return storeGetById(id);
    }

    @Override
    public boolean contains(String title) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select count(*) from store where title = ?");
            preparedStatement.setString(1, title);
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
    public boolean contains(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select count(*) from store where id = ?");
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
}
