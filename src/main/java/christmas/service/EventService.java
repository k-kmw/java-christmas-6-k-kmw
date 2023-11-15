package christmas.service;

import christmas.domain.event.DiscountPolicy;
import christmas.domain.event.Event;
import christmas.domain.event.EventPlanner;
import christmas.domain.order.Order;

public class EventService {
    public Event getEventInfo(Order order) {

        DiscountPolicy discountPolicy = new DiscountPolicy();
        EventPlanner eventPlanner = new EventPlanner(discountPolicy);

        return eventPlanner.event(order);
    }
}
