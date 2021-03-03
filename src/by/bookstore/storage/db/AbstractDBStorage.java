package by.bookstore.storage.db;

import by.bookstore.Constant;
import by.bookstore.entity.*;

import java.sql.*;


public abstract class AbstractDBStorage {

    protected Connection connection;

    public AbstractDBStorage() {
        try {
            connection = DriverManager.getConnection(Constant.DB_URL, Constant.DB_LOGIN, Constant.DB_PASSWORD);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    protected Author authorGetById (int id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from  author where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Author(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    protected User userGetById (int id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users u join address a on a.id = u.address_id " +
                    "  join roles r on r.id = u.role_id where u.id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new User(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    addressGetById(resultSet.getInt(6)),
                    Role.valueOf(resultSet.getString(12)));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    protected Address addressGetById(int addressId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from address where id = ?");
            preparedStatement.setInt(1, addressId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Address(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getInt(3));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    protected Store storeGetById(int storeId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from store where id = ? ");
            preparedStatement.setInt(1, storeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Store(resultSet.getInt(1), resultSet.getString(2), addressGetById((resultSet.getInt(3))));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}
