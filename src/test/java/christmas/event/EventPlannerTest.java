package christmas.event;

import christmas.domain.event.SimpleDiscountPolicy;
import christmas.domain.event.Event;
import christmas.domain.event.EventBadge;
import christmas.domain.event.EventPlanner;
import christmas.domain.order.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static christmas.domain.event.EventBadge.*;
import static org.assertj.core.api.Assertions.*;

public class EventPlannerTest {

    private String menu;
    private int orderDate;
    private Order order;
    private EventPlanner eventPlanner;

    @BeforeEach
    void setUp() {
        menu = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1"; // 142,000원
        orderDate = 3;
        order = new Order(menu, orderDate);
        eventPlanner = new EventPlanner(new SimpleDiscountPolicy());
    }

    @DisplayName("주문에 맞게 이벤트가 적절히 적용 되어야 한다.")
    @Test
    void evenTest() {
        // when
        Event event = eventPlanner.event(order);

        // then
        assertThat(event.getEventBadge()).isEqualTo(EventBadge.SANTA);
        assertThat(event.getGiftEventBenefit()).isEqualTo(25000);
        assertThat(event.getChristmasDiscount()).isEqualTo(1200);
        assertThat(event.getWeekDayDiscount()).isEqualTo(4046);
        assertThat(event.getWeekendDiscount()).isEqualTo(0);
        assertThat(event.getStarDiscount()).isEqualTo(1000);
    }

    @DisplayName("달력에 별이 있으면 총 주문 금액에서 1000원 할인한다.")
    @ParameterizedTest
    @CsvSource({
            "1, 0",
            "3, 1000",
            "10, 1000",
            "17, 1000",
            "24, 1000",
            "25, 1000",
            "31, 1000"
    })
    void weekendDiscountTest(int orderDate, int expected) {
        // given
        Order order = new Order(menu, orderDate);

        // when
        Event event = eventPlanner.event(order);

        // then
        assertThat(event.getStarDiscount()).isEqualTo(expected);
    }

    @DisplayName("주말에는 메인 메뉴 하나 당 할인이 적용된다")
    @ParameterizedTest
    @MethodSource("weekendDiscountProvider")
    void weekendDiscountTest(String menu, int orderDate, int expected) {
        // given
        Order order = new Order(menu, orderDate);

        // when
        Event event = eventPlanner.event(order);

        // then
        assertThat(event.getWeekendDiscount()).isEqualTo(expected);
    }

    private static Stream<Arguments> weekendDiscountProvider() {
        return Stream.of(
                Arguments.of( "티본스테이크-1,양송이수프-1", 26 ,0),
                Arguments.of( "티본스테이크-1,양송이수프-1,초코케이크-1", 1, 2023),
                Arguments.of( "티본스테이크-2,양송이수프-1,초코케이크-1", 1, 2023*2),
                Arguments.of( "티본스테이크-1,해산물파스타-1,양송이수프-1,초코케이크-3", 1 ,2023*2),
                Arguments.of( "티본스테이크-1,해산물파스타-1,바비큐립-2,", 1 ,2023*4)
        );
    }

    @DisplayName("평일에는 디저트 메뉴 하나 당 할인이 적용된다")
    @ParameterizedTest
    @MethodSource("weekDayDiscountProvider")
    void weekDayDiscountTest(String menu, int orderDate, int expected) {
        // given
        Order order = new Order(menu, orderDate);

        // when
        Event event = eventPlanner.event(order);

        // then
        assertThat(event.getWeekDayDiscount()).isEqualTo(expected);
    }

    private static Stream<Arguments> weekDayDiscountProvider() {
        return Stream.of(
                Arguments.of( "티본스테이크-1,양송이수프-1,초코케이크-1", 1, 0),
                Arguments.of( "티본스테이크-1,양송이수프-1", 26 ,0),
                Arguments.of( "티본스테이크-1,양송이수프-1,초코케이크-1", 26 ,2023),
                Arguments.of( "티본스테이크-1,양송이수프-1,초코케이크-3", 26 ,2023*3),
                Arguments.of( "티본스테이크-1,양송이수프-1,초코케이크-1,아이스크림-1", 26 ,2023*2)
        );
    }

    @DisplayName("날짜에 맞게 크리스마스 디데이 할인이 된다.")
    @ParameterizedTest
    @MethodSource("christmasEventTestProvider")
    void christmasEventTest(String menu, int orderDate, int expected) {
        // given
        Order order = new Order(menu, orderDate);

        // when
        Event event = eventPlanner.event(order);

        // then
        assertThat(event.getChristmasDiscount()).isEqualTo(expected);
    }

    private static Stream<Arguments> christmasEventTestProvider() {
        return Stream.of(
                Arguments.of( "티본스테이크-1,양송이수프-1,초코케이크-1", 1, 1000),
                Arguments.of( "티본스테이크-1,양송이수프-1,초코케이크-1", 8 ,1700),
                Arguments.of( "티본스테이크-1,양송이수프-1,초코케이크-1", 16 ,2500),
                Arguments.of( "티본스테이크-1,양송이수프-1,초코케이크-1", 23 ,3200),
                Arguments.of( "티본스테이크-1,양송이수프-1,초코케이크-1", 25 ,3400),
                Arguments.of( "티본스테이크-1,양송이수프-1,초코케이크-1", 26 ,0),
                Arguments.of( "티본스테이크-1,양송이수프-1,초코케이크-1", 31 ,0)
        );
    }

    @DisplayName("주문 금액에 맞게 증정 이벤트가 제공되어야 한다.")
    @ParameterizedTest
    @MethodSource("giftEventTestProvider")
    void giftEventTest(String menu, int orderDate, int expected) {
        // given
        Order order = new Order(menu, orderDate);

        // when
        Event event = eventPlanner.event(order);

        // then
        assertThat(event.getGiftEventBenefit()).isEqualTo(expected);
    }

    private static Stream<Arguments> giftEventTestProvider() {
        return Stream.of(
                Arguments.of( "티본스테이크-1,양송이수프-1,초코케이크-1", 25, 0),
                Arguments.of( "티본스테이크-1,바비큐립-2,제로콜라-1,초코케이크-2,아이스크림-3", 26 ,25000),
                Arguments.of( "티본스테이크-2,바비큐립-2,초코케이크-2,제로콜라-1", 3 ,50000)
        );
    }

    @DisplayName("이벤트 배지가 요구사항에 맞게 적용되어야 한다.")
    @ParameterizedTest
    @MethodSource("eventBadgeTestProvider")
    void eventBadgeTest(String menu, int orderDate, EventBadge expected) {
        // given
        Order order = new Order(menu, orderDate);

        // when
        Event event = eventPlanner.event(order);

        // then
        assertThat(event.getEventBadge()).isEqualTo(expected);
    }

    private static Stream<Arguments> eventBadgeTestProvider() {
        return Stream.of(
                Arguments.of( "타파스-1,제로콜라-1", 26 ,NONE),
                Arguments.of( "티본스테이크-1,양송이수프-1,초코케이크-1", 25, STAR),
                Arguments.of( "티본스테이크-1,제로콜라-1,초코케이크-2,아이스크림-3", 26 ,TREE),
                Arguments.of( "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1", 3 ,SANTA)
        );
    }
}
