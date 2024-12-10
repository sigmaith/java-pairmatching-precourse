package pairmatching.view;

import java.util.Arrays;
import java.util.stream.Collectors;
import pairmatching.domain.MissionMatching;
import pairmatching.domain.constants.Course;
import pairmatching.domain.constants.Level;

public class OutputView {
    private final String PAIR_MATCHING_RESULT_GUIDE = "페어 매칭 결과입니다.";
    private final String RESET_FINISHED_GUIDE = "\n초기화 되었습니다.";
    private final String PREFIX = "#############################################\n";
    private final String SUFFIX = "#############################################\n";

    public void printCourseLevelMission() {
        System.out.println(
                PREFIX + String.format("과정: %s", Arrays.stream(Course.values()).map(Course::getName).collect(
                        Collectors.joining(" | "))));
        System.out.println("미션:");
        StringBuilder sb = new StringBuilder();
        Arrays.stream(Level.values())
                .forEach(level -> sb.append("  - ").append(level.getName()).append(": ")
                        .append(level.getMissions().stream().map(MissionMatching::getMissionName).collect(
                                Collectors.joining(" | "))).append("\n"));
        System.out.print(sb);
        System.out.print(SUFFIX);
    }

    public void printMatchingResult(MissionMatching missionMatching) {
        System.out.println(PAIR_MATCHING_RESULT_GUIDE);
        System.out.println(missionMatching.toString());
    }

    public void printReset() {
        System.out.println(RESET_FINISHED_GUIDE);
    }
}
