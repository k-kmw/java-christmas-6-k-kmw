package christmas.order;

import christmas.domain.order.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static christmas.domain.order.Menu.MENU_ITEMS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrderTest {

    private String menu;
    private int orderDate;
    private Order order;

    @BeforeEach
    void setUp() {
        menu = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        orderDate = 3;
        order = new Order(menu, orderDate);
    }

    @DisplayName("주문이 정상적으로 생성되어야 한다.")
    @Test
    public void createOrderTest() {
        // when
        Order order = new Order(menu, orderDate);

        // then
        assertThat(order.getOrderMenuItems().size()).isEqualTo(menu.split(",").length);
        assertThat(order.getOrderMenuItems().get(0).getQuantity()).isEqualTo( 1);
        assertThat(order.getOrderMenuItems().get(2).getQuantity()).isEqualTo( 2);
        assertThat(order.getOrderDate()).isEqualTo(orderDate);
    }

    @DisplayName("할인 적용 전 총 주문 금액이 올바르게 계산되어야 한다.")
    @Test
    public void calculateTotalPriceBeforeDiscountTest() {
        // when
        int totalPriceBeforeDiscount = order.calculateTotalPriceBeforeDiscount();
        int expected = MENU_ITEMS.get("티본스테이크").getPrice() + MENU_ITEMS.get("바비큐립").getPrice() + MENU_ITEMS.get("초코케이크").getPrice() * 2 + MENU_ITEMS.get("제로콜라").getPrice();

        // then
        assertThat(totalPriceBeforeDiscount).isEqualTo(expected);
    }

    @DisplayName("증정 이벤트 개수가 올바르게 판단되어야 한다.")
    @Test
    public void isGetGiftEventTest() {
        // when
        int getGiftEventNum = order.calculateGiftEventNum();
        int expected = order.calculateTotalPriceBeforeDiscount() / 120000;

        // then
        assertThat(getGiftEventNum).isEqualTo(expected);
    }

    @DisplayName("메뉴판에 없는 메뉴를 입력하는 경우 예외가 발생해야 한다.")
    @Test
    public void NoMenuExceptionTest() {
        // given
        String menu = "스파게티-1,바비큐립-1,초코케이크-2,제로콜라-1";

        // then
        assertThatThrownBy(() -> new Order(menu, orderDate))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("음료만 주문하면 예외가 발생한다.")
    @Test
    public void OnlyDrinkOrderExceptionTest() {
        // given
        String menu = "제로콜라-1,레드와인-1,샴페인-1";

        // then
        assertThatThrownBy(() -> new Order(menu, orderDate))
                .isInstanceOf(IllegalArgumentException.class);

        // given
        String menu2 = "제로콜라-1";

        // then
        assertThatThrownBy(() -> new Order(menu2, orderDate))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("20개 이상의 메뉴를 한 번에 주문할 경우 예외가 발생한다.")
    @Test
    public void OrderOver20ExceptionTest() {
        // given
        String menu = "시저샐러드-10,제로콜라-8,티본스테이크-3";

        // then
        assertThatThrownBy(() -> new Order(menu, orderDate))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
