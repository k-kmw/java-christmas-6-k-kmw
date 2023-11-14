package christmas.domain.event;

public class Calendar {

    public static DiscountType getDiscountType(int orderDate) {
        int remainderOfDate = orderDate % 7;
        if (remainderOfDate == 1 || remainderOfDate == 2)
            return DiscountType.WEEKEND;
        return DiscountType.WEEK;
    }

    public static boolean isStarDay(int orderDate) {
        int remainderOfDate = orderDate % 7;
        return remainderOfDate == 3 || orderDate == 25;
    }
}
