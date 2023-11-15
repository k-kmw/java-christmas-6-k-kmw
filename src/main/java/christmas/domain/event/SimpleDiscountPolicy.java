package christmas.domain.event;

import christmas.domain.order.Category;
import christmas.domain.order.OrderMenuItem;

import java.util.List;

import static christmas.domain.Constant.*;

public class SimpleDiscountPolicy implements DiscountPolicy{

    public int calculateChristmasDiscount(int orderDate) {
        if (orderDate > CHRISTMAS_DATE) {
            return 0;
        }
        return CHRISTMAS_DISCOUNT_BASE_PRICE + (orderDate - 1) * CHRISTMAS_DISCOUNT_INCREASE_PRICE;
    }

    public int calculateWeekDayDiscount(DiscountType discountType, List<OrderMenuItem> orderMenuItems) {
        if (discountType == DiscountType.WEEK) {
            return orderMenuItems.stream()
                    .filter(orderMenuItem -> orderMenuItem.getMenuItem().getCategory() == Category.DESSERT)
                    .mapToInt(orderMenuItem -> orderMenuItem.getQuantity() * DAY_DISCOUNT_PRICE)
                    .sum();
        }
        return 0;
    }

    public int calculateWeekendDiscount(DiscountType discountType, List<OrderMenuItem> orderMenuItems) {
        if (discountType == DiscountType.WEEKEND) {
            return orderMenuItems.stream()
                    .filter(orderMenuItem -> orderMenuItem.getMenuItem().getCategory() == Category.MAIN)
                    .mapToInt(orderMenuItem -> orderMenuItem.getQuantity() * DAY_DISCOUNT_PRICE)
                    .sum();
        }
        return 0;
    }

    public int calculateStarDiscount(boolean isStarDay) {
        if (isStarDay) return STAR_DAY_DISCOUNT_PRICE;
        return 0;
    }

    public int calculateGiftEventBenefit(int giftEventNum) {
        return giftEventNum * GIFT_EVENT_PRICE;
    }
}
