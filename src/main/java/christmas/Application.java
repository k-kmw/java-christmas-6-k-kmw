package christmas;

import christmas.controller.EventPlannerController;
import christmas.service.EventService;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        EventPlannerController eventPlannerController = new EventPlannerController(new InputView(), new OrderService(), new EventService(), new OutputView());
        eventPlannerController.start();
    }
}
