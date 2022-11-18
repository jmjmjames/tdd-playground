package tdd.chap01;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringTest {

    @Test
    void substring() {
        String str = "abcde";
        Assertions.assertThat("cd").isEqualTo(str.substring(2, 4));
    }
}
