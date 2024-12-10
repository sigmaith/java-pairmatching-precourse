package pairmatching.domain;

import java.util.List;

public class Crews {
    private final List<Crew> backend;
    public final List<Crew> frontend;

    public static Crews of(List<Crew> backend, List<Crew> frontend) {
        return new Crews(backend, frontend);
    }

    private Crews(List<Crew> backend, List<Crew> frontend) {
        this.backend = backend;
        this.frontend = frontend;
    }
}
