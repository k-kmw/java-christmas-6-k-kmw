package christmas.order;

import christmas.domain.order.Menu;
import christmas.domain.order.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

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

    @DisplayName("할인 적용 전 총 주문 금액이 옳바르게 계산되어야 한다.")
    @Test
    public void calculateTotalPriceBeforeDiscountTest() {
        // when
        int totalPriceBeforeDiscount = order.calculateTotalPriceBeforeDiscount();
        int expected = Menu.T_BONE_STEAK.getPrice() + Menu.BBQ_LIBS.getPrice() + Menu.CHOCO_CAKE.getPrice() * 2 + Menu.ZERO_COKE.getPrice();

        // then
        assertThat(totalPriceBeforeDiscount).isEqualTo(expected);
    }

    @DisplayName("증정 이벤트 개수가 옳바르게 판단되어야 한다.")
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

    @DisplayName("메뉴의 개수가 1보다 작을 경우 예외가 발생해야 한다.")
    @Test
    public void LessThanOneOnMenuExceptionTest() {
        // given
        String menu = "티본스테이크-0,바비큐립-1,초코케이크-2,제로콜라-1";

        // then
        assertThatThrownBy(() -> new Order(menu, orderDate))
                .isInstanceOf(IllegalArgumentException.class);

        // given
        String menu2 = "티본스테이크--1,바비큐립-1,초코케이크-2,제로콜라-1";

        // then
        assertThatThrownBy(() -> new Order(menu2, orderDate))
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

    @DisplayName("중복 메뉴를 입력하면 예외가 발생한다.")
    @Test
    public void duplicateMenuExceptionTest() {
        // given
        String menu = "시저샐러드-1,시저샐러드-1";

        // then
        assertThatThrownBy(() -> new Order(menu, orderDate))
                .isInstanceOf(IllegalArgumentException.class);

        // given
        String menu2 = "제로콜라-1,제로콜라-1,티본스테이크-1";

        // then
        assertThatThrownBy(() -> new Order(menu2, orderDate))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("메뉴의 형식이 예시와 다른 경우 예외가 발생한다.")
    @Test
    public void invalidMenuFormatExceptionTest() {
        // given
        String menu = "시저샐러드--1";

        // then
        assertThatThrownBy(() -> new Order(menu, orderDate))
                .isInstanceOf(IllegalArgumentException.class);

        // given
        String menu2 = "제로콜라-1시저샐러드-3";

        // then
        assertThatThrownBy(() -> new Order(menu2, orderDate))
                .isInstanceOf(IllegalArgumentException.class);

        // given
        String menu3 = "제로콜라-1, 시저샐러드-3";

        // then
        assertThatThrownBy(() -> new Order(menu3, orderDate))
                .isInstanceOf(IllegalArgumentException.class);

        // given
        String menu4 = "제로콜라-1시저샐러드-3";

        // then
        assertThatThrownBy(() -> new Order(menu4, orderDate))
                .isInstanceOf(IllegalArgumentException.class);

        // given
        String menu5 = "abc";

        // then
        assertThatThrownBy(() -> new Order(menu5, orderDate))
                .isInstanceOf(IllegalArgumentException.class);

        // given
        String menu6 = ",,,,";

        // then
        assertThatThrownBy(() -> new Order(menu6, orderDate))
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
