package pairmatching.exception;

public enum ErrorMessage {
    FILE_IO_EXCEPTION("파일 입출력에 이상이 있습니다."),
    UNVALID_FUNCTION_TYPE_EXCEPTION("기능 종류는 1,2,3,Q 중 하나로 입력하세요.");
    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
