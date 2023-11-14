package christmas.domain.event;

import christmas.domain.order.Order;

public class EventPlanner {
    private final DiscountPolicy discountPolicy;

    public EventPlanner(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    public Event event(Order order) {
        return discountPolicy.calculateDiscount(order);
    }
}
