package net.dazay.code.api;

import net.dazay.code.api.model.IBook;

import java.util.List;

/**
 * Интерфейс для управления API.
 * Контроллер всех функций библиотеки.
 * @author DazayAnarchy
 */
public interface ILibraryControl
{
    /**
     * Метод добавляющий книгу в библиотеку.
     * @param book - книга, которую необходимо добавить.
     */
    public void addBook(IBook book);

    /**
     * Метод удаляющий книгу в библиотеке,
     * в случае если она вообще есть.
     * @param book - книга, которую необходимо удалить из библиотеки.
     */
    public void removeBook(IBook book);

    /**
     * Метод для получения объектов всех книг библиотеки.
     * @return Список всех книг библиотеки на данный момент.
     */
    public List<IBook> getAllBooks();

    /**
     * Метод для получения объектов всех книг библиотеки с определенным жанром.
     * @param genre - Жанр, по которому нужно собрать книги.
     * @return Список всех книг библиотеки с жанром(genre) на данный момент.
     */
    public List<IBook> getBooksWithGenre(String genre);

    /**
     * Метод для поиска всех книг библиотеки, поля 'Автор' и/или 'Название'
     * которых содержат keyword.
     * @param keyword - ключевое слово для поиска.
     * @return Список всех книг с содержащимся ключевым словом.
     */
    public List<IBook> getBooksContains(String keyword);

    /**
     * Метод для получения жанров всех книг библиотеки.
     * @return Список всех жанров библиотеки.
     */
    public List<String> getAllBookGenres();
}
