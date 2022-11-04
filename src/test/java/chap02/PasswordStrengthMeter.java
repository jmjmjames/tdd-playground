package chap02;

import java.util.OptionalInt;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String password) {
        if (password == null || password.isEmpty() || password.isBlank()) {
            return PasswordStrength.INVALID;
        }

        boolean lengthEnough = password.length() > 8;
        boolean containsNum = meetsContainingNumberCriteria(password);
        boolean containsUppercase = meetsContainingUppercaseCriteria(password);

        if (lengthEnough && !containsNum && !containsUppercase) {
            return PasswordStrength.WEAK;
        }
        if (!lengthEnough && containsNum && !containsUppercase) {
            return PasswordStrength.WEAK;
        }

        if (!lengthEnough) {
            return PasswordStrength.NORMAL;
        }

        if (!containsNum) {
            return PasswordStrength.NORMAL;
        }


        if (!containsUppercase) {
            return PasswordStrength.NORMAL;
        }

        return PasswordStrength.STRONG;
    }

    private boolean meetsContainingNumberCriteria(String password) {
        for (char ch : password.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }

    private boolean meetsContainingUppercaseCriteria(String password) {
        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }
}
