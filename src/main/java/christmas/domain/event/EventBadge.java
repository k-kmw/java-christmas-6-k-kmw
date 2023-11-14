package christmas.domain.event;

public enum EventBadge {
    STAR("별", 5000), TREE("트리", 10000), SANTA("산타", 20000);

    private String name;
    private int basePrice;

    EventBadge(String name, int basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    public String getName() {
        return name;
    }

    public int getBasePrice() {
        return basePrice;
    }
}
