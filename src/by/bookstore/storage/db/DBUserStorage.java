package by.bookstore.storage.db;

import by.bookstore.entity.Address;
import by.bookstore.entity.Order;
import by.bookstore.entity.Role;
import by.bookstore.entity.User;
import by.bookstore.storage.UserStorage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUserStorage extends AbstractDBStorage implements UserStorage {

    @Override
    public void save(User user) {

        try {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement1 = connection.prepareStatement("insert into address values (default, ?, ?) returning id");
            preparedStatement1.setString(1, user.getAddress().getStreet());
            preparedStatement1.setInt(2, user.getAddress().getHome());
            ResultSet resultSet = preparedStatement1.executeQuery();
            resultSet.next();

            PreparedStatement preparedStatement = connection.prepareStatement("insert into users values (default, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.setInt(5, resultSet.getInt(1));
            int roleIdByRoleName = getRoleIdByRoleName(user.getRole().name());
            if (roleIdByRoleName > 0) {
                preparedStatement.setObject(6, roleIdByRoleName);
            }
            preparedStatement.execute();

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

    private int getRoleIdByRoleName(String roleName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select id from role where role = ?");
            preparedStatement.setString(1, roleName);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public void updateName(String name, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update users set name = ? where id = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updatePassword(String password, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update users set password = ? where id = ?");
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public void updateAge(int age, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update users set age = ? where id = ?");
            preparedStatement.setInt(1, age);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updateAddress(Address address, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update users set address_id = ? where id = ?");
            preparedStatement.setInt(1, address.getId());
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from users where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteByLogin(String login) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from users where login = ?");
            preparedStatement.setString(1, login);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    private Address addressById(int addressId) {
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

    @Override
    public User[] findAll() {
        List<User> all = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users u join address a on a.id = u.address_id " +
                    "  join roles r on r.id = u.role_id ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                all.add(new User(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        addressById(resultSet.getInt(6)),
                        Role.valueOf(resultSet.getString(12))));
            }
            return all.toArray(new User[0]);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    @Override
    public User[] findAllByAge(int age) {
        List<User> all = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users u join address a on a.id = u.address_id " +
                    "  join roles r on r.id = u.role_id where u.age = ? ");
            preparedStatement.setInt(1, age);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                all.add(new User(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        addressById(resultSet.getInt(6)),
                        Role.valueOf(resultSet.getString(12))));
            }
            return all.toArray(new User[0]);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public User[] findAllByName(String name) {
        List<User> all = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users u join address a on a.id = u.address_id " +
                    "  join roles r on r.id = u.role_id where u.name = ? ");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                all.add(new User(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        addressById(resultSet.getInt(6)),
                        Role.valueOf(resultSet.getString(12))));
            }
            return all.toArray(new User[0]);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public User findByAddress(Address address) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users u join address a on a.id = u.address_id " +
                    "  join roles r on r.id = u.role_id where u.address_id = ? ");
            preparedStatement.setInt(1, address.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return (new User(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    addressById(resultSet.getInt(6)),
                    Role.valueOf(resultSet.getString(12))));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    @Override
    public User findById(int id) {
        return userGetById(id);
    }

    @Override
    public User findByLogin(String login) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users u join address a on a.id = u.address_id " +
                    "  join roles r on r.id = u.role_id where u.login = ? ");
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return (new User(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    addressById(resultSet.getInt(6)),
                    Role.valueOf(resultSet.getString(12))));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean contains(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select count(*) from users where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if (resultSet.getInt(1) > 0){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean contains(String login) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select count(*) from users where login = ?");
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if (resultSet.getInt(1) > 0){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
