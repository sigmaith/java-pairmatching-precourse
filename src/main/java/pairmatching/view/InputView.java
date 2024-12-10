package pairmatching.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

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

    private void validateFunctionType(final String input) {
        if (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("Q")) {
            throw CustomException.from(ErrorMessage.UNVALID_FUNCTION_TYPE_EXCEPTION);
        }
    }
}
