package christmas.domain.order;

public class Menu {

    public static final MenuItem MUSHROOM_SOUP = new MenuItem("양송이수프", 6000, Category.APPETIZER);
    public static final MenuItem  TAPAS = new MenuItem("타파스", 5500, Category.APPETIZER);
    public static final MenuItem CAESAR_SALAD = new MenuItem("시저샐러드", 8000, Category.APPETIZER);

    public static final MenuItem T_BONE_STEAK = new MenuItem("티본스테이크", 55000, Category.MAIN);
    public static final MenuItem BBQ_LIBS = new MenuItem("바비큐립", 54000, Category.MAIN);
    public static final MenuItem SEAFOOD_PASTA = new MenuItem("해산물파스타", 35000, Category.MAIN);
    public static final MenuItem CHRISTMAS_PASTA = new MenuItem("크리스마스파스타", 25000, Category.MAIN);

    public static final MenuItem CHOCO_CAKE = new MenuItem("초코케이크", 15000, Category.DESSERT);
    public static final MenuItem ICE_CREAM = new MenuItem("아이스크림", 5000, Category.DESSERT);

    public static final MenuItem ZERO_COKE = new MenuItem("제로콜라", 3000, Category.DRINK);
    public static final MenuItem RED_WINE = new MenuItem("레드와인", 60000, Category.DRINK);
    public static final MenuItem CHAMPAGNE = new MenuItem("샴페인", 25000, Category.DRINK);
}
