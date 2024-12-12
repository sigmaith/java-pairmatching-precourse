package pairmatching.domain;

import java.util.HashMap;
import java.util.Map;
import pairmatching.domain.constants.Level;

public class MissionManager {
    private static final Map<String, MissionMatching> missionMap = new HashMap<>();

    public static void initialize() {
        for (Level level : Level.values()) {
            for (String missionName : level.getMissionNames()) {
                missionMap.put(missionName, MissionMatching.from(missionName));
            }
        }
    }

    public static MissionMatching getMission(String missionName) {
        return missionMap.get(missionName);
    }

    public static void resetAllMatchings() {
        missionMap.values().forEach(MissionMatching::resetMatchings);
    }
}
