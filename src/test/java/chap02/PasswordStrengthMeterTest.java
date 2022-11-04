package chap02;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PasswordStrengthMeterTest {
    /**
     * <p>아래의 세 가지 규칙을 모두 충족하면 암호는 강함.</p>
     * <p>2개의 규칙을 충족하면 암호는 보통.</p>
     * <p>1개 이하의 규칙을 충족하면 암호는 약함.</p>
     * <ul>
     *  <li>길이가 8글자 이상</li>
     *  <li>0~9 사이의 숫자를 포함</li>
     *  <li>대문자를 포함 </li>
     * </ul>
     */
    @Test
    void meetsAllCriteria_Then_Strong() {
        PasswordStrength result = PasswordStrengthMeter.meter("abcd12!@");
        Assertions.assertThat(result).isEqualTo(PasswordStrength.STRONG);
    }
}
