package net.dazay.code.database;

import net.dazay.code.ProgramStart;
import net.dazay.code.api.database.ISQLDatabase;
import net.dazay.code.api.model.IBook;
import net.dazay.code.model.SimpleBook;
import org.sqlite.SQLiteConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс ответственный за работу с базой данных SQLite
 * @author DazayAnarchy
 */
public class SQLiteDatabase implements ISQLDatabase
{
    private final String databaseName;

    /**
     * Конструктор базы данных SQLite.
     * @param databaseName - название файла базы данных
     */
    public SQLiteDatabase(String databaseName)
    {
        this.databaseName = databaseName;
        loadDriver();
    }

    /**
     * Метод загрузки драйвера базы данных.
     * В данном случае он находится в одном архиве со сборкой.
     */
    private void loadDriver()
    {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException exception)
        {
            ProgramStart.exceptionExit(exception.getMessage());
        }
    }

    /**
     * Метод для получения коннекта к базе данных.
     * @return объект Connection, позволяющий получить Statement,
     * для выполнения действия в БД.
     */
    private Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:sqlite:" + databaseName + ".db");
    }

    @Override
    public void createOrCheckTables()
    {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();

                statement.executeUpdate("CREATE TABLE IF NOT EXISTS library " +
                        "(Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                         "Name VARCHAR(40)," +
                         "Author VARCHAR(40)," +
                         "Genre VARCHAR(20)," +
                         "Description VARCHAR(250))");

            statement.close();
            connection.close();
        } catch (SQLException exception)
        {
            ProgramStart.exceptionExit(exception.getMessage());
        }
    }

    @Override
    public void addBook(IBook book)
    {
        try {
            Connection connection = getConnection();
            PreparedStatement statement =
                    connection
                            .prepareStatement(
                                    "INSERT INTO library(Name, Author, Genre, Description" +
                                            ") VALUES (?, ?, ?, ?)");

                statement.setString(1, book.getName());
                statement.setString(2, book.getAuthor());
                statement.setString(3, book.getGenre());
                statement.setString(4, book.getDescription());
                statement.execute();

            statement.close();
            connection.close();
        } catch (SQLException exception)
        {
            System.out.println(String.format("\u041F\u0440\u043E\u0438\u0437\u043E\u0448\u043B\u0430 \u043E\u0448\u0438\u0431\u043A\u0430 \u043F\u0440\u0438 \u043F\u043E\u043F\u044B\u0442\u043A\u0435 \u0434\u043E\u0431\u0430\u0432\u043B\u0435\u043D\u0438\u044F \u043A\u043D\u0438\u0433\u0438. \n" +
                    "\u041D\u0430\u0437\u0432\u0430\u043D\u0438\u0435: %s, \u0410\u0432\u0442\u043E\u0440: %s, \u0416\u0430\u043D\u0440: %s", book.getName(), book.getAuthor(), book.getGenre()));
        }
    }

    @Override
    public void removeBook(IBook book)
    {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();

                statement.executeUpdate(
                        String.format("DELETE FROM library WHERE Name='%s' AND Author='%s' AND Genre='%s'",
                                book.getName(), book.getAuthor(), book.getGenre()));

            statement.close();
            connection.close();
        } catch (SQLException exception)
        {
            System.out.println(String.format("\u041F\u0440\u043E\u0438\u0437\u043E\u0448\u043B\u0430 \u043E\u0448\u0438\u0431\u043A\u0430 \u043F\u0440\u0438 \u043F\u043E\u043F\u044B\u0442\u043A\u0435 \u0443\u0434\u0430\u043B\u0435\u043D\u0438\u044F \u043A\u043D\u0438\u0433\u0438. \n" +
                    "\u041D\u0430\u0437\u0432\u0430\u043D\u0438\u0435: %s, \u0410\u0432\u0442\u043E\u0440: %s, \u0416\u0430\u043D\u0440: %s", book.getName(), book.getAuthor(), book.getGenre()));
        }
    }

    @Override
    public List<IBook> getAllBooks()
    {
        List<IBook> allBooks = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();

                ResultSet result = statement.executeQuery("SELECT Name, Author, Genre, Description FROM library");
                while(result.next())
                    allBooks.add(new SimpleBook(result.getString("Name"),
                            result.getString("Author"),
                            result.getString("Genre"),
                            result.getString("Description")));

            result.close();
            statement.close();
            connection.close();
        } catch (SQLException exception)
        {
            System.out.println("\u041F\u0440\u043E\u0438\u0437\u043E\u0448\u043B\u0430 \u043E\u0438\u0448\u0431\u043A\u0430 \u043F\u0440\u0438 \u043F\u043E\u043F\u044B\u0442\u043A\u0435 \u0437\u0430\u0433\u0440\u0443\u0437\u043A\u0438 \u0432\u0441\u0435\u0445 \u043A\u043D\u0438\u0433.");
        }
        return allBooks;
    }

    @Override
    public List<IBook> getBooksWithGenre(String genre)
    {
        List<IBook> booksWithGenre = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(
                    "SELECT Name, Author, Genre, Description FROM library WHERE Genre='" + genre + "'");
            while(result.next())
                booksWithGenre.add(new SimpleBook(result.getString("Name"),
                        result.getString("Author"),
                        result.getString("Genre"),
                        result.getString("Description")));

            result.close();
            statement.close();
            connection.close();
        } catch (SQLException exception)
        {
            System.out.println("\u041F\u0440\u043E\u0438\u0437\u043E\u0448\u043B\u0430 \u043E\u0438\u0448\u0431\u043A\u0430 \u043F\u0440\u0438 \u043F\u043E\u043F\u044B\u0442\u043A\u0435 \u0437\u0430\u0433\u0440\u0443\u0437\u043A\u0438 \u0432\u0441\u0435\u0445 \u043A\u043D\u0438\u0433 \u0441 \u0436\u0430\u043D\u0440\u043E\u043C: " + genre);
        }
        return booksWithGenre;
    }

    @Override
    public List<IBook> getBooksContains(String keyword)
    {
        List<IBook> booksWithKeyword = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(String.format(
                    "SELECT Name, Author, Genre, Description FROM library" +
                            " WHERE Author LIKE '%s' OR Name LIKE '%s'", keyword, keyword));
            while(result.next())
                booksWithKeyword.add(new SimpleBook(result.getString("Name"),
                        result.getString("Author"),
                        result.getString("Genre"),
                        result.getString("Description")));

            result.close();
            statement.close();
            connection.close();
        } catch (SQLException exception)
        {
            System.out.println("\u041F\u0440\u043E\u0438\u0437\u043E\u0448\u043B\u0430 \u043E\u0438\u0448\u0431\u043A\u0430 \u043F\u0440\u0438 \u043F\u043E\u043F\u044B\u0442\u043A\u0435 \u0437\u0430\u0433\u0440\u0443\u0437\u043A\u0438 \u0432\u0441\u0435\u0445 \u043A\u043D\u0438\u0433 \u043F\u043E \u043A\u043B\u044E\u0447. \u0437\u043D\u0430\u0447\u0435\u043D\u0438\u044E: " + keyword);
        }
        return booksWithKeyword;
    }

    @Override
    public List<String> getAllBookGenres()
    {
        List<String> allBooksGenres = new ArrayList<>();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();

                ResultSet result = statement.executeQuery("SELECT Genre FROM library");
                while(result.next())
                    allBooksGenres.add(result.getString("Genre"));

            statement.close();
            connection.close();
        } catch (SQLException exception)
        {
            System.out.println("\u041F\u0440\u043E\u0438\u0437\u043E\u0448\u043B\u0430 \u043E\u0438\u0448\u0431\u043A\u0430 \u043F\u0440\u0438 \u043F\u043E\u043F\u044B\u0442\u043A\u0435 \u0437\u0430\u0433\u0440\u0443\u0437\u043A\u0438 \u0432\u0441\u0435\u0445 \u0436\u0430\u043D\u0440\u043E\u0432.");
        }
        return allBooksGenres;
    }
}
