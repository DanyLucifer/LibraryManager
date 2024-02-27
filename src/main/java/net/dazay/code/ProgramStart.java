package net.dazay.code;

import net.dazay.code.api.ILibraryControl;
import net.dazay.code.control.MenuControl;
import net.dazay.code.control.SimpleLibraryControl;
import net.dazay.code.database.SQLiteDatabase;

/**
 * Класс загрузчик.
 * @author DazayAnarchy
 */
public class ProgramStart
{
    private static SQLiteDatabase database;
    private static ILibraryControl libraryControl;

    public static void main(String[] args)
    {
        System.setProperty("console.encoding", "UTF-8");
        System.out.println("\u0417\u0430\u0433\u0440\u0443\u0437\u043A\u0430 \u0431\u0430\u0437\u044B \u0434\u0430\u043D\u043D\u044B\u0445.");
        loadDatabase();
        System.out.println("\u0411\u0430\u0437\u0430 \u0434\u0430\u043D\u043D\u044B\u0445 \u0431\u044B\u043B\u0430 \u0443\u0441\u043F\u0435\u0448\u043D\u043E \u0437\u0430\u0433\u0440\u0443\u0436\u0435\u043D\u0430.");
        System.out.println("\u0417\u0430\u0433\u0440\u0443\u0437\u043A\u0430 \u043A\u043E\u043D\u0442\u0440\u043E\u043B\u043B\u0435\u0440\u0430 \u0431\u0438\u0431\u043B\u0438\u043E\u0442\u0435\u043A\u0438.");
        loadLibraryControl();
        System.out.println("\u041A\u043E\u043D\u0442\u0440\u043E\u043B\u043B\u0435\u0440 \u0431\u0438\u0431\u043B\u0438\u043E\u0442\u0435\u043A\u0438 \u0443\u0441\u043F\u0435\u0448\u043D\u043E \u0437\u0430\u0433\u0440\u0443\u0436\u0435\u043D.");
        new MenuControl(libraryControl);
    }

    private static void loadDatabase()
    {
        database = new SQLiteDatabase("library");
    }

    private static void loadLibraryControl()
    {
        libraryControl = new SimpleLibraryControl(database);
    }

    /**
     * Метод завершения программы в случае серьезной ошибки.
     * @param exceptionMessage - текст ошибки. Для будующей обработки.
     */
    public static void exceptionExit(String exceptionMessage)
    {
        System.out.println("[ERROR]: \u0412\u043E\u0437\u043D\u0438\u043A\u043B\u0430 \u043E\u0448\u0438\u0431\u043A\u0430.");
        System.out.println("\u0422\u0435\u043A\u0441\u0442 \u043E\u0448\u0438\u0431\u043A\u0438: " + exceptionMessage);
        System.exit(0);
    }
}
