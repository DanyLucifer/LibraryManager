package net.dazay.code.util;

import java.util.Scanner;

public class MenuUtil
{
    /**
     * Метод для чтения числа - номера действия с консоли.
     * @param minVal - минимальное значение
     * @param maxVal - максимальное значение
     * @return введенное число соотвествующее требованиям
     */
    public static int readInt(int minVal, int maxVal, Scanner sc)
    {
        int currentAction = -1;
        do {
            try {
                int tempAction = Integer.parseInt(sc.nextLine());
                if(tempAction < minVal || tempAction > maxVal)
                {
                    System.out.println(String.format("\u041C\u0430\u043A\u0441\u0438\u043C\u0430\u043B\u044C\u043D\u043E\u0435 \u0437\u043D\u0430\u0447\u0435\u043D\u0438\u0435 %s, \u043C\u0438\u043D\u0438\u043C\u0430\u043B\u044C\u043D\u043E\u0435 \u0437\u043D\u0430\u0447\u0435\u043D\u0438\u0435: %s", maxVal, minVal));
                } else {
                    currentAction = tempAction;
                }
            } catch (NumberFormatException numberFormatException)
            {
                System.out.println("\u0414\u043B\u044F \u0432\u044B\u0431\u043E\u0440\u0430 \u0434\u0435\u0439\u0441\u0442\u0432\u0438\u044F \u0432\u0432\u0435\u0434\u0438\u0442\u0435 \u043D\u043E\u043C\u0435\u0440 \u0434\u0435\u0439\u0441\u0442\u0432\u0438\u044F!");
            }
        } while (currentAction == -1);
        return currentAction;
    }

    /**
     * Метод для чтения текста с консоли.
     * @param maxLength - максимальная длинна текста
     * @return введенный текст соответствующий требованиям
     */
    public static String readText(int maxLength, Scanner sc)
    {
        String currentText = "";
        do {
            String tempText = System.console().readLine();
            if(tempText.isEmpty())
            {
                System.out.println("\u041F\u0430\u0440\u0430\u043C\u0435\u0442\u0440 \u043D\u0435 \u043C\u043E\u0436\u0435\u0442 \u0431\u044B\u0442\u044C \u043F\u0443\u0441\u0442\u044B\u043C!");
                continue;
            }
            if(tempText.length() > maxLength)
            {
                System.out.println(String.format("\u041C\u0430\u043A\u0441\u0438\u043C\u0430\u043B\u044C\u043D\u0430\u044F \u043A\u043E\u043B-\u0432\u043E \u0441\u0438\u043C\u0432\u043E\u043B\u043E\u0432 - %s, \u0430 \u0432\u044B \u0432\u0432\u0435\u043B\u0438: %s", maxLength,
                        tempText.length()));
                continue;
            }
            currentText = tempText;
        } while (currentText.isEmpty());
        return currentText;
    }
}
