package chap02;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PasswordStrengthMeterTest {

    private final PasswordStrengthMeter passwordStrengthMeter = new PasswordStrengthMeter();

    private void assertStrength(String password, PasswordStrength expStr) {
        PasswordStrength result = passwordStrengthMeter.meter(password);
        assertThat(result).isEqualTo(expStr);
    }

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
        assertStrength("abCD12!@", PasswordStrength.STRONG);
        assertStrength("abc1!Add", PasswordStrength.STRONG);
    }

    @Test
    void meetsOtherCriteria_except_for_Length_Then_Normal() {
        PasswordStrength result = passwordStrengthMeter.meter("ab12!@A");
        assertThat(result).isEqualTo(PasswordStrength.NORMAL);
    }

    @Test
    void meetsOtherCriteria_except_for_number_Then_Normal() {
        PasswordStrength result = passwordStrengthMeter.meter("ab!@@ABqw");
        assertThat(result).isEqualTo(PasswordStrength.NORMAL);
    }

    @Test
    void nullInput_Then_Invalid() {
        assertStrength(null, PasswordStrength.INVALID);
        assertStrength("", PasswordStrength.INVALID);
        assertStrength("     ", PasswordStrength.INVALID);
    }

    @Test
    void meetsOtherCriteria_except_for_Uppercase_Then_Normal() {
        assertStrength("ab12!@df", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("길이가 8글자 이상인 조건만 충족하는 경우")
    void meetsOnlyLengthCriteria_Then_Weak() {
        assertStrength("abcdefghi", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("숫자 포함 조건만 충족하는 경우")
    void meetsOnlyNumCriteria_Then_Weak() {
        assertStrength("1234", PasswordStrength.WEAK);
    }
}
