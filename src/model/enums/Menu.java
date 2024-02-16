package model.enums;

import consts.Consts;
import exceptions.ValidateException;
import service.LoggerService;

import java.util.ArrayList;
import java.util.List;

public enum Menu {
    START_MENU(0, "Изъяли из области видимости нулевой выбор", false),
    ENCRYPT(1, "Зашифровать файл", true),
    DECRYPT(2, "Расшифровать файл, если известен ключ", true),
    BRUTE_FORCE(3, "Расшифровать методом перебора ключей (брутфорс)", true),
    STATISTIC_ANALYZE(4, "Расшифровать методом статистического анализа", false);

    private final int number;
    private final String text;
    private final boolean isDisplayed;

    Menu(int number, String text, boolean isDisplayed) {
        this.number = number;
        this.text = text;
        this.isDisplayed = isDisplayed;
    }

    public int getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }

    public boolean isDisplayed() {
        return isDisplayed;
    }

    public static Menu getMenu(int number) throws ValidateException {
        Menu[] menus = Menu.values();
        if (0 <= number && number < menus.length) {
            if (menus[number].isDisplayed()) {
                return menus[number];
            }
        }

        ValidateException e = new ValidateException(Consts.INCORRECT_MENU_TYPE);
        new LoggerService().logException(e.getMessage(), e);
        throw e;
    }

    public static List<Menu> getAllDisplayable() {
        Menu[] menus = Menu.values();
        List<Menu> displayedMenus = new ArrayList<>();
        for (Menu menu : menus) {
            if (menu.isDisplayed())
                displayedMenus.add(menu);
        }
        return displayedMenus;
    }
}
