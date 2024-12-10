package pairmatching.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.exception.CustomException;
import pairmatching.exception.ErrorMessage;

public class InputView {
    private final String FUNCTION_SELECTION_INPUT_GUIDE = "기능을 선택하세요.\n"
            + "1. 페어 매칭\n"
            + "2. 페어 조회\n"
            + "3. 페어 초기화\n"
            + "Q. 종료";
    private final String COURSE_LEVEL_MISSION_INPUT_GUIDE = "과정, 레벨, 미션을 선택하세요.\n"
            + "ex) 백엔드, 레벨1, 자동차경주";
    private final String REMATCHING_QUESTION = "매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n네 | 아니오";
    private final String YES = "네";
    private final String NO = "아니오";
    private final String MATCH = "1";
    private final String SELECT = "2";
    private final String RESET = "3";
    private final String QUIT = "Q";

    public String getFunctionType() {
        System.out.println(FUNCTION_SELECTION_INPUT_GUIDE);
        String input = readLine();
        validateFunctionType(input);
        return input;
    }

    public List<String> getCourseLevelMission() {
        System.out.println(COURSE_LEVEL_MISSION_INPUT_GUIDE);
        return Arrays.stream(readLine().split(", ", -1)).collect(Collectors.toList());
    }

    public String getRematch() {
        System.out.println(REMATCHING_QUESTION);
        String input = readLine();
        validateYesOrNo(input);
        return input;
    }

    private void validateYesOrNo(final String input) {
        if (!input.equals(YES) && !input.equals(NO)) {
            throw CustomException.from(ErrorMessage.UNVALID_ANSWER_INPUT);
        }
    }

    private void validateFunctionType(final String input) {
        if (!input.equals(MATCH) && !input.equals(SELECT) && !input.equals(RESET) && !input.equals(QUIT)) {
            throw CustomException.from(ErrorMessage.UNVALID_FUNCTION_TYPE);
        }
    }
}
