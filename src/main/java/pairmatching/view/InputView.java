package pairmatching.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.exception.CustomException;
import pairmatching.exception.ErrorMessage;

public class InputView {

    public String getFunctionType() {
        System.out.println("기능을 선택하세요.\n"
                + "1. 페어 매칭\n"
                + "2. 페어 조회\n"
                + "3. 페어 초기화\n"
                + "Q. 종료");
        String input = readLine();
        validateFunctionType(input);
        return input;
    }

    public List<String> getCourseLevelMission() {
        System.out.println("과정, 레벨, 미션을 선택하세요.\n"
                + "ex) 백엔드, 레벨1, 자동차경주");
        return Arrays.stream(readLine().split(", ", -1)).collect(Collectors.toList());
    }

    public String getRematch() {
        System.out.println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n네 | 아니오");
        String input = readLine();
        validateYesOrNo(input);
        return input;
    }

    private void validateYesOrNo(final String input) {
        if (!input.equals("네") && !input.equals("아니오")) {
            throw CustomException.from(ErrorMessage.UNVALID_ANSWER_INPUT);
        }
    }

    private void validateFunctionType(final String input) {
        if (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("Q")) {
            throw CustomException.from(ErrorMessage.UNVALID_FUNCTION_TYPE);
        }
    }
}
