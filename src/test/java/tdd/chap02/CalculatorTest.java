package tdd.chap02;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import tdd.chap02.Calculator;

public class CalculatorTest {

    @Test
    void plus() {
        int result = Calculator.plus(1, 2);
        Assertions.assertThat(result).isEqualTo(3);
    }
}
