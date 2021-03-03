package by.bookstore.storage.db;

import by.bookstore.entity.Address;
import by.bookstore.entity.Order;
import by.bookstore.storage.AddressStorage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBAddressStorage extends AbstractDBStorage implements AddressStorage {

    @Override
    public void save(Address address) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into address values (default, ?, ?)");
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setInt(2, address.getHome());
            preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from address where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void delete(Address address) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from address where street = ? and home = ?");
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setInt(2, address.getHome());
            preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public String updateStreet(String street, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update address set street = ? where id = ?");
            preparedStatement.setString(1, street);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();

            return null;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateHome(int home, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update address set home = ? where id = ?");
            preparedStatement.setInt(1, home);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
            return 0;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return 0;
    }

    @Override
    public Address[] getAll() {
        List<Address> all = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from address ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Address address = new Address(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3));
                all.add(address);
            }
            return all.toArray(new Address[0]);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return new Address[0];
    }

    @Override
    public Address[] getStreet(String street) {
        List<Address> all = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from address where street = ?");
            preparedStatement.setString(1, street);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Address address = new Address(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3));
                all.add(address);
            }
            return all.toArray(new Address[0]);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return new Address[0];
    }


    @Override
    public Address[] getHome(int home) {
        List<Address> all = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from address where home = ?");
            preparedStatement.setInt(1, home);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Address address = new Address(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3));
                all.add(address);
            }
            return all.toArray(new Address[0]);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return new Address[0];
    }


    @Override
    public Address getById(int id) {
        return addressGetById(id);
    }

    @Override
    public boolean contains(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select count(*) from address where id = ?");
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
    public boolean contains(Address address) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select count(*) from address where street = ? and home = ? ");
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setInt(2, address.getHome());
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
