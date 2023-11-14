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
            case "티본스테이크" -> T_BONE_STEAK;
            case "바비큐립" -> BBQ_LIBS;
            case "해산물파스타" -> SEAFOOD_PASTA;
            case "크리스마스파스타" -> CHRISTMAS_PASTA;
            case "양송이수프" -> MUSHROOM_SOUP;
            case "타파스" -> TAPAS;
            case "시저샐러드" -> CAESAR_SALAD;
            case "초코케이크" -> CHOCO_CAKE;
            case "아이스크림" -> ICE_CREAM;
            case "제로콜라" -> ZERO_COKE;
            case "레드와인" -> RED_WINE;
            case "샴페인" -> CHAMPAGNE;
            default -> throw new IllegalArgumentException(MyException.INVALID_ORDER.getMessage());
        };
        return new OrderMenuItem(menuItem, quantity);
    }
}
