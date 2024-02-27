package net.dazay.code.control;

import net.dazay.code.api.ILibraryControl;
import net.dazay.code.api.database.ISQLDatabase;
import net.dazay.code.api.model.IBook;

import java.util.List;

/**
 * @author DazayAnarchy
 */
public class SimpleLibraryControl implements ILibraryControl {

    private final ISQLDatabase sqlDatabase;

    /**
     * Конструктор контроллера библиотеки.
     * @param sqlDatabase - объект нашей базы данных для выполнения запросов.
     */
    public SimpleLibraryControl(ISQLDatabase sqlDatabase)
    {
        this.sqlDatabase = sqlDatabase;
        this.sqlDatabase.createOrCheckTables();
    }

    @Override
    public void addBook(IBook book)
    {
        sqlDatabase.addBook(book);
    }

    @Override
    public void removeBook(IBook book)
    {
        sqlDatabase.removeBook(book);
    }

    @Override
    public List<IBook> getAllBooks()
    {
        return sqlDatabase.getAllBooks();
    }

    @Override
    public List<IBook> getBooksWithGenre(String genre)
    {
        return sqlDatabase.getBooksWithGenre(genre);
    }

    @Override
    public List<IBook> getBooksContains(String keyword)
    {
        return sqlDatabase.getBooksContains(keyword);
    }

    @Override
    public List<String> getAllBookGenres()
    {
        return sqlDatabase.getAllBookGenres();
    }
}
