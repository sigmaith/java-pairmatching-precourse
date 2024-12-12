package pairmatching.controller;

import static pairmatching.controller.ConfigController.configCrews;

import java.util.List;
import java.util.function.Supplier;
import pairmatching.domain.CourseLevelMission;
import pairmatching.domain.Crews;
import pairmatching.domain.MissionManager;
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
        MissionManager.initialize();
    }

    public void run() {
        while (true) {
            String functionType = retry(inputView::getFunctionType);
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
            outputView.printCourseLevelMission();
            CourseLevelMission CLM = getCourseLevelMission();
            match(functionType, CLM);
            select(functionType, CLM);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private CourseLevelMission getCourseLevelMission() {
        return retry(() -> {
            List<String> clm = inputView.getCourseLevelMission();
            CourseLevelMission CLM = CourseLevelMission.from(clm.get(0), clm.get(1), clm.get(2));
            return CLM;
        });
    }

    public void select(String functionType, CourseLevelMission CLM) {  // 조회
        if (!functionType.equals("2")) {
            return;
        }
        if (CLM.isMissionEmptyMatching()) { // 없을 시 "[ERROR] 매칭 이력이 없습니다." -> 다시 CLM
            throw CustomException.from(ErrorMessage.EMPTY_MATCHING_HISTORY);
        }
        outputView.printMatchingResult(CLM.getMissionMatching()); // 있을 시 보여주고 return
    }

    public void reset(String functionType) {  // 초기화
        if (!functionType.equals("3")) {
            return;
        }
        MissionManager.resetAllMatchings(); // 모든 미션 초기화
        outputView.printReset();
    }

    public void match(String functionType, CourseLevelMission CLM) { // 매칭
        if (!functionType.equals("1")) {
            return;
        }
        if (!CLM.isMissionEmptyMatching() && !rematch()) { // 이전 매칭 기록 존재 no-> 다시 input
            CourseLevelMission newCLM = getCourseLevelMission();
            match(functionType, newCLM);
            return;
        }
        CLM.match(crews); // 처음 매칭 or 재매칭 -> 매칭하고 return
        outputView.printMatchingResult(CLM.getMissionMatching()); // 출력
    }

    private boolean rematch() {
        String input = inputView.getRematch();
        return input.equals("네");
    }

    private static <T> T retry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
