package net.dazay.code.api.database;

import net.dazay.code.api.model.IBook;

import java.util.List;

/**
 * Интерфейс управления базой данных.
 * @author DazayAnarchy
 */
public interface ISQLDatabase
{
    /**
     * Метод ответственный за таблицы базы данных.
     * Создает таблицы в случае их отсутствия.
     */
    public void createOrCheckTables();

    /**
     * Метод добавляющий книгу в базу данных.
     * @param book - книга, которую необходимо добавить.
     */
    public void addBook(IBook book);

    /**
     * Метод удаляющий книгу с базы данных,
     * в случае ее существования.
     * @param book - книга, которую необходимо удалить из базы данных.
     */
    public void removeBook(IBook book);

    /**
     * Метод для получения объектов всех книг базы данных.
     * @return Список всех книг базы данных на данный момент.
     */
    public List<IBook> getAllBooks();

    /**
     * Метод для получения объектов всех книг базы данных с определенным жанром.
     * @param genre - Жанр, по которому нужно собрать книги.
     * @return Список всех книг базы данных с жанром(genre) на данный момент.
     */
    public List<IBook> getBooksWithGenre(String genre);

    /**
     * Метод для поиска всех книг базы данных, поля 'Автор' и/или 'Название'
     * которых содержат keyword.
     * @param keyword - ключевое слово для поиска.
     * @return Список всех книг с содержащимся ключевым словом.
     */
    public List<IBook> getBooksContains(String keyword);

    /**
     * Метод для получения жанров всех книг базы данных.
     * @return Список всех жанров базы данных.
     */
    public List<String> getAllBookGenres();
}
