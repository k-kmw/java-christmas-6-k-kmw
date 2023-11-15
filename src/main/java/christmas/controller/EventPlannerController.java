package christmas.controller;

import _core.exception.MyException;
import christmas.domain.event.Calendar;
import christmas.domain.event.Event;
import christmas.domain.order.Order;
import christmas.service.EventService;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Arrays;

import static christmas.domain.Constant.*;

public class EventPlannerController {
    private final InputView inputView;
    private final OrderService orderService;
    private final EventService eventService;
    private final OutputView outputView;

    public EventPlannerController(InputView inputView, OrderService orderService, EventService eventService, OutputView outputView) {
        this.inputView = inputView;
        this.orderService = orderService;
        this.eventService = eventService;
        this.outputView = outputView;
    }

    public void start() {
        outputView.printOrderStartMessage();
        int date = readValidateDate();
        String menus = readValidateMenu();
        outputView.printOrderEndMessage(date);
        Order order = orderService.createOrder(menus, date);
        outputView.printMenu(order);
        outputView.printTotalPriceBeforeDiscount(order);

        Event event = eventService.getEventInfo(order);
        outputView.printBenefit(event, Calendar.getDiscountType(order.getOrderDate()));
        outputView.printTotalPay(order, event);
        outputView.printEventBadge(event);
    }

    private int readValidateDate() {
        while (true) {
            try {
                String inputDate = inputView.readDate();
                validateDate(inputDate);
                return Integer.parseInt(inputDate);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void validateDate(String inputDate) {
        try {
            int date = Integer.parseInt(inputDate);
            if (date < MONTH_FIRST_DAY || date > MONTH_LAST_DAY) {
                throw new IllegalArgumentException(MyException.INVALID_DATE.getMessage());
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException(MyException.INVALID_DATE.getMessage());
        }
    }

    private String readValidateMenu() {
        while (true) {
            try {
                String menu = inputView.readMenus();
                validateMenu(menu);
                return menu;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void validateMenu(String menu) {
        String[] orderItems = menu.split(MENU_SEPARATOR);
        for (String orderItem : orderItems) {
            String[] menuAndQuantity = orderItem.split(MENU_QUANTITY_SEPARATOR);
            if (menuAndQuantity.length != 2) {
                throw new IllegalArgumentException(MyException.INVALID_ORDER.getMessage());
            }
            try {
                String menuName = menuAndQuantity[0];
                int quantity = Integer.parseInt(menuAndQuantity[1]);
                checkDuplicateMenu(orderItems, menuName);
                validateQuantity(quantity);
            } catch (NumberFormatException e) {
                throw new NumberFormatException(MyException.INVALID_ORDER.getMessage());
            }
        }
    }

    private void checkDuplicateMenu(String[] orderItems, String menuName) {
        if (Arrays.asList(orderItems).contains(menuName)) {
            throw new IllegalArgumentException(MyException.INVALID_ORDER.getMessage());
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException(MyException.INVALID_ORDER.getMessage());
    }
}
