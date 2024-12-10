package pairmatching.view;

import pairmatching.domain.MissionMatching;

public class OutputView {
    public void printCourseLevelMission() {
        System.out.println("#############################################\n"
                + "과정: 백엔드 | 프론트엔드\n"
                + "미션:\n"
                + "  - 레벨1: 자동차경주 | 로또 | 숫자야구게임\n"
                + "  - 레벨2: 장바구니 | 결제 | 지하철노선도\n"
                + "  - 레벨3: \n"
                + "  - 레벨4: 성능개선 | 배포\n"
                + "  - 레벨5: \n"
                + "############################################");
    }

    public void printMatchingResult(MissionMatching missionMatching) {
        System.out.println("페어 매칭 결과입니다.");
        System.out.println(missionMatching.toString());
    }
}
