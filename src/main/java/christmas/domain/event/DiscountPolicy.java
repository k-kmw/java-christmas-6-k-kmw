package christmas.domain.event;

import christmas.domain.order.Order;

public interface DiscountPolicy {
    Event calculateDiscount(Order order);
}
