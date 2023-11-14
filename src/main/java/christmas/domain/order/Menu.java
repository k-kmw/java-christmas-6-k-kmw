package christmas.domain.order;

public class Menu {

    public static final MenuItem APPETIZER_1 = new MenuItem("양송이수프", 6000, Category.APPETIZER);
    public static final MenuItem APPETIZER_2 = new MenuItem("타파스", 5500, Category.APPETIZER);
    public static final MenuItem APPETIZER_3 = new MenuItem("시저샐러드", 8000, Category.APPETIZER);

    public static final MenuItem MAIN_1 = new MenuItem("티본스테이크", 55000, Category.MAIN);
    public static final MenuItem MAIN_2 = new MenuItem("바비큐립", 54000, Category.MAIN);
    public static final MenuItem MAIN_3 = new MenuItem("해산물파스타", 35000, Category.MAIN);
    public static final MenuItem MAIN_4 = new MenuItem("크리스마스파스타", 25000, Category.MAIN);

    public static final MenuItem DESSERT_1 = new MenuItem("초코케이크", 15000, Category.DESSERT);
    public static final MenuItem DESSERT_2 = new MenuItem("아이스크림", 5000, Category.DESSERT);

    public static final MenuItem DRINK_1 = new MenuItem("제로콜라", 3000, Category.DRINK);
    public static final MenuItem DRINK_2 = new MenuItem("레드와인", 60000, Category.DRINK);
    public static final MenuItem DRINK_3 = new MenuItem("샴페인", 25000, Category.DRINK);
}
