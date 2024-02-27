package net.dazay.code.model;

import net.dazay.code.api.model.IBook;

/**
 * Класс стандартной книги.
 * @author DazayAnarchy
 */
public class SimpleBook implements IBook
{
    private final String name;
    private final String author;
    private final String genre;
    private final String description;

    /**
     * Конструктор класса SimpleBook имплементирующего
     * интерфейс API IBook.
     * @param name - Название книги.
     * @param author - Автор книги.
     * @param genre - Жанр книги.
     * @param description - Описание книги.
     */
    public SimpleBook(String name, String author, String genre, String description)
    {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getGenre() {
        return genre;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
