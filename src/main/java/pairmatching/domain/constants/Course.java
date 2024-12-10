package pairmatching.domain.constants;

import java.util.Arrays;
import pairmatching.exception.CustomException;
import pairmatching.exception.ErrorMessage;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private final String name;

    Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Course getCourseBy(String name) {
        return Arrays.stream(values())
                .filter(course -> course.name.equals(name))
                .findFirst().orElseThrow(() -> CustomException.from(ErrorMessage.UNVALID_COURSE_NAME));
    }
}
