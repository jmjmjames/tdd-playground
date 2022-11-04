package chap02;

public class PasswordStrengthMeter {
    public static PasswordStrength meter(String password) {
        if (password.length() < 8) {
            return PasswordStrength.NORMAL;
        }

        boolean hasNumber = false;
        for (char ch : password.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                hasNumber = true;
                break;
            }
        }
        if (hasNumber) {
            return PasswordStrength.NORMAL;
        }
        return PasswordStrength.STRONG;
    }
}
