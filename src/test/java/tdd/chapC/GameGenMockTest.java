package tdd.chapC;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;

public class GameGenMockTest {
    @Test
    void mockTest() {
        GameNumGen genMock = mock(GameNumGen.class);
        BDDMockito.given(genMock.generate(GameLevel.EASY)).willReturn("123");

        String num = genMock.generate(GameLevel.EASY);
        assertThat(num).isEqualTo("123");
    }

    @Test
    void mockThrowTest() {
        GameNumGen genMock = mock(GameNumGen.class);
        BDDMockito.given(genMock.generate(null)).willThrow(IllegalArgumentException.class);

        assertThatThrownBy(() -> genMock.generate(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
