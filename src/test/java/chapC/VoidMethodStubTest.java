package chapC;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.mock;

import java.util.List;
import org.junit.jupiter.api.Test;

public class VoidMethodStubTest {
    @Test
    void voidMethodWillThrowTest() {
        List mockList = mock(List.class);
        willThrow(UnsupportedOperationException.class)
                .given(mockList)
                .clear();

        assertThatThrownBy(mockList::clear)
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
