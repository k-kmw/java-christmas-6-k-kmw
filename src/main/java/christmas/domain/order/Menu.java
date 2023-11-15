package christmas.domain.order;

import java.util.HashMap;
import java.util.Map;

public final class Menu {

    public static final Map<String, MenuItem> MENU_ITEMS = new HashMap<>();

    static {
        MENU_ITEMS.put("티본스테이크", new MenuItem("티본스테이크", 55000, Category.MAIN));
        MENU_ITEMS.put("바비큐립", new MenuItem("바비큐립", 54000, Category.MAIN));
        MENU_ITEMS.put("해산물파스타", new MenuItem("해산물파스타", 35000, Category.MAIN));
        MENU_ITEMS.put("크리스마스파스타", new MenuItem("크리스마스파스타", 25000, Category.MAIN));
        MENU_ITEMS.put("양송이수프", new MenuItem("양송이수프", 6000, Category.APPETIZER));
        MENU_ITEMS.put("타파스", new MenuItem("타파스", 5500, Category.APPETIZER));
        MENU_ITEMS.put("시저샐러드", new MenuItem("시저샐러드", 8000, Category.APPETIZER));
        MENU_ITEMS.put("초코케이크", new MenuItem("초코케이크", 15000, Category.DESSERT));
        MENU_ITEMS.put("아이스크림", new MenuItem("아이스크림", 5000, Category.DESSERT));
        MENU_ITEMS.put("제로콜라", new MenuItem("제로콜라", 3000, Category.DRINK));
        MENU_ITEMS.put("레드와인", new MenuItem("레드와인", 60000, Category.DRINK));
        MENU_ITEMS.put("샴페인", new MenuItem("샴페인", 25000, Category.DRINK));
    }
}
