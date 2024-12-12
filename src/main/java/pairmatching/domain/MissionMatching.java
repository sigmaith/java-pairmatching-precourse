package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MissionMatching { // 미션 마다 가지고 있는 매칭 결과 -> enum에 박을 것임.
    private final String missionName;
    private List<List<Crew>> matchings;

    public static MissionMatching from(String missionName) {
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

    public void setMatchings(List<List<Crew>> matchings) {
        this.matchings = matchings;
    }

    public boolean isDuplicated(List<List<Crew>> matchings) {
        for (List<Crew> otherMatchings : this.matchings) {
            if (anyOnceMatched(matchings, otherMatchings)) {
                return true;
            }
        }
        return false;
    }

    private static boolean anyOnceMatched(List<List<Crew>> matchings, List<Crew> otherMatching) {
        long count = 0;
        for (List<Crew> matching : matchings) {
            count = otherMatching.stream().filter(matching::contains).count();
            if (count >= 2) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (List<Crew> crews : matchings) {
            String pair = crews.stream().map(Crew::getName).collect(Collectors.joining(" : "));
            sb.append(pair).append("\n");
        }
        return sb.toString();
    }
}
