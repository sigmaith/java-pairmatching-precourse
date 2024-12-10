package pairmatching.domain.constants;

import java.util.Arrays;
import java.util.List;
import pairmatching.domain.MissionMatching;
import pairmatching.exception.CustomException;
import pairmatching.exception.ErrorMessage;

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

    public String getName() {
        return name;
    }

    public static Level getLevelBy(String name) {
        return Arrays.stream(values()).filter(level -> level.name.equals(name)).findFirst()
                .orElseThrow(() -> CustomException.from(
                        ErrorMessage.UNVALID_LEVEL_NAME));
    }

    public List<MissionMatching> getMissions() {
        return missions;
    }

    public static void resetEveryMatchings() {
        for (Level level : values()) {
            List<MissionMatching> missions = level.getMissions();
            missions.stream().forEach(MissionMatching::resetMatchings);
        }
    }
}

