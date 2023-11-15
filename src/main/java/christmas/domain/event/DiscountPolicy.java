package christmas.domain.event;

import christmas.domain.order.Category;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenuItem;

import java.util.List;

import static christmas.domain.Constant.*;

public class DiscountPolicy {
    public Event calculateDiscount(Order order) {
        int orderDate = order.getOrderDate();
        int giftEventNum = order.calculateGiftEventNum();
        DiscountType discountType = Calendar.getDiscountType(orderDate);
        boolean isStarDay = Calendar.isStarDay(orderDate);
        List<OrderMenuItem> orderMenuItems = order.getOrderMenuItems();

        int christmasDiscount = getChristmasDiscount(orderDate);
        int weekDayDiscount = getWeekDayDiscount(discountType, orderMenuItems);
        int weekendDiscount = getWeekendDiscount(discountType, orderMenuItems);
        int starDiscount = getStarDiscount(isStarDay);
        int giftEventBenefit = getGiftEventBenefit(giftEventNum);

        return new Event(christmasDiscount, weekDayDiscount, weekendDiscount, starDiscount, giftEventBenefit);
    }

    private int getChristmasDiscount(int orderDate) {
        if (orderDate > CHRISTMAS_DATE) {
            return 0;
        }
        return CHRISTMAS_DISCOUNT_BASE_PRICE + (orderDate - 1) * CHRISTMAS_DISCOUNT_INCREASE_PRICE;
    }

    private int getWeekDayDiscount(DiscountType discountType, List<OrderMenuItem> orderMenuItems) {
        if (discountType == DiscountType.WEEK) {
            return orderMenuItems.stream()
                    .filter(orderMenuItem -> orderMenuItem.getMenuItem().getCategory() == Category.DESSERT)
                    .mapToInt(orderMenuItem -> orderMenuItem.getQuantity() * DAY_DISCOUNT_PRICE)
                    .sum();
        }
        return 0;
    }

    private int getWeekendDiscount(DiscountType discountType, List<OrderMenuItem> orderMenuItems) {
        if (discountType == DiscountType.WEEKEND) {
            return orderMenuItems.stream()
                    .filter(orderMenuItem -> orderMenuItem.getMenuItem().getCategory() == Category.MAIN)
                    .mapToInt(orderMenuItem -> orderMenuItem.getQuantity() * DAY_DISCOUNT_PRICE)
                    .sum();
        }
        return 0;
    }

    private int getStarDiscount(boolean isStarDay) {
        if (isStarDay) return STAR_DAY_DISCOUNT_PRICE;
        return 0;
    }

    private int getGiftEventBenefit(int giftEventNum) {
        return giftEventNum * GIFT_EVENT_PRICE;
    }
}
