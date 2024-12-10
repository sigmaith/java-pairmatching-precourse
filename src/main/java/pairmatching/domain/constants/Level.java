package pairmatching.domain.constants;

import java.util.Arrays;
import java.util.List;
import pairmatching.domain.MissionMatching;

public enum Level {
    LEVEL1("레벨1", Arrays.asList(MissionMatching.of("자동차경주"), MissionMatching.of("로또"), MissionMatching.of("숫자야구게임"))),
    LEVEL2("레벨2", Arrays.asList(MissionMatching.of("장바구니"), MissionMatching.of("결제"), MissionMatching.of("지하철노선도"))),
    LEVEL3("레벨3", Arrays.asList()),
    LEVEL4("레벨4", Arrays.asList(MissionMatching.of("성능개선"), MissionMatching.of("배포"))),
    LEVEL5("레벨5", Arrays.asList());

    private final String name;
    private final List<MissionMatching> missions;

    Level(String name, List<MissionMatching> missions) {
        this.name = name;
        this.missions = missions;
    }

    // 추가 기능 구현
}

