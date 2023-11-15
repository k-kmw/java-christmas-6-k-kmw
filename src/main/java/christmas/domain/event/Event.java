package christmas.domain.event;

public class Event {

    private final int christmasDiscount;
    private final int weekDayDiscount;
    private final int weekendDiscount;
    private final int starDiscount;
    private final int giftEventBenefit;

    public Event(int christmasDiscount, int weekDayDiscount, int weekendDiscount, int starDiscount, int giftEventBenefit) {
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

    public int calculateTotalBenefit() {
        return christmasDiscount + weekDayDiscount + weekendDiscount + starDiscount + giftEventBenefit;
    }

    public EventBadge getEventBadge() {
        int totalBenefit = calculateTotalBenefit();
        return EventBadge.create(totalBenefit);
    }
}
