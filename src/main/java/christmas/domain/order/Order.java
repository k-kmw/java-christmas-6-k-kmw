package christmas.domain.order;

import _core.exception.MyException;

import java.util.ArrayList;
import java.util.List;

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

    public boolean isGetGiftEvent() {
        return calculateTotalPriceBeforeDiscount() >= 120000;
    }

    private void makeOrderMenuItems(String menus) {
        String[] orderItems = menus.split(",");
        for (String orderItem : orderItems) {
            String[] menuAndCount = orderItem.split("-");
            if (menuAndCount.length != 2) {
                throw new IllegalArgumentException(MyException.INVALID_ORDER.getMessage());
            }
            try {
                String menuName = menuAndCount[0];
                int quantity = Integer.parseInt(menuAndCount[1]);
                checkDuplicateMenu(orderMenuItems, menuName);
                OrderMenuItem orderMenuItem = OrderMenuItem.create(menuName, quantity);
                orderMenuItems.add(orderMenuItem);
            } catch (NumberFormatException e) {
                throw new NumberFormatException(MyException.INVALID_ORDER.getMessage());
            }
        }
    }

    private void checkDuplicateMenu(List<OrderMenuItem> orderMenuItems, String menuName) {
        if (orderMenuItems.stream().anyMatch(orderMenuItem -> orderMenuItem.getMenuItem().getName().equals(menuName)))
            throw new IllegalArgumentException(MyException.INVALID_ORDER.getMessage());
    }
}
