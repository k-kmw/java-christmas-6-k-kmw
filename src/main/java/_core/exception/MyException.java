package _core.exception;

public enum MyException {
    INVALID_ORDER("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_DATE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_FORMAT("[ERROR] 유효하지 않은 형식입니다.");


    private final String message;

    MyException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
