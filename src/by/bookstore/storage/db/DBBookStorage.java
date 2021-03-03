package by.bookstore.storage.db;

import by.bookstore.entity.Author;
import by.bookstore.entity.Book;
import by.bookstore.entity.Order;
import by.bookstore.storage.BookStorage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBBookStorage extends AbstractDBStorage implements BookStorage {

    private final String SAVE_BOOK = "insert into book values (default, ?, ?, ?, ?)";
    private final String DELETE_ID = "delete from book where id = ?";
    private final String DELETE_TITLE = "delete from book where title = ?";
    private final String BOOK_GET_BY_ID = "select * from book where id = ?";
    private final String BOOK_GET_BY_TITLE = "select * from book where title = ?";

    @Override
    public void save(Book book) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_BOOK);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getDescription());
            preparedStatement.setInt(3, book.getAuthor().getId());
            preparedStatement.setDouble(4, book.getPrice());
            preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void delete(String title) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TITLE);
            preparedStatement.setString(1, title);
            preparedStatement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }


    @Override
    public String updateDescription(String newDescription, int id) {
        try {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("select description from book where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            PreparedStatement preparedStatement1 = connection.prepareStatement("update book set description = ? where id = ? ");
            preparedStatement1.setString(1, newDescription);
            preparedStatement1.setInt(2, id);
            preparedStatement1.execute();

            connection.commit();

            return resultSet.getString(1);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Author updateAuthor(Author newAuthor, int id) {
        try {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement1 = connection.prepareStatement("select author_id from book where id = ?");
            preparedStatement1.setInt(1, id);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            resultSet1.next();

            PreparedStatement preparedStatement2 = connection.prepareStatement("update book set author_id = ? where id = ?");
            preparedStatement2.setInt(1, newAuthor.getId());
            preparedStatement2.setInt(2, id);
            preparedStatement2.execute();

            connection.commit();

            return authorGetById(resultSet1.getInt(1));

        } catch (SQLException throwable) {
            throwable.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public int updatePrice(int newPrice, int id) {
        try {
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("select price from book where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            PreparedStatement preparedStatement1 = connection.prepareStatement("update book set price = ? where id = ?");
            preparedStatement1.setInt(1, newPrice);
            preparedStatement1.setInt(2, id);
            preparedStatement1.execute();

            connection.commit();

            return Integer.parseInt(resultSet.getString(1));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public Book[] getAll() {
        List<Book> all = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from book b join author a on b.author_id = a.id ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        new Author(resultSet.getInt(4),
                                resultSet.getString(7),
                                resultSet.getString(8)),
                        resultSet.getDouble(5));
                all.add(book);
            }
            return all.toArray(new Book[0]);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new Book[0];
    }

    @Override
    public Book getById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(BOOK_GET_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Book(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    authorGetById(resultSet.getInt(4)),
                    resultSet.getDouble(5));
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return null;
    }

    @Override
    public Book getByTitle(String title) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(BOOK_GET_BY_TITLE);
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return new Book(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    authorGetById(resultSet.getInt(4)),
                    resultSet.getDouble(5));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Book[] getAllByAuthor(Author author) {
        List<Book> allAuthor = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from book where author_id = ?");
            preparedStatement.setInt(1, author.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                allAuthor.add(new Book(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        authorGetById(resultSet.getInt(4)),
                        resultSet.getDouble(5)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allAuthor.toArray(new Book[0]);
    }

    @Override
    public Book[] getAllByPrice(double price) {
        List<Book> allPrice = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from book where price = ?");
            preparedStatement.setDouble(1, price);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                allPrice.add(new Book(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        authorGetById(resultSet.getInt(4)),
                        resultSet.getDouble(5)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allPrice.toArray(new Book[0]);
    }

    @Override
    public boolean contains(int id) {
        return getById(id) != null;
    }

    @Override
    public boolean contains(String title) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from book where title = ?");
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
