package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MissionMatching { // 미션 마다 가지고 있는 매칭 결과 -> enum에 박을 것임.
    private final String missionName;
    private List<Set<Crew>> matchings;

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

    public void resetMatchings() {
        matchings.clear();
    }

    public void setMatchings(List<Set<Crew>> matchings) {
        this.matchings = matchings;
    }

    public boolean isDuplicated(List<Set<Crew>> matchings) {
        for (Set<Crew> set1 : this.matchings) {
            if (anyOnceMatched(matchings, set1)) {
                return true;
            }
        }
        return false;
    }

    private static boolean anyOnceMatched(List<Set<Crew>> matchings, Set<Crew> set1) {
        long count = 0;
        for (Set<Crew> set2 : matchings) {
            count = set1.stream().filter(crew -> set2.contains(crew)).count();
            if (count >= 2) {
                return true;
            }
        }
        return false;
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
