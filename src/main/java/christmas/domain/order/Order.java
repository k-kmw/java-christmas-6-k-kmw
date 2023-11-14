package christmas.domain.order;

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
                throw new IllegalArgumentException("메뉴명-수량 형식으로 입력해야 합니다.");
            }
            String menuName = menuAndCount[0];
            int quantity = Integer.parseInt(menuAndCount[1]);
            OrderMenuItem orderMenuItem = OrderMenuItem.create(menuName, quantity);
            orderMenuItems.add(orderMenuItem);
        }
    }
}
