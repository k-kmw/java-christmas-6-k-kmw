package christmas.domain.event;

public class Event {

    private int christmasDiscount;
    private int weekDayDiscount;
    private int weekendDiscount;
    private int starDiscount;
    private int giftEvent;

    public Event(int christmasDiscount, int weekDayDiscount, int weekendDiscount, int starDiscount, int giftEvent) {
        this.christmasDiscount = christmasDiscount;
        this.weekDayDiscount = weekDayDiscount;
        this.weekendDiscount = weekendDiscount;
        this.starDiscount = starDiscount;
        this.giftEvent = giftEvent;
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

    public int getGiftEvent() {
        return giftEvent;
    }

    public int getStarDiscount() {
        return starDiscount;
    }

    public int calculateTotalBenefit() {
        return christmasDiscount + weekDayDiscount + weekendDiscount + starDiscount + giftEvent;
    }

    public EventBadge getEventBadge() {
        int totalBenefit = calculateTotalBenefit();
        if (totalBenefit >= 20000) {
            return EventBadge.SANTA;
        }
        else if (totalBenefit >= 10000) {
            return EventBadge.TREE;
        }
        return EventBadge.STAR;
    }
}
