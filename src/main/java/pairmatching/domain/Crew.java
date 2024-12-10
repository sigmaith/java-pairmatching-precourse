package pairmatching.domain;

import java.util.Objects;
import pairmatching.domain.constants.Course;

public class Crew {
    private final Course course;
    private final String name;

    public static Crew of(Course course, String name) {
        return new Crew(course, name);
    }

    private Crew(Course course, String name) {
        this.course = course;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isIn(Course course) {
        return this.course.equals(course);
    }

    public boolean has(String name) {
        return this.name.equals(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Crew)) {
            return false;
        }
        Crew crew = (Crew) o;
        return course == crew.course &&
                Objects.equals(name, crew.name);
    }

    // hashcode 재정의
    @Override
    public int hashCode() {
        return Objects.hash(course, name);
    }
}
