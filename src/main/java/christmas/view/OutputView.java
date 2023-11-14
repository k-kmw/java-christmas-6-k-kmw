package christmas.view;

import christmas.domain.Event;
import christmas.domain.Order;

public class OutputView {
    public void printMenu(Order order) {
        System.out.println("<주문 메뉴>");
    }

    public void printBeforeDiscountTotalPrice(Order order) {
    }

    public void printBenefit(Event event) {
    }

    public void printTotalPay(Order order, Event event) {
    }

    public void printEventBadge(Event event) {
    }
}
