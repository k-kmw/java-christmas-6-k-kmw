package christmas.domain.order;

import _core.exception.MyException;

import static christmas.domain.order.Menu.*;

public class OrderMenuItem {

    private MenuItem menuItem;
    private int quantity;

    public OrderMenuItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public static OrderMenuItem create(String menuName, int quantity) {
        MenuItem menuItem = switch (menuName) {
            case "티본스테이크" -> MAIN_1;
            case "바비큐립" -> MAIN_2;
            case "해산물파스타" -> MAIN_3;
            case "크리스마스파스타" -> MAIN_4;
            case "양송이수프" -> APPETIZER_1;
            case "타파스" -> APPETIZER_2;
            case "시저샐러드" -> APPETIZER_3;
            case "초코케이크" -> DESSERT_1;
            case "아이스크림" -> DESSERT_2;
            case "제로콜라" -> DRINK_1;
            case "레드와인" -> DRINK_2;
            case "샴페인" -> DRINK_3;
            default -> throw new IllegalArgumentException(MyException.INVALID_ORDER.getMessage());
        };
        return new OrderMenuItem(menuItem, quantity);
    }
}
