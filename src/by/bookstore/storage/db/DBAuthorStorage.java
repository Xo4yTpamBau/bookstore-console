package by.bookstore.storage.db;

import by.bookstore.entity.Author;
import by.bookstore.entity.Store;
import by.bookstore.storage.AuthorStorage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBAuthorStorage extends AbstractDBStorage implements AuthorStorage {

    @Override
    public void save(Author author) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into author values (default, ? , ?)");
            preparedStatement.setString(1, author.getName());
            preparedStatement.setString(2, author.getDescription());
            preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void updateDescription(String newDescription, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update author set description = ? where id = ?");
            preparedStatement.setString(1, newDescription);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void delete(String name) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from author where name = ? ");
            preparedStatement.setString(1, name);
            preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete  from author where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public Author[] getAll() {
        List<Author> all = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from author");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Author author = new Author(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                all.add(author);
            }
            return all.toArray(new Author[0]);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return new Author[0];
    }

    @Override
    public Author getById(int id) {
        return authorGetById(id);
    }

    @Override
    public Author getByName(String name) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from author where name = ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Author(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean contains(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select count(*) from author where id = ?");
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
    public boolean contains(String name) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select count(*) from author where name = ?");
            preparedStatement.setString(1, name);
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
