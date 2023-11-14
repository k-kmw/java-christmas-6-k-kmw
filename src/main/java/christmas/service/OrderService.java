package christmas.service;

import christmas.domain.order.Order;

public class OrderService {
    public Order createOrder(String menus, int date) {
        return new Order(menus, date);
    }
}
