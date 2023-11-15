package christmas.view;

import christmas.domain.event.DiscountType;
import christmas.domain.event.Event;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenuItem;

import java.text.DecimalFormat;

import static christmas.domain.Constant.GIFT_EVENT_PRICE;

public class OutputView {

    private final DecimalFormat df = new DecimalFormat("###,###");

    public void printOrderStartMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printOrderEndMessage(int orderDate) {
        System.out.println("12월 "+ orderDate + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }
    
    public void printMenu(Order order) {
        System.out.println("\n<주문 메뉴>");
        for (OrderMenuItem orderMenuItem : order.getOrderMenuItems()) {
            System.out.println(orderMenuItem.getMenuItem().getName() + " " + orderMenuItem.getQuantity() + "개");
        }
    }

    public void printTotalPriceBeforeDiscount(Order order) {
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(df.format(order.calculateTotalPriceBeforeDiscount()) + "원");
    }

    public void printBenefit(Event event, DiscountType discountType) {
        printEventList(event, discountType);
        System.out.println("\n<총혜택 금액>");
        System.out.println(df.format(-event.calculateTotalBenefit()) + "원");
    }

    private void printEventList(Event event, DiscountType discountType) {
        printGiftEventMenu(event.getGiftEventBenefit() / GIFT_EVENT_PRICE);
        printBenefitList(event, discountType);
    }

    private void printGiftEventMenu(int giftMenuNum) {
        System.out.println("\n<증정 메뉴>");
        if (giftMenuNum == 0) {
            System.out.println("없음");
        } else {
            System.out.println("샴페인 " + giftMenuNum);
        }
    }

    private void printBenefitList(Event event, DiscountType discountType) {
        System.out.println("\n<혜택 내역>");
        int totalBenefit = event.calculateTotalBenefit();
        if(totalBenefit == 0) {
            System.out.println("없음");
        } else {
            printBenefit("크리스마스 디데이 할인: ", event.getChristmasDiscount());
            if (discountType == DiscountType.WEEK) {
                printBenefit("평일 할인: ", event.getWeekDayDiscount());
            } else {
                printBenefit("주말 할인: ", event.getWeekendDiscount());
            }
            printBenefit("특별 할인: ", event.getStarDiscount());
            printBenefit("증정 이벤트: ", event.getGiftEventBenefit());
        }
    }

    private void printBenefit(String benefitName, int discountAmount) {
        if (discountAmount != 0) {
            System.out.println(benefitName + df.format(-discountAmount) + "원");
        }
    }

    public void printTotalPay(Order order, Event event) {
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(df.format(order.calculateTotalPriceBeforeDiscount() - (event.calculateTotalBenefit()-event.getGiftEventBenefit())) + "원");
    }

    public void printEventBadge(Event event) {
        System.out.println("\n<12월 이벤트 배지>");
        System.out.println(event.getEventBadge().getName());
    }
}
