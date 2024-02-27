package net.dazay.code.api.model;

/**
 * @author DazayAnarchy
 */
public interface IBook
{
    /**
     * Метод, возвращающий название объекта книги
     * @return Название книги
     */
    public String getName();

    /**
     * Метод, возвращающий автора объекта книги
     * @return Автор книги
     */
    public String getAuthor();

    /**
     * Метод, возвращающий жанр объекта книги
     * @return Жанр книги
     */
    public String getGenre();

    /**
     * Метод, возвращающий описание объекта книги
     * @return Описание книги
     */
    public String getDescription();
}
