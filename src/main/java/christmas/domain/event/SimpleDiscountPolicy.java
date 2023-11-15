package christmas.domain.event;

import christmas.domain.order.Category;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenuItem;

import java.util.List;

import static christmas.domain.Constant.*;

public class SimpleDiscountPolicy implements DiscountPolicy{
    @Override
    public Event calculateDiscount(Order order) {
        int orderDate = order.getOrderDate();
        int giftEventNum = order.calculateGiftEventNum();
        DiscountType discountType = Calendar.getDiscountType(orderDate);
        boolean isStarDay = Calendar.isStarDay(orderDate);
        List<OrderMenuItem> orderMenuItems = order.getOrderMenuItems();

        int christmasDiscount = calculateChristmasDiscount(orderDate);
        int weekDayDiscount = calculateWeekDayDiscount(discountType, orderMenuItems);
        int weekendDiscount = calculateWeekendDiscount(discountType, orderMenuItems);
        int starDiscount = calculateStarDiscount(isStarDay);
        int giftEventBenefit = calculateGiftEventBenefit(giftEventNum);

        return new Event(christmasDiscount, weekDayDiscount, weekendDiscount, starDiscount, giftEventBenefit);
    }

    private int calculateChristmasDiscount(int orderDate) {
        if (orderDate > CHRISTMAS_DATE) {
            return 0;
        }
        return CHRISTMAS_DISCOUNT_BASE_PRICE + (orderDate - 1) * CHRISTMAS_DISCOUNT_INCREASE_PRICE;
    }

    private int calculateWeekDayDiscount(DiscountType discountType, List<OrderMenuItem> orderMenuItems) {
        if (discountType == DiscountType.WEEK) {
            return orderMenuItems.stream()
                    .filter(orderMenuItem -> orderMenuItem.getMenuItem().getCategory() == Category.DESSERT)
                    .mapToInt(orderMenuItem -> orderMenuItem.getQuantity() * DAY_DISCOUNT_PRICE)
                    .sum();
        }
        return 0;
    }

    private int calculateWeekendDiscount(DiscountType discountType, List<OrderMenuItem> orderMenuItems) {
        if (discountType == DiscountType.WEEKEND) {
            return orderMenuItems.stream()
                    .filter(orderMenuItem -> orderMenuItem.getMenuItem().getCategory() == Category.MAIN)
                    .mapToInt(orderMenuItem -> orderMenuItem.getQuantity() * DAY_DISCOUNT_PRICE)
                    .sum();
        }
        return 0;
    }

    private int calculateStarDiscount(boolean isStarDay) {
        if (isStarDay) return STAR_DAY_DISCOUNT_PRICE;
        return 0;
    }

    private int calculateGiftEventBenefit(int giftEventNum) {
        return giftEventNum * GIFT_EVENT_PRICE;
    }
}
