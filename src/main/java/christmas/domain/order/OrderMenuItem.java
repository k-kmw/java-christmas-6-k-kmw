package christmas.domain.order;

import _core.exception.MyException;

import static christmas.domain.order.Menu.*;

public class OrderMenuItem {

    private final MenuItem menuItem;
    private final int quantity;

    private OrderMenuItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public static OrderMenuItem create(String menuName, int quantity) {
        MenuItem menuItem = MENU_ITEMS.get(menuName);
        if (menuItem == null) {
            throw new IllegalArgumentException(MyException.INVALID_ORDER.getMessage());
        }
        return new OrderMenuItem(menuItem, quantity);
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }
}
