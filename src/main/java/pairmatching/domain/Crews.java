package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.domain.constants.Course;
import pairmatching.exception.CustomException;
import pairmatching.exception.ErrorMessage;

public class Crews {
    private final List<Crew> crews;

    public static Crews of(List<Crew> backend, List<Crew> frontend) {
        return new Crews(backend, frontend);
    }

    private Crews(List<Crew> backend, List<Crew> frontend) {
        crews = new ArrayList<>();
        crews.addAll(backend);
        crews.addAll(frontend);
    }

    public List<String> getCrewNamesBy(Course course) {
        return crews.stream().filter(crew -> crew.isIn(course)).map(Crew::getName).collect(Collectors.toList());
    }


    public Crew getCrewBy(Course course, String name) {
        return crews.stream().filter(crew -> crew.isIn(course) && crew.has(name))
                .findFirst().orElseThrow(() -> CustomException.from(ErrorMessage.UNVALID_CREW_NAME));
    }
}
