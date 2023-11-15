package christmas.domain.order;

public class MenuItem {

    private final String name;
    private final int price;
    private final Category category;

    public MenuItem(String name, int price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }
}
