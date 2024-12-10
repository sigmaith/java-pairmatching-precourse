package pairmatching;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class test {
    @Test
    void test() {
        Set<String> set1 = new HashSet<>();
        set1.add("수미");
        set1.add("수미1");
        Set<String> set2 = new HashSet<>();
        set2.add("수미");
        set2.add("수미1");
        assertThat(set1.equals(set2)).isEqualTo(true);
    }
}
