package christmas.domain.event;

public enum EventBadge {
    NONE("없음", 0), STAR("별", 5000), TREE("트리", 10000), SANTA("산타", 20000);

    private final String name;
    private final int basePrice;

    EventBadge(String name, int basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    // 정적 생성 메서드
    public static EventBadge create(int totalBenefit) {
        if (totalBenefit >= 20000) {
            return EventBadge.SANTA;
        } else if (totalBenefit >= 10000) {
            return EventBadge.TREE;
        } else if (totalBenefit >= 5000) {
            return EventBadge.STAR;
        }
        return EventBadge.NONE;
    }

    public String getName() {
        return name;
    }

    public int getBasePrice() {
        return basePrice;
    }
}
