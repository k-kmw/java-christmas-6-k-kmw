package christmas.view;

import christmas.domain.event.DiscountType;
import christmas.domain.event.Event;
import christmas.domain.order.Order;
import christmas.domain.order.OrderMenuItem;

import java.text.DecimalFormat;

public class OutputView {

    private DecimalFormat df = new DecimalFormat("###,###");

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
        System.out.println("\n<증정 메뉴>");
        int giftMenuNum = event.getGiftEventBenefit() / 25000;
        if (giftMenuNum == 0) {
            System.out.println("없음");
        } else {
            System.out.println("샴페인 " + giftMenuNum);
        }
        System.out.println("\n<혜택 내역>");
        int totalBenefit = event.calculateTotalBenefit();
        if(totalBenefit == 0) {
            System.out.println("없음");
        } else {
            System.out.println("크리스마스 디데이 할인: " + df.format(-event.getChristmasDiscount()) + "원");
            if (discountType == DiscountType.WEEK) {
                System.out.println("평일 할인: " + df.format(-event.getWeekDayDiscount()) + "원");
            } else {
                System.out.println("주말 할인: " + df.format(-event.getWeekendDiscount()) + "원");
            }
            System.out.println("특별 할인: " + df.format(-event.getStarDiscount()) + "원");
            System.out.println("증정 이벤트: " + df.format(-event.getGiftEventBenefit()) + "원");
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
