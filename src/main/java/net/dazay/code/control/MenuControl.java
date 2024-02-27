package net.dazay.code.control;

import net.dazay.code.api.ILibraryControl;
import net.dazay.code.api.model.IBook;
import net.dazay.code.model.SimpleBook;
import net.dazay.code.util.MenuUtil;

import java.io.Console;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

/**
 * Класс управления меню.
 * Создал для удобства, вдруг понадобится переписать под ГУИ.
 * @author DazayAnarchy
 */
public class MenuControl
{
    final ILibraryControl libraryControl;

    /**
     * Общий объект сканнера для всего меню,
     * для чтения текста
     */
    final Scanner scanner = new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));

    final String SEPARATOR = "---------------------------------------------";

    public MenuControl(ILibraryControl libraryControl)
    {
        this.libraryControl = libraryControl;
        showMainMenu();
    }

    /**
     * Метод показывающий главное меню.
     * Выводит основной функционал.
     */
    private void showMainMenu()
    {
        System.out.println(SEPARATOR);
        System.out.println("\u0412\u044B\u0431\u0435\u0440\u0438\u0442\u0435 \u043D\u0435\u043E\u0431\u0445\u043E\u0434\u0438\u043C\u043E\u0435 \u0434\u0435\u0439\u0441\u0442\u0432\u0438\u0435: ");
        System.out.println("1. \u0414\u043E\u0431\u0430\u0432\u043B\u0435\u043D\u0438\u0435 \u043D\u043E\u0432\u043E\u0439 \u043A\u043D\u0438\u0433\u0438.");
        System.out.println("2. \u041F\u0440\u043E\u0441\u043C\u043E\u0442\u0440 \u0441\u043F\u0438\u0441\u043A\u0430 \u043A\u043D\u0438\u0433.");
        System.out.println("3. \u041F\u043E\u0438\u0441\u043A \u043A\u043D\u0438\u0433\u0438.");
        int currentAction = MenuUtil.readInt(1, 3, scanner);
        switch (currentAction)
        {
            case 1 -> addBookMenu();
            case 2 -> allBooksMenu();
            case 3 -> searchBookMenu();
        }
    }

    /**
     * Метод показывающий меню добавления книги.
     * Выводит основной функционал данного меню.
     */
    private void addBookMenu()
    {
        System.out.println(SEPARATOR);
        System.out.println("\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u043D\u0430\u0437\u0432\u0430\u043D\u0438\u0435 \u043A\u043D\u0438\u0433\u0438.");
        String bookName = MenuUtil.readText(40, scanner);
        System.out.println("\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0424\u0418\u041E \u0410\u0432\u0442\u043E\u0440\u0430.");
        String authorName = MenuUtil.readText(40, scanner);
        String genre = getGenre();
        System.out.println("\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u043E\u043F\u0438\u0441\u0430\u043D\u0438\u0435 \u043A\u043D\u0438\u0433\u0438. \u041C\u0430\u043A\u0441\u0438\u043C\u0443\u043C 240 \u0441\u0438\u043C\u0432\u043E\u043B\u043E\u0432.");
        String description = MenuUtil.readText(240, scanner);
        libraryControl.addBook(new SimpleBook(bookName, authorName, genre, description));
        System.out.println("\u0412\u0430\u0448\u0430 \u043A\u043D\u0438\u0433\u0430 \u0443\u0441\u043F\u0435\u0448\u043D\u043E \u0434\u043E\u0431\u0430\u0432\u043B\u0435\u043D\u0430!");
        showMainMenu();
    }

    /**
     * Метод показывающий список книг.
     * Выводит основной функционал данного меню,
     * а так же все книги.
     */
    private void allBooksMenu()
    {
        List<IBook> allBooks = libraryControl.getAllBooks();
        System.out.println(SEPARATOR);
        if(allBooks.isEmpty())
        {
            System.out.println("\u0418\u0437\u0432\u0438\u043D\u0438\u0442\u0435, \u0432 \u043D\u0430\u0448\u0435\u0439 \u0431\u0438\u0431\u043B\u0438\u043E\u0442\u0435\u043A\u0435 \u043D\u0435\u0442 \u043A\u043D\u0438\u0433.");
            showMainMenu();
        }
        for (int i = 0; i < allBooks.size(); i++) {
            System.out.println(i+1 + String.format(". \u041D\u0430\u0437\u0432\u0430\u043D\u0438\u0435: %s, \u0410\u0432\u0442\u043E\u0440: %s", allBooks.get(i).getName(), allBooks.get(i).getAuthor()));
        }
        int startIndex = allBooks.size()+1;
        System.out.println(startIndex + ". \u0412\u044B\u0431\u0440\u0430\u0442\u044C \u043A\u043D\u0438\u0433\u0438 \u043F\u043E \u0436\u0430\u043D\u0440\u0443.");
        System.out.println(startIndex+1 + ". \u0412\u0435\u0440\u043D\u0443\u0442\u044C\u0441\u044F \u0432 \u0433\u043B\u0430\u0432\u043D\u043E\u0435 \u043C\u0435\u043D\u044E.");

        int action = MenuUtil.readInt(1, startIndex+1, scanner);
        if(action<=allBooks.size())
        {
            showBookMenu(allBooks.get(action-1));
        }
        else if(startIndex==action)
        {
            showSelectBookGenreMenu();
        } else {
            showMainMenu();
        }
    }

    /**
     * Метод позволяющий совершать поиск по книгам.
     */
    private void searchBookMenu()
    {
        System.out.println(SEPARATOR);
        System.out.println("\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u043A\u043B\u044E\u0447\u0435\u0432\u043E\u0435 \u0441\u043B\u043E\u0432\u043E \u0434\u043B\u044F \u043F\u043E\u0438\u0441\u043A\u0430:");
        String keyword = MenuUtil.readText(50, scanner);
        List<IBook> booksWithKeyword = libraryControl.getBooksContains(keyword);
        if(booksWithKeyword.isEmpty())
        {
            System.out.println("\u0418\u0437\u0432\u0438\u043D\u0438\u0442\u0435, \u0432 \u043D\u0430\u0448\u0435\u0439 \u0431\u0438\u0431\u043B\u0438\u043E\u0442\u0435\u043A\u0435 \u043D\u0435\u0442 \u0442\u0430\u043A\u043E\u0439 \u043A\u043D\u0438\u0433\u0438.");
            System.out.println("1. \u0412\u044B\u043F\u043E\u043B\u043D\u0438\u0442\u044C \u043D\u043E\u0432\u044B\u0439 \u043F\u043E\u0438\u0441\u043A.");
            System.out.println("2. \u0412\u0435\u0440\u043D\u0443\u0442\u044C\u0441\u044F \u0432 \u0433\u043B\u0430\u0432\u043D\u043E\u0435 \u043C\u0435\u043D\u044E.");
            int action = MenuUtil.readInt(1, 2, scanner);
            if(action == 1)
                searchBookMenu();
            else
                showMainMenu();
        } else {
            System.out.println("\u041D\u0430\u0439\u0434\u0435\u043D\u043D\u044B\u0435 \u043A\u043D\u0438\u0433\u0438:");
            for(int i = 0; i < booksWithKeyword.size(); i++)
            {
                System.out.println(i+1 + String.format(". \u041D\u0430\u0437\u0432\u0430\u043D\u0438\u0435: %s, \u0410\u0432\u0442\u043E\u0440: %s", booksWithKeyword.get(i).getName(), booksWithKeyword.get(i).getAuthor()));
            }
            System.out.println(booksWithKeyword.size()+1 + ". \u0412\u0435\u0440\u043D\u0443\u0442\u044C\u0441\u044F \u0432 \u0433\u043B\u0430\u0432\u043D\u043E\u0435 \u043C\u0435\u043D\u044E.");
            int action = MenuUtil.readInt(1, booksWithKeyword.size()+1, scanner);
            if(action <= booksWithKeyword.size())
            {
                showBookMenu(booksWithKeyword.get(action-1));
            } else {
                showMainMenu();
            }
        }
    }

    /**
     * Метод показывающий информацию об опред. книге
     * @param book - книга которую нужно отобразить
     */
    private void showBookMenu(IBook book)
    {
        System.out.println(SEPARATOR);
        System.out.println("\u041D\u0430\u0437\u0432\u0430\u043D\u0438\u0435: " + book.getName());
        System.out.println("\u0410\u0432\u0442\u043E\u0440: " + book.getAuthor());
        System.out.println("\u0416\u0430\u043D\u0440: " + book.getGenre());
        System.out.println("\u041E\u043F\u0438\u0441\u0430\u043D\u0438\u0435: " + book.getDescription());
        System.out.println("1. \u0423\u0434\u0430\u043B\u0438\u0442\u044C \u043A\u043D\u0438\u0433\u0443.");
        System.out.println("2. \u0412\u0435\u0440\u043D\u0443\u0442\u044C\u0441\u044F \u0432 \u0433\u043B\u0430\u0432\u043D\u043E\u0435 \u043C\u0435\u043D\u044E.");
        int action = MenuUtil.readInt(1, 2, scanner);
        if(action == 1)
        {
            libraryControl.removeBook(book);
            System.out.println("\u041A\u043D\u0438\u0433\u0430 \u0443\u0441\u043F\u0435\u0448\u043D\u043E \u0443\u0434\u0430\u043B\u0435\u043D\u0430!");
        }
        showMainMenu();
    }

    /**
     * Метод для поиска книги по жанру
     */
    private void showSelectBookGenreMenu()
    {
        System.out.println(SEPARATOR);
        String genre = getGenre();
        List<IBook> booksWithGenre = libraryControl.getBooksWithGenre(genre);
        if(booksWithGenre.isEmpty())
        {
            System.out.println("\u0418\u0437\u0432\u0438\u043D\u0438\u0442\u0435 \u043A\u043D\u0438\u0433 \u0441 \u0434\u0430\u043D\u043D\u044B\u043C \u0436\u0430\u043D\u0440\u043E\u043C \u043D\u0435 \u043D\u0430\u0439\u0434\u0435\u043D\u043E.");
            System.out.println("1. \u0412\u044B\u043F\u043E\u043B\u043D\u0438\u0442\u044C \u043D\u043E\u0432\u044B\u0439 \u043F\u043E\u0438\u0441\u043A.");
            System.out.println("2. \u0412\u0435\u0440\u043D\u0443\u0442\u044C\u0441\u044F \u0432 \u0433\u043B\u0430\u0432\u043D\u043E\u0435 \u043C\u0435\u043D\u044E.");
            int action = MenuUtil.readInt(1, 2, scanner);
            if(action == 1)
                showSelectBookGenreMenu();
            else
                showMainMenu();
        } else {
            for (int i = 0; i < booksWithGenre.size(); i++) {
                System.out.println(i+1 + String.format(". \u041D\u0430\u0437\u0432\u0430\u043D\u0438\u0435: %s, \u0410\u0432\u0442\u043E\u0440: %s", booksWithGenre.get(i).getName(), booksWithGenre.get(i).getAuthor()));
            }
            System.out.println(booksWithGenre.size()+1 + ". \u0412\u0435\u0440\u043D\u0443\u0442\u044C\u0441\u044F \u0432 \u0433\u043B\u0430\u0432\u043D\u043E\u0435 \u043C\u0435\u043D\u044E.");
            int action = MenuUtil.readInt(1, booksWithGenre.size()+1, scanner);
            if(action <= booksWithGenre.size())
            {
                showBookMenu(booksWithGenre.get(action-1));
            } else {
                showMainMenu();
            }
        }
    }

    /**
     * Метод ответственный за поиск существующих жанров, либо
     * принудительно заставляющий ввести свой
     * @return Жанр
     */
    private String getGenre()
    {
        List<String> genres = libraryControl.getAllBookGenres();
        if(genres.isEmpty())
        {
            System.out.println("\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0436\u0430\u043D\u0440 \u043A\u043D\u0438\u0433\u0438:");
            return MenuUtil.readText(40, scanner);
        }
        System.out.println("\u0412\u044B \u043C\u043E\u0436\u0435\u0442\u0435 \u0432\u0432\u0435\u0441\u0442\u0438 \u0441\u0432\u043E\u0439 \u0436\u0430\u043D\u0440, \u0438\u043B\u0438 \u0436\u0435 \u0432\u044B\u0431\u0440\u0430\u0442\u044C \u0438\u0437 \u0441\u0443\u0449\u0435\u0441\u0442\u0432\u0443\u044E\u0449\u0438\u0445.");
        System.out.println("\u0422\u0435\u043A\u0443\u0449\u0438\u0435 \u0436\u0430\u043D\u0440\u044B \u0432 \u0431\u0438\u0431\u043B\u0438\u043E\u0442\u0435\u043A\u0435:");
        for(int i = 0; i < genres.size(); i++)
        {
            System.out.println(i+1 + ". " + genres.get(i));
        }
        System.out.println(genres.size()+1 + ". \u0412\u0432\u0435\u0441\u0442\u0438 \u0441\u0432\u043E\u0439 \u0436\u0430\u043D\u0440.");

        int action = MenuUtil.readInt(1, genres.size()+1, scanner);
        if(action <= genres.size())
            return genres.get(action-1);
        else
        {
            System.out.println("\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0436\u0430\u043D\u0440 \u043A\u043D\u0438\u0433\u0438:");
            return MenuUtil.readText(40, scanner);
        }
    }
}
