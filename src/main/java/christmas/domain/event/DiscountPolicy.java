package christmas.domain.event;

import christmas.domain.order.Order;
import christmas.domain.order.OrderMenuItem;

import java.util.List;

public interface DiscountPolicy {
    int calculateChristmasDiscount(int orderDate);
    int calculateWeekDayDiscount(DiscountType discountType, List<OrderMenuItem> orderMenuItems);
    int calculateWeekendDiscount(DiscountType discountType, List<OrderMenuItem> orderMenuItems);
    int calculateStarDiscount(boolean isStarDay);
    int calculateGiftEventBenefit(int giftEventNum);
}
