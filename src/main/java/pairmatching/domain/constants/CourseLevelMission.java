package pairmatching.domain.constants;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import pairmatching.domain.Crew;
import pairmatching.domain.Crews;
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

    public boolean isMissionEmptyMatching() {
        return mission.isEmptyMatching();
    }

    public MissionMatching getMissionMatching() {
        return mission;
    }

    public void match(Crews crews) {
        mission.resetMatchings(); // 매칭 초기화
        while (true) { // 새로운 매칭 제조
            List<String> crewNames = crews.getCrewNamesBy(course), shuffle = Randoms.shuffle(crewNames);
            List<Set<Crew>> matchings = new ArrayList<>();
            for (int i = 2; i <= shuffle.size(); i += 2) {
                Set<Crew> matching = new HashSet<>();
                matching.add(crews.getCrewBy(course, shuffle.get(i - 2)));
                matching.add(crews.getCrewBy(course, shuffle.get(i - 1)));
                if (crewNames.size() % 2 == 1 && i == crewNames.size() - 1) {
                    matching.add(crews.getCrewBy(course, shuffle.get(i)));
                }
                matchings.add(matching);
            }
            if (isDuplicated(matchings)) { // 유효성 검증
                continue;
            }
            mission.setMatchings(matchings);
            break;
        }
    }

    private boolean isDuplicated(List<Set<Crew>> matchings) {
        for (MissionMatching missionMatching : missions) {
            if (missionMatching.getMissionName().equals(mission.getMissionName())) {
                continue; // 같은 건 할 필요 없다
            }
            if (missionMatching.isDuplicated(matchings)) {
                return true;
            }
        }
        return false;
    }
}
