package pairmatching.exception;

public enum ErrorMessage {
    FILE_IO("파일 입출력에 이상이 있습니다."),
    UNVALID_FUNCTION_TYPE("기능 종류는 1,2,3,Q 중 하나로 입력하세요."),
    UNVALID_COURSE_NAME("존재하지 않는 코스 이름"),
    UNVALID_LEVEL_NAME("존재하지 않는 레벨 이름"),
    UNVALID_MISSION_NAME("존재하지 않는 미션 이름"),
    EMPTY_MATCHING_HISTORY("매칭 이력이 없습니다."),
    UNVALID_ANSWER_INPUT("네 혹은 아니오 로 대답해주세요"),
    UNVALID_CREW_NAME("유효하지 않은 크루");
    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
