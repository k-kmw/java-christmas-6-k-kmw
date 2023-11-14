package christmas.controller;

import christmas.domain.event.Event;
import christmas.domain.order.Order;
import christmas.service.EventService;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;

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
        int date = inputView.readDate();
        String menus = inputView.readMenus();
        Order order = orderService.createOrder(menus, date);
        outputView.printMenu(order);
        outputView.printTotalPriceBeforeDiscount(order);

        Event event = eventService.getEventInfo(order);
        outputView.printBenefit(event);
        outputView.printTotalPay(order, event);
        outputView.printEventBadge(event);
    }
}
