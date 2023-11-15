package christmas.domain.event;

import christmas.domain.order.Order;

import static christmas.domain.Constant.TO_GET_EVENT_MINIMUM_ORDER_PRICE;

public class Event {

    private final int christmasDiscount;
    private final int weekDayDiscount;
    private final int weekendDiscount;
    private final int starDiscount;
    private final int giftEventBenefit;

    private Event(int christmasDiscount, int weekDayDiscount, int weekendDiscount, int starDiscount, int giftEventBenefit) {
        this.christmasDiscount = christmasDiscount;
        this.weekDayDiscount = weekDayDiscount;
        this.weekendDiscount = weekendDiscount;
        this.starDiscount = starDiscount;
        this.giftEventBenefit = giftEventBenefit;
    }

    public int getChristmasDiscount() {
        return christmasDiscount;
    }

    public int getWeekDayDiscount() {
        return weekDayDiscount;
    }

    public int getWeekendDiscount() {
        return weekendDiscount;
    }

    public int getGiftEventBenefit() {
        return giftEventBenefit;
    }

    public int getStarDiscount() {
        return starDiscount;
    }

    // 정적 생성 메서드
    public static Event create(Order order, DiscountPolicy discountPolicy) {
        int totalPriceBeforeDiscount = order.calculateTotalPriceBeforeDiscount();
        if (totalPriceBeforeDiscount < TO_GET_EVENT_MINIMUM_ORDER_PRICE) {
            return new Event(0, 0,0, 0, 0);
        }

        int christmasDiscount = discountPolicy.calculateChristmasDiscount(order.getOrderDate());
        int weekDayDiscount = discountPolicy.calculateWeekDayDiscount(Calendar.getDiscountType(order.getOrderDate()), order.getOrderMenuItems());
        int weekendDiscount = discountPolicy.calculateWeekendDiscount(Calendar.getDiscountType(order.getOrderDate()), order.getOrderMenuItems());
        int starDiscount = discountPolicy.calculateStarDiscount(Calendar.isStarDay(order.getOrderDate()));
        int giftEventBenefit = discountPolicy.calculateGiftEventBenefit(order.calculateGiftEventNum());
        return new Event(christmasDiscount, weekDayDiscount, weekendDiscount, starDiscount, giftEventBenefit);
    }

    public int calculateTotalBenefit() {
        return christmasDiscount + weekDayDiscount + weekendDiscount + starDiscount + giftEventBenefit;
    }

    public EventBadge getEventBadge() {
        int totalBenefit = calculateTotalBenefit();
        return EventBadge.create(totalBenefit);
    }
}
