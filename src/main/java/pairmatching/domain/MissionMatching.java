package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MissionMatching { // 미션 마다 가지고 있는 매칭 결과 -> enum에 박을 것임.
    private final String missionName;
    private final List<Set<Crew>> matchings;

    public static MissionMatching of(String missionName) {
        return new MissionMatching(missionName);
    }

    private MissionMatching(String missionName) {
        this.missionName = missionName;
        this.matchings = new ArrayList<>();
    }

    public String getMissionName() {
        return missionName;
    }

    public boolean isEmptyMatching() {
        return matchings.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Set<Crew> set : matchings) {
            String pair = set.stream().map(Crew::getName).collect(Collectors.joining(" : "));
            sb.append(pair).append("\n");
        }
        return sb.toString();
    }
}
