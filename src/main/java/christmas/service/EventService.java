package christmas.service;

import christmas.domain.event.DiscountPolicy;
import christmas.domain.event.SimpleDiscountPolicy;
import christmas.domain.event.Event;
import christmas.domain.event.EventPlanner;
import christmas.domain.order.Order;

public class EventService {
    public Event getEventInfo(Order order) {

        DiscountPolicy simpleDiscountPolicy = new SimpleDiscountPolicy();
        EventPlanner eventPlanner = new EventPlanner(simpleDiscountPolicy);

        return eventPlanner.event(order);
    }
}
