package pairmatching.controller;

import static pairmatching.controller.ConfigController.configCrews;

import java.util.List;
import pairmatching.domain.Crews;
import pairmatching.domain.constants.CourseLevelMission;
import pairmatching.domain.constants.Level;
import pairmatching.exception.CustomException;
import pairmatching.exception.ErrorMessage;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class MatchingController {
    private final InputView inputView;
    private final OutputView outputView;
    private final Crews crews;

    public MatchingController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.crews = configCrews();
    }

    public void run() {
        while (true) {
            String functionType = inputView.getFunctionType();
            if (functionType.equals("Q")) {
                break;
            }
            if (functionType.equals("3")) {
                reset(functionType);
                continue;
            }
            matchOrSelect(functionType);
        }
    }

    private void matchOrSelect(String functionType) {
        try {
            List<String> clm = inputView.getCourseLevelMission();
            CourseLevelMission CLM = CourseLevelMission.from(clm.get(0), clm.get(1), clm.get(2));
            match(functionType, CLM);
            select(functionType, CLM);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void select(String functionType, CourseLevelMission CLM) {  // 조회
        if (!functionType.equals("2")) {
            return;
        }
        outputView.printCourseLevelMission(); // TODO: 이거 제대로 출력하기
        if (CLM.isMissionEmptyMatching()) { // 없을 시 "[ERROR] 매칭 이력이 없습니다." -> 다시 CLM
            throw CustomException.from(ErrorMessage.EMPTY_MATCHING_HISTORY);
        }
        outputView.printMatchingResult(CLM.getMissionMatching()); // 있을 시 보여주고 return
    }

    public void reset(String functionType) {  // 초기화
        if (!functionType.equals("3")) {
            return;
        }
        Level.resetEveryMatchings(); // 모든 미션 초기화
    }

    public void match(String functionType, CourseLevelMission CLM) { // 매칭
        if (!functionType.equals("1")) {
            return;
        }
        outputView.printCourseLevelMission(); // TODO: 이거 제대로 출력하기
        if (!CLM.isMissionEmptyMatching() && !rematch()) { // 이전 매칭 기록 존재 no-> 다시 input
            List<String> clm = inputView.getCourseLevelMission();
            CourseLevelMission newCLM = CourseLevelMission.from(clm.get(0), clm.get(1), clm.get(2));
            match(functionType, newCLM);
        }
        // 처음 매칭 or 재매칭 -> 매칭하고 return
    }

    private boolean rematch() {
        String input = inputView.getRematch();
        return input.equals("네");
    }
}
