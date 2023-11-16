package christmas.domain.event;

import static christmas.domain.Constant.*;

public class Calendar {

    public static DiscountType getDiscountType(int orderDate) {
        int remainderOfDate = orderDate % DAYS_A_WEEK;
        if (remainderOfDate == FRIDAY_REMAINDER || remainderOfDate == SATURDAY_REMAINDER)
            return DiscountType.WEEKEND;
        return DiscountType.WEEK;
    }

    public static boolean isStarDay(int orderDate) {
        int remainderOfDate = orderDate % DAYS_A_WEEK;
        return remainderOfDate == STAR_DAY_REMAINDER || orderDate == CHRISTMAS_DATE;
    }
}
