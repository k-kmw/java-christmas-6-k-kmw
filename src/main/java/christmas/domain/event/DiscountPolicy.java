package christmas.domain.event;

import christmas.domain.order.Category;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenuItem;

import java.util.List;

public class DiscountPolicy {
    public Event calculateDiscount(Order order) {
        int orderDate = order.getOrderDate();
        boolean isGetGiftEvent = order.isGetGiftEvent();
        DiscountType discountType = Calendar.getDiscountType(orderDate);
        boolean isStarDay = Calendar.isStarDay(orderDate);
        List<OrderMenuItem> orderMenuItems = order.getOrderMenuItems();

        int christmasDiscount = getChristmasDiscount(orderDate);
        int weekDayDiscount = getWeekDayDiscount(discountType, orderMenuItems);
        int weekendDiscount = getWeekendDiscount(discountType, orderMenuItems);
        int starDiscount = getStarDiscount(isStarDay);
        int giftEvent = getGiftEvent(isGetGiftEvent);

        return new Event(christmasDiscount, weekDayDiscount, weekendDiscount, starDiscount, giftEvent);
    }

    private int getChristmasDiscount(int orderDate) {
        return 1000 + (orderDate-1) * 100;
    }

    private int getWeekDayDiscount(DiscountType discountType, List<OrderMenuItem> orderMenuItems) {
        if (discountType == DiscountType.WEEK) {
            int dessertCount = (int) orderMenuItems.stream().filter(orderMenuItem -> orderMenuItem.getMenuItem().getCategory() == Category.DESSERT).count();
            return dessertCount * 2023;
        }
        return 0;
    }

    private int getWeekendDiscount(DiscountType discountType, List<OrderMenuItem> orderMenuItems) {
        if (discountType == DiscountType.WEEKEND) {
            int mainMenuCount = (int) orderMenuItems.stream().filter(orderMenuItem -> orderMenuItem.getMenuItem().getCategory() == Category.MAIN).count();
            return mainMenuCount * 2023;
        }
        return 0;
    }

    private int getStarDiscount(boolean isStarDay) {
        if (isStarDay)  return 1000;
        return 0;
    }

    private int getGiftEvent(boolean isGetGiftEvent) {
        if (isGetGiftEvent) return 25000;
        return 0;
    }
}
