package pairmatching.domain.constants;

import java.util.List;
import pairmatching.domain.MissionMatching;
import pairmatching.exception.CustomException;
import pairmatching.exception.ErrorMessage;

public class CourseLevelMission {
    private final Course course;
    private final Level level;
    private final List<MissionMatching> missions;
    private final MissionMatching mission;

    public static CourseLevelMission from(String course, String level, String mission) {
        return new CourseLevelMission(course, level, mission);
    }

    private CourseLevelMission(String course, String level, String mission) {
        this.course = Course.getCourseBy(course);
        this.level = Level.getLevelBy(level);
        this.missions = this.level.getMissions();
        this.mission = this.missions.stream()
                .filter(missionMatching -> missionMatching.getMissionName().equals(mission)).findFirst()
                .orElseThrow(() -> CustomException.from(
                        ErrorMessage.UNVALID_MISSION_NAME));
    }
}
