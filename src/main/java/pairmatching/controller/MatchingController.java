package pairmatching.controller;

import static pairmatching.controller.ConfigController.configCrews;

import java.util.List;
import pairmatching.domain.Crews;
import pairmatching.domain.constants.CourseLevelMission;
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
            match(functionType);
            select(functionType);
            reset(functionType);
        }
    }

    public void match(String functionType) { // 매칭
        if (!functionType.equals("1")) {
            return;
        }
        while (true) {
            try {
                List<String> courseLevelMission = inputView.getCourseLevelMission();
                CourseLevelMission CLM = CourseLevelMission.from(courseLevelMission.get(0), courseLevelMission.get(1),
                        courseLevelMission.get(2));
                // 처음 매칭 or 재매칭 -> 매칭하고 return
                // 이전 매칭 기록 존재 -> 다시 input
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void select(String functionType) {  // 조회
        if (!functionType.equals("2")) {
            return;
        }
        while (true) {
            try {
                List<String> courseLevelMission = inputView.getCourseLevelMission();
                CourseLevelMission CLM = CourseLevelMission.from(courseLevelMission.get(0), courseLevelMission.get(1),
                        courseLevelMission.get(2));
                // 있을 시 보여주고 return
                // 없을 시 "[ERROR] 매칭 이력이 없습니다." -> 다시 CLM
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void reset(String functionType) {  // 초기화
        if (!functionType.equals("3")) {
            return;
        }
        // missionMatchig 의 List<Set<Crew>> 초기화
    }
}
