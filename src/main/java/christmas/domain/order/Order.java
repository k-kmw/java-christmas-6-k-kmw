package christmas.domain.order;

import _core.exception.MyException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static christmas.domain.Constant.*;

public class Order {

    private final List<OrderMenuItem> orderMenuItems;
    private final int orderDate;

    public Order(String menus, int orderDate) {
        // 티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1
        this.orderMenuItems = new ArrayList<>();
        makeOrderMenuItems(menus);
        this.orderDate = orderDate;
    }

    public List<OrderMenuItem> getOrderMenuItems() {
        return orderMenuItems;
    }

    public int getOrderDate() {
        return orderDate;
    }

    public int calculateTotalPriceBeforeDiscount() {
        return orderMenuItems.stream().mapToInt(orderMenuItem -> orderMenuItem.getMenuItem().getPrice() * orderMenuItem.getQuantity()).sum();
    }

    public int calculateGiftEventNum() {
        return calculateTotalPriceBeforeDiscount() / GIFT_EVENT_PURCHASE_PRICE;
    }

    private void makeOrderMenuItems(String menus) {
        String[] orderItems = menus.split(MENU_SEPARATOR);
        Arrays.stream(orderItems)
                .map(orderItem -> orderItem.split(MENU_QUANTITY_SEPARATOR))
                .map(menuAndQuantity -> OrderMenuItem.create(menuAndQuantity[0], Integer.parseInt(menuAndQuantity[1])))
                .forEach(orderMenuItems::add);
        validateOrderMenuItems(orderMenuItems);
    }

    private void validateOrderMenuItems(List<OrderMenuItem> orderMenuItems) {
        checkOnlyDrinkOrder(orderMenuItems);
        checkOver20Num(orderMenuItems);
    }

    private void checkOnlyDrinkOrder(List<OrderMenuItem> orderMenuItems) {
        boolean isAllDrink = orderMenuItems.stream().allMatch(orderMenuItem -> orderMenuItem.getMenuItem().getCategory().equals(Category.DRINK));
        if (isAllDrink) {
            throw new IllegalArgumentException(MyException.INVALID_ORDER.getMessage());
        }
    }

    private void checkOver20Num(List<OrderMenuItem> orderMenuItems) {
        boolean isOver20 = orderMenuItems.stream().mapToInt(OrderMenuItem::getQuantity).sum() > 20;
        if (isOver20) {
            throw new IllegalArgumentException(MyException.INVALID_ORDER.getMessage());
        }
    }
}
