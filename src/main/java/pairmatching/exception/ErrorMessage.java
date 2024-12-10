package pairmatching.exception;

public enum ErrorMessage {
    FILE_IO_EXCEPTION("파일 입출력에 이상이 있습니다");
    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
