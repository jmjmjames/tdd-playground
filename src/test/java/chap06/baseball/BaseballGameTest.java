package chap06.baseball;


import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BaseballGameTest {

    private BaseballGame game = new BaseballGame("123");

    @BeforeEach
    void givenGame() {
        game = new BaseballGame("456");
    }

    @Test
    void exactMatch() {
        // 실행
        Score score = game.guess("456");
        // 결과 확인
        assertThat(score.strikes()).isEqualTo(3);
        assertThat(score.balls()).isEqualTo(0);
    }

    @Test
    void failMethod() {
        // 정답이 123인 상황
        BaseballGame game = new BaseballGame("123");
        // 실행
        Score score = game.guess("456");
        // 결과 확인
        assertThat(score.strikes()).isEqualTo(0);
        assertThat(score.balls()).isEqualTo(0);
    }

    @Test
    void genGame_With_DupNumber_Then_Fail() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new BaseballGame("110"));
    }

}
